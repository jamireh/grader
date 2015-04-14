/**
 * @author Quan Tran
 */

package grader.model.gradebook;

import grader.model.items.Assignment;

/**
 * A composite container that aggregates various statistical values for an
 * assignment or category.
 */
public abstract class Statistics {
    double min, max, average;

    /**
     * Constructs a new set of Statistics for the given assignment.
     * @param scores the Scores data structure
     * @param assignment the assignment to calculate scores for
     */
    public Statistics(Scores scores, Assignment assignment) {
        int count = 0;
        double total = 0;

        min = assignment.getPoints();
        max = 0;

        // iterate through all of the raw scores
        for (RawScore score : scores.rawScores) {
            // check if the assignment matches the one given
            if (score.getAssignment().equals(assignment)) {
                double current = score.getScore();

                // update total and count
                total += current;
                count++;

                // update max score
                if (current > max)
                    max = current;
                else if (current < min)
                    min = current;
            }
        }
        average = total / count;
    }

    /**
     * Gets the minimum score for the given item.
     * @return the average score
     */
    public double getMin() { return min; }

    /**
     * Gets the average score for the given item.
     * @return the average score
     */
    public double getMax() { return max; }

    /**
     * Gets the average score for the given item.
     * @return the average score
     */
    public double getAverage() { return average; }
}
