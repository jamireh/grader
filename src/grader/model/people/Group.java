package grader.model.people;

import java.util.*;

/**
 * The Group class defines an organizational group of Students.
 *
 * Derived from the requirements documentation regarding student management.
 */
public abstract class Group
{
	/**
	 * Abstract Collection of Students to hold in this Group.
	 */
	Collection<Student> students;
	/**
	 * Name assigned to this Group.
	 */
	String groupName;
	/**
	 * Modifies the group name to the one provided.
	 * @param newGroupName String representing the new group name.
	 */
	abstract void editGroupName(String newGroupName);
	/**
	 * Adds a new Student to the underlying Collection.
	 * @param studentToAdd Student to add to this Group.
	 */
	abstract void addStudent(Student studentToAdd);
}