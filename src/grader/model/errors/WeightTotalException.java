/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when the user allocates an invalid weight total.
 */
public class WeightTotalException extends Exception {
    /**
     * Constructs a new WeightTotalException.
     */
    public WeightTotalException(double weight) {
        super("Error: the given weight will cause a balancing error.");
    }
}
