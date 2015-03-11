package grader.controller;

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
import java.util.ResourceBundle;

public class HistogramController implements Initializable{

    private boolean smallData = true;

    @FXML
    ObservableList<Entry> data = FXCollections.observableArrayList(
            new Entry("A+", "99", " "),
            new Entry("A", "94", " "),
            new Entry("A-", "90", " "),
            new Entry("B+", "87", " "),
            new Entry(" ", "85", "* * *"),
            new Entry(" ", "83", "*"),
            new Entry("B-", "80", "*"),
            new Entry(" ", "78", "* * * *"),
            new Entry("C+", "77", "*"),
            new Entry(" ", "75", "*"),
            new Entry("C", "74", "*"),
            new Entry("C-", "70", " "),
            new Entry(" ", "68", "*"),
            new Entry("D+", "67", " "),
            new Entry(" ", "66", "*"),
            new Entry("D", "64", " "),
            new Entry(" ", "63", "*")
    );

    @FXML
    ObservableList<Entry> dataLarge = FXCollections.observableArrayList(
            new Entry("A+", "99", " "),
            new Entry(" ", "98", " "),
            new Entry(" ", "97", " "),
            new Entry(" ", "96", " "),
            new Entry(" ", "95", " "),
            new Entry("A", "94", " "),
            new Entry(" ", "93", " "),
            new Entry(" ", "92", " "),
            new Entry(" ", "91", " "),
            new Entry("A-", "90", " "),
            new Entry(" ", "89", " "),
            new Entry(" ", "88", " "),
            new Entry("B+", "87", " "),
            new Entry(" ", "86", " "),
            new Entry(" ", "85", "* * *"),
            new Entry(" ", "84", " "),
            new Entry(" ", "83", "*"),
            new Entry(" ", "82", " "),
            new Entry(" ", "81", " "),
            new Entry("B-", "80", "*"),
            new Entry(" ", "79", " "),
            new Entry(" ", "78", "* * * *"),
            new Entry("C+", "77", "*"),
            new Entry(" ", "76", " "),
            new Entry(" ", "75", "*"),
            new Entry("C", "74", "*"),
            new Entry(" ", "73", " "),
            new Entry(" ", "72", " "),
            new Entry(" ", "71", " "),
            new Entry("C-", "70", " "),
            new Entry(" ", "69", " "),
            new Entry(" ", "68", "*"),
            new Entry("D+", "67", " "),
            new Entry(" ", "66", "*"),
            new Entry(" ", "65", " "),
            new Entry("D", "64", " "),
            new Entry(" ", "63", "*"),
            new Entry(" ", "62", " "),
            new Entry(" ", "61", " "),
            new Entry(" ", "60", " "),
            new Entry(" ", "59", " "),
            new Entry(" ", "58", " "),
            new Entry(" ", "57", " "),
            new Entry(" ", "56", " "),
            new Entry(" ", "55", " "),
            new Entry(" ", "54", " "),
            new Entry(" ", "53", " "),
            new Entry(" ", "52", " "),
            new Entry(" ", "51", " "),
            new Entry(" ", "50", " "),
            new Entry(" ", "49", " "),
            new Entry(" ", "48", " "),
            new Entry(" ", "47", " "),
            new Entry(" ", "46", " "),
            new Entry(" ", "45", " "),
            new Entry(" ", "44", " "),
            new Entry(" ", "43", " "),
            new Entry(" ", "42", " "),
            new Entry(" ", "41", " "),
            new Entry(" ", "40", " "),
            new Entry(" ", "39", " "),
            new Entry(" ", "38", " "),
            new Entry(" ", "37", " "),
            new Entry(" ", "36", " "),
            new Entry(" ", "35", " "),
            new Entry(" ", "34", " "),
            new Entry(" ", "33", " "),
            new Entry(" ", "32", " "),
            new Entry(" ", "31", " "),
            new Entry(" ", "30", " "),
            new Entry(" ", "29", " "),
            new Entry(" ", "28", " "),
            new Entry(" ", "27", " "),
            new Entry(" ", "26", " "),
            new Entry(" ", "25", " "),
            new Entry(" ", "24", " "),
            new Entry(" ", "23", " "),
            new Entry(" ", "22", " "),
            new Entry(" ", "21", " "),
            new Entry(" ", "20", " "),
            new Entry(" ", "19", " "),
            new Entry(" ", "18", " "),
            new Entry(" ", "17", " "),
            new Entry(" ", "16", " "),
            new Entry(" ", "15", " "),
            new Entry(" ", "14", " "),
            new Entry(" ", "13", " "),
            new Entry(" ", "12", " "),
            new Entry(" ", "11", " "),
            new Entry(" ", "10", " "),
            new Entry(" ", "9", " "),
            new Entry(" ", "8", " "),
            new Entry(" ", "7", " "),
            new Entry(" ", "6", " "),
            new Entry(" ", "5", " "),
            new Entry(" ", "4", " "),
            new Entry(" ", "3", " "),
            new Entry(" ", "2", " "),
            new Entry(" ", "1", " "),
            new Entry("F", "0", " ")
    );


    @FXML
    private TableView<Entry> table = new TableView<Entry>();

    @FXML private TableColumn letterCol;
    @FXML private TableColumn percentCol;
    @FXML private TableColumn starCol;

    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private javafx.scene.control.Button saveButton;
    @FXML private javafx.scene.control.Button updateButton;


    @FXML
    private void saveButtonAction(ActionEvent event)
    {
        System.out.println("Save Button Selected.");
    }

    @FXML
    private void updateButtonAction(ActionEvent event)
    {
        System.out.println("Update Button Selected.");
    }

    @FXML
    private void cancelButtonAction(ActionEvent event)
    {
        System.out.println("Cancel Button Selected.");
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        letterCol.setMinWidth(100);
        percentCol.setMinWidth(100);
        starCol.setMinWidth(200);
        table.setItems(this.data);

        table.setOnMousePressed(new EventHandler<MouseEvent>() {
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
        });
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
