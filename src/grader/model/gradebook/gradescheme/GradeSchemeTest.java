package grader.model.gradebook.gradescheme;

import grader.model.errors.OverlappingRangeException;
import grader.model.items.Percentage;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The GradeSchemeTest class is the companion testing class for the grader
 * GradeScheme. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test accessor methods.
 *
 *    Phase 3: Unit test updateGradeRange exception cases.
 *
 *    Phase 4: Unit test the overloaded updateGradeRange methods.
 *
 *    Phase 5: Unit test toString.
 *
 *    Phase 6: Repeat phases 1 through 5.
 *	                                       								 </pre>
 *	  @author Tobias Bleisch
 */
public class GradeSchemeTest {

    /**
     * Unit test of the GradeScheme constructor.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1        -          -             Ensure default data is set properly
     *                                     and no unexpected Exceptions occur.
     *
     *                                                  </pre>
     */
    @Test
    public void testConstructor() {
        GradeScheme gradeScheme = new GradeScheme();
        assertEquals(LetterGrade.SIZE.ordinal(), gradeScheme.ranges.size());

        for (int i = 0; i < LetterGrade.SIZE.ordinal(); i++)
            assertEquals(0, gradeScheme.ranges.get(i).getLetterGrade().
                    compareTo(LetterGrade.values()[i]));
    }

    /**
     * Unit test the accessor methods of GradeScheme. Only one
     * currently existing is getGradeRange(), also used frequently
     * in the testUpdateGradeRangePercentage() method.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1        0%        F GradeRange   Default value of F Range: Lower-Bound
     *
     *   2        97%       A+ GradeRange  Default value of the A+ Range: Upper-Bound
     *
     *   3        71.09%    C- GradeRange  Middle case
     *
     *                                                  </pre>
     */
    @Test
    public void testGetters() throws Exception {
        GradeScheme gradeScheme = new GradeScheme();
        Percentage percentageOne = new Percentage(0);
        Percentage percentageTwo = new Percentage(97);
        Percentage percentageThree = new Percentage(71.09);

        assertEquals(LetterGrade.F,
                gradeScheme.getGradeRange(percentageOne).getLetterGrade());
        assertEquals(LetterGrade.A_PLUS,
                gradeScheme.getGradeRange(percentageTwo).getLetterGrade());
        assertEquals(LetterGrade.C_MINUS,
                gradeScheme.getGradeRange(percentageThree).getLetterGrade());
    }

    /**
     * Unit test the exception cases of updateGradeRange()
     * overloaded for Percentage.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output                     Remarks
     * ====================================================================
     *   1      83.4%      OverlappingRangeException  First enum, possible
     *                                                  index out of bounds
     *   2      90%        OverlappingRangeException  Upper-Bound
     *
     *   3      22.0268%   OverlappingRangeException  Lower-Bound                                              </pre>
     */
    @Test (expected= OverlappingRangeException.class)
    public void testUpdateGradeRangePercentageException() throws Exception {
        GradeScheme gradeScheme = new GradeScheme();
        Percentage percentageOne = new Percentage(83.4);
        Percentage percentageTwo = new Percentage(90);
        Percentage percentageThree = new Percentage(22.0268);

        gradeScheme.updateGradeRange(LetterGrade.A_PLUS, percentageOne);
        gradeScheme.updateGradeRange(LetterGrade.A_MINUS, percentageTwo);
        gradeScheme.updateGradeRange(LetterGrade.C, percentageThree);
    }


    /**
     * Unit test updateGradeRange() overloaded for Color.
     *                                                                    <pre>
     *  Test
     *  Case    Input                           Output    Remarks
     * ====================================================================
     *   1      Color(r=.1, g=.2, b=.3, o=.4)     -       Simple set and get
     *                                                      of GradeRange color
     *
     *                                   </pre>
     */
    @Test
    public void testUpdateGradeRangeColor() {
        GradeScheme gradeScheme = new GradeScheme();
        Color colorOne = new Color(.1, .2, .3, .4);

        /*Just to ensure that the test is valid, checking that
          we're not getting a color that was already set as default
         */
        assertFalse(colorOne.equals(gradeScheme.ranges.
                get(LetterGrade.A.ordinal()).getColor()));
        gradeScheme.updateGradeRange(LetterGrade.A, colorOne);
        assertEquals(colorOne, gradeScheme.ranges.
                get(LetterGrade.A.ordinal()).getColor());
    }

    /**
     * Unit test updateGradeRange() overloaded for Percentage.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output             Remarks
     * ====================================================================
     *   1      105%      LetterGrade.A+      Above Upper-Bound
     *
     *   2      100%      LetterGrade.A+      Upper-Bound
     *
     *   3      99%       LetterGrade.A+      In-Between Range
     *
     *   4      67%       LetterGrade.D_Plus  Border Range
     *
     *   5      44.85%    LetterGrade.F       In-Between Range w/ decimals
     *
     *   6      0%        LetterGrade.F       Lower-Bound
     *
     *   </pre>
     */
    @Test
    public void testUpdateGradeRangePercentage() {
        GradeScheme gradeScheme = new GradeScheme();

        Percentage percentOne = new Percentage(105);
        Percentage percentTwo = new Percentage(100);
        Percentage percentThree = new Percentage(99);
        Percentage percentFour = new Percentage(67);
        Percentage percentFive = new Percentage(44.85);
        Percentage percentSix = new Percentage(0);

        assertEquals(gradeScheme.ranges.get(LetterGrade.A_PLUS.ordinal()),
                gradeScheme.getGradeRange(percentOne));
        assertEquals(gradeScheme.ranges.get(LetterGrade.A_PLUS.ordinal()),
                gradeScheme.getGradeRange(percentTwo));
        assertEquals(gradeScheme.ranges.get(LetterGrade.A_PLUS.ordinal()),
                gradeScheme.getGradeRange(percentThree));
        assertEquals(gradeScheme.ranges.get(LetterGrade.D_PLUS.ordinal()),
                gradeScheme.getGradeRange(percentFour));
        assertEquals(gradeScheme.ranges.get(LetterGrade.F.ordinal()),
                gradeScheme.getGradeRange(percentFive));
        assertEquals(gradeScheme.ranges.get(LetterGrade.F.ordinal()),
                gradeScheme.getGradeRange(percentSix));
    }

    @Test
    /**
     * Unit test the toString() method for the GradeScheme. This will
     * simply ensure that the GradeScheme is capable of printing out
     * default values of it's Ranges.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output                     Remarks
     * ====================================================================
     *   1        -        (list of collection)       Default values of GradeScheme
     *
     </pre>
     */
    public void testToString() throws Exception {
        GradeScheme gradeScheme = new GradeScheme();
        assertEquals("GradeScheme{" + "ranges=" + gradeScheme.ranges + '}',
                gradeScheme.toString());
    }

    /**
     * Unit test the copy() method which ensures that a proper
     * deep copy of GradeScheme's list of Ranges is made.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input        Output             Remarks
     * ====================================================================
     *   1        -          Copy of Ranges     Copy of GradeScheme consists
     *                                          only of copy of Ranges
     *
     *                                       </pre>
     */
    @Test
    public void testCopy() {
        GradeScheme gradeScheme = new GradeScheme();
        assertEquals(gradeScheme.ranges, gradeScheme.copy().ranges);
        gradeScheme.ranges.get(0).setColor(new Color(.1, .2, .3, .4));
        assertEquals(gradeScheme.ranges, gradeScheme.copy().ranges);
    }
}