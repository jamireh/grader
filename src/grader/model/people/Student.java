package grader.model.people;
import javax.swing.ImageIcon;

/**
 * A Student is a person enrolled at the university who 
 * participates in classes and receives grades on assignments.
 *
 * @author Connor Batch
 */
public class Student extends Person
{	
	/**
	 * The Name of this Student.
	 */
	Name name;

	/**
	 * The phone number of this Student.
	 */
	String phoneNumber;

	/**
	 * A digital representation of this Student.
	 */
	ImageIcon photo;

	/**
	 * The university-assigned identification number of this Student.
	 */
	String emplID;

    public Student(Name name, String phoneNumber)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        photo = null;
        emplID = null;
    }
}
