package grader.model.items;

import grader.model.errors.PercentageFormatException;
import grader.model.errors.RawScoreFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

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
*	  @author Jon Amireh
 */
public class AssignmentTest
{

    @Test
    public void testConstructor() throws RawScoreFormatException, PercentageFormatException
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        Assert.assertNotNull("Construction resulted in a null Object", a);
        Assert.assertEquals("Name not properly set", name, a.name);
        Assert.assertEquals("DueDate not properly set", date, a.dueDate);
        Assert.assertEquals("rawPoints not properly set", Integer.valueOf(rawPoints), (Integer) a.rawPoints);
        Assert.assertEquals("weight not properly set", Double.valueOf(weight), (Double) a.weight.getValue());
        Assert.assertTrue("weight flag not properly set", a.hasWeight);

        Assignment a1 = new Assignment(name, date, rawPoints, "");
        Assert.assertNotNull("Construction resulted in a null Object", a1);
        Assert.assertEquals("Name not properly set", name, a1.name);
        Assert.assertEquals("DueDate not properly set", date, a1.dueDate);
        Assert.assertEquals("rawPoints not properly set", Integer.valueOf(rawPoints), (Integer) a1.rawPoints);
        Assert.assertNull("weight not properly set", a1.weight);
        Assert.assertFalse("weight flag not properly set", a1.hasWeight);
    }

    @Test(expected = PercentageFormatException.class)
    public void testInvalidPercentage()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "-20";
        Assignment a = new Assignment(name, date, rawPoints, weight);
    }

    @Test(expected = RawScoreFormatException.class)
    public void testInvalidRawScoreOne()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "-5";
        String weight = "20";
        Assignment a = new Assignment(name, date, rawPoints, weight);
    }

    @Test(expected = RawScoreFormatException.class)
    public void testInvalidRawScoreTwo()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "-5";
        String weight = "20";
        rawPoints = "NaNaNaNaNaN";
        Assignment a1 = new Assignment(name, date, rawPoints, weight);
    }

    @Test
    public void testAdjustPointValue() throws Exception
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        int newValue = 20;
        a.adjustPointValue(newValue);
        Assert.assertEquals("Raw points not set properly", newValue, a.rawPoints);
    }
    @Test
    public void testGetPoints() throws Exception
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        Assert.assertEquals("Raw points not set properly", Integer.valueOf(rawPoints), (Integer) a.getPoints());
    }

    @Test
    public void testToString() throws RawScoreFormatException, PercentageFormatException
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        Assert.assertEquals("Name not set properly", name, a.toString());
    }

    @Test
    public void testDebugConstructor()
    {
        String name = "Midterm";
        Assignment a = new Assignment(name);
        Assert.assertNotNull(a);
        Assert.assertEquals("Name not set properly", name, a.name);
        Assert.assertEquals("Raw Score not set properly", 100, a.getPoints());
    }
}