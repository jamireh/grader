package grader.model.gradebook;
import java.util.*;

import grader.model.errors.PercentageFormatException;

/**
 * The model containing the range breakdown for each letter-grade 
 * as well as the associated colors.
 *
 * @author Tobias Bleisch
 */
public class GradeScheme
{   
    /**
     * Comprised of every available grade range.
     */
    public ArrayList<GradeRange> ranges;

    /**
     * Constructor for fields of GradeScheme.
     *
     * Creates and sets a generic GradeScheme to be used in the class.
     */
    public GradeScheme() {
        ranges = new ArrayList<GradeRange>();

        /*
         * Create a generic GradeScheme to be used initially:
         * 90%-100%, A, Green | 80%-90%, B, Blue | 70%-80%, C, Orange | 60%-70%, D, Purple | 0%-60%, F, Red
         */
        try {
            ranges.add(new GradeRange(new Percentage(97.0), LetterGrade.A_PLUS, Color.Green));
            ranges.add(new GradeRange(new Percentage(93.0), LetterGrade.A, Color.Green));
            ranges.add(new GradeRange(new Percentage(90.0), LetterGrade.A_MINUS, Color.Green));
            ranges.add(new GradeRange(new Percentage(87.0), LetterGrade.B_PLUS, Color.Blue));
            ranges.add(new GradeRange(new Percentage(83.0), LetterGrade.B, Color.Blue));
            ranges.add(new GradeRange(new Percentage(80.0), LetterGrade.B_MINUS, Color.Blue));
            ranges.add(new GradeRange(new Percentage(77.0), LetterGrade.C_PLUS, Color.Orange));
            ranges.add(new GradeRange(new Percentage(73.0), LetterGrade.C, Color.Orange));
            ranges.add(new GradeRange(new Percentage(70.0), LetterGrade.C_MINUS, Color.Orange));
            ranges.add(new GradeRange(new Percentage(67.0), LetterGrade.D_PLUS, Color.Purple));
            ranges.add(new GradeRange(new Percentage(63.0), LetterGrade.D, Color.Purple));
            ranges.add(new GradeRange(new Percentage(60.0), LetterGrade.D_MINUS, Color.Purple));
            ranges.add(new GradeRange(new Percentage(0.0), LetterGrade.F, Color.Red));
        }
        catch (PercentageFormatException except) {
            except.printStackTrace();
        }
    }

//    TODO - Along with implementing updateDivisions(), assimilate input actions for GradeScheme
//    Have two connecting ranges update together so that there aren't any gaps left (B 80-90, A 90-100). They can be the same
//    value on the GUI and underlying can take it to be < value = B from underneath and >= value = A coming from the top.

    /**
     * Upon changing of the data, changes the modified division
     * as well as adjusts the other ranges accordingly.
     * pre:
     *    forall(GradeRange bar;
     *    divisions.contains(bar);
     *    bar != null);
     */
    public void updateDivisions() {
        System.out.println("Divisions have been updated.");
    }

    /**
     * A String representation of this GradeScheme.
     * @return a String representation of this GradeScheme.
     */
    @Override
    public String toString() {
        return "GradeScheme{" +
                "ranges=" + ranges +
                '}';
    }
}
