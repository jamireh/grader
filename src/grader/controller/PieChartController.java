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

public class PieChartController implements Initializable{


    @FXML
    ObservableList<PieChart.Data> pieChartData;


    @FXML
    private PieChart piechart = new PieChart(pieChartData);


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        grader.model.curve.PieChart temp = WorkSpace.instance.getPieChart();

        pieChartData = FXCollections.observableArrayList(
                new javafx.scene.chart.PieChart.Data("A", temp.getNumA()),
                new javafx.scene.chart.PieChart.Data("B", temp.getNumB()),
                new javafx.scene.chart.PieChart.Data("C", temp.getNumC()),
                new javafx.scene.chart.PieChart.Data("D", temp.getNumD()),
                new javafx.scene.chart.PieChart.Data("F", temp.getNumF()));

        piechart.setData(pieChartData);


    }








}
