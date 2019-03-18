package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Phone;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Conditions;
import seedu.address.model.tag.ConditionTag;
import seedu.address.model.tag.Tag;

/**
 * Utility class for building EditPatientDescriptor objects for testing.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        this.descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setEmail(patient.getEmail());
        descriptor.setAddress(patient.getAddress());
        descriptor.setNric(patient.getNric());
        descriptor.setTags(patient.getTags());
        descriptor.setConditions(patient.getConditions());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptorBuilder} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        this.descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptorBuilder} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        this.descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptorBuilder} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        this.descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptorBuilder} that we are building.
     */
    public EditPatientDescriptorBuilder withAddress(String address) {
        this.descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptorBuilder}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        this.descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptorBuilder} that we are building.
     */
    public EditPatientDescriptorBuilder withNric(String nric) {
        this.descriptor.setNric(new Nric(nric));
        return this;
    }

    /**
     * Sets the {@code Conditions} of the {@code EditPatientDescriptorBuilder} that we are building.
     */
    public EditPatientDescriptorBuilder withConditions(String... conditionTag) {
        Set<ConditionTag> conditionTags = new HashSet<>();
        for (String s : conditionTag) {
            conditionTags.add(new ConditionTag(s));
        }
        Conditions conditions = new Conditions(conditionTags);
        this.descriptor.setConditions(conditions);
        return this;
    }

    public EditPatientDescriptor build() {
        return this.descriptor;
    }
}
