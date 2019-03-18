package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BENSON;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.patient.Patient;
import seedu.address.testutil.Assert;
import seedu.address.testutil.PatientBuilder;

public class AddPatientCommandTest extends AddPersonCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new
                AddPatientCommand(null));
    }

    @Test
    public void execute() throws CommandException {
        ModelStubAcceptingPatientAdded modelStub = new
                ModelStubAcceptingPatientAdded();
        Patient validPatient = new PatientBuilder().build();

        CommandResult commandResult = new AddPatientCommand(validPatient)
                .execute(modelStub, commandHistory);

        assertEquals(String.format(AddPatientCommand.MESSAGE_SUCCESS, validPatient),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPatient), modelStub.patientsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);

        // Duplicate Patient
        Patient newValidPatient = new PatientBuilder().build();
        thrown.expectMessage(AddPatientCommand.DUPLICATE_PATIENT);
        thrown.expect(CommandException.class);
        new AddPatientCommand(newValidPatient).execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        AddPatientCommand addAliceCommand = new AddPatientCommand(ALICE);
        AddPatientCommand addBensonCommand = new AddPatientCommand(BENSON);

        // same object -> return true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> return true
        assertTrue(addBensonCommand.equals(new AddPatientCommand(BENSON)));

        // different types -> return false
        assertFalse(addAliceCommand.equals(1));

        // null command -> return false
        assertFalse(addAliceCommand.equals(null));

        // different patient -> return false
        assertFalse(addAliceCommand.equals(addBensonCommand));
    }

    /**
     * Model Stub class for accepting Patient objects.
     */
    private class ModelStubAcceptingPatientAdded extends ModelStub {
        private ArrayList<Patient> patientsAdded = new ArrayList<>();

        @Override
        public void addPatient(Patient patient) {
            requireNonNull(patient);
            this.patientsAdded.add(patient);
        }

        @Override
        public boolean hasPatient(Patient patient) {
            requireNonNull(patient);
            return this.patientsAdded.stream().anyMatch(
                    patient::isSamePatient);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddPatientCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
