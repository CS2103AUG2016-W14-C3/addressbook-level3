package seedu.addressbook.logic;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadAndWritePerson;
import seedu.addressbook.history.History;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Represents the main Logic of the AddressBook.
 */
public class Logic {


    private StorageFile storage;
    private AddressBook addressBook;
    private History history;
    
    /** The list of person shown to the user most recently.  */
    private List<? extends ReadAndWritePerson> lastShownList = Collections.emptyList();

    public Logic() throws Exception{
        setStorage(initializeStorage());
        setAddressBook(storage.load());
        history = new History();
    }

    Logic(StorageFile storageFile, AddressBook addressBook){
        setStorage(storageFile);
        setAddressBook(addressBook);
        history = new History();
    }

    void setStorage(StorageFile storage){
        this.storage = storage;
    }

    void setAddressBook(AddressBook addressBook){
        this.addressBook = addressBook;
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @throws StorageFile.InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage() throws StorageFile.InvalidStorageFilePathException {
        return new StorageFile();
    }

    public String getStorageFilePath() {
        return storage.getPath();
    }

    /**
     * Unmodifiable view of the current last shown list.
     */
    public List<ReadAndWritePerson> getLastShownList() {
        return Collections.unmodifiableList(lastShownList);
    }

    protected void setLastShownList(List<? extends ReadAndWritePerson> newList) {
        lastShownList = newList;
    }

    /**
     * Parses the user command, executes it, and returns the result.
     * @throws Exception if there was any problem during command execution.
     */
    public CommandResult execute(String userCommandText) throws Exception {
        Command command = new Parser().parseCommand(userCommandText);
        CommandResult result = execute(command);
        recordResult(result);
        return result;
    }

    /**
     * Executes the command, updates storage, and returns the result.
     *
     * @param command user command
     * @return result of the command
     * @throws Exception if there was any problem during command execution.
     */
    private CommandResult execute(Command command) throws Exception {
        command.setData(addressBook, lastShownList, history);
        CommandResult result = command.execute();
        if (command.isMutating()) {
            storage.save(addressBook);
        }
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadAndWritePerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }
}
