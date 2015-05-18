package test;

import grader.model.items.Category;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
/**
 * The CategoryTest class is the companion testing class for the grader
 * Category. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test the constructor with invalid values.
 *
 *    Phase 3: Repeat phases 1 through 2.
 *	                                       								 </pre>
 *
 *	 @author Jon Amireh
 */
public class CategoryTest
{
    @Test(expected= IllegalArgumentException.class)
    public void testInvalidPercentage()
    {
        String name = "Tests";
        String weight = "-10";
        boolean categorize = false;
        Category c = new Category(name, weight, categorize);
    }

    @Test(expected= IllegalArgumentException.class)
    public void testInvalidName()
    {
        String name = "";
        String weight = "10";
        boolean categorize = false;
        Category c = new Category(name, weight, categorize);
    }
    @Test
    public void testConstructor()
    {
        String name = "Tests";
        String weight = "10";
        boolean categorize = false;
        Category c = new Category(name, weight, categorize);
        assertThat(c, notNullValue());
        assertThat(name, equalTo(c.name));
        assertThat(Double.valueOf(weight), equalTo(c.weight.getValue()));
        assertThat(categorize, equalTo(c.uncategorizedByRawScore));
    }

}