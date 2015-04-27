package grader.model.curve;

import grader.model.gradebook.Gradebook;
import grader.model.gradebook.Section;
import grader.model.items.Assignment;
import grader.model.people.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The StatisticsBar class performs different math operations on the Scores of a certain Section.
 *  
 * Derived from the requirements documentation regarding statistics;
 */
public abstract class Statistics
{
	
	/**
	* Determines the maximum RawScore in the collection. Used in Spest.
	* @param collection Collection to determine the max RawScore
	* @return RawScore maximum raw score
	*/
	private Double max(List<Double> collection)
	{
		return maxHelper(collection, collection.get(0));
	}
	private Double maxHelper(List<Double> collection, Double given)
	{
		if(collection.size() == 0)
		{
			return given;
		}
		else
		{
			if(given > collection.get(collection.size() - 1))
			{
				return maxHelper(collection.subList(0, collection.size() - 1), given);
			}
			else
			{
				return maxHelper(collection.subList(0, collection.size() - 1), collection.get(0));
			}
		}
	}

	/**
	* Determines the minimum RawScore in the collection. Used in Spest.
	* @param collection Collection to determine the min RawScore
	* @return RawScore minimum raw score
	*/
	private Double min(List<Double> collection)
	{
		return minHelper(collection, collection.get(0));
	}
	private Double minHelper(List<Double> collection, Double given)
	{
		if(collection.size() == 0)
		{
			return given;
		}
		else
		{
			if(given < collection.get(collection.size() - 1))
			{
				return minHelper(collection.subList(0, collection.size() - 1), given);
			}
			else
			{
				return minHelper(collection.subList(0, collection.size() - 1), collection.get(0));
			}
		}
	}

	/**
	* Returns an List of RawScores based on Assignment and Sections. Used in Spest.
	* @param Assignment assignment of interest
	* @return List<RawScore> rawscores of all the students for this assignment
	*/
	private List<Double> getScores(Assignment assignment)
	{
		List<Double> toReturn = new ArrayList<Double>();
		for(Section section : sections)
		{
			for(Student student : section.students)
			{
				toReturn.add(gradebook.getScores().getRawScore(student, assignment));
			}
		}
		return toReturn;
	}
	/**
	 * Context Section to apply these operations to.
	 */
	Collection<Section> sections;

    /**
     * Gradebook to apply these operations to.
     */
    Gradebook gradebook;

	/**
	 * Calculates the average Score for the Assignment in the Section.
     * @param assignment assignment you wish to calculate the average for
	 * @return Double representing the average
	 * <pre>
	 post:
	 	return =< max(getScores(assignment) && return >= min(getScores(assignment);
	 */
	abstract Double average(Assignment assignment);
}
