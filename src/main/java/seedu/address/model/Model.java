package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
import seedu.address.model.person.healthworker.HealthWorker;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.request.Request;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<HealthWorker> PREDICATE_SHOW_ALL_HEALTHWORKERS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Request> PREDICATE_SHOW_ALL_REQUESTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /** Returns the HealthWorkerBook */
    ReadOnlyHealthWorkerBook getHealthWorkerBook();

    /**
     * Returns the PatientBook
     */
    ReadOnlyPatientBook getPatientBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    // ============== Added methods for AddHealthWorkerCommand ===============
    // @author: Lookaz

    /**
     * Returns true if a person with the same identity as {@code healthWorker}
     * exists in the address book.
     */
    boolean hasHealthWorker(HealthWorker healthWorker);

    /**
     * Deletes the given HealthWorker.
     * The HealthWorker object must exist in the address book.
     */
    void deleteHealthWorker(HealthWorker target);

    /**
     * Adds the given HealthWorker.
     * {@code healthWorker} must not already exist in the address book.
     */
    void addHealthWorker(HealthWorker healthWorker);

    /**
     * Replaces the given person {@code target} with {@code editedWorker}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedWorker} must not be the same as
     * another existing HealthWorker in the address book.
     */
    void setHealthWorker(HealthWorker target, HealthWorker editedWorker);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<HealthWorker> getFilteredHealthWorkerList();

    /**
     * Updates the filter of the filtered HealthWorker list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredHealthWorkerList(Predicate<HealthWorker> predicate);

    // =======================================================================

    // ============== Added methods for AddPatientCommand ===============
    // @author: Rohan

    /**
     * Returns true if a person with the same identity as {@code patient}
     * exists in the address book.
     */
    boolean hasPatient(Patient patient);

    /**
     * Adds the given Patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPatient(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The identity of {@code editedPatient} must not be the same as
     * another existing Patient in the address book.
     */
    void setPatient(Patient target, Patient editedWorker);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered Patient list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    // =======================================================================

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();

    /**
     * Selected person in the filtered person list.
     * null if no person is selected.
     */
    ReadOnlyProperty<Person> selectedPersonProperty();

    /**
     * Returns the selected person in the filtered person list.
     * null if no person is selected.
     */
    Person getSelectedPerson();

    /**
     * Sets the selected person in the filtered person list.
     */
    void setSelectedPerson(Person person);

    /**
     * Returns the user prefs' request book file path.
     */
    Path getRequestBookFilePath();

    /**
     * Sets the user prefs' request book file path.
     */
    void setRequestBookFilePath(Path requestBookFilePath);

    /**
     * Replaces request book data with the data in {@code requestBook}.
     */
    void setRequestBook(ReadOnlyRequestBook requestBook);

    /** Returns the RequestBook */
    ReadOnlyRequestBook getRequestBook();

    /**
     * Returns an unmodifiable view of the request list.
     */
    ObservableList<Request> getFilteredRequestList();

    /**
     * Returns true if a request with the same identity as {@code request} exists in the address
     * book.
     */
    boolean hasRequest(Request request);


    /**
     * Replaces the given order {@code target} with {@code editedRequest}.
     * {@code target} must exist in the request book.
     * The request identity of {@code editedRequest} must not be the same as another existing
     * request in the request book.
     */
    void updateRequest(Request target, Request editedRequest);

    /**
     * Deletes the given request.
     * The request must exist in the request book.
     */
    void deleteRequest(Request target);

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredRequestList(Predicate<Request> predicate);

    /**
     * Adds the given request.
     * {@code request} must not already exist in the request book.
     */
    void addRequest(Request request);

    /**
     * Replaces the given request {@code target} with {@code editedRequest}.
     * {@code target} must exist in the request book.
     * The request identity of {@code editedRequest} must not be the same as another existing
     * request in the request book.
     */
    void setRequest(Request target, Request editedRequest);

    void commitRequestBook();
}
