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

    public void onDoneButtonClicked(ActionEvent event)
    {
        System.out.println("Done button clicked");
        Stage stage = (Stage) bDone.getScene().getWindow();
        stage.close();
    }

    public void onTextFieldEdited(ActionEvent event)
    {
        //MainController.course.sections.get(0).gradeScheme.updateDivisions();
        TextField textField = (TextField) event.getSource();
        System.out.println("Updated information: " + textField.getCharacters());
    }

    public void onColorChosen(ActionEvent event)
    {
        System.out.println("Color updated: " + ((ColorPicker)event.getSource()).getValue());
    }
}
