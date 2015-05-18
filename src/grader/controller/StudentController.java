package grader.controller;

import grader.model.errors.InvalidPhoneNumberException;
import grader.model.errors.InvalidUserIDException;
import grader.model.errors.NameFormatException;
import grader.model.file.WorkSpace;
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
    @FXML TextField tfUserID;
    @FXML TextField tfP1;
    @FXML TextField tfP2;
    @FXML TextField tfP3;

    /**
     * Verifies through the model that the student is valid.
     *
     * @param event hitting the add student button.
     */
    public void onAddPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());

        try
        {
            WorkSpace.instance.getSection().addStudent(new Student(new Name(tfFirst.getText(), tfMiddle.getText(),
                    tfLast.getText()), tfUserID.getText(), tfP1.getText() + tfP2.getText() + tfP3.getText()));
        }
        catch (NameFormatException e)
        {
            tfFirst.requestFocus();
            System.out.println("Name exception, try again.");
            return;
        }
        catch (InvalidUserIDException e)
        {
            tfUserID.requestFocus();
            System.out.println("UserID exception, try again.");
            return;
        }
        catch (InvalidPhoneNumberException e)
        {
            tfP1.requestFocus();
            tfP1.setText("");
            tfP2.setText("");
            tfP3.setText("");
            System.out.println("Invalid Phone Number exception, try again");
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
