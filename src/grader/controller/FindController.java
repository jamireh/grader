package grader.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FindController implements Initializable {
    @FXML ComboBox findType;
    @FXML ComboBox courseBox;
    @FXML Button findButton;
    @FXML Button cancelButton;

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
                        "CPE 308",
                        "CPE 309"
                );
        courseBox.setValue(courseOptions.get(0));
        courseBox.setItems(courseOptions);
    }

    public void onFindButtonPress(ActionEvent actionEvent)
    {
        Stage stage = (Stage) findButton.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonPress(ActionEvent actionEvent)
    {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
