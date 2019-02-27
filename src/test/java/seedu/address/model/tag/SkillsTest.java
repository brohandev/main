package seedu.address.model.tag;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;

public class SkillsTest {

    @Test
    public void contains() {
        //initialization
        Skills skillsTest = new Skills();

        skillsTest.addSpecialisation(Specialisation.GENERAL_PRACTICE);
        skillsTest.addSpecialisation(Specialisation.ANAESTHESIOLOGY);
        skillsTest.addSpecialisation(Specialisation.NEUROLOGY);
        skillsTest.addSpecialisation(Specialisation.GYNAECOLOGY);
        skillsTest.addSpecialisation(Specialisation.ORTHOPAEDIC);

        // Specialisation
        assertFalse(skillsTest.contains(Specialisation.CARDIOLOGY));
        assertFalse(skillsTest.contains(Specialisation.HAEMATOLOGY));
        assertFalse(skillsTest.contains(Specialisation.OCCUPATIONAL_THERAPY));

        assertTrue(skillsTest.contains(Specialisation.GENERAL_PRACTICE));
        assertTrue(skillsTest.contains(Specialisation.ANAESTHESIOLOGY));
        assertTrue(skillsTest.contains(Specialisation.GYNAECOLOGY));

        // String
        assertFalse(skillsTest.contains("CARDIOLOGY")); // does not contain
        assertFalse(skillsTest.contains("general_practice")); // lowercase
        assertFalse(skillsTest.contains("")); // empty string
        assertFalse(skillsTest.contains("General_Practice")); // Not all caps

        assertTrue(skillsTest.contains("GENERAL_PRACTICE"));
        assertTrue(skillsTest.contains("NEUROLOGY"));
        assertTrue(skillsTest.contains("ORTHOPAEDIC"));
    }

    @Test
    public void addSpecialisation() {
        // Initialization
        Skills skillsTest = new Skills();

        skillsTest.addSpecialisation(Specialisation.GENERAL_PRACTICE);
        assertTrue(skillsTest.getSkills().equals(new HashSet<>(Arrays
                .asList(Specialisation.GENERAL_PRACTICE))));

        skillsTest.addSpecialisation(Specialisation.ANAESTHESIOLOGY);
        skillsTest.addSpecialisation(Specialisation.NEUROLOGY);
        assertTrue(skillsTest.getSkills().equals(new HashSet<>(Arrays
                .asList(Specialisation.GENERAL_PRACTICE, Specialisation
                        .ANAESTHESIOLOGY, Specialisation.NEUROLOGY))));

        skillsTest.addSpecialisation(Specialisation.GYNAECOLOGY);
        skillsTest.addSpecialisation(Specialisation.ORTHOPAEDIC);
        assertTrue(skillsTest.getSkills().equals(new HashSet<>(Arrays
                .asList(Specialisation.GENERAL_PRACTICE, Specialisation
                        .ANAESTHESIOLOGY, Specialisation.NEUROLOGY,
                Specialisation.GYNAECOLOGY, Specialisation.ORTHOPAEDIC))));
    }
}
