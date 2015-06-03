package grader.model.gradebook.scores;

import grader.model.file.WorkSpace;
import grader.model.items.Assignment;
import grader.model.people.Student;


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
    public Prediction(Student student, Assignment assignment, double target) {
        this.student = student;
        this.assignment = assignment;
        this.target = target;
        this.scores = WorkSpace.instance.getScores();
    }


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
