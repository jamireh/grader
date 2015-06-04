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
public class Histogram extends AbstractGraph implements Observer
{

    private Hashtable<Double, Integer> vals = new Hashtable<Double, Integer>();

    /**
     * GradeScheme used to project how a particular adjustment will propagate.
     * Pushed to the Section once the user finalizes their choice with apply().
     */
    GradeScheme tempGradeScheme;

    /**
     * Given a particular letter grade and new lowerbound, this method will apply
     * them to the temporary GradeScheme.
     *
     * @param letterGrade The LetterGrade that has been changed
     * @param newLowerBound The new Percentage that denotes the LetterGrade's new
     * lower bound.
     * <pre>
    post:
    //The LetterGrade and Percentage have been properly applied to the
    //tempGradeScheme and no other changes have been made
    forall (GradeRange divisionBar;
    tempGradeScheme'.divisions.contains(divisionBar) iff
    (divisionBar.letterGrade.equals(letterGrade) && divisionBar.low.equals(newLowerBound))
    ||
    tempGradeScheme.divisions.contains(divisionBar));
     */
    public void adjustCurve(LetterGrade letterGrade, Percentage newLowerBound) {}

    /**
     * Changes made via the GUI manipulations will be pushed over to the full
     * model.
     post:
     //The GradeScheme of the Section must be identical to the tempGradeScheme.
     forall (GradeRange divisionBar;
     tempGradeScheme.contains(divisionBar);
     exists(GradeRange sDivisionBar;
     section'.gradeScheme.divisions.contains(sDivisionBar);
     divisionBar.equals(sDivisionBar)));
     */

    /**
     * Uses the Section to find the Scores necessary to draw the chart as well
     * as categorizes them into numScores.
     * <pre>
     post:
     //Ensure that the numScores list is correct by summing the contents of the
     //list and verifying that it is the same size as the number of students.
     countCollection(numScores) == countStudents(sections);
     */
    public void categorizeScores() {}

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
            Integer temp = vals.get(tempPercent);
            vals.replace(tempPercent, ++temp);

        }

    }

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