package grader.model.items;

/**
 * Represents an assignment of a specific category.
 */
public abstract class Assignment
{
    /** the category to which this item belongs */
    Category category;

    /** the date and time at which this item is due */
    DateTime dueDate;

    /** the name of this particular item */
    String name;

    /** the number of points this item is worth */
    int rawPoints;

    /** the curved number of points this item is worth */
    int curvedPoints;

    /**
     * Adjusts an item's point value.
     * @param newValue the new value to adjust to
     post:
       // the raw point value of this Item is adjusted
       this.rawPoints == newValue
     */
    public abstract void adjustPointValue(int newValue);

    /**
     * Curves this item's point value based on some given value.
     * @param value some value with which to curve this item
     */
    public abstract void curve(Object value);

    /**
     * Assigns this item to the courses it is associated with.
     */
    public abstract void assign();

    /**
     * Finds the maximum score of an item.
     * @return the maximum score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public abstract int findMax();

    /**
     * Finds the minimum score of an item.
     * @return the minimum score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public abstract int findMin();

    /**
     * Finds the median score of an item.
     * @return the median score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public abstract int findMedian();

    /**
     * Calculates the mean score of an item.
     * @return the mean score of an item
     pre:
       // this Item must have at least one score associated with it
       exists(RawScore score ; score.getAssignment().equals(this))
     */
    public abstract double calculateMean();
}
