package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.PatientBook;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.ConditionTag;
import seedu.address.model.tag.Conditions;

/**
 * A utility class containing a list of {@code Patient} objects to be used in tests.
 */
public class TypicalPatients {

    public static final Patient ALICE = ((PatientBuilder) new PatientBuilder()
        .withName("Alice Pauline")
        .withAddress("123, Jurong West Ave 6, #08-111")
        .withEmail("alice@example.com")
            .withPhone("94351253")
        .withNric("S9670515H"))
        .withConditions(new Conditions(new HashSet<>(
            Arrays.asList(new ConditionTag("Physiotherapy"),
                new ConditionTag("Dialysis")))))
        .build();
    public static final Patient BENSON = ((PatientBuilder) new PatientBuilder()
        .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
        .withEmail("johnd@example.com")
        .withPhone("98765432")
        .withNric("S9274100D"))
        .withConditions(new Conditions(new HashSet<>(
            Arrays.asList(new ConditionTag("Eldercare"),
                new ConditionTag("Stroke")))))
        .build();
    public static final Patient CARL = ((PatientBuilder) new PatientBuilder()
        .withName("Carl Kurz").withPhone("95352563")
        .withAddress("wall street")
        .withEmail("heinz@example.com")
        .withPhone("87652533")
        .withNric("S9328723A"))
        .withConditions(new Conditions(new HashSet<>(
            Arrays.asList(new ConditionTag("Palliative")))))
        .build();
    public static final Patient DANIEL = ((PatientBuilder) new PatientBuilder()
        .withName("Daniel Meier")
        .withAddress("10th street")
        .withEmail("cornelia@example.com")
        .withPhone("82015737")
        .withNric("S2652663Z"))
        .withConditions(new Conditions(new HashSet<>()))
        .build();
    public static final Patient ELLE = ((PatientBuilder) new PatientBuilder()
        .withName("Elle Meyer")
        .withAddress("michegan ave")
        .withEmail("werner@example.com")
        .withPhone("9482224")
        .withNric("S9462345E"))
        .withConditions(new Conditions(new HashSet<>(
            Arrays.asList(new ConditionTag("Dementia")))))
            .build();
    public static final Patient FIONA = ((PatientBuilder) new PatientBuilder()
        .withName("Fiona Kunz")
        .withAddress("little tokyo")
        .withEmail("lydia@example.com")
        .withPhone("9482427")
        .withNric("S5450367F"))
        .withConditions(new Conditions(new HashSet<>(
            Arrays.asList(new ConditionTag("Cancer")))))
        .build();
    public static final Patient GEORGE = ((PatientBuilder) new PatientBuilder()
        .withName("George Best")
        .withAddress("4th street")
        .withEmail("anna@example.com")
        .withPhone("9482442")
        .withNric("S8736498R"))
        .withConditions(new Conditions(new HashSet<>(
            Arrays.asList(new ConditionTag("Alzheimer's")))))
        .build();
    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPatients() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static PatientBook getTypicalPatientBook() {
        PatientBook patientBook = new PatientBook();
        for (Patient patient : getTypicalPatients()) {
            patientBook.addPatient(patient);
        }
        return patientBook;
    }

    public static List<Patient> getTypicalPatients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
