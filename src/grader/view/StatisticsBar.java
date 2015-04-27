package grader.view;

import grader.model.gradebook.stats.Statistics;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * The view for the Statistics bar.
 */
public class StatisticsBar extends TableView<Statistics> {
    List<TableColumn<Statistics, Double>> columns;

    /**
     * Constructs a new StatisticsBar for the given data.
     */
    public StatisticsBar(ObservableList<Statistics> list) {
        super(list);

        columns = new ArrayList<TableColumn<Statistics, Double>>();
        for (Statistics stat : list) {
            TableColumn<Statistics, Double> current =
                    new TableColumn<Statistics, Double>();

            current.setCellValueFactory(new PropertyValueFactory("pMax"));
        }

        getColumns().setAll(columns);
    }
}
