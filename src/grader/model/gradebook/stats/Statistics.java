package grader.model.gradebook.stats;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
    public final StringProperty pMin, pMax, pMean;

    /**
     * Constructs a new set of StatisticsBar for the given data collection.
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

        pMin = new SimpleStringProperty("" + min);
        pMax = new SimpleStringProperty("" + max);
        pMean = new SimpleStringProperty("" + mean);
    }
}
