package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadAndWritePerson;
import seedu.addressbook.data.tag.Tag;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindTagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Finds all persons whose tags contains the specific keyword (case-sensitive and tagged) and displays them as a list with index numbers.\n\t"
            + "Parameters: KEYWORD \n\t"
            + "Example: " + COMMAND_WORD + " [Korean]";

    private final String keyword;
    
    public FindTagCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Returns copy of keyword in this command.
     */
    public String getKeyword() {
        return keyword;
    }

    @Override
    public CommandResult execute() {
        final List<ReadAndWritePerson> personsFound = getPersonsWithNameContainingAnyKeyword(keyword);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadAndWritePerson> getPersonsWithNameContainingAnyKeyword(String keyword) {
        final List<ReadAndWritePerson> matchedPersons = new ArrayList<>();

        for (ReadAndWritePerson person : addressBook.getAllPersons()) {
        	for (Tag tag : person.getTags()) {
        		String tagInString = tag.toString();
        		if(tagInString.equals(keyword))
        		{
        			matchedPersons.add(person);
        			//System.out.println(tagInString);
        		}
            }
        }
        return matchedPersons;
    }

    @Override
    public boolean isMutating() {
        return false;
    }

}
