package grader.model.gradebook;

import grader.model.errors.PercentageFormatException;

/**
 * Wrapper class for a float to ensure the correct percentage format.
 *
 * @author Jon Amireh
 */
public class Percentage
{
    /**
     * Value between 0.0 and 100.0
     */ 
    private double value;

    /**
     * Creates a default percentage with a 0% value
     */
    public Percentage()
    {
        this.value = 0.0;
    }

    /**
     * Creates a Percentage with the given value
     * @param value String representing the Double representing the value
     * @throws PercentageFormatException if the value given is invalid
     */
    public Percentage(String value) throws PercentageFormatException
    {
        double dValue;
        try
        {
            dValue = Double.valueOf(value);
        }
        catch(NumberFormatException e)
        {
            throw new PercentageFormatException(value);
        }

        if(dValue < 0.0 || dValue > 100.0)
        {
            throw new PercentageFormatException(value);
        }
        else
        {
            this.value = dValue;
        }
    }
    /**
     * Creates a Percentage with the given value
     * @param dValue Double representing the value
     * @throws PercentageFormatException if the value given is invalid
     */
    public Percentage(double dValue) throws PercentageFormatException
    {
        if(dValue < 0.0 || dValue > 100.0)
        {
            throw new PercentageFormatException(value);
        }
        else
        {
            this.value = dValue;
        }
    }

    /**
     * Retrieves the value of this Percentage
     * @return Double representing the value of this Percentage
     */
    public double getValue()
    {
        return value;
    }
}
