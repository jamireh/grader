/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when a required field is provided blank input.
 */
public class MissingInputException extends Exception {
    String label;

    /**
     * Constructs a new MissingInputException for the given invalid label.
     * @param label the label for the field missing input
     */
    public MissingInputException(String label) {
        super("Error: '" + label + "' field is required.");
        this.label = label;
    }

    /**
     * Gets the label of the empty field that triggered this exception.
     * @return the label of the field missing input
     */
    public String getField() {
        return label;
    }
}
