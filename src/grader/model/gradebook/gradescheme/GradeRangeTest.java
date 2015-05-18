package grader.model.gradebook.gradescheme;

import grader.model.items.Percentage;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The GradeRangeTest class is the companion testing class for the
 * GradeRange. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test accessor methods.
 *
 *    Phase 3: Unit test mutator methods.
 *
 *    Phase 4: Unit test toString.
 *
 *    Phase 5: Repeat phases 1 through 4.
 *	                                       								 </pre>
 *	  @author Tobias Bleisch
 */
public class GradeRangeTest {

    /**
     * Unit test the constructor of the GradeRange class.
     *                                                                    <pre>
     *  Test
     *  Case    Input               Output      Remarks
     * ====================================================================
     *   1      82.3%, B-, Black      -         Ensure no unexpected Exception,
     *                                          no null is precondition
     *
     *   </pre>
     */
    @Test
    public void testConstructor() {
        new GradeRange(new Percentage(82.3), LetterGrade.B_MINUS,
                new Color(0, 0, 0, 0));
    }

    /**
     * Unit test accessor methods of the GradeRange class.
     *
     * getLowerBound()
     * getLetterGrade()
     * getColor()
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input                   Output                  Remarks
     * ====================================================================
     *   1      97.0%, A+, LightGreen   97.0%, A+, LightGreen   Average values
     *
     *   2      null, A, Green          null, A, Green          null Percent
     *
     *   3      90.0%, null, DarkGreen  90.0%, null, DarkGreen  null LetterGrade
     *
     *   4      87.0%, B+, null         87.0%, B+, null         null Color
     *
     *   </pre>
     */
    @Test
    public void testAccessors() {
        GradeRange gradeRangeOne = new GradeRange(new Percentage(97.0),
                LetterGrade.A_PLUS, Color.LIGHTGREEN);
        GradeRange gradeRangeTwo = new GradeRange(null,
                LetterGrade.A, Color.GREEN);
        GradeRange gradeRangeThree = new GradeRange(new Percentage(90.0),
                null, Color.DARKGREEN);
        GradeRange gradeRangeFour = new GradeRange(new Percentage(87.0),
                LetterGrade.B_PLUS, null);

        assertEquals(new Percentage(97.0), gradeRangeOne.getLowerBound());
        assertEquals(LetterGrade.A_PLUS, gradeRangeOne.getLetterGrade());
        assertEquals(Color.LIGHTGREEN, gradeRangeOne.getColor());

        assertNull(gradeRangeTwo.getLowerBound());
        assertEquals(LetterGrade.A, gradeRangeTwo.getLetterGrade());
        assertEquals(Color.GREEN, gradeRangeTwo.getColor());

        assertEquals(new Percentage(90.0), gradeRangeThree.getLowerBound());
        assertNull(gradeRangeThree.getLetterGrade());
        assertEquals(Color.DARKGREEN, gradeRangeThree.getColor());

        assertEquals(new Percentage(87.0), gradeRangeFour.getLowerBound());
        assertEquals(LetterGrade.B_PLUS, gradeRangeFour.getLetterGrade());
        assertNull(gradeRangeFour.getColor());

    }

    /**
     * Unit test mutator methods of the GradeRange class.
     *
     * setLowerBound()
     * setColor()
     *                                                                    <pre>
     *  Test
     *  Case    Input                   Output             Remarks
     * ====================================================================
     *   1      97.0% | LightGreen        -                Average values
     *
     *   2      null | Green              -                null Percent
     *
     *   3      90.0% | null              -                null Color
     *   </pre>
     */
    @Test
    public void testMutators() {
        GradeRange gradeRangeOne = new GradeRange(new Percentage(200),
                LetterGrade.A_PLUS, Color.BLACK);
        GradeRange gradeRangeTwo = new GradeRange(new Percentage(200),
                LetterGrade.A_PLUS, Color.BLACK);
        GradeRange gradeRangeThree = new GradeRange(new Percentage(200),
                LetterGrade.A_PLUS, Color.BLACK);

        gradeRangeOne.setLowerBound(new Percentage(97.0));
        gradeRangeOne.setColor(Color.LIGHTGREEN);
        assertEquals(new Percentage(97.0), gradeRangeOne.getLowerBound());
        assertEquals(Color.LIGHTGREEN, gradeRangeOne.getColor());

        gradeRangeTwo.setLowerBound(null);
        gradeRangeTwo.setColor(Color.GREEN);
        assertNull(gradeRangeTwo.getLowerBound());
        assertEquals(Color.GREEN, gradeRangeTwo.getColor());

        gradeRangeThree.setLowerBound(new Percentage(90.0));
        gradeRangeThree.setColor(null);
        assertEquals(new Percentage(90.0), gradeRangeThree.getLowerBound());
        assertNull(gradeRangeThree.getColor());
    }

    /**
     * Unit test the toString() method for the GradeRange class.
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output                     Remarks
     * ====================================================================
     *   1        -        (String)       Default values of GradeScheme
     *
     </pre>
     */
    @Test
    public void testToString() throws Exception {
        GradeRange gradeRange = new GradeRange(new Percentage(97.0),
                LetterGrade.A_PLUS, Color.LIGHTGREEN);
        assertEquals("GradeRange{lowerBound=Percentage{value=97.0}, " +
                "letterGrade=LetterGrade{letter='A+', textID1='A_Plus_Low', " +
                "textID2='A_High', colorID='A_PLUS_COLOR'}, color=0x90ee90ff}", gradeRange.toString());
    }
}