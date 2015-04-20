package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.people.Student;

import java.util.Collection;
import java.util.Map;

/**
 * A container class for gradebook statistics aggregating all individual
 * Statistics objects for the gradebook scope.
 * @author Quan Tran
 */
public class StatsContainer {
    Collection<Student> students;
    Collection<Assignment> assignments;
    Scores scores;
    Map<Assignment, Statistics> stats;

    /**
     * Constructs a new StatsContainer for the given scope and scores.
     * @param students    the students scope
     * @param assignments the assignments scope
     * @param scores      the score data
     */
    public StatsContainer(Collection<Student> students,
                          Collection<Assignment> assignments, Scores scores) {
        this.students = students;
        this.assignments = assignments;
        this.scores = scores;

        //buildStats();
    }

    /**
     * Gets the map of statistics in this container.
     * @return the map
     */
    public Map<Assignment, Statistics> getMap() { return stats; }

    /**
     * Builds the stats map, mapping Assignments to Statistics.
     */
    /*private void buildStats() {
        stats = new HashMap<>();

        // iterate through each assignment in the scope
        for (Assignment ass : assignments) {
            //List<RawScore> rawScores = new Vector<>();
            List<Double> rawScores = new Vector<>();

            // iterate through each student in the scope
            for (Student student : students)
                rawScores.add(scores.getRawScore(student, ass));

            // add the value to the map
            stats.put(ass, new Statistics(rawScores));
        }
    }*/
}
