package grader.model.people;
import javax.swing.ImageIcon;

/**
 * A Student is a person enrolled at the university who 
 * participates in classes and recieves grades on assignments.
 */
public abstract class Student extends Person
{	
	/**
	 * The Name of this Student.
	 */
	Name name;

	/**
	 * The phone number of this Student.
	 */
	PhoneNumber phoneNumber;

	/**
	 * A digital representation of this Student.
	 */
	ImageIcon photo;

	/**
	 * The university-assigned identification number of this Student.
	 */
	String emplID;
}
