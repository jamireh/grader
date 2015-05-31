package grader.controller;

import grader.model.errors.MissingInputException;
import grader.model.file.WorkSpace;
import grader.model.people.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.Comparator;

import static grader.model.file.WorkSpace.instance;

/**
 * Controller for the group forming view.
 *
 * @author Connor Batch
 */
public class EditGroupController
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

        tfGroupName.setText(WorkSpace.instance.getGroup().groupName);

        // sets up the current students in the formed group list
        for (Student s : WorkSpace.instance.getGroup().getStudents())
        {
            formedGroup.add(s);
            sectionRoster.remove(s);
        }
    }

    /**
     * filters the roster view
     * @param event
     */
    @FXML
    public void filterRoster(KeyEvent event)
    {

//        ArrayList<Student> filteredList = new ArrayList<Student>();
//        for (Student student : instance.getSection().getStudents())
//        {
//            if (student.name.toString().toLowerCase().contains(tfStudentSearch.getText().toLowerCase()) &&
//                    !formedGroup.contains(student))
//                filteredList.add(student);
//        }
//        sectionRoster = FXCollections.observableArrayList(filteredList);
//        formedGroup.sort(comparator);
//        sectionRoster.sort(comparator);
//        lRosterList.setItems(sectionRoster);
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
            WorkSpace.instance.getGroup().editGroupName(tfGroupName.getText());
            WorkSpace.instance.getGroup().setGroupMembers(new ArrayList<Student>(formedGroup));
        }
        catch (InvalidNameException e)
        {
            tfGroupName.requestFocus();
            return;
        }
        catch (MissingInputException e)
        {
            lFormedGroup.requestFocus();
            return;
        }
        WorkSpace.instance.sidebarSelect(WorkSpace.instance.course, WorkSpace.instance.section, WorkSpace.instance.getGroup());
        stage.close();

    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Cancel Pressed");
        stage.close();
    }
}
