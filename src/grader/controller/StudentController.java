package grader.controller;

import grader.model.errors.InvalidPhoneNumberException;
import grader.model.errors.InvalidUserIDException;
import grader.model.errors.NameFormatException;
import grader.model.gradebook.WorkSpace;
import grader.model.people.Name;
import grader.model.people.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
            /*WorkSpace.instance.getSection().addStudent(new Student(new Name(tfFirst.getText(), tfMiddle.getText(),
                    tfLast.getText()), tfUserID.getText(), tfP1.getText() + tfP2.getText() + tfP3.getText()));*/
            WorkSpace.instance.addStudent(new Student(new Name(tfFirst.getText(), tfMiddle.getText(),
                    tfLast.getText()), tfUserID.getText(), tfP1.getText() + tfP2.getText() + tfP3.getText()));
        }
        catch (NameFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            tfFirst.requestFocus();
            return;
        }
        catch (InvalidUserIDException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            tfUserID.requestFocus();
            return;
        }
        catch (InvalidPhoneNumberException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            tfP1.requestFocus();
            return;
        }
        WorkSpace.instance.update();
        stage.close();
    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.close();
    }
}
