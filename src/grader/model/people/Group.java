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
	private List<Student> students;
	/**
	 * Name assigned to this Group.
	 */
	private String groupName;

	/**
	 * Modifies the group name to the one provided.
	 * @param newGroupName String representing the new group name.
	 */
	public void editGroupName(String newGroupName) {
	   groupName = newGroupName;
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
