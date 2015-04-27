package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.gradebook.Percentage;

/**
 * Represents a category of assignments.
 * @author Jon Amireh
 * @author Quan Tran
 */
public class Category
{
    /** the name of this category */
    String name;

    /** the amount of weight this category has in the final grade */
    Percentage weight;

    /** whether uncategorized assignments are graded by raw score or not */
    boolean uncategorizedByRawScore;

    public Category(String name, String weight, boolean uncategorizedByRawScore) throws PercentageFormatException
    {
        this.name = name;
        this.weight = new Percentage(weight);
        this.uncategorizedByRawScore = uncategorizedByRawScore;
    }
}
