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

    public Percentage()
    {
        this.value = 0.0;
    }

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

    public double getValue()
    {
        return value;
    }

    /**
     * Corrects the passed in value to a valid percentage.
     * <pre>
     *    post:
     *    (value' <= 100.0) && (value' >= 0.0);
     * </pre>
     */ 
    public void format()
    {

    }
}
