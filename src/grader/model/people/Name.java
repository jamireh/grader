package grader.model.people;

/**
 * A Name is the set of words by which a Person is
 * addressed.
 */
public abstract class Name
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
}