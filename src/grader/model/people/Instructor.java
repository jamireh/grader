package grader.model.people;

import java.util.*;

/**
 * The Instructor class defines the metadata for an instructor such as their name and username for authentification.
 *
 * At this point in time, password for authentification is not stored and will be asked for when required.
 *
 * Derived from the requirements documentation regarding admin features and network functionality.
*/
public abstract class Instructor extends Person
{
	/**
	 * Name of the instructor.
	 */
	String name;
}
