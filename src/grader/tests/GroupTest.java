package grader.tests;

import grader.model.errors.InvalidPhoneNumberException;
import grader.model.errors.InvalidUserIDException;
import grader.model.errors.MissingInputException;
import grader.model.people.Group;
import grader.model.people.Name;
import grader.model.people.Student;
import org.junit.Test;
import org.omg.CORBA.ORBPackage.InvalidName;

import javax.naming.InvalidNameException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Connor Batch
 */
public class GroupTest
{
    Student connor;
    Student jon;
    Student greg;
    Group kewlKids;

    public void initialize() throws InvalidUserIDException, InvalidPhoneNumberException, MissingInputException,
                                    InvalidNameException
    {
        connor = new Student(new Name("Connor", "", "Batch"), "cbatch", "");
        jon = new Student(new Name("Jon", "", "Amireh"), "jamireh", "");
        greg = new Student(new Name("Greg", "", "Davis"), "gdavis", "");
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(connor);
        students.add(jon);
        students.add(greg);
        kewlKids = new Group("Kewl Kids", students);
    }


    @Test (expected = MissingInputException.class)
    public void testMisisngInputException() throws Exception
    {
        kewlKids = new Group("Kewl Kids", new ArrayList<Student>());
    }

    @Test (expected = InvalidNameException.class)
    public void testInvalidNameException() throws Exception
    {
        initialize();
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(connor);
        students.add(jon);
        students.add(greg);
        kewlKids = new Group("", students);
    }

    @Test (expected = InvalidNameException.class)
    public void testEditGroupName() throws Exception
    {
        initialize();
        assertEquals("Kewl Kids", kewlKids.groupName);
        kewlKids.editGroupName("Not Kewl Anymore");
        assertNotEquals("Kewl Kids", kewlKids.groupName);
        kewlKids.editGroupName("");
    }

    @Test
    public void testRemoveStudent() throws Exception
    {
        initialize();
        assertEquals(3, kewlKids.getStudents().size());
        kewlKids.removeStudent(jon);
        assertEquals(2, kewlKids.getStudents().size());
    }

    @Test
    public void testSetGroupMembers() throws Exception
    {
        initialize();
        assertEquals(3, kewlKids.getStudents().size());
        ArrayList<Student> coolGuys = new ArrayList<Student>();
        coolGuys.add(jon);
        coolGuys.add(connor);
        kewlKids.setGroupMembers(coolGuys);
        assertEquals(2, kewlKids.getStudents().size());
    }

    @Test
    public void testAddStudent() throws Exception
    {
        initialize();
        assertEquals(3, kewlKids.getStudents().size());
        kewlKids.addStudent(new Student(new Name("Quan", "", "Tran"), "qtran", ""));
        assertEquals(4, kewlKids.getStudents().size());
    }
}