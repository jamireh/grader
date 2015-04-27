package grader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 * GradeSchemeController is the controller for the 'GradeScheme' menu option.
 * The GradeSchemeController connects the GUI and model components of the
 * GradeScheme.
 *
 * @author Tobias Bleisch
 */
public class GradeSchemeController
{
    @FXML Button bDone;

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
     * TODO - Write method to run through the existing GradeRanges and check
     * their validity (Overlap). Result comes back from onTextFieldEdited and
     * throw an exception if it's not correct.
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
