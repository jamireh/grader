package grader.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML TextField tfCourseNum;
    @FXML ComboBox cbDept;
    @FXML Button bAdd;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> parentOptions =
                FXCollections.observableArrayList(
                        "CPE"
                );
        cbDept.setValue(parentOptions.get(0));
        cbDept.setItems(parentOptions);

    }

    public void onAddButtonClick(ActionEvent actionEvent)
    {
        System.out.println("Department: " + cbDept.getValue());
        System.out.println("Course Number: " + tfCourseNum.getText());
        Stage stage = (Stage) bAdd.getScene().getWindow();
        stage.close();
    }
}
