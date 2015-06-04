package grader.model.curve;

/**
 * @author Mallika Potter
 */

import grader.model.errors.OverlappingRangeException;
import grader.model.gradebook.WorkSpace;
import grader.model.gradebook.gradescheme.GradeRange;
import grader.model.gradebook.gradescheme.GradeScheme;
import grader.model.gradebook.gradescheme.LetterGrade;
import grader.model.gradebook.scores.RawScore;
import grader.model.items.Assignment;
import grader.model.items.Percentage;
import grader.model.people.Student;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

/**
 * The Histogram class defines the necessary components for graphically changing the GradeScheme
 *
 * Derived from the requirements documentation regarding visuals.
 */
public class Histogram implements Observer
{

    private Hashtable<Double, Integer> vals = new Hashtable<Double, Integer>();

    /**
     * GradeScheme used to project how a particular adjustment will propagate.
     * Pushed to the Section once the user finalizes their choice with apply().
     */
    GradeScheme tempGradeScheme;



    /**
     * Updates current gradescheme to temp section gradescheme.
     */
    public void apply() {
        WorkSpace.instance.gradeScheme = tempGradeScheme;
        WorkSpace.instance.setGradeSchemeChanged();
    }

    /**
     * Returns changed gradescheme to Section.
     */
    public void push() {

        WorkSpace.instance.gradeScheme = tempGradeScheme;
        WorkSpace.instance.getSection().pushGradeScheme(tempGradeScheme);
        WorkSpace.instance.gradeSchemeChanged = true;
        WorkSpace.instance.updateGradeScheme();
    }

    /**

    /**
     * Updates the Histogram.
     */
    public void update(Observable obj, Object args) {
        for (int i = 0; i <= 100; i++)
        {
            vals.put(new Double(i), 0);
        }

        java.util.List<Student> students = WorkSpace.instance.getStudents();
        tempGradeScheme = WorkSpace.instance.getGradeScheme();


        for (Student s : students)
        {
            HashMap<Assignment, RawScore> map = WorkSpace.instance.getScores().getScoresMap(s);
            Percentage percent = WorkSpace.instance.getAssignmentTree().calculatePercentage(map);

            double tempPercent = Math.ceil(percent.getValue());
            if (tempPercent > 100.0)
            {
                tempPercent = 100.0;
            }
            else if (tempPercent < 0.0)
            {
                tempPercent = 0.0;
            }

            Integer temp = vals.get(tempPercent);
            vals.replace(tempPercent, ++temp);

        }

    }

    /**
     * Adjust histogram automatically.
     * @param oldPercent original percent for letter.
     * @param newPercent new percent for letter.
     * @param letter letter to move.
     */
    public void adjustHistogram(double oldPercent, double newPercent, String letter)
    {

        try {
            LetterGrade let = LetterGrade.valueOfFromID(letter);
            tempGradeScheme.updateGradeRange(let, new Percentage(newPercent));
        }
        catch (OverlappingRangeException e)
        {
            if (oldPercent > newPercent) {
                //System.out.println("Lower");
                boolean movedSomething = false;

                for (int i = 1; i < ((int) oldPercent); i++)
                {
                    double temp = (double) i;
                    GradeRange range = tempGradeScheme.getGradeRange(new Percentage(temp));
                    if (temp == range.getLowerBound().getValue())
                    {
                        try {
                            tempGradeScheme.updateGradeRange(range.getLetterGrade(), new Percentage(temp-1));
                            movedSomething = true;
                        }
                        catch (OverlappingRangeException e2)
                        {
                            //System.out.println("Can't move");
                        }
                    }
                }

                if (movedSomething) {
                    adjustHistogram(oldPercent, newPercent, letter);
                }
            }
            else
            {
                //System.out.println("Higher");
                boolean movedSomething = false;

                for (int i = 100; i > ((int) oldPercent); i--)
                {
                    double temp = (double) i;
                    GradeRange range = tempGradeScheme.getGradeRange(new Percentage(temp));
                    if (temp == range.getLowerBound().getValue())
                    {
                        try {
                            tempGradeScheme.updateGradeRange(range.getLetterGrade(), new Percentage(temp+1));
                            movedSomething = true;
                        }
                        catch (OverlappingRangeException e3)
                        {
                            //System.out.println("Can't move");
                        }
                    }
                }

                if (movedSomething) {
                    adjustHistogram(oldPercent, newPercent, letter);
                }
            }
        }
    }

    /**
     * Creates new Entry.
     * @param percent key for Entry.
     * @return new entry.
     */

    public Entry getEntry(double percent)
    {
        String letter;
        String stars = "";

        GradeRange range = tempGradeScheme.getGradeRange(new Percentage(percent));

        if (percent == range.getLowerBound().getValue())
            letter = range.getLetterGrade().letter;
        else
            letter = " ";

        for (int i = 0; i < vals.get(percent); i++)
        {
            stars += " *";
        }

        return new Entry(letter, String.valueOf(percent), stars);
    }

}