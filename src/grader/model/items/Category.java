package grader.model.items;

import grader.model.errors.PercentageFormatException;

/**
 * Represents a category of assignments.
 * @author Jon Amireh
 * @author Quan Tran
 */
public class Category
{
    /** the name of this category */
    public String name;

    /** the amount of weight this category has in the final grade */
    public Percentage weight;

    /** whether uncategorized assignments are graded by raw score or not */
    public boolean uncategorizedByRawScore;

    public Category(String name, String weight, boolean uncategorizedByRawScore) throws PercentageFormatException
    {
        this.name = name;
        this.weight = new Percentage(weight);
        this.uncategorizedByRawScore = uncategorizedByRawScore;
    }
}
