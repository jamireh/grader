package grader.model.gradebook;

import grader.controller.MainController;
import grader.model.file.WorkSpace;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * The Spreadsheet class represents the underlying model for the grade spreadsheet.
 *
 * @author Gregory Davis
 */
public class Spreadsheet implements Observer {
   private List<Student> students;
   private List<Assignment> assignments;
   private Scores scores;
   private MainController controller;

   /**
     Sets the controller bound to this model
    * @param controller Controller to delegate to when the model changes
    */
   public void setController(MainController controller) {
       this.controller = controller;
   }

   /**
    * Renders the scores spreadsheet to the view.
    */
   public void render() {
       if (controller != null) {
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

           controller.setupGradebook(headers, grades);
       }
   }

   public void save() {
      WorkSpace.instance.saveGrades();
   }

   public void revert() {
      WorkSpace.instance.revertGrades();
   }

   public void updateGrade(Student student, Assignment assignment, double score) {
      WorkSpace.instance.updateGrade(student, assignment, score);
   }

   /**
    * Updates the spreadsheet's context assignment tree, students, and scores.
    */
   public void update(Observable obj, Object args) {
      assignments = new ArrayList<Assignment>();
      AssignmentTree.AssignmentIterator itr =
         WorkSpace.instance.getAssignmentTree().getAssignmentIterator();

      while (itr.hasNext()) {
         assignments.add(itr.next());
      }

      students = WorkSpace.instance.getStudents();
      scores = WorkSpace.instance.getScores();
      render();
   }
}