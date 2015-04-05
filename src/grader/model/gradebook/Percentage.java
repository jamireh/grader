package grader.model.gradebook;

/**
 * Wrapper class for a float to ensure the correct percentage format.
 */
public abstract class Percentage
{
    /**
     * Value between 0.0 and 100.0
     */ 
    double value;
    
    /**
     * Corrects the passed in value to a valid percentage.
     * <pre>
     *    post:
     *    (value' <= 100.0) && (value' >= 0.0);
     * </pre>
     */ 
    public abstract void format();
}
