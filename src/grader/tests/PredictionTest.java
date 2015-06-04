package grader.tests;

import grader.model.gradebook.WorkSpace;
import grader.model.gradebook.scores.Prediction;
import grader.model.gradebook.scores.Scores;
import grader.model.items.Assignment;
import grader.model.people.Name;
import grader.model.people.Student;

import java.time.LocalDate;

import static org.junit.Assert.*;


/**
 * Test class for Prediction.java
 *
 * @author Alexander Miller
 */
public class PredictionTest {
    Student goodStudent;
    Assignment goodAss;
    Scores scores;

    @org.junit.Test
    public void testConstructor() throws Exception {
        goodStudent = WorkSpace.instance.getStudents().get(0);
        goodAss = WorkSpace.instance.getAssignmentTree().getAssignmentIterator().next();
        assertFalse(caughtExceptionThing(goodStudent, goodAss, 2.0));
        assertTrue(caughtExceptionThing(goodStudent, goodAss, -2.0));

        assertEquals(0,0,0);
    }

    @org.junit.Test
    public void testCountAssignments() {
        goodStudent = WorkSpace.instance.getStudents().get(0);
        goodAss = WorkSpace.instance.getAssignmentTree().getAssignmentIterator().next();
        try {
            Prediction prediction = new Prediction(goodStudent, goodAss, 100.0);
            //Canned data has 9
            assertEquals(9, prediction.countAssignments());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @org.junit.Test
    public void testGetRequired() {

        goodStudent = WorkSpace.instance.getStudents().get(0);
        goodAss = WorkSpace.instance.getAssignmentTree().getAssignmentIterator().next();
        scores = WorkSpace.instance.getScores();

        double target = 100.0;

        try {
            Prediction prediction = new Prediction(goodStudent, goodAss, target);

            double oldTotal =
                    WorkSpace.instance.getAssignmentTree().calculatePercentage
                            (WorkSpace.instance.getScores().getScoresMap(
                                    goodStudent)).getValue();
            double required;
            double score = scores.getRawScore(goodStudent, goodAss);

            double weight = 1.0/prediction.countAssignments();
            required = (target - oldTotal) / weight
                    + scores.getRawScore(goodStudent, goodAss);

            assertEquals(required, prediction.getRequired(), 1e-12);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean caughtExceptionThing(Student s, Assignment ass, double num) {
        try {
            Prediction prediction = new Prediction(s, ass, num);
        }
        catch (Exception e) {
            return true;
        }
        return false;
    }

    private boolean caughtExceptionRequired(double expected, double required) {
        if(required>=0) {
            return true;
        }
        return false;
    }
}
