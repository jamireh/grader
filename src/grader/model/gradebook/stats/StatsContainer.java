package grader.model.gradebook.stats;

import grader.model.file.WorkSpace;
import grader.model.gradebook.Scores;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * A container class for gradebook statistics aggregating all individual
 * Statistics objects for the gradebook scope.
 * @author Quan Tran
 */
public class StatsContainer implements Observer {
    private List<Student> students;
    private List<Assignment> assignments;
    private AssignmentTree assignmentTree;
    private Scores scores;
    private Map<Assignment, Statistics> stats;

    /**
     * Gets the map of statistics in this container.
     * @return the map
     */
    public Map<Assignment, Statistics> getMap() { return stats; }

    /**
     * Builds the list of Assignments from the AssignmentTree.
     */
    private void buildAssignments() {
        AssignmentTree.AssignmentIterator itr =
                assignmentTree.getAssignmentIterator();

        assignments = new ArrayList<Assignment>();
        while (itr.hasNext())
            assignments.add(itr.next());
    }

    /**
     * Builds the stats map, mapping Assignments to Statistics.
     */
    private void buildStats() {
        stats = new HashMap<Assignment, Statistics>();

        // iterate through each assignment in the scope
        for (Assignment ass : assignments) {
            List<Double> rawScores = new ArrayList<Double>();

            // iterate through each student in the scope
            for (Student student : students)
                rawScores.add(scores.getRawScore(student, ass));

            // add the value to the map
            stats.put(ass, new Statistics(rawScores));
        }
    }

    /**
     * Renders the statistics spreadsheet in the view.
     */
    public void render() {}

    /**
     * Observe update method.
     * Queries the WorkSpace for necessary data.
     */
    public void update(Observable obj, Object args) {
        students = WorkSpace.instance.getStudents();
        assignmentTree = WorkSpace.instance.getAssignmentTree();
        scores = WorkSpace.instance.getScores();
        buildAssignments();
        buildStats();
        render();
    }

    // test
    public static void main(String[] args) {
        StatsContainer container = new StatsContainer();
        container.update(null, null);
        Map<Assignment, Statistics> map = container.getMap();
        for (Assignment ass : map.keySet()) {
            Statistics stats = map.get(ass);
            System.out.println(ass + ". " +
                    "mean: " + stats.mean + ", " +
                    "min: " + stats.min + ", " +
                    "max: " + stats.max);
        }
    }
}
