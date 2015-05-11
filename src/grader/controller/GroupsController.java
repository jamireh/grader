package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.people.Group;
import grader.model.people.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.naming.InvalidNameException;

import java.util.ArrayList;
import java.util.Comparator;
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

    ObservableList<Student> sectionRoster;
    ObservableList<Student> formedGroup;

    Comparator<Student> comparator;
    /**
     * Initializes the groups.fxml with data from the model.
     */
    @FXML
    private void initialize()
    {
        sectionRoster = FXCollections.observableArrayList(instance.getSection().getStudents());
        formedGroup = FXCollections.observableArrayList(new ArrayList<Student>());
        lRosterList.setItems(sectionRoster);
        lFormedGroup.setItems(formedGroup);
        comparator = new Comparator<Student>()
        {
            @Override
            public int compare(Student o1, Student o2)
            {
                return o1.compareTo(o2);
            }
        };
    }

    @FXML
    public void addToGroup(MouseEvent click)
    {
        if (click.getClickCount() == 2)
        {
            formedGroup.add((Student)lRosterList.getSelectionModel().getSelectedItem());
            sectionRoster.remove((Student)lRosterList.getSelectionModel().getSelectedItem());
        }
        formedGroup.sort(comparator);
        sectionRoster.sort(comparator);
    }

    @FXML
    public void removeFromGroup(MouseEvent click)
    {
        if (click.getClickCount() == 2)
        {
            sectionRoster.add((Student) lFormedGroup.getSelectionModel().getSelectedItem());
            formedGroup.remove((Student) lFormedGroup.getSelectionModel().getSelectedItem());
        }
        formedGroup.sort(comparator);
        sectionRoster.sort(comparator);
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
