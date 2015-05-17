package grader.model.items;

import grader.model.errors.PercentageFormatException;

/**
 * Wrapper class for a float to ensure the correct percentage format.
 *
 * @author Jon Amireh
 */
public class Percentage implements Comparable<Percentage>
{
    /**
     * Value between 0.0 and 100.0
     */ 
    private double value;

    /**
     * Creates a Percentage with the given value
     * @param value String representing the Double representing the value
     * @throws PercentageFormatException if the value given is invalid
     */
    public Percentage(String value)
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

        if(Double.isNaN(dValue))
        {
            throw new PercentageFormatException(value);
        }

        if(dValue < 0.0)
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
    public Percentage(double dValue)
    {
        if(dValue < 0.0)
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

    /**
     * Compares this Percentage to the specified one.
     * @param o the Percentage to compare to this one.
     * @return neg. if this Percentage is less than the specified,
     * pos. if this Percentage is less than the specified,
     * 0 if they are considered equal
     */
    @Override
    public int compareTo(Percentage o)
    {
        return Double.compare(value, o.getValue());
    }

    /**
     * Compares this Percentage to the specified one for equality.
     * @param o the Percentage to compare to this one.
     * @return true if the specified Percentage is equal to this, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Percentage that = (Percentage) o;

        return Double.compare(that.value, value) == 0;

    }

    /**
     * Generates a String representation of this Percentage.
     * @return a String representation of this Percentage
     */
    @Override
    public String toString() {
        return "Percentage {" +
                "value=" + value +
                '}';
    }
}
