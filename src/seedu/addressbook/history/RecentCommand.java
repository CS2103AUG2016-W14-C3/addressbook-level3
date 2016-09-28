package seedu.addressbook.history;

import java.util.List;

import seedu.addressbook.data.person.Person;

public class RecentCommand {

    private String commandName;
    private List<Person> listOfPersons;
    
    public RecentCommand(String commandName, List<Person> listOfPersons) {
        this.commandName = commandName;
        this.listOfPersons = listOfPersons;
    }
    
    public String getCommandName() {
        return commandName;
    }
    
    public List<Person> getListOfPersons() {
        return listOfPersons;
    }
}
