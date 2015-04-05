package grader.model.gradebook;

import grader.model.people.Instructor;

import java.util.Collection;

/**
*This class collects all the various components of the grader: Instructor, TA and Student Views.
**/
public abstract class Gradebook {

	/**
	*The courses associated with this gradebook.
	*/
	public Collection<Course> courses;

	/**
	*The Instructor associated with this gradebook.
	*No limits on permissions.
	*/
	public Instructor instructor;

	/**
	*Set Instructor associated with this gradebook.
	*pre:
	*	instructor.equals(null);
	*/
	public abstract void setInstructor(Instructor ins);

	/**
	*Add courses to collection associated with this gradebook.
	*pre:
	*	!courses.contains(cor);
	*/
	public abstract void addCourse(Course cor);

}

