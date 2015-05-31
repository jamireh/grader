package grader.tests;

import grader.model.errors.NameFormatException;
import grader.model.people.Name;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests class for Name.java
 *
 * @author Connor Batch
 */
public class NameTest
{

    @Test
    public void testGetLastName() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        assertEquals("Batch", name.getLastName());
        assertNotEquals("batch", name.getLastName());
    }

    @Test
    public void testGetFirstName() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        assertEquals("Connor", name.getFirstName());
        assertNotEquals("connor", name.getFirstName());
    }

    @Test
    public void testGetMiddleName() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        assertEquals("Raymond", name.getMiddleName());
        assertNotEquals("raymond", name.getMiddleName());
    }

    @Test
    public void testToString() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        assertEquals("Connor Batch", name.toString());
        assertNotEquals("connor batch", name.toString());
    }

    @Test (expected = NameFormatException.class)
    public void testNameException() throws Exception
    {
        Name name = new Name("", "Raymond", "Batch");
    }

    @Test (expected = NameFormatException.class)
    public void testNameException1() throws Exception
    {
        Name name = new Name("1", "Raymond", "Batch");
    }

    @Test (expected = NameFormatException.class)
    public void testNameException2() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "2");
    }

    @Test (expected = NameFormatException.class)
    public void testNameException3() throws Exception
    {
        Name name = new Name("Connor", "3", "Batch");
    }

    @Test (expected = NameFormatException.class)
    public void testNameException4() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "");
    }
}