/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when an input name is not of a valid format.
 */
public class NameFormatException extends Exception {
    String name;

    /**
     * Constructs a new NameFormatException for the given invalid name.
     * @param name the name that triggered this exception
     */
    public NameFormatException(String name) {
        super("Error: '" + name + "' is not a valid name.");
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
