package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.scores.Prediction;
import grader.model.gradebook.scores.RawScore;
import grader.model.items.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class PredictionController {
    @FXML ComboBox cbCatParent;
    @FXML Label pStudentName;
    @FXML Label pAssignment;
    @FXML Label pAssignmentGrade;
    @FXML Label pCurTotal;
    @FXML Label pRequired;
    @FXML TextField pTfNewTotal;

    private RawScore score;
    public void initialize()
    {
        ObservableList<String> parentOptions = FXCollections.observableArrayList();
        parentOptions.add(WorkSpace.instance.getCourse().name);
        for(Category c : WorkSpace.instance.getCourse().categories)
        {
            parentOptions.add(c.name);
        }

        score = WorkSpace.instance.selectedScore;
        // whatever display method
        DecimalFormat format = new DecimalFormat("0.0");
        pStudentName.setText(score.getStudent().toString());
        pAssignment.setText(score.getAssignment().toString());
        pAssignmentGrade.setText(format.format(score.getScore()));

        pCurTotal.setText(format.format(WorkSpace.instance.getAssignmentTree()
                .calculatePercentage(WorkSpace.instance.getScores()
                        .getScoresMap(score.getStudent())).getValue()));
        pRequired.setText("??");

    }

    public void updateRequiredScore() {

        DecimalFormat format = new DecimalFormat("0.0");
        try {
            Prediction prediction = new Prediction(score.getStudent(), score.getAssignment(), Double.parseDouble(pTfNewTotal.getText()));
            pRequired.setText(format.format(prediction.getRequired()));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }


    }
}
