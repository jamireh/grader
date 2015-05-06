package grader.model.curve;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import grader.model.file.*;
import grader.model.people.*;
import grader.model.gradebook.*;
import grader.model.items.*;
import javafx.collections.FXCollections;

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

/**
 * The PieChart class takes all of its required data and functionality from the AbstractGraph class.
 * 
 * Derived from the requirements documentation regarding visuals.
 */
public class PieChart extends AbstractGraph implements Observer
{

    private int aS = 0;
    private int bS = 0;
    private int cS = 0;
    private int dS = 0;
    private int fS = 0;

    private List<Student> students;


    /**
     * To be expanded once GradeScheme is complete.
     */
    public void categorizeScores() {}

   /**
    * Updates the Pie Chart.
    */
   public void update(Observable obj, Object args) {
       aS = 0;
       bS = 0;
       cS = 0;
       dS = 0;
       fS = 0;

       students = WorkSpace.instance.getStudents();

       for (Student s : students)
       {
           HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
           Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);
           addToGrade(percent);
       }

    }


    /**
     * Returns percentage of As.
     * @return percentage of As
     */
    public double getNumA()
    {
        System.out.println("A: " + aS);
        return (aS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Bs.
     * @return percentage of Bs
     */
    public double getNumB()
    {
        System.out.println("B: " + bS);
        return (bS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Cs.
     * @return percentage of Cs
     */
    public double getNumC()
    {
        System.out.println("C: " + cS);
        return (cS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Ds.
     * @return percentage of Ds
     */
    public double getNumD()
    {
        System.out.println("D: " + dS);
        return (dS/((double)students.size())) * 100;
    }

    /**
     * Returns percentage of Fs.
     * @return percentage of Fs
     */
    public double getNumF()
    {
        System.out.println("F: " + fS);
        return (fS/((double)students.size())) * 100;
    }

    /**
     * Adds to grade variables.
     * @param percent of grade
     */
    private void addToGrade(Percentage percent)
    {
        //System.out.println("added : " + percent.getValue());
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
}
