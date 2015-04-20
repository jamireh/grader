/**
 * @author Quan Tran
 * @author Jon Amireh
 */

package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.errors.RawScoreFormatException;
import grader.model.gradebook.Percentage;

import java.time.LocalDate;

/**
 * Represents an assignment of a specific category.
 */
public class Assignment
{
    /** the date and time at which this item is due */
    LocalDate dueDate;

    /** the name of this particular item */
    String name;

    /** the number of points this item is worth */
    int rawPoints;
    /** weight for this particular assignment **/
    Percentage weight;

    public Assignment(String name, LocalDate dueDate, String rawPoints, String weight) throws PercentageFormatException, RawScoreFormatException
    {
        this.name = name;
        this.dueDate = dueDate;
        try
        {
            this.rawPoints = Integer.valueOf(rawPoints);
        }
        catch(NumberFormatException e)
        {
            throw new RawScoreFormatException(rawPoints);
        }
        if(this.rawPoints < 0)
        {
            throw new RawScoreFormatException(this.rawPoints);
        }
        this.weight = new Percentage(weight);
    }

    public Assignment() {}
    public Assignment(String name) {
       this.name = name;
    }

    /**
     * Adjusts an item's point value.
     * @param newValue the new value to adjust to
     post:
       // the raw point value of this Item is adjusted
       this.rawPoints == newValue
     */
    public void adjustPointValue(int newValue)
    {

    }

    /**
     * Gets the total points this assignment is worth.
     * @return the total raw points this assignment is worth
     */
    public int getPoints() {
        return rawPoints;
    }

    /**
     * Finds the maximum score of an item.
     * @return the maximum score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public int findMax()
    {
        return 0;
    }

    /**
     * Finds the minimum score of an item.
     * @return the minimum score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public int findMin()
    {
       return 0;
    }

    /**
     * Finds the median score of an item.
     * @return the median score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public int findMedian()
    {
        return 0;
    }

    /**
     * Calculates the mean score of an item.
     * @return the mean score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public double calculateMean()
    {
        return 0.0;
    }
}
