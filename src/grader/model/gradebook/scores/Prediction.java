package grader.model.gradebook.scores;

import grader.model.errors.ScoreOutOfRangeException;
import grader.model.gradebook.WorkSpace;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
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
     * @return the required grade for the prediction.
     */
    public double getRequired() {
        double oldTotal =
                WorkSpace.instance.getAssignmentTree().calculatePercentage
                        (WorkSpace.instance.getScores().getScoresMap(
                                WorkSpace.instance.selectedScore.getStudent())).getValue();
        double required;

        // calculation for if the assignment has a weight
        if (assignment.hasWeight) {
            required = (target - oldTotal) / assignment.weight.getValue()
                    + scores.getRawScore(student, assignment);
        }
        // otherwise assume equal distribution of assignment weight
        else {
            double weight = 1.0/countAssignments();
            required = (target - oldTotal) / weight
                    + scores.getRawScore(student, assignment);
        }

        return required;
    }

    /**
     * Counts the number of assignments in the assignment tree
     * for prediction calculation.
     * @return the number of assignments in the tree
     */
    private int countAssignments() {
        AssignmentTree.AssignmentIterator itr =
                WorkSpace.instance.getAssignmentTree().getAssignmentIterator();

        int count = 0;

        // iterate through all assignments and count
        while (itr.hasNext()) {
            itr.next();
            ++count;
        }

        return count;
    }
}
