package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHealthWorkers.getTypicalHealthWorkerBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRequests.getTypicalRequestBook;

import org.junit.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.HealthWorkerBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PatientBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;

public class EditPatientCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalHealthWorkerBook(),
            getTypicalPatientBook(), getTypicalRequestBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_allFieldsSpecified() {
        Patient editedPatient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new HealthWorkerBook(model.getHealthWorkerBook()), new PatientBook(model.getPatientBook()),
                getTypicalRequestBook(), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editPatientCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified() {
        Index indexLastPatient = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPatient.getZeroBased());

        Patient editedPatient = ((PatientBuilder) new PatientBuilder(lastPatient)
                .withName(VALID_NAME_BENSON).withPhone(VALID_PHONE_BENSON)).build();

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BENSON)
                .withPhone(VALID_PHONE_BENSON).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(indexLastPatient,
                descriptor);

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new HealthWorkerBook(model.getHealthWorkerBook()), new PatientBook(model.getPatientBook()),
                getTypicalRequestBook(), new UserPrefs());
        expectedModel.setPatient(lastPatient, editedPatient);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editPatientCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldsSpecified() {
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST, new
                EditPatientDescriptor());
        Patient editedPatient = model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new HealthWorkerBook(model.getHealthWorkerBook()), new PatientBook(model.getPatientBook()),
                getTypicalRequestBook(), new UserPrefs());
        expectedModel.commitAddressBook();

        assertCommandSuccess(editPatientCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList() {
        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST
                .getZeroBased());
        Patient editedPatient = ((PatientBuilder) new PatientBuilder(patientInFilteredList)
                .withName(VALID_NAME_BENSON)).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST, new
                EditPatientDescriptorBuilder().withName(VALID_NAME_BENSON).build());

        String expectedMessage = String.format(EditPatientCommand.MESSAGE_EDIT_PATIENT_SUCCESS,
                editedPatient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new HealthWorkerBook(model.getHealthWorkerBook()), new PatientBook(model.getPatientBook()),
                getTypicalRequestBook(), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editPatientCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePatient() {
        Patient firstPatient = model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased());
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(firstPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editPatientCommand, model, commandHistory,
                PatientCommand.DUPLICATE_PATIENT);
    }

    @Test
    public void execute_duplicatePatientFilteredList() {
        Patient patientInList = model.getPatientBook().getPatientList().get(INDEX_SECOND
                .getZeroBased());
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST,
                new EditPatientDescriptorBuilder(patientInList).build());

        assertCommandFailure(editPatientCommand, model, commandHistory,
                EditPatientCommand.DUPLICATE_PATIENT);
    }

    @Test
    public void execute_invalidIndex() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BENSON)
                .build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBounds, descriptor);

        assertCommandFailure(editPatientCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_undoRedo() throws Exception {
        Patient editedPatient = new PatientBuilder().build();
        Patient toEdit = model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased());
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new HealthWorkerBook(model.getHealthWorkerBook()), new PatientBook(model.getPatientBook()),
                getTypicalRequestBook(), new UserPrefs());
        expectedModel.setPatient(toEdit, editedPatient);
        expectedModel.commitAddressBook();

        editPatientCommand.execute(model, commandHistory);

        // undo
        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_undoRedo_invalidIndex() {
        Index outOfBounds = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BENSON)
                .build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(outOfBounds, descriptor);

        assertCommandFailure(editPatientCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        assertCommandFailure(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
    @Test
    public void execute_undoRedo_filteredList() throws Exception {
        Patient editedPatient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST, descriptor);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new HealthWorkerBook(model.getHealthWorkerBook()), new PatientBook(model.getPatientBook()),
                getTypicalRequestBook(), new UserPrefs());

        Patient toEdit = model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased());
        expectedModel.setPatient(toEdit, editedPatient);
        expectedModel.commitAddressBook();

        editPatientCommand.execute(model, commandHistory);

        expectedModel.undoAddressBook();
        assertCommandSuccess(new UndoCommand(), model, commandHistory, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        assertNotEquals(model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased()), toEdit);
        expectedModel.redoAddressBook();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final EditPatientCommand standardCommand = new EditPatientCommand(INDEX_FIRST, DESC_ALICE);

        // same object -> return true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> return true
        EditPatientDescriptor descriptor = new EditPatientDescriptor(DESC_ALICE);
        EditPatientCommand editPatientCommand = new EditPatientCommand(INDEX_FIRST, descriptor);
        assertTrue(editPatientCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> return false
        assertFalse(standardCommand.equals(1));

        // different index -> return false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_SECOND, DESC_ALICE)));

        // different descriptor -> return false
        assertFalse(standardCommand.equals(new EditPatientCommand(INDEX_FIRST, DESC_BENSON)));
    }
}
