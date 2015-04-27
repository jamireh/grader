package grader.model.gradebook;

import grader.model.errors.PercentageFormatException;
import org.junit.Test;
/**
 * The PercentTest class is the companion testing class for the grader
 * Percent. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test the constructor with invalid values.
 *
 *    Phase 3: Unit test getValue
 *
 *    Phase 3: Repeat phases 1 through 3.
 *	                                       								 </pre>
 */
public class PercentageTest
{
    @Test(expected= PercentageFormatException.class)
    public void testInvalidData()
    {

    }
    @Test
    public void testData()
    {

    }

    @Test
    public void testGetValue()
    {

    }
}