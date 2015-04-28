package grader.model.people;

import grader.model.errors.NameFormatException;

import javax.naming.InvalidNameException;

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

    public Name(String firstName, String middleName, String lastName) throws InvalidNameException
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        if (firstName.length() == 0 || lastName.length() == 0 || firstName.matches("/^[A-z]+$/") || middleName
                .matches("/^[A-z]+$/") || lastName.matches("/^[A-z]+$/"))
            throw new InvalidNameException("");
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

    public String toString() {
        return firstName + " " + lastName;
    }
}
