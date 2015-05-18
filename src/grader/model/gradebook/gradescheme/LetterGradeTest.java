package grader.model.gradebook.gradescheme;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The LetterGradeTest class is the companion testing class for the
 * LetterGrade class. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test valueOfFromID() and therefore
 *              also test static initialization segment.
 *
 *    Phase 3: Unit test toString.
 *
 *    Phase 4: Repeat phases 1 through 3.
 *	                                       								 </pre>
 *	  @author Tobias Bleisch
 */
public class LetterGradeTest {

    /**
     * Unit test the constructor of the LetterGrade class. Heavily
     * relies on the valueOfFromID() method.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1        -          -             Check default values of enum
     *
     *                                                  </pre>
     */
    @Test
    public void testConstructor() {
        assertEquals(LetterGrade.A_PLUS, LetterGrade.valueOfFromID("A_Plus_Low"));
        assertEquals(LetterGrade.A_PLUS, LetterGrade.valueOfFromID("A_High"));
        assertEquals(LetterGrade.A_PLUS, LetterGrade.valueOfFromID("A_PLUS_COLOR"));
    }

    /**
     * Unit test the valueOfFromID() method.
     *                                                                    <pre>
     *  Test
     *  Case    Input           Output                Remarks
     * ====================================================================
     *   1      "A_Plus_Low"    LetterGrade.A_Plus    Upper-Bound TextID
     *
     *   2      "B_Minus_High"  LetterGrade.B_Minus   Middle Case TextID
     *
     *   3      "F"             LetterGrade.F         Lower-Bound ColorID
     *
     *   4      "D_COLOR"       LetterGrade.D         ColorID
     *
     *   5      ""              LetterGrade.SIZE      Empty String
     *                                                  </pre>
     */
    @Test
    public void testValueOfFromID() throws Exception {
        assertEquals(LetterGrade.A_PLUS, LetterGrade.valueOfFromID("A_Plus_Low"));
        assertEquals(LetterGrade.B_MINUS, LetterGrade.valueOfFromID("B_Minus_Low"));
        assertEquals(LetterGrade.F, LetterGrade.valueOfFromID("F_COLOR"));
        assertEquals(LetterGrade.D, LetterGrade.valueOfFromID("D_COLOR"));
        assertEquals(LetterGrade.SIZE, LetterGrade.valueOfFromID(""));
    }

    /**
     * Unit test the toString() method of the LetterGrade.
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output                   Remarks
     * ====================================================================
     *   1        -        (String Representation)  Final values in LetterGrade
     *                                                  </pre>
     */
    @Test
    public void testToString() throws Exception {
        assertEquals("LetterGrade{letter='B', textID1='B_Low', textID2='B_Minus_High', colorID='B_COLOR'}",
                LetterGrade.B.toString());
    }
}