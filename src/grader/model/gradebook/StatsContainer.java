package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.people.Student;

import java.util.*;

/**
 * A container class for gradebook statistics aggregating all individual
 * Statistics objects for the gradebook scope.
 * @author Quan Tran
 */
public class StatsContainer implements Observer {
    private Collection<Student> students;
    private Collection<Assignment> assignments;
    private AssignmentTree assignmentTree;
    private Scores scores;
    private Map<Assignment, Statistics> stats;

    /**
     * Gets the map of statistics in this container.
     * @return the map
     */
    public Map<Assignment, Statistics> getMap() { return stats; }

    /**
     * Builds the stats map, mapping Assignments to Statistics.
     */
    private void buildStats() {
        stats = new HashMap<Assignment, Statistics>();

        // iterate through each assignment in the scope
        for (Assignment ass : assignments) {
            //List<RawScore> rawScores = new Vector<>();
            List<Double> rawScores = new Vector<Double>();

            // iterate through each student in the scope
            for (Student student : students)
                rawScores.add(scores.getRawScore(student, ass));

            // add the value to the map
            stats.put(ass, new Statistics(rawScores));
        }
    }

    /**
     * Observe update method.
     * Queries the WorkSpace for necessary data.
     */
    public void update(Observable obj, Object args) {
       students = WorkSpace.instance.getStudents();
       assignmentTree = WorkSpace.instance.getAssignmentTree();
       scores = WorkSpace.instance.getScores();
       buildStats();
    }
}
