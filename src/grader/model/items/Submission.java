package grader.model.items;

import grader.model.people.Student;

import java.io.File;

/**
 * A Submission represents a file and completion for an assignment with
 * additional information about the student and the time of the submission.
 */
public abstract class Submission 
{
	/**
 	* The File data for a file handed-in for an assignment.
 	*/
	File file;

	/**
 	* The date and time a file is submitted.
 	*/
	DateTime dateTime;

	/**
 	* The Student who is handing in a submission
 	*/
	Student student;

	/**
 	* The assignment the Student is handing in a submission to.
 	*/
	Assignment assignment;
}
