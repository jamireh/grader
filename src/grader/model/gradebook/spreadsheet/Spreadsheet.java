package grader.model.gradebook.spreadsheet;

import grader.model.StudentEntry;
import grader.model.file.WorkSpace;
import grader.model.gradebook.Scores;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.items.Category;
import grader.model.people.Student;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

import java.util.*;

/**
 * The Spreadsheet class represents the underlying model for the grade spreadsheet.
 *
 * @author Gregory Davis
 * @author Quan Tran
 */
public class Spreadsheet implements Observer {
   private AssignmentTree assignmentTree;
   private List<Student> students;
   private Scores scores;

   private List<StudentEntry> rows;

   /**
    * Builds and returns an ObservableList representation of the spreadsheet.
    * @return an ObservableList
    */
   public ObservableList<StudentEntry> getList() {
      return FXCollections.observableArrayList(rows);
   }

   /**
    * Renders the scores spreadsheet to the view.
    */
   public void render() {}

   /**
    * Updates the spreadsheet's context assignment tree, students, and scores.
    */
   public void update(Observable obj, Object args) {
      assignmentTree = WorkSpace.instance.getAssignmentTree();
      students = WorkSpace.instance.getStudents();
      scores = WorkSpace.instance.getScores();
      rows = new ArrayList<StudentEntry>();
      buildCannedData();
      // buildData();
      render();
   }

   /**
    * Builds and returns the column headers from the assignment tree.
    * @return a list of TableColumns for use by the TableView
    */
   private List<TableColumn> getColumns() {
      // this is all fake data for now

      // build categories list
      List<Category> categories = new ArrayList<Category>();
      Map<Category, List<Assignment>> assignments
      Category projects, quizzes, midterms;
      try {
         projects = new Category("Projects", "30", false);
         quizzes = new Category("Quizzes", "10", false);
         midterms = new Category("Midterms", "20", false);
         categories.add(projects);
         categories.add(quizzes);
         categories.add(midterms);
         categories.add(new Category("Final", "30", false));
         categories.add(new Category("Participation", "10", false));

         // build assignments map
         assignments = new HashMap<Category, List<Assignment>>();
         assignments.put(projects, new ArrayList<Assignment>());
         assignments.put(quizzes, new ArrayList<Assignment>());
         assignments.put(midterms, new ArrayList<Assignment>());
         assignments.get(projects).add(new Assignment("Project 1"));
         assignments.get(projects).add(new Assignment("Project 2"));
         assignments.get(quizzes).add(new Assignment("Quiz 1"));
         assignments.get(quizzes).add(new Assignment("Quiz 2"));
         assignments.get(midterms).add(new Assignment("Midterm 1"));
         assignments.get(midterms).add(new Assignment("Midterm 2"));

         // create column header names
         List<TableColumn> headers = new ArrayList<TableColumn>();
         // start headers
         headers.add(new TableColumn("Name"));
         headers.add(new TableColumn("Group"));
         // add all categories
         for (Category cat: categories) {
            TableColumn current = new TableColumn("" + cat);
            // add assignments
            if (assignments.containsKey(cat)) {
               for (Assignment ass : assignments.get(cat))
                  current.getColumns().add(new TableColumn("" + ass));
            }
            // add the column
            headers.add(current);
         }
         // end headers
         headers.add(new TableColumn("Total"));
         headers.add(new TableColumn("Letter Grade"));

         return headers;
      }
      catch (Exception e) {
         // whatever
      }

      return null;
   }

   /** TEST METHOD */
   private void buildCannedData() {
      rows.add(new StudentEntry("Clark, Brandon J.", "Team 1", 19, 18, 8, 13, 32, 30, 89, 1, 85, "B+"));
      rows.add(new StudentEntry("Dang, Carmen", "Team 2", 19, 20, 8, 12, 35, 50, 92, 4, 78, "C+"));
      rows.add(new StudentEntry("Fong, Vivian", "Team 3", 16, 15, 9, 12, 37, 46, 80, 2, 68, "D+"));
      rows.add(new StudentEntry("Garg, Nupur", "Team 4", 20, 16, 8, 17, 45, 35, 72, 2, 78, "C+"));
      rows.add(new StudentEntry("Hicks, Katelyn C.", "Team 5", 18, 22, 8, 14, 34, 36, 93, 2, 66, "D+"));
      rows.add(new StudentEntry("Hwang, Helen", "Team 1", 24, 16, 7, 10, 32, 39, 89, 5, 80, "B-"));
      rows.add(new StudentEntry("Joshi, Esha", "Team 2", 15, 19, 8, 19, 47, 32, 95, 4, 85, "B"));
      rows.add(new StudentEntry("Krier, Connor M.", "Team 3", 17, 24, 6, 10, 39, 30, 75, 2, 83, "B"));
      rows.add(new StudentEntry("Lee, Daniel H.", "Team 4", 25, 21, 6, 14, 30, 46, 95, 5, 74, "C"));
      rows.add(new StudentEntry("Lukens, Myra C.", "Team 5", 21, 20, 8, 19, 39, 39, 95, 1, 75, "C"));
      rows.add(new StudentEntry("Oelkers, Blaine P.", "Team 1", 21, 15, 7, 17, 37, 38, 73, 2, 77, "C+"));
      rows.add(new StudentEntry("Poole V, Frank", "Team 2", 20, 25, 9, 19, 30, 38, 93, 5, 78, "C+"));
      rows.add(new StudentEntry("Quezada, Brian J.", "Team 3", 16, 22, 6, 19, 48, 31, 93, 2, 63, "D-"));
      rows.add(new StudentEntry("Qureshi, Wasae Abdul", "Team 4", 25, 24, 9, 14, 48, 45, 80, 3, 78, "C+"));
      rows.add(new StudentEntry("Toy, Daniel L.", "Team 5", 24, 16, 7, 15, 39, 47, 77, 5, 85, "B"));
   }

   /**
    * Builds the underlying data model for the spreadsheet.
    */
   private void buildData() { }
}
