package grader.controller;

/**
 * @author Mallika Potter
 */

import grader.model.gradebook.Section;
import grader.model.gradebook.scores.RawScore;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.*;

import grader.model.file.*;
import grader.model.people.*;
import grader.model.items.*;


public class HistogramController implements Initializable, Observer {

    //private boolean smallData = true;

    private Section section = new Section();

    private Hashtable<Double, Integer> vals = new Hashtable<Double, Integer>();

    /*@FXML
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
    );*/

    @FXML
    ObservableList<Entry> data = FXCollections.observableArrayList();


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
        WorkSpace.instance.addObserver(this);

        for (int i = 0; i <= 100; i++)
        {
            vals.put(new Double(i), 0);
        }

        java.util.List<Student> students = WorkSpace.instance.getStudents();

        for (Student s : students)
        {
            HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
            Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);

            double tempPercent = Math.ceil(percent.getValue());
            Integer temp = vals.get(tempPercent);
            vals.replace(tempPercent, ++temp);

        }

        for (int i = 100; i >= 0; i--) {
            data.add(createEntry((double) i));
        }

        letterCol.setMinWidth(100);
        percentCol.setMinWidth(100);
        starCol.setMinWidth(200);
        table.setItems(this.data);

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

    public void update(Observable obv, Object obj)
    {

        System.out.println("Updated");

        vals = new Hashtable<Double, Integer>();
        for (int i = 0; i <= 100; i++)
        {
            vals.put(new Double(i), 0);
        }

        for (int i = 0; i < 100; i++)
        {
            vals.put(((double)i), 0);
        }

        java.util.List<Student> students = WorkSpace.instance.getStudents();

        for (Student s : students)
        {
            HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
            Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);

            double tempPercent = Math.ceil(percent.getValue());
            Integer temp = vals.get(tempPercent);
            vals.replace(tempPercent, ++temp);

        }

        for (int i = 100; i >= 0; i--) {
            data.add(createEntry((double) i));
        }
    }

    private Entry createEntry(double percent)
    {
        String letter;
        String stars = "";

        switch (((int)percent))
        {
            case 99:
                letter = "A+";
                break;
            case 94:
                letter = "A";
                break;
            case 90:
                letter = "A-";
                break;
            case 89:
                letter = "B+";
                break;
            case 84:
                letter = "B";
                break;
            case 80:
                letter = "B-";
                break;
            case 79:
                letter = "C+";
                break;
            case 74:
                letter = "C";
                break;
            case 70:
                letter = "C-";
                break;
            case 69:
                letter = "D+";
                break;
            case 64:
                letter = "D";
                break;
            case 60:
                letter = "D-";
                break;
            case 0:
                letter = "F";
                break;
            default:
                letter = " ";
                break;

        }

        for (int i = 0; i < vals.get(percent); i++)
        {
            stars += " *";
        }

        return new Entry(letter, String.valueOf(percent), stars);
    }




}
