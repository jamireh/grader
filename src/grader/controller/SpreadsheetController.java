package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.scores.RawScore;
import grader.model.gradebook.scores.Scores;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
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

    static TableView<SpreadsheetCell[]> table = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
       table = new TableView<SpreadsheetCell[]>();
       hbTable.setSpacing(5);
       hbTable.getChildren().addAll(table);

       table.setEditable(true);
       table.setMinWidth(1200);
       table.setMaxWidth(1200);
       table.setMaxHeight(600);

       WorkSpace.instance.addObserver(this);
       update(null, null);
    }

    public void setupGradebook(String[] headers, SpreadsheetCell[][] grades)
    {
       table.setEditable(true);
       table.getColumns().clear();

       for (int i = 0; i < headers.length; i++) {
          TableColumn tc = new TableColumn(headers[i]);
          tc.setEditable(i != 0);
          final int colNo = i;
          tc.setCellFactory(TextFieldTableCell.<SpreadsheetCell[]>forTableColumn());

          tc.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<SpreadsheetCell[], String>,
                      ObservableValue<String>>() {
                   @Override
                   public ObservableValue<String> call(
                         TableColumn.CellDataFeatures<SpreadsheetCell[], String> p) {
                      return new SimpleStringProperty(((p.getValue()[colNo].toString())));
                   }
                });
          tc.setPrefWidth(90);
          table.getColumns().add(tc);

          // Make score cell columns editable.
          if (i != 0) {
             tc.setOnEditCommit(
                   new EventHandler<TableColumn.CellEditEvent<SpreadsheetCell[], String>>() {
                      @Override
                      public void handle(TableColumn.CellEditEvent<SpreadsheetCell[], String> t) {
                         int row = t.getTablePosition().getRow();
                         int col = t.getTablePosition().getColumn();
                         ObservableList<SpreadsheetCell[]> ol = t.getTableView().getItems();
                         ol.get(row)[col].hasChanged = true;
                         RawScore rawScore = ol.get(row)[col].getScore();
                         try
                         {
                            WorkSpace.instance.updateGrade(
                                    rawScore.getStudent(),
                                    rawScore.getAssignment(),
                                    Double.parseDouble(t.getNewValue()));
                         } catch (Exception e) {
                            update(null, null);
                         }
                      }
                   }
             );
          }
       }

       ObservableList<SpreadsheetCell[]> data = FXCollections.observableArrayList();
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
      Collections.sort(students);
      Scores scores = WorkSpace.instance.getScores();

      SpreadsheetCell[][] grades = new SpreadsheetCell[students.size()][assignments.size() + 1];
      String[] headers = new String[assignments.size() + 1];
      headers[0] = "Student";

      // Populate scores table
      for (int studentIndex = 0; studentIndex < students.size(); ++studentIndex) {
         Student student = students.get(studentIndex);
         grades[studentIndex][0] = new SpreadsheetCell(student);
         for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex) {
            grades[studentIndex][assignmentIndex + 1] =
                  new SpreadsheetCell(scores.getScoresMap(student).get(assignments.get(assignmentIndex)));
         }
      }

      // Populate column headers
      for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex) {
         headers[assignmentIndex + 1] = assignments.get(assignmentIndex).toString();
      }

      setupGradebook(headers, grades);
   }

   /**
    * Spreadsheet TableView cell for holding either a Student or a RawScore.
    */
   private class SpreadsheetCell {
      public Student student;
      public RawScore rawScore;
      public boolean hasChanged;

      public SpreadsheetCell(Student student) {
         this.student = student;
         this.rawScore = null;
         this.hasChanged = false;
      }

      public SpreadsheetCell(RawScore rawScore) {
         this.rawScore = rawScore;
         this.student = null;
         this.hasChanged = false;
      }

      public RawScore getScore() {
         return rawScore;
      }

      public String toString() {
         if (student != null) return student.toString();
         if (rawScore != null) return "" + rawScore.getScore();
         return "";
      }
   }
}