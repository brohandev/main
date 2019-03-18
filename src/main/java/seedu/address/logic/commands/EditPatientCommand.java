package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Conditions;
import seedu.address.model.tag.Tag;

/**
 * @author Rohan
 * Edits the details of an existing Patient object in the addressbook.
 */
public class EditPatientCommand extends EditCommand implements PatientCommand {

    public static final String MESSAGE_EDIT_PATIENT_SUCCESS = "Edited Patient: %1$s";

    private final EditPatientDescriptor editPatientDescriptor;

    public EditPatientCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        super(index);
        requireNonNull(editPatientDescriptor);

        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public void edit(Model model, Object toEdit, Object edited) {
        model.setPatient((Patient) toEdit, (Patient) edited);
        model.updateFilteredPatientList(Model.PREDICATE_SHOW_ALL_PATIENTS);
        model.commitAddressBook();
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient toEdit = lastShownList.get(index.getZeroBased());
        Patient edited = createEditedPatient(toEdit, editPatientDescriptor);

        if (!toEdit.isSamePatient(edited) && model.hasPatient(edited)) {
            throw new CommandException(DUPLICATE_PATIENT);
        }

        edit(model, toEdit, edited);
        return new CommandResult(String.format(MESSAGE_EDIT_PATIENT_SUCCESS, edited));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code toEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient toEdit,
                                                         EditPatientDescriptor editPatientDescriptor) {
        assert toEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(toEdit.getName());
        Nric updatedNric = editPatientDescriptor.getNric().orElse(toEdit.getNric());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(toEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(toEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(toEdit.getAddress());
        Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(toEdit.getTags());
        Conditions updatedConditions = editPatientDescriptor.getConditions().orElse(toEdit.getConditions());

        return new Patient(updatedName, updatedPhone, updatedEmail, updatedNric, updatedAddress, updatedTags,
                updatedConditions);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditPatientCommand)) {
            return false;
        }

        EditPatientCommand e = (EditPatientCommand) other;
        return index.equals(e.index) && editPatientDescriptor.equals(e.editPatientDescriptor);
    }

    /**
     * Stores the details to edit the Patient with. Each non-empty field value will replace the
     * corresponding field value of the Patient.
     */
    public static class EditPatientDescriptor extends EditPersonCommand.EditPersonDescriptor {

        private Conditions conditions;

        public EditPatientDescriptor() {}

        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            super(toCopy);
            this.conditions = toCopy.conditions;
        }

        @Override
        public boolean isAnyFieldEdited() {
            return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(this.conditions);
        }

        public void setConditions(Conditions conditions) {
            this.conditions = conditions;
        }

        public Optional<Conditions> getConditions() {
            return Optional.ofNullable(this.conditions);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditPatientDescriptor)) {
                return false;
            }

            EditPatientDescriptor e = (EditPatientDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getConditions().equals(getConditions());
        }
    }
}
