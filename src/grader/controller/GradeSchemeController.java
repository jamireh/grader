package grader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GradeSchemeController
{
    @FXML Button bDone;

    public void onDoneButtonClicked(ActionEvent event)
    {
        System.out.println("Done button clicked");
        Stage stage = (Stage) bDone.getScene().getWindow();
        stage.close();
    }
}
