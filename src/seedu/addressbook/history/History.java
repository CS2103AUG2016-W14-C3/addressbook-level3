package seedu.addressbook.history;

import java.util.Stack;

public class History {

    private Stack<RecentCommand> recentCommands;
    
    public History() {
        recentCommands = new Stack<RecentCommand>();
    }
    
    /*
     * Checks if history contains recent commands
     * @return true if history is empty
     */
    public boolean isEmpty() {
        return recentCommands.isEmpty();
    }
    
    /*
     * Insert recent command into history
     * @param most recent command
     */
    public void insert(RecentCommand command) {
        recentCommands.push(command);
    }
    
    /*
     * Remove recent command from history
     * @return most recent command
     */
    public RecentCommand remove() {
        return recentCommands.pop();
    }
}
