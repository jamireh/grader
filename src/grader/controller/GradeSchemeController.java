package grader.controller;

import grader.model.errors.OverlappingRangeException;
import grader.model.errors.PercentageFormatException;
import grader.model.file.WorkSpace;
import grader.model.gradebook.GradeRange;
import grader.model.gradebook.GradeScheme;
import grader.model.gradebook.LetterGrade;
import grader.model.gradebook.Percentage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import java.util.Collection;
import java.util.List;

/**
 * GradeSchemeController is the controller for the 'GradeScheme' menu option.
 * The GradeSchemeController connects the GUI and model components of the
 * GradeScheme.
 *
 * @author Tobias Bleisch
 */
public class GradeSchemeController
{
    @FXML ColorPicker A_PLUS_COLOR;
    @FXML ColorPicker A_COLOR;
    @FXML ColorPicker A_MINUS_COLOR;
    @FXML ColorPicker B_PLUS_COLOR;
    @FXML ColorPicker B_COLOR;
    @FXML ColorPicker B_MINUS_COLOR;
    @FXML ColorPicker C_PLUS_COLOR;
    @FXML ColorPicker C_COLOR;
    @FXML ColorPicker C_MINUS_COLOR;
    @FXML ColorPicker D_PLUS_COLOR;
    @FXML ColorPicker D_COLOR;
    @FXML ColorPicker D_MINUS_COLOR;
    @FXML ColorPicker F_COLOR;

    @FXML TextField A_Plus_High;
    @FXML TextField A_Plus_Low;
    @FXML TextField A_High;
    @FXML TextField A_Low;
    @FXML TextField A_Minus_High;
    @FXML TextField A_Minus_Low;
    @FXML TextField B_Plus_High;
    @FXML TextField B_Plus_Low;
    @FXML TextField B_High;
    @FXML TextField B_Low;
    @FXML TextField B_Minus_High;
    @FXML TextField B_Minus_Low;
    @FXML TextField C_Plus_High;
    @FXML TextField C_Plus_Low;
    @FXML TextField C_High;
    @FXML TextField C_Low;
    @FXML TextField C_Minus_High;
    @FXML TextField C_Minus_Low;
    @FXML TextField D_Plus_High;
    @FXML TextField D_Plus_Low;
    @FXML TextField D_High;
    @FXML TextField D_Low;
    @FXML TextField D_Minus_High;
    @FXML TextField D_Minus_Low;
    @FXML TextField F_High;
    @FXML TextField F_Low;

    @FXML Button bDone;

    /**
     * The currently-active GradeScheme to be modified by this GradeScheme GUI menu.
     */
    GradeScheme gradeScheme;

    /**
     * Constructor to set the current GradeScheme of this Controller's fxml thread
     * from the WorkSpace.
     */
    public GradeSchemeController() {
        gradeScheme = WorkSpace.instance.getGradeScheme();
    }


