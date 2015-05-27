package grader.tests;

import grader.model.items.Assignment;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

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
    public void testConstructor()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        assertThat(a, notNullValue());
        assertThat(name, equalTo(a.name));
        assertThat(date, equalTo(a.dueDate));
        assertThat(Integer.valueOf(rawPoints), equalTo((Integer) a.rawPoints));
        assertThat(Double.valueOf(weight), equalTo((Double) a.weight.getValue()));
        assertThat(a.hasWeight, equalTo(true));

        Assignment a1 = new Assignment(name, date, rawPoints, "");
        assertThat(a1, notNullValue());
        assertThat(name, equalTo(a1.name));
        assertThat(date, equalTo(a1.dueDate));
        assertThat(Integer.valueOf(rawPoints), equalTo((Integer) a1.rawPoints));
        assertThat(a1.weight, nullValue());
        assertThat(a1.hasWeight, equalTo(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPercentage()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "-20";
        Assignment a = new Assignment(name, date, rawPoints, weight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidName()
    {
        String name = "";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "20";
        Assignment a = new Assignment(name, date, rawPoints, weight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRawScoreOne()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "-5";
        String weight = "20";
        Assignment a = new Assignment(name, date, rawPoints, weight);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRawScoreTwo()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "-5";
        String weight = "20";
        rawPoints = "NaN";
        Assignment a1 = new Assignment(name, date, rawPoints, weight);
    }

    @Test
    public void testAdjustPointValue()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        int newValue = 20;
        a.adjustPointValue(newValue);
        assertThat(newValue, equalTo(a.rawPoints));
    }
    @Test
    public void testGetPoints()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        assertThat(Integer.valueOf(rawPoints), equalTo((Integer) a.getPoints()));
    }

    @Test
    public void testToString()
    {
        String name = "Midterm";
        LocalDate date = LocalDate.now();
        String rawPoints = "100";
        String weight = "50";
        Assignment a = new Assignment(name, date, rawPoints, weight);
        assertThat(name, equalTo(a.toString()));
    }

    @Test
    public void testDebugConstructor()
    {
        String name = "Midterm";
        Assignment a = new Assignment(name);
        assertThat(a, notNullValue());
        assertThat(name, equalTo(a.name));
        assertThat(100, equalTo(a.getPoints()));
    }
}