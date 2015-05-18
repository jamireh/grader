/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when an input name is not of a valid format.
 * @author Connor Batch
 */
public class InvalidUserIDException extends Exception {
    String name;

    /**
     * Constructs a new NameFormatException for the given invalid name.
     * @param name the name that triggered this exception
     */
    public InvalidUserIDException(String name) {
        super("Error: '" + name + "' is not unique.");
        this.name = name;
    }

    /**
     * Gets the invalid name that triggered this exception.
     * @return the name that triggered this exception
     */
    public String getName() {
        return name;
    }
}
