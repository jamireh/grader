package grader.controller;
/**
 * @author Mallika Potter
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import java.util.*;

import java.net.URL;
import java.util.ResourceBundle;

import grader.model.file.*;
import grader.model.people.*;
import grader.model.gradebook.*;
import grader.model.items.*;

public class PieChartController implements Initializable, Observer{

    private int aS = 0;
    private int bS = 0;
    private int cS = 0;
    private int dS = 0;
    private int fS = 0;

    @FXML
    ObservableList<PieChart.Data> pieChartData =
            FXCollections.observableArrayList(
                    new PieChart.Data("B+", 29),
                    new PieChart.Data("B-", 7),
                    new PieChart.Data("C+", 29),
                    new PieChart.Data("C", 14),
                    new PieChart.Data("D+", 7),
                    new PieChart.Data("D", 7),
                    new PieChart.Data("D-", 7)
            );

    @FXML
    ObservableList<PieChart.Data> pieChartDataSmall;


    @FXML
    private PieChart piechart = new PieChart(pieChartData);

    private boolean smallData = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        WorkSpace.instance.addObserver(this);
        List<Student> students = WorkSpace.instance.getStudents();

        for (Student s : students)
        {
            HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
            Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);
            addToGrade(percent);

        }

        //System.out.println(aS + " " + bS + " " + cS + " " + dS + " " + fS);

        pieChartDataSmall = FXCollections.observableArrayList(
                new PieChart.Data("A", (aS/((double)students.size())) * 100),
                new PieChart.Data("B", (bS/((double)students.size())) * 100),
                new PieChart.Data("C", (cS/((double)students.size())) * 100),
                new PieChart.Data("D", (dS/((double)students.size())) * 100),
                new PieChart.Data("F", (fS/((double)students.size())) * 100));

        piechart.setData(pieChartDataSmall);

        /*piechart.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                if (smallData)
                {
                    piechart.setData(pieChartData);
                    smallData = false;
                }
                else
                {
                    piechart.setData(pieChartDataSmall);
                    smallData = true;
                }
            }
        });*/

    }

    /**
     * Adds to grade variables.
     */
    private void addToGrade(Percentage percent)
    {
        System.out.println("added : " + percent.getValue());
        double per = percent.getValue();

        if (per >= 90.0)
        {
            aS++;
        }
        else if (per >= 80.0)
        {
            bS++;
        }
        else if (per >= 70.0)
        {
            cS++;
        }
        else if (per >= 60.0)
        {
            dS++;
        }
        else
        {
            fS++;
        }
    }

    public void update(Observable obs, Object obj)
    {
        List<Student> students = WorkSpace.instance.getStudents();

        for (Student s : students)
        {
            HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
            Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);
            addToGrade(percent);

        }

        //System.out.println(aS + " " + bS + " " + cS + " " + dS + " " + fS);

        pieChartDataSmall = FXCollections.observableArrayList(
                new PieChart.Data("A", (aS/((double)students.size())) * 100),
                new PieChart.Data("B", (bS/((double)students.size())) * 100),
                new PieChart.Data("C", (cS/((double)students.size())) * 100),
                new PieChart.Data("D", (dS/((double)students.size())) * 100),
                new PieChart.Data("F", (fS/((double)students.size())) * 100));

        piechart.setData(pieChartDataSmall);
    }




}
