package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.errors.RawScoreFormatException;
import org.junit.Test;
/**
 * The AssignmentTest class is the companion testing class for the grader
 * Assignment. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test the constructor with invalid values.
 *
 *    Phase 3: Unit test adjustPointValue
 *
 *    Phase 4: Unit test getPoints
 *
 *    Phase 3: Repeat phases 1 through 4.
 *	                                       								 </pre>
 */
public class AssignmentTest
{

    @Test(expected = PercentageFormatException.class)
    public void testInvalidPercentage()
    {

    }
    @Test(expected = RawScoreFormatException.class)
    public void testInvalidRawScore()
    {

    }
    @Test
    public void testAdjustPointValue() throws Exception
    {

    }
    @Test
    public void testGetPoints() throws Exception
    {

    }
}