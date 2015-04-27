package grader.controller;

import grader.model.edit.Find;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the 'Find' dialog.
 *
 * @author Gregory Davis
 */
public class FindController implements Initializable {
    @FXML ComboBox findType;
    @FXML ComboBox courseBox;
    @FXML TextField nameField;
    @FXML Button findButton;
    @FXML Button cancelButton;

    static Find find;

    /**
     * Constructor.
     */
    public FindController() {
        find = new Find();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> findOptions =
                FXCollections.observableArrayList(
                        "Student",
                        "Assignment"
                );
        findType.setValue(findOptions.get(0));
        findType.setItems(findOptions);

        ObservableList<String> courseOptions =
                FXCollections.observableArrayList(
                        "All",
                        "CPE 309"
                );
        courseBox.setValue(courseOptions.get(0));
        courseBox.setItems(courseOptions);
    }

    /**
     * Handles find button press.
     * @param actionEvent action event.
     */
    public void onFindButtonPress(ActionEvent actionEvent)
    {
        String courseName = (String) courseBox.getValue();
        String name = nameField.getText();
        if (findType.getValue().equals("Student")) {
            find.findStudents(name, courseName);
        } else {
            find.findAssignments(name, courseName);
        }
        Stage stage = (Stage) findButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles cancel button press.
     * @param actionEvent action event.
     */
    public void onCancelButtonPress(ActionEvent actionEvent)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
