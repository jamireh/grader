package grader.model.gradebook.scores;

import grader.model.errors.ScoreOutOfRangeException;
import grader.model.gradebook.WorkSpace;
import grader.model.items.Assignment;
import grader.model.people.Student;

/**
 * A Prediction calculates and provides a required grade for a particular
 * student and a particular assignment for the given student to receive
 * a target grade in the course.
 *
 * @author Quan Tran
 * @author Alexander Miller
 */
public class Prediction {
    private final Student student;
    private final Assignment assignment;
    private final Scores scores;
    private final double target;


    /**
     * Constructs a new Prediction container for the given student on the given assignment.
     * @param student the student
     * @param assignment the assignment
     */
    public Prediction(Student student, Assignment assignment, double target)
            throws ScoreOutOfRangeException {
        this.student = student;
        this.assignment = assignment;
        if (target < 0.0)
            throw new ScoreOutOfRangeException(target);
        this.target = target;
        this.scores = WorkSpace.instance.getScores();
    }

    /**
     * Calculates and returns the required score of the prediction.
     * NOTE: needs work
     * @return the required grade for the prediction.
     */
    public double getRequired() {
        // if weights are not set this gets a bit confusing..
        double weight = 1.0/8.0;
        /*(required - oldScore) * weight
                =
        (newTotal - oldTotal)*/
        double oldTotal = WorkSpace.instance.getAssignmentTree().calculatePercentage(WorkSpace.instance.getScores()
                        .getScoresMap(WorkSpace.instance.selectedScore.getStudent())).getValue();
        double required = ((target - oldTotal) / weight) + scores.getRawScore(student, assignment);
        return required;
    }

}
