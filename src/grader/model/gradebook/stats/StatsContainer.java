package grader.model.gradebook.stats;

import grader.controller.StatsController;
import grader.model.file.WorkSpace;
import grader.model.gradebook.scores.Scores;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.Student;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * A container class for gradebook statistics aggregating all individual
 * StatisticsBar objects for the gradebook scope.
 * @author Quan Tran
 */
public class StatsContainer implements Observer {
    private final static int STATS_COUNT = 3;
    private StatsController controller;
    private List<Student> students;
    private List<Assignment> assignments;
    private AssignmentTree assignmentTree;
    private Scores scores;
    private Map<Assignment, Statistics> stats;

    /**
     * Gets the list of Assignments used by this container.
     * @return the list of Assignments
     */
    public List<Assignment> getAssignments() { return assignments; }

    /**
     * Gets the map of Statistics in this container.
     * @return the Assignments->Statistics map
     */
    public Map<Assignment, Statistics> getMap() { return stats; }

    /**
     * Sets the controller for this StatsContainer.
     * @param controller the controller to use
     */
    public void setController(StatsController controller) {
        this.controller = controller;
    }

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
     * Builds the stats map, mapping Assignments to StatisticsBar.
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
    public void render() {
        if (controller != null) {
            int size = assignments.size();
            String[][] statsTable = new String[STATS_COUNT][size + 1];

            statsTable[0][0] = "Max";
            statsTable[1][0] = "Average";
            statsTable[2][0] = "Min";

            DecimalFormat format = new DecimalFormat("#.0#");

            // populate stats table
            for (int i = 0; i < size; ++i) {
                Statistics current = stats.get(assignments.get(i));

                statsTable[0][i + 1] = format.format(current.max);
                statsTable[1][i + 1] = format.format(current.mean);
                statsTable[2][i + 1] = format.format(current.min);
            }

            controller.render(statsTable);
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
        buildAssignments();
        buildStats();
        render();
    }
}
