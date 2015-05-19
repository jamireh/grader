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

        pieChartData = FXCollections.observableArrayList();

        if (temp.getNumAPlus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("A+", temp.getNumAPlus()));

        if (temp.getNumA() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("A", temp.getNumA()));

        if (temp.getNumAMinus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("A-", temp.getNumAMinus()));

        if (temp.getNumBPlus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("B+", temp.getNumBPlus()));

        if (temp.getNumB() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("B", temp.getNumB()));

        if (temp.getNumBMinus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("B-", temp.getNumBMinus()));

        if (temp.getNumCPlus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("C+", temp.getNumCPlus()));

        if (temp.getNumC() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("C", temp.getNumC()));

        if (temp.getNumCMinus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("C-", temp.getNumCMinus()));

        if (temp.getNumDPlus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("D+", temp.getNumDPlus()));

        if (temp.getNumD() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("D", temp.getNumD()));

        if (temp.getNumDMinus() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("D-", temp.getNumDMinus()));

        if (temp.getNumF() != 0)
            pieChartData.add(new javafx.scene.chart.PieChart.Data("F", temp.getNumF()));

        piechart.setData(pieChartData);


    }











}
