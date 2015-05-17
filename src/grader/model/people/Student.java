package grader.model.people;
import grader.model.errors.InvalidPhoneNumberException;
import grader.model.errors.InvalidUserIDException;

import javax.swing.ImageIcon;


/**
 * A Student is a person enrolled at the university who 
 * participates in classes and receives grades on assignments.
 *
 * @author Connor Batch
 */
public class Student extends Person implements Comparable<Student>
{	
	/**
	 * The Name of this Student.
	 */
	public Name name;

    /**
     * The unique userID of this Student.
     */
    String userID;

	/**
	 * The phone number of this Student.
	 */
	String phoneNumber;

   public Student(Name name) {
      this.name = name;
   }

   public Student(Name name, String userID, String phoneNumber) throws InvalidPhoneNumberException, InvalidUserIDException
   {
       if (phoneNumber.length() != 0 && (phoneNumber.length() != 10 || !phoneNumber.matches("^[0-9]+$")))
           throw new InvalidPhoneNumberException(phoneNumber);
       if (userID.length() == 0)
           throw new InvalidUserIDException(userID);
       this.name = name;
       this.userID = userID;
       this.phoneNumber = phoneNumber;
   }

   /**
    * Compares two students by last, then first name, lexicographically.
    */
   @Override
   public int compareTo(Student other) {
      int compareLast = name.getLastName().compareTo(
                           other.name.getLastName());
      int compareFirst = name.getFirstName().compareTo(
                           other.name.getFirstName());

      if (compareLast != 0)  return compareLast;
      else return compareFirst;
   }

    public String toString()
    {
        return name.lastName + ", " + name.firstName;
    }
}
