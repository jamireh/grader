package grader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GradeSchemeController
{
    @FXML Button bDone;

    /**
     * Closes the GradeScheme menu GUI upon click of the "Done" button.
     * @param event The ActionEvent that kicks off this mehtod.
     */
    public void onDoneButtonClicked(ActionEvent event)
    {
        System.out.println("Done button clicked");
        Stage stage = (Stage) bDone.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the divisions of the GradeScheme for the chosen Section.
     * @param event The ActionEvent that kicks off this mehtod.
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
