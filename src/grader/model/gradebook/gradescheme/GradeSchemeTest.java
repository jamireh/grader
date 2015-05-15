package grader.model.gradebook.gradescheme;

import grader.model.items.Percentage;
import javafx.scene.paint.Color;
import org.junit.Test;

import static org.junit.Assert.*;

public class GradeSchemeTest {

    /**
     * The GradeSchemeTest class is the companion testing class for the grader
     * GradeScheme. It implements the following module test plan:
     *									                                       <pre>
     *    Phase 1: Unit test the constructor.
     *
     *    Phase 2: Unit test updateDivisions.
     *
     *    Phase 3: Unit test toString.
     *
     *    Phase 4: Repeat phases 1 through 3.
     *	                                       								 </pre>
     *	  @author Tobias Bleisch
     */

    @Test
    public void testConstructor() {
        GradeScheme gradeScheme = new GradeScheme();
    }

    @Test
    public void testGradeScheme() throws Exception {

    }

    @Test
    public void testUpdateDivisions() throws Exception {

    }

    @Test
    public void testGetGradeRange() {
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
    public void testToString() throws Exception {

    }
}