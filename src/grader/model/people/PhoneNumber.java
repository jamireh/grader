package grader.model.people;

/**
 * A PhoneNumber is the set of number digits by which 
 * a person can be contacted with a Telephone.
 */
public abstract class PhoneNumber
{
	/**
	 * A three-digit number that identifies a telephone service regions.
	 */
	String areaCode;

	/**
	 * A seven-digit number that is used as a direct link to a specific telephone.
	 */
	String phoneNumber;
}