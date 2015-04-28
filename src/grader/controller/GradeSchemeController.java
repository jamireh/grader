package grader.controller;

import grader.model.gradebook.GradeRange;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public TextField A_Plus_High;
    public TextField A_Plus_Low;
    public TextField A_High;
    public TextField A_Low;
    public TextField A_Minus_High;
    public TextField A_Minus_Low;
    public TextField B_Plus_High;
    public TextField B_Plus_Low;
    public TextField B_High;
    public TextField B_Low;
    public TextField B_Minus_High;
    public TextField B_Minus_Low;
    public TextField C_Plus_High;
    public TextField C_Plus_Low;
    public TextField C_High;
    public TextField C_Low;
    public TextField C_Minus_High;
    public TextField C_Minus_Low;
    public TextField D_Plus_High;
    public TextField D_Plus_Low;
    public TextField D_High;
    public TextField D_Low;
    public TextField D_Minus_High;
    public TextField D_Minus_Low;
    public TextField F_High;
    public TextField F_Low;
    @FXML Button bDone;

    @FXML
    private void initialize() {
        //List<GradeRange> ranges = MainController.course.sections.get(0).gradeScheme.ranges;
        A_Plus_High.setText("100");
        A_Plus_High.setDisable(true);
        A_Plus_Low.setText("97.0");
        A_High.setText("97.0");
        A_Low.setText("93.0");
        A_Minus_High.setText("93.0");
        A_Minus_Low.setText("90.0");
        B_Plus_High.setText("90.0");
        B_Plus_Low.setText("87.0");
        B_High.setText("87.0");
        B_Low.setText("83.0");
        B_Minus_High.setText("83.0");
        B_Minus_Low.setText("80.0");
        C_Plus_High.setText("80.0");
        C_Plus_Low.setText("77.0");
        C_High.setText("77.0");
        C_Low.setText("73.0");
        C_Minus_High.setText("73.0");
        C_Minus_Low.setText("70.0");
        D_Plus_High.setText("70.0");
        D_Plus_Low.setText("67.0");
        D_High.setText("67.0");
        D_Low.setText("63.0");
        D_Minus_High.setText("63.0");
        D_Minus_Low.setText("60.0");
        F_High.setText("60.0");
        F_Low.setText("0");
        F_Low.setDisable(true);
}

    /**
     * Closes the GradeScheme menu GUI upon click of the "Done" button.
     * @param event The ActionEvent that kicks off this method.
     */
    public void onDoneButtonClicked(ActionEvent event)
    {
        System.out.println("Done button clicked");
        Stage stage = (Stage) bDone.getScene().getWindow();
        stage.close();
    }



    /**
     * TODO - Once the prototype is back up to creating concrete Sections, display data from the model in the view
     */

    /**
     * Updates the divisions of the GradeScheme for the chosen Section.
     * @param event The ActionEvent that kicks off this method.
     */
    public void onTextFieldEdited(ActionEvent event)
    {
        try {
            MainController.course.sections.get(0).gradeScheme.updateDivisions();
            TextField textField = (TextField) event.getSource();
            System.out.println("Updated information: " + textField.getCharacters());
        }
        catch (NullPointerException except) {
            System.out.println("Please create a new course first...");
        }
    }

    /**
     * Updates the Color of the chosen division for the chosen Section.
     * @param event The ActionEvent that kicks off this method.
     */
    public void onColorChosen(ActionEvent event)
    {
        System.out.println("Color updated: " + ((ColorPicker)event.getSource()).getValue());
    }
}
