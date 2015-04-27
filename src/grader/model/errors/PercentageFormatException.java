package grader.model.errors;

/**
 * Exception thrown when a entered weight is invalid.
 * @author Jon Amireh
 */


public class PercentageFormatException extends Exception
{
    /**
     * Creates an exception with the given invalid value
     * @param value Double representing the invalid data
     */
    public PercentageFormatException(double value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }
    /**
     * Creates an exception with the given invalid value
     * @param value String representing the invalid data
     */
    public PercentageFormatException(String value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }
}
