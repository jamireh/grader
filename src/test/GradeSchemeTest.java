package test;

import grader.model.errors.OverlappingRangeException;
import grader.model.gradebook.gradescheme.GradeScheme;
import grader.model.gradebook.gradescheme.LetterGrade;
import grader.model.items.Percentage;
import javafx.scene.paint.Color;
import org.junit.Assert;
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
        Assert.assertEquals(LetterGrade.SIZE.ordinal(), gradeScheme.ranges.size());

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
     * Unit test the first exception case of updateGradeRange()
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
    @Test
    public void testUpdateGradeRangePercentageExceptionCase1() throws Exception {
        GradeScheme gradeScheme = new GradeScheme();
        Percentage percentageOne = new Percentage(83.4);
        Percentage percentageTwo = new Percentage(98.0);
        Percentage percentageThree = new Percentage(22.0268);

        try {
            gradeScheme.updateGradeRange(LetterGrade.A_PLUS, percentageOne);
            fail("Test Case 1 failed to throw exception.");
        }
        catch (OverlappingRangeException except) {}

        try {
            gradeScheme.updateGradeRange(LetterGrade.A_MINUS, percentageTwo);
            fail("Test Case 2 failed to throw exception.");
        }
        catch (OverlappingRangeException except) {}

        try {
            gradeScheme.updateGradeRange(LetterGrade.C, percentageThree);
            fail("Test Case 3 failed to throw exception.");
        }
        catch (OverlappingRangeException except) {}
    }

    /**
     * Unit test the second exception case of updateGradeRange()
     * overloaded for Percentage.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output                     Remarks
     * ====================================================================
     *   1      100.0%      OverlappingRangeException  Upper-Bound
     *
     *   2      105.4%      OverlappingRangeException  Above Upper-Bound
     *   </pre>
     */
    @Test
    public void testUpdateGradeRangePercentageExceptionCase2() throws Exception {
        GradeScheme gradeScheme = new GradeScheme();
        Percentage percentageOne = new Percentage(100.0);
        Percentage percentageTwo = new Percentage(105.4);

        try {
            gradeScheme.updateGradeRange(LetterGrade.A_PLUS, percentageOne);
            fail("Test Case 1 failed to throw exception.");
        }
        catch (OverlappingRangeException except) {}

        try {
            gradeScheme.updateGradeRange(LetterGrade.A_PLUS, percentageTwo);
            fail("Test Case 2 failed to throw exception.");
        }
        catch (OverlappingRangeException except) {}
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
     * Upper-Bound and beyond Upper-Bound checked in exception tests
     * of this method.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output             Remarks
     * ====================================================================
     *   1      99%       LetterGrade.A+      In-Between Range
     *
     *   2      67%       LetterGrade.D_Plus  Border Range
     *
     *   3      44.85%    LetterGrade.F       In-Between Range w/ decimals
     *
     *   4      0%        LetterGrade.F       Lower-Bound
     *
     *   </pre>
     */
    @Test
    public void testUpdateGradeRangePercentage() throws OverlappingRangeException {
        GradeScheme gradeScheme = new GradeScheme();

        Percentage percentOne = new Percentage(99);
        Percentage percentTwo = new Percentage(67);
        Percentage percentThree = new Percentage(44.85);
        Percentage percentFour = new Percentage(0.5);

        gradeScheme.updateGradeRange(LetterGrade.A_PLUS, percentOne);
        assertEquals(percentOne, gradeScheme.ranges.get(LetterGrade.A_PLUS.ordinal()).getLowerBound());
        gradeScheme.updateGradeRange(LetterGrade.D_PLUS, percentTwo);
        assertEquals(percentTwo, gradeScheme.ranges.get(LetterGrade.D_PLUS.ordinal()).getLowerBound());
        gradeScheme.updateGradeRange(LetterGrade.D_MINUS, percentThree);
        assertEquals(percentThree, gradeScheme.ranges.get(LetterGrade.D_MINUS.ordinal()).getLowerBound());
        gradeScheme.updateGradeRange(LetterGrade.D_MINUS, percentFour);
        assertEquals(percentFour, gradeScheme.ranges.get(LetterGrade.D_MINUS.ordinal()).getLowerBound());
    }


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
    @Test
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