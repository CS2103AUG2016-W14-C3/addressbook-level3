package seedu.addressbook.commands;

import java.util.ArrayList;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadAndWritePerson;

public class EditCommand extends Command {

	public static final String COMMAND_WORD = "edit";

	public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person in the address book "
			+ "identified by the index number used in the last person listing.\n"
			+ "The details have to be appended to the back with the appropriate "
			+ "prepended tags (e.g. Name is n/, Phone is p/, Email is e/).\n\t"
			+ "Parameters: INDEX [PREPENDED TAG]/[NEW VALUE]" + "Example: " + COMMAND_WORD + " 1 n/John Lee p/99112233";
	
	public static final String MESSAGE_SUCCESS = "Person edited: %1$s";
	
	private static final String NAME_PREPEND = "n";
	private static final String PHONE_PREPEND = "p";
	private static final String EMAIL_PREPEND = "e";
	private static final String ADDRESS_PREPEND = "a";
	
	private ArrayList<String> commands;

	public EditCommand(int targetVisibleIndex, ArrayList<String> commands) {
		super(targetVisibleIndex);
		this.commands = commands;
	}

	@Override
	public CommandResult execute() throws IllegalValueException {
		try {
            final ReadAndWritePerson target = getTargetPerson();
            for(int i = 0; i < commands.size(); i++) {
            	String command = commands.get(i);
            	String prepend = command.substring(0, 1);
            	
            	switch(prepend) {
            	case NAME_PREPEND:
            		String name = command.substring(command.indexOf("/") + 1);
            		target.setName(new Name(name));
            		break;
            		
            	case PHONE_PREPEND:
            		String phone = command.substring(command.indexOf("/") + 1);
            		target.setPhone(new Phone(phone, false));
            		break;
            		
            	case EMAIL_PREPEND:
            		String email = command.substring(command.indexOf("/") + 1);
            		target.setEmail(new Email(email, false));
            		break;
            		
            	case ADDRESS_PREPEND:
            		String address = command.substring(command.indexOf("/") + 1);
            		target.setAddress(new Address(address, false));
            		break;
            		
            	default:
            		return new CommandResult(MESSAGE_USAGE);
            	}
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
	}

    @Override
    public boolean isMutating() {
        return true;
    }

}
