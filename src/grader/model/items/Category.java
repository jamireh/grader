package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.errors.WeightTotalException;
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

    /** reference to the AssignmentTree **/
    AssignmentTree assignmentTree;

    /** the amount of weight this category has in the final grade */
    Percentage weight;

    /** whether uncategorized assignments are graded by raw score or not */
    boolean uncategorizedByRawScore;

    public Category(String name, String weight, boolean uncategorizedByRawScore) throws PercentageFormatException
    {
        this.name = name;
        this.weight = new Percentage(weight);
        this.uncategorizedByRawScore = uncategorizedByRawScore;
        assignmentTree = AssignmentTree.newInstance();
        //subcategories = new ArrayList<Category>();
        //items = new ArrayList<Assignment>();
    }

    /**
     * Adds the given subcategory to this category.
     * @param subcategory the subcategory to add
     post:
       // the Collection now contains the added subcategory
       subcategories'.contains(subcategory);
     */
    public void add(Category subcategory) throws WeightTotalException
    {
        //subcategories.add(subcategory);
        assignmentTree.addTo(this, subcategory);
        System.out.println("items.Category.add(Category) called");
    }

    /**
     * Adds the given item to this category.
     * @param assignment the item to add
     post:
       // the Collection now contains the new item
       items'.contains(assignment);
     */
    public void add(Assignment assignment) throws WeightTotalException
    {
        //items.add(assignment);
        assignmentTree.addTo(this, assignment);
        System.out.println("items.Category.add(Assignment) called");
    }
}
