package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.Course;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main controller class for the Grader application.
 *
 * @author Gregory Davis
 */
public class MainController implements Initializable {

   @FXML VBox vbContainer;
   @FXML Node sidebar;
   @FXML Node spreadsheet;
   @FXML Node menubar;

   @FXML SidebarController sidebarController;
   @FXML SpreadsheetController spreadsheetController;
   @FXML StatsController statsController;
   @FXML MenubarController menubarController;


   static Course course;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      sidebar.setVisible(true);
      vbContainer.setVisible(true);
      spreadsheet.setVisible(true);
      course = WorkSpace.instance.getCourse();
      StatsController stats = new StatsController();
   }
   /**
    * Controller method for Revert Grades.
    *
    * @param event action event
    */
   public void onRevertButtonClicked(ActionEvent event) {
      System.out.println("Revert button clicked");
   }

   /**
    * Controller method for Save Grades.
    *
    * @param event action event
    */
   public void onSaveButtonClicked(ActionEvent event) {
      System.out.println("Save button clicked");
   }

   /**
    * Controller method for Save and Post Grades.
    *
    * @param event action event
    */
   public void onSavePostButtonClicked(ActionEvent event) {
      System.out.println("Save & Post button clicked");
   }

}
