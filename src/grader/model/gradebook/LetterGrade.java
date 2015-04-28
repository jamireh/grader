package grader.model.gradebook;

/**
 * LetterGrade represents the set of grades possible for
 * a student to receive in a class.
 *
 * @author Tobias Bleisch
 */
public enum LetterGrade
{
    A_PLUS ("A+"), A ("A"), A_MINUS ("A-"), B_PLUS ("B+"), B ("B"), B_MINUS ("B-"),
    C_PLUS ("C+"), C ("C"), C_MINUS ("C-"), D_PLUS ("D+"), D ("D"), D_MINUS ("D-"), F ("F");

    private final String letter;

    LetterGrade(String letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        return letter;
    }
}
