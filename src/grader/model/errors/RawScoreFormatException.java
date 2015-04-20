package grader.model.errors;

public class RawScoreFormatException extends Exception
{

/**
 * Exception thrown when a entered raw score is invalid.
 * @author Jon Amireh
 */
    public RawScoreFormatException(double value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }

    public RawScoreFormatException(String value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }
}
