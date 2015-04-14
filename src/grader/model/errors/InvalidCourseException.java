/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when the user attempts to create a course with an invalid
 * course number.
 */
public class InvalidCourseException extends Exception {
    String number;

    /**
     * Constructs a new InvalidCourseException for the given invalid course.
     * @param number the invalid course number that triggered this exception
     */
    public InvalidCourseException(String number) {
        super("Error: '" + number + "' is an invalid course number.");
        this.number = number;
    }

    /**
     * Gets the invalid course number that triggered this exception.
     * @return the course number that triggered this exception
     */
    public String getNumber() {
        return number;
    }
}
