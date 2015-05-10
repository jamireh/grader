package grader.model.gradebook;

import java.util.HashMap;

/**
 * LetterGrade represents the set of grades possible for
 * a student to receive in a class.
 *
 * @author Tobias Bleisch
 */
public enum LetterGrade
{
    A_PLUS ("A+", "A_Plus_Low", "A_High", "A_PLUS_COLOR"),
    A ("A", "A_Low", "A_Minus_High", "A_COLOR"),
    A_MINUS ("A-", "A_Minus_Low", "B_Plus_High", "A_MINUS_COLOR"),
    B_PLUS ("B+", "B_Plus_Low", "B_High", "B_PLUS_COLOR"),
    B ("B", "B_Low", "B_Minus_High", "B_COLOR"),
    B_MINUS ("B-", "B_Minus_Low", "C_Plus_High", "B_COLOR"),
    C_PLUS ("C+", "C_Plus_Low", "C_High", "C_PLUS_COLOR"),
    C ("C", "C_Low", "C_Minus_High", "C_COLOR"),
    C_MINUS ("C-", "C_Minus_Low", "D_Plus_High", "C_MINUS_COLOR"),
    D_PLUS ("D+", "D_Plus_Low", "D_High", "D_PLUS_COLOR"),
    D ("D", "D_Low", "D_Minus_High", "D_COLOR"),
    D_MINUS ("D-", "D_Minus_Low", "F_High", "D_MINUS_COLOR"),
    F ("F", "", "", "F_COLOR"),
    SIZE("", "", "", "");

    /**
     * The String form of the LetterGrade represented by this Enum.
     */
    public final String letter;

    /**
     * The fxml ID for the "Low" TextBox that must update this LetterGrade's GradeRange' Percentage.
     */
    public final String textID1;

    /**
     * The fxml ID for the "High" TextBox that must update this LetterGrade's GradeRange' Percentage.
     */
    public final String textID2;

    /**
     * The fxml ID for the ColorPicker that must update this LetterGrade's GradeRange' Color.
     */
    public final String colorID;

    /**
     * Maps the fxml IDs to the corresponding LetterGrade.
     */
    private static HashMap<String, LetterGrade> map = new HashMap<String, LetterGrade>();

    /**
     * Constructor for fields of LetterGrade.
     * @param letter the String form of the LetterGrade represented by this Enum.
     * @param textID1 the fxml ID for the "Low" TextBox that must update this LetterGrade's GradeRange' Percentage.
     * @param textID2 the fxml ID for the "High" TextBox that must update this LetterGrade's GradeRange' Percentage.
     * @param colorID the fxml ID for the ColorPicker that must update this LetterGrade's GradeRange' Color.
     */
    LetterGrade(String letter, String textID1, String textID2, String colorID) {
        this.letter = letter;
        this.textID1 = textID1;
        this.textID2 = textID2;
        this.colorID = colorID;
    }


    /**
     * Static initialization of the mapping between fxml IDs and LetterGrades.
     */
    static {
        for (LetterGrade grade : LetterGrade.values()) {
            map.put(grade.textID1, grade);
            map.put(grade.textID2, grade);
            map.put(grade.colorID, grade);
        }
    }

    /**
     * Retrieves the LetterGrade associated with the given fxml ID.
     * @param id either the TextBox or ColorPicker fxml ID identifying the seeked LetterGrade.
     * @return the LetterGrade associated with the given fxml ID.
     */
    public static LetterGrade valueOfFromID(String id) {
        return map.get(id);
    }


    /**
     * A string representation of this LetterGrade.
     */
    @Override
    public String toString() {
        return "LetterGrade{" +
                "letter='" + letter + '\'' +
                ", textID1='" + textID1 + '\'' +
                ", textID2='" + textID2 + '\'' +
                ", colorID='" + colorID + '\'' +
                '}';
    }
}
