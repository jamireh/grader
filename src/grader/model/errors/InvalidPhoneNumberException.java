/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when the user inputs an invalid phone number.
 */
public class InvalidPhoneNumberException extends Exception {
    String number;

    /**
     * Constructs a new InvalidPhoneNumberException for the given number.
     * @param number the invalid phone number that triggered this exception
     */
    public InvalidPhoneNumberException(String number) {
        super("Error: '" + number + "' is an invalid phone number.");
        this.number = number;
    }

    /**
     * Gets the invalid phone number that triggered this exception.
     * @return the phone number that triggered this exception
     */
    public String getNumber() {
        return number;
    }
}
