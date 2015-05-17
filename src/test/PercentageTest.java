package test;

import grader.model.errors.PercentageFormatException;
import grader.model.items.Percentage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

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
 *
 * @author Jon Amireh
 */
public class PercentageTest
{
    @Test(expected= PercentageFormatException.class)
    public void testNegativeNumber()
    {
        Percentage p = new Percentage("-10");
    }

    @Test(expected= PercentageFormatException.class)
    public void testNegativeNumberDouble()
    {
        Percentage p = new Percentage(-10.0);
    }

    @Test(expected= PercentageFormatException.class)
    public void testNaN()
    {
        Percentage p = new Percentage("NaN");
    }
    @Test
    public void testGetValue()
    {
        Percentage p = new Percentage("10");
        assertThat(10.0, equalTo(p.getValue()));
    }

    @Test
    public void testToString()
    {
        String compare = "Percentage {" +
                "value=" + 10.0 +
                '}';
        Percentage p = new Percentage(10.0);
        assertThat(compare, equalTo(p.toString()));
    }

    @Test
    public void testEquals()
    {
        Percentage p = new Percentage("10.0");
        Percentage p1 = new Percentage(10.0);
        assertThat(p, equalTo(p1));


        assertThat(p, equalTo(p1));
        Percentage p2 = new Percentage("20.0");
        assertThat(p, not(equalTo(p2)));
    }
}