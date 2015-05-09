package grader.model.gradebook;

import javafx.scene.paint.Color;

/**
 *  GradeRange represents the percentage range that specifies
 *  a specific attainable LetterGrade within the class. A GradeRange contains the
 *  lower-bound percentage of this GradeRange, the LetterGrade associated with
 *  this GradeRange, and the Color that will help to define this GradeRange.
 *
 *  The upper-bound percentage of this GradeRange is implicit, as it is defined
 *  relatively by the other GradeRanges above and below it. There is an absolute low
 *  and high percentage in this GradeRange hierarchy defined to be 0% and 100% respectively.
 *
 *  @author Tobias Bleisch
 */
public class GradeRange
{
    /**
     * The lower-bound Percentage that defines this GradeRange
     * relative to the other existing GradeRanges.
     */ 
    private Percentage lowerBound;

    /**
     * The LetterGrade associated with this GradeRange.
     */ 
    private LetterGrade letterGrade;

    /**
     * The Color used to modify the visuals of this GradeRange.
     */
    private Color color;

    /**
     * Constructor for fields of GradeRange.
     */
    public GradeRange(Percentage lowerBound, LetterGrade letterGrade, Color color) {
        this.lowerBound = lowerBound;
        this.letterGrade = letterGrade;
        this.color = color;
    }

    /**
     * Accessor method for the lowerBound.
     * @return the lowerBound Percentage of this GradeRange.
     */
    public Percentage getLowerBound() {
        return lowerBound;
    }

    /**
     * Mutator method for the lowerBound.
     * @param lowerBound the lowerBound Percentage of this GradeRange.
     */
    public void setLowerBound(Percentage lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * Accessor method for the letterGrade.
     * @return the LetterGrade of this GradeRange.
     */
    public LetterGrade getLetterGrade() {
        return letterGrade;
    }

    /**
     * Accessor method for the color.
     * @return the Color of this GradeRange.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Mutator method for the color.
     * @param color the color of this GradeRange.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * A String representation of this GradeRange.
     * @return a String representation of this GradeRange.
     */
    @Override
    public String toString() {
        return "GradeRange{" +
                "lowerBound=" + lowerBound +
                ", letterGrade=" + letterGrade +
                ", color=" + color +
                '}';
    }
}
