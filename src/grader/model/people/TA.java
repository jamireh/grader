package grader.model.people;

/**
 * A TA is a student at the university who works for the 
 * instructor and helps to maintain grades in the gradebook.
 *
 * Different TAs are allowed different access to operations
 * within the gradebook.
 */
public class TA extends Person
{
	/**
	 * The set of permissions that grant this TA
	 * access to operations within the Grader.
	 */
	Permissions permissions;	
}

