package seedu.address.logic.commands.request;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.REQ_DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.REQ_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_PHYSIO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHealthWorkers.ANDY;
import static seedu.address.testutil.TypicalHealthWorkers.getTypicalHealthWorkerBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRequests.ALICE_REQUEST;
import static seedu.address.testutil.TypicalRequests.getTypicalRequestBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Email;
import seedu.address.model.person.Nric;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.request.Request;
import seedu.address.testutil.EditRequestDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.RequestBuilder;

class EditRequestCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(),
        getTypicalHealthWorkerBook(), getTypicalPatientBook(), getTypicalRequestBook(),
        new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();
    private final String defaultAddress = "123, Jurong West Ave 6, #08-111";

    @Test
    void execute_allFieldsSpecifiedUnfilteredList_success() {
        Request editedRequest =
            new RequestBuilder().withAddress(defaultAddress).withHealthStaff(ANDY).withEmail(Email.DEFAULT_EMAIL).withNric(Nric.DEFAULT_NRIC).build();
        EditRequestCommand.EditRequestDescriptor descriptor =
            new EditRequestDescriptorBuilder(editedRequest).build();
        EditRequestCommand editRequestCommand = new EditRequestCommand(INDEX_FIRST, descriptor);


        String expectedMessage = String.format(EditRequestCommand.MESSAGE_EDIT_REQUEST_SUCCESS,
            editedRequest);

        Model expectedModel = new ModelManager(model.getAddressBook(),
            model.getHealthWorkerBook(), model.getPatientBook(), model.getRequestBook(),
            new UserPrefs());
        expectedModel.updateRequest(model.getFilteredRequestList().get(0), editedRequest);
        expectedModel.commitRequestBook();

        assertCommandSuccess(editRequestCommand, model, commandHistory, expectedMessage,
            expectedModel);
    }

    @Test
    public void equals() {
        final EditRequestCommand standardCommand = new EditRequestCommand(INDEX_FIRST,
            REQ_DESC_ALICE);

        // same values -> returns true
        EditRequestCommand.EditRequestDescriptor copyDescriptor =
            new EditRequestCommand.EditRequestDescriptor(REQ_DESC_ALICE);
        EditRequestCommand commandWithSameValues = new EditRequestCommand(INDEX_FIRST,
            copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new AddRequestCommand(ALICE_REQUEST)));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditRequestCommand(INDEX_SECOND, REQ_DESC_ALICE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditRequestCommand(INDEX_FIRST, REQ_DESC_BOB)));


    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditRequestCommand editRequestCommand = new EditRequestCommand(INDEX_FIRST,
            new EditRequestCommand.EditRequestDescriptor());
        Request editedRequest = model.getFilteredRequestList().get(INDEX_FIRST.getZeroBased());
        Patient patient =
            new PatientBuilder(ALICE).withConditionTags(VALID_CONDITION_PHYSIO).withEmail(Email.DEFAULT_EMAIL)
                .withConditionTags(VALID_CONDITION_PHYSIO).withNric(Nric.DEFAULT_NRIC).build();
        editedRequest =
            new RequestBuilder(editedRequest).withId(RequestBuilder.DEFAULT_ID).withEmail(Email.DEFAULT_EMAIL)
                .withPatient(patient).build();

        String expectedMessage = String.format(EditRequestCommand.MESSAGE_EDIT_REQUEST_SUCCESS,
            editedRequest);

        Model expectedModel = new ModelManager(model.getAddressBook(),
            model.getHealthWorkerBook(), model.getPatientBook(), model.getRequestBook(),
            new UserPrefs());
        expectedModel.commitRequestBook();

        assertCommandSuccess(editRequestCommand, model, commandHistory, expectedMessage, expectedModel);
    }


}