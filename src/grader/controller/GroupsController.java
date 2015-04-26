package grader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.naming.InvalidNameException;

/**
 * Controller for the group forming view.
 *
 * @author Connor Batch
 */
public class GroupsController
{
    @FXML TextField tfGroupName;
    @FXML TextField tfStudentSearch;
    @FXML ListView lRosterList;
    @FXML ListView lFormedGroup;
    @FXML Button bAddToGroup;
    @FXML Button bRemoveFromGroup;

    public void onAddStudent(ActionEvent event)
    {

    }

    public void onAddPressed(ActionEvent event)
    {
        // nothing
    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Cancel Pressed");
        stage.close();
    }
}
