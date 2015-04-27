package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.errors.RawScoreFormatException;
import org.junit.Test;

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