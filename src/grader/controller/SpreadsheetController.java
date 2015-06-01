package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.scores.RawScore;
import grader.model.gradebook.scores.Scores;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.items.Percentage;
import grader.model.people.Student;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
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
    private int totalGradeIndex;
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

        table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                //Check whether item is selected and set value of selected item to Label
                if(table.getSelectionModel().getSelectedItem() != null && WorkSpace.instance.section != null)
                {
                    TableView.TableViewSelectionModel selectionModel = table.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    if(!selectedCells.isEmpty())
                    {
                        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                        //System.out.println(WorkSpace.instance.getStudents().get(tablePosition.getRow()).name);
                        StringProperty val = (StringProperty) table.getColumns().get(0).getCellObservableValue(tablePosition
                                .getRow());
                        String[] split = val.get().split(", ");
                        for (Student s : WorkSpace.instance.getStudents())
                        {
                            if (s.name.getFirstName().equals(split[1]) && s.name.getLastName().equals(split[0]))
                            {
                                Platform.runLater(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        WorkSpace.instance.setSelectedStudent(s);
                                    }
                                });
                                break;
                            }
                        }
                    }
                }
            }
        });

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
           tc.setEditable(i != totalGradeIndex);
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
          if (i != 0 || i != totalGradeIndex) {
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

   public void update(Observable obs, Object args) {
       boolean ignoreMe = false;
       if(args != null)
       {
           Class[] toIgnore = ((Class[]) args);
           for(int i = 0; i < toIgnore.length; i++)
           {
               if(toIgnore[i] == getClass())
               {
                   ignoreMe = true;
                   break;
               }
           }
       }
       if(!ignoreMe)
       {
           List<Assignment> assignments = new ArrayList<Assignment>();
           AssignmentTree.AssignmentIterator itr =
                   WorkSpace.instance.getAssignmentTree().getAssignmentIterator();

           while (itr.hasNext())
           {
               Assignment assignment = itr.next();
               assignments.add(assignment);
           }

           List<Student> students = WorkSpace.instance.getStudents();
           Collections.sort(students);
           Scores scores = WorkSpace.instance.getScores();

           SpreadsheetCell[][] grades = new SpreadsheetCell[students.size()][assignments.size() + 2];
           String[] headers = new String[assignments.size() + 2];
           headers[0] = "Student";
            headers[assignments.size() + 1] = "Total Grade";
           // Populate scores table
           for (int studentIndex = 0; studentIndex < students.size(); ++studentIndex)
           {
               Student student = students.get(studentIndex);
               grades[studentIndex][0] = new SpreadsheetCell(student);
               for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex)
               {
                   grades[studentIndex][assignmentIndex + 1] =
                           new SpreadsheetCell(scores.getScoresMap(student).get(assignments.get(assignmentIndex)));
               }
               for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex)
               {
                   grades[studentIndex][assignments.size() + 1] =
                           new SpreadsheetCell(WorkSpace.instance.getAssignmentTree()
                                   .calculatePercentage(WorkSpace.instance.getScores()
                                           .getScoresMap(student)));
               }
           }
           totalGradeIndex = assignments.size() + 1;

           // Populate column headers
           for (int assignmentIndex = 0; assignmentIndex < assignments.size(); ++assignmentIndex)
           {
               headers[assignmentIndex + 1] = assignments.get(assignmentIndex).toString();
           }

           setupGradebook(headers, grades);
       }
   }

   /**
    * Spreadsheet TableView cell for holding either a Student or a RawScore.
    */
   private class SpreadsheetCell {
      public Student student;
      public RawScore rawScore;
      public boolean hasChanged;
       public Percentage percentage;

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

       public SpreadsheetCell(Percentage percentage) {
           this.rawScore = null;
           this.student = null;
           this.hasChanged = false;
           this.percentage = percentage;
       }

      public RawScore getScore() {
         return rawScore;
      }

      public String toString() {
         if (student != null) return student.toString();
         if (rawScore != null) return "" + rawScore.getScore();
          if(percentage != null) return Double.toString(percentage.getValue());
         return "";
      }
   }
}