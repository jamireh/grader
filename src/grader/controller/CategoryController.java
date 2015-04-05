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

public class CategoryController implements Initializable {
    @FXML ComboBox cbCatParent;
    @FXML TextField tfCatName;
    @FXML Button bAdd;
    @FXML ComboBox cbWeights;
    @FXML TextField tfWeight;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> parentOptions =
                FXCollections.observableArrayList(
                        "CPE 309",
                        "Tests",
                        "Projects",
                        "Quizzes",
                        "Midterms"
                );
        cbCatParent.setValue(parentOptions.get(0));
        cbCatParent.setItems(parentOptions);
        ObservableList<String> weightOptions =
                FXCollections.observableArrayList(
                        "Evenly within the category",
                        "According to raw scores"
                );
        cbWeights.setValue(weightOptions.get(0));
        cbWeights.setItems(weightOptions);

    }

    public void onAddButtonClick(ActionEvent actionEvent)
    {
        System.out.println("Name: " + tfCatName.getText());
        System.out.println("Parent: " + cbCatParent.getValue());
        System.out.println("Weight: " + tfWeight.getText());
        System.out.println("Weight Behavior: " + cbWeights.getValue());
        Stage stage = (Stage) bAdd.getScene().getWindow();
        stage.close();
    }
}
