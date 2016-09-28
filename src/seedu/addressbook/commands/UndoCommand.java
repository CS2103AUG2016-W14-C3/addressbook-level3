package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.history.RecentCommand;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Undo previous command.\n\t"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undo: %1$s %2$s";
    public static final String MESSAGE_EMPTY_HISTORY = "Empty History. Nothing to Undo.";
    
    @Override
    public CommandResult execute() {
        if (history.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_HISTORY);
        } else {
            RecentCommand command = history.remove();
            
            switch (command.getCommandName()) {
                case "add":
                    for (Person person : command.getListOfPersons()) {
                        try {
                            addressBook.removePerson(person);
                        } catch (PersonNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    return new CommandResult(String.format(MESSAGE_SUCCESS, command.getCommandName(), command.getListOfPersons().get(0).toString()));
                    
                case "delete":
                    for (Person person : command.getListOfPersons()) {
                        try {
                            addressBook.addPerson(person);
                        } catch (DuplicatePersonException e) {
                            e.printStackTrace();
                        }
                    }
                    return new CommandResult(String.format(MESSAGE_SUCCESS, command.getCommandName(), command.getListOfPersons().get(0).toString()));
                    
                case "clear":
                    for (Person person : command.getListOfPersons()) {
                        try {
                            addressBook.addPerson(person);
                        } catch (DuplicatePersonException e) {
                            e.printStackTrace();
                        }
                    }
                    return new CommandResult(String.format(MESSAGE_SUCCESS, command.getCommandName(), ""));
                
                default:
                    return new CommandResult(MESSAGE_EMPTY_HISTORY);
            }
        }
    }

}
