/**
 * @author Quan Tran
 */

package grader.model.errors;

/**
 * Exception thrown when the user inputs a range into the grade scheme that
 * overlaps with another grade subsection.
 */
public class OverlappingRangeException extends Exception {
    String sectionA, sectionB;

    /**
     * Constructs a new OverlappingRangeException triggered by input for the
     * first section overlapping the second section.
     * @param overlapper the subsection that the user updated to overlap
     * @param overlapped the subsection that was overlapped by the user update
     */
    public OverlappingRangeException(String overlapper, String overlapped) {
        super("Error: the range for '" + overlapper + "' " +
              "cannot overlap the range for '" + overlapped + "'.");
        sectionA = overlapper;
        sectionB = overlapped;
    }

    /**
     * Gets the subsection that was updated to overlap another.
     * @return the subsection overlapping another
     */
    public String getOverlapper() {
        return sectionA;
    }

    /**
     * Gets the subsection that was overlapped by another subsection.
     * @return the subsection overlapped by another
     */
    public String getOverlapped() {
        return sectionB;
    }
}
