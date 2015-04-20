package grader.model.errors;

/**
 * Exception thrown when a entered weight is invalid.
 * @author Jon Amireh
 */


public class PercentageFormatException extends Exception
{
    public PercentageFormatException(double value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }

    public PercentageFormatException(String value)
    {
        super("Error: " + value + " is an invalid format for weight.");
    }
}
