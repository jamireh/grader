package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.people.Group;
import grader.model.people.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.naming.InvalidNameException;

import java.util.List;

import static grader.model.file.WorkSpace.*;

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

    Group newGroup = new Group();

    /**
     * Initializes the groups.fxml with data from the model.
     */
    @FXML
    private void initialize()
    {
        ObservableList<Student> sectionRoster = FXCollections.observableArrayList(instance.getSection().getStudents());
        lRosterList.setItems(sectionRoster);
    }

    public void onAddPressed(ActionEvent event)
    {

    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Cancel Pressed");
        stage.close();
    }
}
