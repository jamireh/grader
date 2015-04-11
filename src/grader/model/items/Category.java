/**
 * @author Jon Amireh
 */

package grader.model.items;

import java.util.Collection;

/**
 * Represents a category of assignments.
 */
public class Category
{
    /** the name of this category */
    String name;

    /** a collection of the subcategories of this category */
    Collection<Category> subcategories;

    /** a collection of the assignments of this category */
    Collection<Assignment> items;

    /** the amount of weight this category has in the final grade */
    Weight weight;

    /** whether uncategorized assignments are graded by raw score or not */
    boolean uncategorizedByRawScore;

    /**
     * Adds the given subcategory to this category.
     * @param subcategory the subcategory to add
     post:
       // the Collection now contains the added subcategory
       subcategories'.contains(subcategory);
     */
    public void add(Category subcategory)
    {
        System.out.println("items.Category.add(Category) called");
    }

    /**
     * Adds the given item to this category.
     * @param item the item to add
     post:
       // the Collection now contains the new item
       items'.contains(assignment);
     */
    public void add(Assignment assignment)
    {
        System.out.println("items.Category.add(Assignment) called");
    }
}
