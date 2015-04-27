package grader.view;

import grader.model.gradebook.stats.Statistics;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

/**
 * The view for the Statistics bar.
 */
public class StatisticsBar extends TableView<Statistics> {
    /**
     * Constructs a new StatisticsBar for the given data.
     */
    public StatisticsBar(ObservableList<Statistics> list) {
        super(list);
        System.out.println("stats bar constructed");
        setVisible(true);
    }
}
