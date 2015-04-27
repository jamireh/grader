package grader.model.errors;

/**
 * Exception thrown when a entered raw score is invalid.
 * @author Jon Amireh
 */
public class RawScoreFormatException extends Exception
{
    /**
     * Creates an exception with the given invalid value
     * @param value Double representing the invalid value
     */
    public RawScoreFormatException(double value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }
    /**
     * Creates an exception with the given invalid value
     * @param value String representing the invalid value
     */
    public RawScoreFormatException(String value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }
}
