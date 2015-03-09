package grader.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentController
{
    @FXML TextField tfFirst;
    @FXML TextField tfMiddle;
    @FXML TextField tfLast;
    @FXML TextField tfNickname;
    @FXML TextField tfP1;
    @FXML TextField tfP2;
    @FXML TextField tfP3;

    public void onAddPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Add Pressed");
        System.out.println("First: " + tfFirst.getText());
        System.out.println("Middle: " + tfMiddle.getText());
        System.out.println("Last: " + tfLast.getText());
        System.out.println("Nickname: " + tfNickname.getText());
        System.out.println("Phone number: " + tfP1.getText() + "-" + tfP2.getText() + "-" + tfP3.getText());
        stage.close();
    }

    public void onCancelPressed(ActionEvent event)
    {
        Stage stage = ((Stage) ((Node) event.getSource()).getScene().getWindow());
        System.out.println("Cancel Pressed");
        stage.close();
    }
}
