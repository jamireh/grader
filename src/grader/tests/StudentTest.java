package grader.tests;

import grader.model.errors.InvalidPhoneNumberException;
import grader.model.errors.InvalidUserIDException;
import grader.model.errors.NameFormatException;
import grader.model.people.Name;
import grader.model.people.Student;

import javax.naming.InvalidNameException;

import static org.junit.Assert.*;

/**
 * Test class for Student.java
 *
 * @author Connor Batch
 */
public class StudentTest
{
    @org.junit.Test
    public void testCompareTo() throws Exception
    {
        Name name1 = new Name("Connor", "Raymond", "Batch");
        Name name2 = new Name("Jon", "H", "Amireh");
        Name name3 = new Name("Christian", "Marvin", "Batch");
        Student student1 = new Student(name1, "1", "");
        Student student2 = new Student(name2, "2", "");
        Student student3 = new Student(name3, "3", "");

        assertTrue(student1.compareTo(student2) > 0);
        assertTrue(student2.compareTo(student1) < 0);
        assertTrue(student1.compareTo(student3) > 0);
        assertTrue(student3.compareTo(student1) < 0);
    }

    @org.junit.Test
    public void testConstructor() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "cbatch", "");
        student = new Student(name, "cbatch", "5555555555");
        assertNotNull(student);
    }

    @org.junit.Test
    public void testEditStudent() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "cbatch", "");
        student.editStudentInfo(name, "jamireh", "");
        assertNotNull(student);
    }

    @org.junit.Test (expected = InvalidPhoneNumberException.class)
    public void testEditStudentExceptionInvalidPhoneNumber() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "cbatch", "5555555555");
        student.editStudentInfo(name, "jamireh", "sdfsdfsd");
    }

    @org.junit.Test (expected = InvalidPhoneNumberException.class)
    public void testEditStudentExceptionInvalidPhoneNumber2() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "cbatch", "5555555555");
        student.editStudentInfo(name, "jamireh", "5545555555");
        student.editStudentInfo(name, "jamireh", "555555555");
    }

    @org.junit.Test (expected = InvalidUserIDException.class)
    public void testEditStudentInvalidUserID() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "cbatch", "");
        student.editStudentInfo(name, "", "");
    }

    @org.junit.Test(expected = NameFormatException.class)
    public void testNameException() throws Exception
    {
        Name nameException = new Name("", "", "");
    }

    @org.junit.Test(expected = InvalidUserIDException.class)
    public void testUserIDException() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "", "");
    }

    @org.junit.Test(expected = InvalidPhoneNumberException.class)
    public void testPhoneNumberException() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "cbatch", "sdf");
    }

    @org.junit.Test(expected = InvalidPhoneNumberException.class)
    public void testPhoneNumberException2() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name, "sdf", "555555555");
    }

    @org.junit.Test
    public void testDebugConstructor() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name);
        assertNotNull(student);
        assertEquals(name, student.name);
    }

    @org.junit.Test
    public void testToString() throws Exception
    {
        Name name = new Name("Connor", "Raymond", "Batch");
        Student student = new Student(name);
        assertEquals(student.toString(), "Batch, Connor");
        assertNotEquals(student.toString(), "batch, connor");
    }
}