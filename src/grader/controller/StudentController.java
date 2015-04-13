package grader.controller;

import grader.model.people.Name;
import grader.model.people.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controls the Add Student manually view.
 *
 * @author Connor Batch
 */
public class StudentController
{
    @FXML TextField tfFirst;
    @FXML TextField tfMiddle;
    @FXML TextField tfLast;
    @FXML TextField tfNickname;
    @FXML TextField tfP1;
    @FXML TextField tfP2;
    @FXML TextField tfP3;

    public void onAddPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());

        MainController.course.sections.get(0).addStudent(new Student(new Name(tfFirst.getText(), tfMiddle.getText(),
                tfLast.getText(), tfNickname.getText()), tfP1.getText() + tfP2.getText() + tfP3.getText()));

        stage.close();
    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Cancel Pressed");
        stage.close();
    }
}
