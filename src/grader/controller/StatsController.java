package grader.controller;

import grader.model.gradebook.WorkSpace;
import grader.model.gradebook.stats.StatsContainer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Controller for the Statistics model.
 * @author Quan Tran
 */
public class StatsController implements Initializable {
    @FXML HBox hbox;
    private StatsContainer stats = WorkSpace.instance.statistics;
   // private StatisticsBar bar;
    static TableView<String[]> table = null;

    /**
     * Initializes this StatsController.
     * @param  location unused
     * @param resources unused
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table = new TableView<String[]>();
        hbox.setSpacing(5);
        hbox.getChildren().addAll(table);

        table.setEditable(true);
        table.setMinWidth(1200);
        table.setMaxWidth(1200);
        table.setMaxHeight(600);
        stats.setController(this);
        stats.update(null, null);
    }

    /**
     * Renders the StatisticsBar bar in the view.
     */
    public void render(String[][] statsTable) {

        table.getColumns().clear();

        for (int i = 0; i < statsTable[0].length; i++) {
            TableColumn tc = new TableColumn(null);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(statsTable));

        table.setItems(data);
    }
}
