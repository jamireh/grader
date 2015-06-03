/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when an input grade is out of its assignment range.
 */
public class ScoreOutOfRangeException extends Exception {
    private final static String NEGATIVE_MESSAGE = "' is negative.";
    private final static String POSITIVE_MESSAGE = "' is out of range for this assignment.";

    private final double score;

    /**
     * Constructs a new ScoreOutOfRangeException for the given invalid score.
     * @param score the score that triggered this exception
     */
    public ScoreOutOfRangeException(double score) {
        super("Error: '" + score + (score < 0 ? NEGATIVE_MESSAGE : POSITIVE_MESSAGE));
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
