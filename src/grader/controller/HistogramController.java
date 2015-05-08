package grader.controller;

/**
 * @author Mallika Potter
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.awt.Button;
import java.net.URL;
import java.util.*;

import grader.model.file.*;
import grader.model.people.*;
import grader.model.gradebook.*;
import grader.model.items.*;
import grader.model.curve.Entry;


public class HistogramController implements Initializable {

    //private boolean smallData = true;

    private Section section = new Section();

    private Hashtable<Double, Integer> vals = new Hashtable<Double, Integer>();


    @FXML
    ObservableList<grader.model.curve.Entry> data = FXCollections.observableArrayList();


    @FXML
    private TableView<grader.model.curve.Entry> table = new TableView<grader.model.curve.Entry>();

    @FXML private TableColumn letterCol;
    @FXML private TableColumn percentCol;
    @FXML private TableColumn starCol;

    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private javafx.scene.control.Button saveButton;
    @FXML private javafx.scene.control.Button updateButton;


    @FXML
    private void saveButtonAction(ActionEvent event)
    {
        section.pushGradeScheme();
    }

    @FXML
    private void updateButtonAction(ActionEvent event)
    {
        section.applyHistogram();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event)
    {
        System.out.println("Cancel Button Selected.");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //WorkSpace.instance.addObserver(this);

        grader.model.curve.Histogram hist = WorkSpace.instance.getHistogram();

        for (int i = 100; i >= 0; i--) {
            data.add(hist.getEntry((double) i));
        }

        letterCol.setMinWidth(100);
        percentCol.setMinWidth(100);
        starCol.setMinWidth(200);
        table.setItems(data);

        /*table.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (smallData)
                {
                    table.setItems(dataLarge);
                    smallData = false;
                }
                else
                {
                    table.setItems(data);
                    smallData = true;
                }
            }
        });*/
    }

    public static class Entry {

        private final SimpleStringProperty letter;
        public final SimpleStringProperty percent;
        public final SimpleStringProperty star;

        public Entry(String let, String per, String st)
        {
            letter = new SimpleStringProperty(let);
            percent = new SimpleStringProperty(per);
            star = new SimpleStringProperty(st);
        }

        public String getLetter()
        {
            return letter.get();
        }

        public String getPercent()
        {
            return percent.get();
        }

        public String getStar()
        {
            return star.get();
        }
    }







}