    /**
     * Initializes the gradescheme.fxml with data from the model.
     */
    @FXML
    private void initialize() {
        List<GradeRange> ranges = gradeScheme.ranges;

        A_PLUS_COLOR.setValue(ranges.get(LetterGrade.A_PLUS.ordinal()).getColor());
        A_COLOR.setValue(ranges.get(LetterGrade.A.ordinal()).getColor());
        A_MINUS_COLOR.setValue(ranges.get(LetterGrade.A_MINUS.ordinal()).getColor());
        B_PLUS_COLOR.setValue(ranges.get(LetterGrade.B_PLUS.ordinal()).getColor());
        B_COLOR.setValue(ranges.get(LetterGrade.B.ordinal()).getColor());
        B_MINUS_COLOR.setValue(ranges.get(LetterGrade.B_MINUS.ordinal()).getColor());
        C_PLUS_COLOR.setValue(ranges.get(LetterGrade.C_PLUS.ordinal()).getColor());
        C_COLOR.setValue(ranges.get(LetterGrade.C.ordinal()).getColor());
        C_MINUS_COLOR.setValue(ranges.get(LetterGrade.C_MINUS.ordinal()).getColor());
        D_PLUS_COLOR.setValue(ranges.get(LetterGrade.D_PLUS.ordinal()).getColor());
        D_COLOR.setValue(ranges.get(LetterGrade.D.ordinal()).getColor());
        D_MINUS_COLOR.setValue(ranges.get(LetterGrade.D_MINUS.ordinal()).getColor());
        F_COLOR.setValue(ranges.get(LetterGrade.F.ordinal()).getColor());


        A_Plus_High.setText("100");
        A_Plus_High.setDisable(true);
        A_Plus_Low.setText(String.valueOf(ranges.get(LetterGrade.A_PLUS.ordinal()).getLowerBound().getValue()));
        A_High.setText(String.valueOf(ranges.get(LetterGrade.A_PLUS.ordinal()).getLowerBound().getValue()));
        A_Low.setText(String.valueOf(ranges.get(LetterGrade.A.ordinal()).getLowerBound().getValue()));
        A_Minus_High.setText(String.valueOf(ranges.get(LetterGrade.A.ordinal()).getLowerBound().getValue()));
        A_Minus_Low.setText(String.valueOf(ranges.get(LetterGrade.A_MINUS.ordinal()).getLowerBound().getValue()));
        B_Plus_High.setText(String.valueOf(ranges.get(LetterGrade.A_MINUS.ordinal()).getLowerBound().getValue()));
        B_Plus_Low.setText(String.valueOf(ranges.get(LetterGrade.B_PLUS.ordinal()).getLowerBound().getValue()));
        B_High.setText(String.valueOf(ranges.get(LetterGrade.B_PLUS.ordinal()).getLowerBound().getValue()));
        B_Low.setText(String.valueOf(ranges.get(LetterGrade.B.ordinal()).getLowerBound().getValue()));
        B_Minus_High.setText(String.valueOf(ranges.get(LetterGrade.B.ordinal()).getLowerBound().getValue()));
        B_Minus_Low.setText(String.valueOf(ranges.get(LetterGrade.B_MINUS.ordinal()).getLowerBound().getValue()));
        C_Plus_High.setText(String.valueOf(ranges.get(LetterGrade.B_MINUS.ordinal()).getLowerBound().getValue()));
        C_Plus_Low.setText(String.valueOf(ranges.get(LetterGrade.C_PLUS.ordinal()).getLowerBound().getValue()));
        C_High.setText(String.valueOf(ranges.get(LetterGrade.C_PLUS.ordinal()).getLowerBound().getValue()));
        C_Low.setText(String.valueOf(ranges.get(LetterGrade.C.ordinal()).getLowerBound().getValue()));
        C_Minus_High.setText(String.valueOf(ranges.get(LetterGrade.C.ordinal()).getLowerBound().getValue()));
        C_Minus_Low.setText(String.valueOf(ranges.get(LetterGrade.C_MINUS.ordinal()).getLowerBound().getValue()));
        D_Plus_High.setText(String.valueOf(ranges.get(LetterGrade.C_MINUS.ordinal()).getLowerBound().getValue()));
        D_Plus_Low.setText(String.valueOf(ranges.get(LetterGrade.D_PLUS.ordinal()).getLowerBound().getValue()));
        D_High.setText(String.valueOf(ranges.get(LetterGrade.D_PLUS.ordinal()).getLowerBound().getValue()));
        D_Low.setText(String.valueOf(ranges.get(LetterGrade.D.ordinal()).getLowerBound().getValue()));
        D_Minus_High.setText(String.valueOf(ranges.get(LetterGrade.D.ordinal()).getLowerBound().getValue()));
        D_Minus_Low.setText(String.valueOf(ranges.get(LetterGrade.D_MINUS.ordinal()).getLowerBound().getValue()));
        F_High.setText(String.valueOf(ranges.get(LetterGrade.D_MINUS.ordinal()).getLowerBound().getValue()));
        F_Low.setText("0");
        F_Low.setDisable(true);
}

    /**
     * Closes the GradeScheme menu GUI upon click of the "Done" button.
     * @param event The ActionEvent that kicks off this method.
     */
    public void onDoneButtonClicked(ActionEvent event) {
        System.out.println("Done button clicked");
        Stage stage = (Stage) bDone.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the LowerBound of the chosen GradeRange under the GradeScheme of the
     * currently-selected Section. Appropriate error messages are displayed for
     * incorrect input or overlapping ranges.
     * @param event The ActionEvent that kicks off this method.
     */
    public void onTextFieldEdited(ActionEvent event) {
        try {
            TextField textField = (TextField) event.getSource();
            Percentage percent = new Percentage(textField.getCharacters().toString());
            gradeScheme.updateGradeRange(LetterGrade.valueOfFromID(textField.getId()), percent);

            System.out.println("Updated information: " + textField.getCharacters());
        }
        catch (PercentageFormatException percentExcept) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText("Please enter a percentage in the range between 0%-100%");
            alert.showAndWait();
        }
        catch (OverlappingRangeException overlapExcept) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Overlapping Ranges");
            alert.setContentText("Please ensure that " + overlapExcept.getOverlapper() +
                    " does not overlap with " + overlapExcept.getOverlapped());
            alert.showAndWait();

        }
        catch (NullPointerException except) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("No Currently Open Courses");
            alert.setContentText("Please first select a course to modify");
            alert.showAndWait();
        }

        initialize();
    }

    /**
     * Updates the Color of the chosen GradeRange under the GradeScheme of the
     * currently-selected Section.
     * @param event The ActionEvent that kicks off this method.
     */
    public void onColorChosen(ActionEvent event) {
        ColorPicker cp = (ColorPicker) event.getSource();
        Color color = cp.getValue();
        gradeScheme.updateGradeRange(LetterGrade.valueOfFromID(cp.getId()), color);

        System.out.println("Color updated: " + ((ColorPicker)event.getSource()).getValue());
    }
}
