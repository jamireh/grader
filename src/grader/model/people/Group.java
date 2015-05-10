package grader.model.people;

import java.util.*;

/**
 * The Group class defines an organizational group of Students.
 *
 * Derived from the requirements documentation regarding student management.
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
	 * Modifies the group name to the one provided.
	 * @param newGroupName String representing the new group name.
	 */
	public void editGroupName(String newGroupName) {
	   groupName = newGroupName;
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
