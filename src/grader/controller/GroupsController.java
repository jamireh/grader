package grader.controller;

import grader.model.errors.MissingInputException;
import grader.model.gradebook.WorkSpace;
import grader.model.people.Group;
import grader.model.people.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.*;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.naming.InvalidNameException;

import java.util.ArrayList;
import java.util.Comparator;

import static grader.model.gradebook.WorkSpace.*;

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

    /**
     * filters the roster view
     * @param event
     */
    @FXML
    public void filterRoster(KeyEvent event)
    {

        ArrayList<Student> filteredList = new ArrayList<Student>();
        for (Student student : instance.getSection().getStudents())
        {
            if (student.name.toString().toLowerCase().contains(tfStudentSearch.getText().toLowerCase()) &&
                    !formedGroup.contains(student))
                filteredList.add(student);
        }
        sectionRoster = FXCollections.observableArrayList(filteredList);
        formedGroup.sort(comparator);
        sectionRoster.sort(comparator);
        lRosterList.setItems(sectionRoster);
    }

    @FXML
    public void addToGroup(MouseEvent click)
    {
        if (click.getClickCount() == 2 && lRosterList.getSelectionModel().getSelectedItem() != null)
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
        if (click.getClickCount() == 2 && lFormedGroup.getSelectionModel().getSelectedItem() != null)
        {
            sectionRoster.add((Student) lFormedGroup.getSelectionModel().getSelectedItem());
            formedGroup.remove((Student) lFormedGroup.getSelectionModel().getSelectedItem());
        }
        formedGroup.sort(comparator);
        sectionRoster.sort(comparator);
    }

    public void onAddPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        try
        {
            WorkSpace.instance.addGroup(new Group(tfGroupName.getText(), new ArrayList<Student>(formedGroup)));
        }
        catch (InvalidNameException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            tfGroupName.requestFocus();
            return;
        }
        catch (MissingInputException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
            lFormedGroup.requestFocus();
            return;
        }
        stage.close();

    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        stage.close();
    }
}
