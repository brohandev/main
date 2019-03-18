package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyHealthWorkerBook;
import seedu.address.model.ReadOnlyPatientBook;
import seedu.address.model.ReadOnlyRequestBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.request.Request;
import seedu.address.testutil.PersonBuilder;

public class AddPersonCommandTest {

    protected static final CommandHistory EMPTY_COMMAND_HISTORY = new
            CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddPersonCommand(null);
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddPersonCommand(validPerson).execute(modelStub, commandHistory);

        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() throws Exception {
        Person validPerson = new PersonBuilder().build();
        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddPersonCommand.MESSAGE_DUPLICATE_PERSON);
        addPersonCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddPersonCommand addAliceCommand = new AddPersonCommand(alice);
        AddPersonCommand addBobCommand = new AddPersonCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPersonCommand addAliceCommandCopy = new AddPersonCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    protected class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        // =========== Implemented methods supporting Health Worker ===========
        // @author Lookaz

        @Override
        public boolean hasHealthWorker(HealthWorker healthWorker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteHealthWorker(HealthWorker target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addHealthWorker(HealthWorker healthWorker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setHealthWorker(HealthWorker target, HealthWorker editedWorker) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<HealthWorker> getFilteredHealthWorkerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredHealthWorkerList(Predicate<HealthWorker> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyHealthWorkerBook getHealthWorkerBook() {
            throw new AssertionError("This method should not be called.");
        }

        // ===================================================================

        // =========== Implemented methods supporting Patient ===========
        // @author Rohan

        @Override
        public boolean hasPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPatient(Patient patient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPatient(Patient target, Patient editedPatient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Patient> getFilteredPatientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPatientList(Predicate<Patient> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPatientBook getPatientBook() {
            return null;
        }
        // ===================================================================


        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Person> selectedPersonProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getSelectedPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the user prefs' request book file path.
         */
        @Override
        public Path getRequestBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Sets the user prefs' request book file path.
         *
         * @param requestBookFilePath
         */
        @Override
        public void setRequestBookFilePath(Path requestBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces request book data with the data in {@code requestBook}.
         *
         * @param requestBook
         */
        @Override
        public void setRequestBook(ReadOnlyRequestBook requestBook) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the RequestBook
         */
        @Override
        public ReadOnlyRequestBook getRequestBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Request> getFilteredRequestList() {
            return null;
        }

        /**
         * Returns true if a request with the same identity as {@code request} exists in the address
         * book.
         *
         * @param request
         */
        @Override
        public boolean hasRequest(Request request) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateRequest(Request target, Request editedRequest) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Deletes the given request.
         * The request must exist in the request book.
         *
         * @param target
         */
        @Override
        public void deleteRequest(Request target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRequestList(Predicate<Request> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given request.
         * {@code request} must not already exist in the request book.
         */
        @Override
        public void addRequest(Request request) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces the given request {@code target} with {@code editedRequest}.
         * {@code target} must exist in the request book.
         * The request identity of {@code editedRequest} must not be the same as another existing
         * request in the request book.
         *
         * @param target
         * @param editedRequest
         */
        @Override
        public void setRequest(Request target, Request editedRequest) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitRequestBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddPersonCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
