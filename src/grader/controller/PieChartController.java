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

import java.net.URL;
import java.util.ResourceBundle;

public class PieChartController implements Initializable{

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
    ObservableList<PieChart.Data> pieChartDataSmall =
            FXCollections.observableArrayList(new PieChart.Data("B", 36),
                    new PieChart.Data("C", 43),
                    new PieChart.Data("D", 21));

    @FXML
    private PieChart piechart = new PieChart(pieChartData);

    private boolean smallData = true;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        piechart.setData(pieChartDataSmall);

        piechart.setOnMousePressed(new EventHandler<MouseEvent>() {
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
        });

    }




}
