package grader.model.gradebook;

import grader.model.people.Group;
import grader.model.people.Student;
import grader.model.people.TA;

import java.util.ArrayList;
import java.util.Collection;

/**
*Collection of objects that are necessary for Section functionality.
 *
 * @author Jon Amireh
*/
public class Section
{
	/**
	*Scores associated with students and assignments in this Section.
	*/
	public Scores scores;

	/**
	*Collection of all students associated with this Section.
	*/
	public ArrayList<Student> students;

	/**
	*Collection of all groups associated with this section.
	*Groups are quarter-long.
	*/
	Collection<Group> groups;

	/**
	*The TAs associated with this section.
	*May have varying permissions.
	*/
	Collection<TA> tas;

	/**
	*String representing section number.
	*/
	String sectionNumber;

	/**
	*Name associated with section.
	*/
	String sectionName;

	/**
	*GradeScheme associated with this section.
	*/
	GradeScheme gradeScheme;

	/**
	*Add unenrolled student.
	*pre:
	*	!students.contains(stu);
	*/
	public void addStudent(Student student)
	{
        System.out.println("gradebook/Section.addStudent called");
	}

	/**
	*Add new TA.
	*pre:
	*	!tas.contains(ta);
	*/
	public void addTA(TA ta)
	{

	}
}
