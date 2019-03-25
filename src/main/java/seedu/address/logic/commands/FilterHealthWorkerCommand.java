package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.person.healthworker.HealthWorker;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterHealthWorkerCommand extends FilterCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final List<Predicate> predicateList;

    public FilterHealthWorkerCommand(Collection<Predicate> predicates) {
        this.predicateList = new ArrayList<>(predicates);
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        filter(model, predicateList);
        return new CommandResult(String.format(Messages.MESSAGE_HEALTHWORKER_LISTED_OVERVIEW,
                model.getFilteredHealthWorkerList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterHealthWorkerCommand // instanceof handles nulls
                && predicateList.equals(((FilterHealthWorkerCommand) other).predicateList)); // state check
    }

    @Override
    protected void filter(Model model, Collection<Predicate> predicates) {
        Predicate<HealthWorker> predicate = reducePredicates(predicates);
        model.updateFilteredHealthWorkerList(predicate);
    }

    /**
     * Method that reduces a collection of predicates in to a single predicate that fulfils all the predicates.
     */
    public static Predicate<HealthWorker> reducePredicates(Collection<Predicate> predicates) {
        return predicates.stream().reduce(x -> true, Predicate::and);
    }
}
