package seedu.addressbook.history;

import java.util.ArrayList;
import seedu.addressbook.data.person.Person;

public class RecentCommand {

    private String commandName;
    private ArrayList<Person> listOfPersons;
    
    public RecentCommand(String commandName, ArrayList<Person> listOfPersons) {
        this.commandName = commandName;
        this.listOfPersons = listOfPersons;
    }
    
    public String getCommandName() {
        return commandName;
    }
    
    public ArrayList<Person> getListOfPersons() {
        return listOfPersons;
    }
}
