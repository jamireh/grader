package grader.model.items;

import grader.model.errors.PercentageFormatException;
import org.junit.Test;
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
 */
public class CategoryTest
{
    @Test(expected = PercentageFormatException.class)
    public void testInvalidPercentage()
    {

    }
    @Test
    public void testCreation()
    {

    }

}