package grader.model.people;

/**
 * A Name is the set of words by which a Person is
 * addressed.
 *
 * @author Connor Batch
 */
public class Name
{
	/**
	 * The first name of this Person.
	 */
	String firstName;

	/**
	 * The last name of this Person.
	 */
	String lastName;

	/**
	 * The middle name of this Person.
	 */
	String middleName;

	/**
	 * A shorthand name for this Person.
	 */
	String nickName;

    public Name(String firstName, String middleName, String lastName, String nickName)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
    }

    /**
     * Gets this student's last name.
     */
    public String getLastName() {
       return lastName;
    }

    /**
     * Gets this student's first name.
     */
    public String getFirstName() {
       return firstName;
    }
}
