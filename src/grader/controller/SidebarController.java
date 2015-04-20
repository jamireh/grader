package grader.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class SidebarController implements Initializable
{
    @FXML Accordion aCourses;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        aCourses.getPanes().add(new TitledPane("CPE 309", new ListView<String>(FXCollections.observableArrayList(Arrays.asList("Section 1")))));
    }
}
