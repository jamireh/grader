package grader.controller;

import grader.model.errors.InvalidPhoneNumberException;
import grader.model.people.Name;
import grader.model.people.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import org.omg.CORBA.DynAnyPackage.Invalid;

import javax.naming.InvalidNameException;

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

        try
        {
            MainController.course.sections.get(0).addStudent(new Student(new Name(tfFirst.getText(), tfMiddle.getText(),
                    tfLast.getText(), tfNickname.getText()), tfP1.getText() + tfP2.getText() + tfP3.getText()));
        }
        catch (InvalidPhoneNumberException e)
        {
            tfP1.setText("");
            tfP2.setText("");
            tfP3.setText("");
            System.out.println("Invalid Phone Number exception, try again");
            return;
        }
        catch (InvalidNameException e)
        {
            System.out.println("Name exception, try again.");
            return;
        }
        stage.close();
    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Cancel Pressed");
        stage.close();
    }
}
