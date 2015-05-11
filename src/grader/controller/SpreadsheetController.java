package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.scores.Scores;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import java.net.URL;
import java.util.*;


/**
 * Controller for the grade spreadsheet.
 * @author Jon Amireh
 * @author Alexander Miller
 * @author Gregory Davis
 */
public class SpreadsheetController implements Initializable, Observer
{
    @FXML HBox hbTable;

    static TableView<String[]> table = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       table = new TableView<String[]>();
       hbTable.setSpacing(5);
       hbTable.getChildren().addAll(table);

       table.setEditable(true);
       table.setMinWidth(1200);
       table.setMaxWidth(1200);
       table.setMaxHeight(600);

       WorkSpace.instance.addObserver(this);
       update(null, null);
    }

    public void setupGradebook(String[] headers, String[][] grades)
    {
       table.getColumns().clear();
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

   public void update(Observable obs, Object arg) {
      List<Assignment> assignments = new ArrayList<Assignment>();
      AssignmentTree.AssignmentIterator itr =
            WorkSpace.instance.getAssignmentTree().getAssignmentIterator();

      while (itr.hasNext()) {
         Assignment assignment = itr.next();
         assignments.add(assignment);
      }

      List<Student> students = WorkSpace.instance.getStudents();
      Scores scores = WorkSpace.instance.getScores();

      String[][] grades = new String[students.size()][assignments.size() + 1];
      String[] headers = new String[assignments.size() + 1];
      headers[0] = "Student";

      // Populate scores table
      for (int studentIndex = 0; studentIndex < students.size(); ++studentIndex) {
         Student student = students.get(studentIndex);
         student.getName();
         grades[studentIndex][0] = student.getName().toString();
         for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex) {
            grades[studentIndex][assignmentIndex + 1] =
                  Double.toString(
                        scores.getRawScore(student,
                              assignments.get(assignmentIndex)));
         }
      }

      // Populate column headers
      for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex) {
         headers[assignmentIndex + 1] = assignments.get(assignmentIndex).toString();
      }

      setupGradebook(headers, grades);
   }
}
