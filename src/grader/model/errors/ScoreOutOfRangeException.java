/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when an input grade is out of its assignment range.
 */
public class ScoreOutOfRangeException extends Exception {
    double score;

    /**
     * Constructs a new ScoreOutOfRangeException for the given invalid score.
     * @param score the score that triggered this exception
     */
    public ScoreOutOfRangeException(double score) {
        super("Error: '" + score + "' is out of range for this assignment.");
        this.score = score;
    }

    /**
     * Gets the invalid score that triggered this exception.
     * @return the score that triggered this exception
     */
    public double getScore() {
        return score;
    }
}
