package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.history.RecentCommand;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Clears address book permanently.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ClearCommand() {}


    @Override
    public CommandResult execute() {
        List<Person> personsAffected = new ArrayList<Person>();
        for (Person person : addressBook.getAllPersons()){
            personsAffected.add(person);
        }
        history.insert(new RecentCommand(COMMAND_WORD, personsAffected));
        addressBook.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
