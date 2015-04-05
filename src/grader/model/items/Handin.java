package grader.model.items;

import java.util.*;

/**
 * A Handin represents a collection of submissions for an
 * assignment or course.
 */
public abstract class Handin {
	/**
 	* Collection of submissions for a GradedItem.
 	*/
	Collection<Submission> submissions;
}
