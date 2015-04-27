package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.items.Assignment;
import grader.model.gradebook.stats.Statistics;
import grader.model.gradebook.stats.StatsContainer;
import grader.view.StatisticsBar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

/**
 * Controller for the StatisticsBar bar and model.
 * @author Quan Tran
 */
public class StatsController implements Initializable {
    private StatsContainer model = WorkSpace.instance.statistics;
    private StatisticsBar view;

    /**
     * Constructs a new StatsController.
     */
    public StatsController() {
        System.out.println("StatsController constructed s[agiqperihnpeirhn");
        model.setController(this);
    }

    /**
     * Initializes this StatsController.
     * @param  location unused
     * @param resources unused
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("StatsController initialized 3q98gnae08rgub8bfn9amfc87");
        model.setController(this);
    }

    /**
     * Renders the StatisticsBar bar in the view.
     */
    public void render() {
        List<Assignment> assignments = model.getAssignments();
        Map<Assignment, Statistics> statsMap = model.getMap();
        List<Statistics> statistics = new ArrayList<Statistics>();

        for (Assignment ass : assignments)
            statistics.add(statsMap.get(ass));

        view = new StatisticsBar(FXCollections.observableList(statistics));
    }
/*
    public static void main(String[] args) {
        StatsController control = new StatsController();
        control.render();
    }


    /*
 ObservableList<Person> teamMembers = ...;
 table.setItems(teamMembers);

 TableColumn<Person,String> firstNameCol = new TableColumn<Person,String>("First Name");
 firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
 TableColumn<Person,String> lastNameCol = new TableColumn<Person,String>("Last Name");
 lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));

 table.getColumns().setAll(firstNameCol, lastNameCol);
     */
}
