package grader.controller;

import grader.model.errors.ScoreOutOfRangeException;
import grader.model.gradebook.WorkSpace;
import grader.model.gradebook.scores.Prediction;
import grader.model.gradebook.scores.RawScore;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Modality;

import java.text.DecimalFormat;

/**
 * The Controller class for grade predictions. Links to the Prediction
 * class in the model and prediction.fxml.
 *
 * @author Alexander Miller
 * @author Quan Tran
 */
public class PredictionController {
    @FXML ComboBox cbCatParent;
    @FXML Label pStudentName;
    @FXML Label pAssignment;
    @FXML Label pAssignmentGrade;
    @FXML Label pCurTotal;
    @FXML Label pRequired;
    @FXML TextField pTfNewTotal;

    // used to access the scores data
    private RawScore score;

    /**
     * Initializes the controller with required values.
     */
    public void initialize()
    {
        score = WorkSpace.instance.selectedScore;
        // set the default display label text
        DecimalFormat format = new DecimalFormat("0.0");
        pStudentName.setText(score.getStudent().toString());
        pAssignment.setText(score.getAssignment().toString());
        pAssignmentGrade.setText(format.format(score.getScore()));
        pCurTotal.setText(format.format(WorkSpace.instance.getAssignmentTree()
                .calculatePercentage(WorkSpace.instance.getScores()
                        .getScoresMap(score.getStudent())).getValue()));
        pRequired.setText("??");
    }

    /**
     * Updates the required score upon requesting a prediction.
     * Catches exceptions thrown by the model.
     */
    public void updateRequiredScore() {
        DecimalFormat format = new DecimalFormat("0.0");

        try {
            Prediction prediction = new Prediction(score.getStudent(), score.getAssignment(), Double.parseDouble(pTfNewTotal.getText()));
            pRequired.setText(format.format(prediction.getRequired()));
        }
        // catch an invalid input string
        catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid target score input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
        // catch a negative target score
        catch (ScoreOutOfRangeException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Target score is negative");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
    }
}
