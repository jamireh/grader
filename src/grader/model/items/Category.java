package grader.model.items;

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

    public Category(String name, String weight, boolean uncategorizedByRawScore)
    {
        if(name.isEmpty())
        {
            throw new IllegalArgumentException("Please enter a non-empty name");
        }
        else
        {
            this.name = name;
        }
        this.weight = new Percentage(weight);
        this.uncategorizedByRawScore = uncategorizedByRawScore;
    }
}
