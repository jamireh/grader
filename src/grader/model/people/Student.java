package grader.model.people;
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

   public Student(Name name) {
      this.name = name;
   }

   public Student(Name name, String phoneNumber)
   {
       this.name = name;
       this.phoneNumber = phoneNumber;
       photo = null;
       emplID = null;
   }

    /**
     * Returns this student's name.
     */
    public Name getName() {
        return name;
    }

   /**
    * Compares two students by last, then first name, lexicographically.
    */
   @Override
   public int compareTo(Student other) {
      int compareLast = name.getLastName().compareTo(
                           other.getName().getLastName());
      int compareFirst = name.getFirstName().compareTo(
                           other.getName().getFirstName());

      if (compareLast != 0)  return compareLast;
      else return compareFirst;
   }
}
