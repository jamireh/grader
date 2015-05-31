package grader.model.people;

import grader.model.errors.MissingInputException;

import javax.naming.InvalidNameException;
import java.util.*;

/**
 * The Group class defines an organizational group of Students.
 * Derived from the requirements documentation regarding student management.
 *
 * @author Connor Batch
 */
public class Group
{
	/**
	 * List of Students to hold in this Group.
	 */
	private List<Student> students;
	/**
	 * Name assigned to this Group.
	 */
	public String groupName;

    /**
     * Constructs a group with a name and list of students.
     * @param groupName to call this group
     * @param students to add to this group
     */
    public Group(String groupName, ArrayList<Student> students) throws InvalidNameException, MissingInputException
    {
        if (groupName.length() < 1)
            throw new InvalidNameException();
        if (students.size() == 0)
            throw new MissingInputException("No students in this group!");

        this.groupName = groupName;
        this.students = students;

        System.out.println("called add group from the model! model/people/Group->constructor called.");
    }

	/**
	 * Modifies the group name to the one provided.
	 * @param newGroupName String representing the new group name.
	 */
	public void editGroupName(String newGroupName) throws InvalidNameException
    {
        if (newGroupName.length() < 1)
            throw new InvalidNameException();
	   groupName = newGroupName;
    }

    /**
     * Overwrites the students in this group with a new list.
     * @param students to be a part of the group
     * @throws MissingInputException if no students in the group
     */
    public void setGroupMembers(ArrayList<Student> students) throws MissingInputException
    {
        if (students.size() == 0)
            throw new MissingInputException("No students in this group!");

        this.students = students;
    }

	/**
	 * Removes a Student currently within the group.
	 * @param studentToRemove Student to add to this Group.
	 */
	public void removeStudent(Student studentToRemove) {
	   students.remove(studentToRemove);
   }

    /**
     * Adds a new Student to the underlying Collection.
     * @param studentToAdd Student to add to this Group.
     */
    public void addStudent(Student studentToAdd) {
        students.add(studentToAdd);
    }

	/**
	 * Returns the group's student list.
	 */
	public List<Student> getStudents() {
	   return students;
   }
}
