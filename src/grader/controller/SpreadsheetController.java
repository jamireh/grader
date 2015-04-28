package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.Sidebar;
import grader.model.gradebook.Spreadsheet;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;


/**
 * Controller for the Sidebar model
 * @author Jon Amireh
 */
public class SpreadsheetController implements Initializable
{
    @FXML HBox hbTable;
    Spreadsheet spreadsheet = WorkSpace.instance.spreadsheet;

    static TableView<String[]> table = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        spreadsheet.setController(this);
        spreadsheet.update(null, null);
    }



    public void setupGradebook(String[] headers, String[][] grades)
    {
       if (table == null) {
          table = new TableView<String[]>();
          hbTable.setSpacing(5);
          hbTable.getChildren().addAll(table);

          final Button b1 = new Button("Revert");
          b1.setFont(new Font("Arial", 16));
          final Button b2 = new Button("Save");
          b2.setFont(new Font("Arial", 16));
          final Button b3 = new Button("Save and Publish");
          b3.setFont(new Font("Arial", 16));

          table.setEditable(true);
          table.setMinWidth(1200);
          table.setMaxWidth(1200);
          table.setMaxHeight(600);
       }

       for (int i = 0; i < headers.length; i++) {
          TableColumn tc = new TableColumn(headers[i]);
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
       data.addAll(Arrays.asList(grades));

       table.setItems(data);
    }
}
