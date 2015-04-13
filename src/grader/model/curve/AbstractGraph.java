
package grader.model.curve;



import grader.model.gradebook.Section;

import java.util.List;

/**
 * The AbstractGraph class defines the necessary and commont components for both a Histogram and PieChart
 *
 * Derived from the requirements documentation regarding visuals.
 */

public abstract class AbstractGraph
{
	/**
	 * Section to which the visual will apply to.
	 */
	List<Section> sections;
	/**
	 * The List containing an entry for how many students have a particular score.
	 * Written to by categorizeScores()
	 */
	List<Integer> numScores;
	private static int countCollection(List<Integer> collection)
	{
		if(collection.size() == 0)
		{
			return 0;
		}
		else
		{
			return collection.get(collection.size() - 1) + countCollection(collection.subList(0, collection.size() - 1));
		}
	}

	private static int countStudents(List<Section> sections)
	{
		int toReturn = 0;
		for(Section section : sections)
		{
			toReturn += section.students.size();
		}
		return toReturn;
	}

	/**
	 * Uses the Section to find the Scores necessary to draw the chart as well as categorizes them into numScores
	 * <pre>
	 post:
	 //Ensure that the numScores list is correct by summing the contents of the list and verifying that it is the
	 //same size as the number of students
	 	countCollection(numScores) == countStudents(sections);
	 */
	abstract public void categorizeScores();
}