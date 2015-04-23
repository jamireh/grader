package grader.model.gradebook.stats;

import java.util.Collections;
import java.util.List;

/**
 * A composite container that aggregates various statistical values for an
 * assignment or category.
 *
 * @author Quan Tran
 */
public class Statistics {
    public final double min, max, mean;

    /**
     * Constructs a new set of Statistics for the given data collection.
     * @param scores a list of the raw scores for the item
     */
    /*
    public Statistics(List<RawScore> scores) {
        Collections.sort(scores);

        // get min and max values
        min = scores.get(0).getScore();
        max = scores.get(scores.size() - 1).getScore();

        // calculate mean value
        double total = 0.0;
        for (RawScore score : scores)
            total += score.getScore();
        mean = total / scores.size();
    }
    */

    /**
     * Constructs a new set of Statistics for the given data collection.
     * @param scores a list of the scores as doubles for the item
     */
    public Statistics(List<Double> scores) {
        Collections.sort(scores);

        // get min and max values
        min = scores.get(0);
        max = scores.get(scores.size() - 1);

        // calculate mean value
        double total = 0.0;
        for (Double score : scores)
            total += score;
        mean = total / scores.size();
    }
}
