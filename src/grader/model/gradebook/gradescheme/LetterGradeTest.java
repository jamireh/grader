package grader.model.gradebook.gradescheme;

import org.junit.Test;

import static org.junit.Assert.*;

public class LetterGradeTest {

    @Test
    public void testConstructor() {
        assertNotNull(LetterGrade.A);
        assertNotSame(LetterGrade.A, LetterGrade.C_PLUS);
    }

    @Test
    public void testValueOfFromID() throws Exception {
        assertEquals(LetterGrade.A_PLUS, LetterGrade.valueOfFromID("A_Plus_Low"));
        assertEquals(LetterGrade.B_MINUS, LetterGrade.valueOfFromID("B_Minus_High"));
        assertEquals(LetterGrade.D, LetterGrade.valueOfFromID("D_COLOR"));
        assertEquals(LetterGrade.SIZE, LetterGrade.valueOfFromID(""));
    }

    @Test
    public void testToString() throws Exception {

    }
}