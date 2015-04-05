package grader.model.items;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

/**
 * Represents an assignment of a specific category handed in via
 * an online handin tool.
 */

public abstract class HandinItem extends Assignment
{
    /** the handin submission associated with this item */
    Submission submission;

    /** a history of this handin item's past submissions */
    Collection<Submission> history;

    /** the handin directory associated with this item */
    Path directory;

    /** 
     * Updates the current submission, adding the previous 
     * submission to the history.
     * @param newFile the newly submitted file
     <pre>
     post:
       // the File contained in this Item's Submission is updated
       this.submission.file.equals(newFile);
    </pre>
     */
    public abstract void updateSubmission(File newFile);

    /**
     * Searches this item's submission history for the given query,
     * returning the first item matched.
     * @param expression the expression to search for
     * @return the first submission found matching the given expression
     <pre>
     pre:
       // this File's history must not be empty
       this.history.size() > 0;
    </pre>
     */
    public abstract Submission searchHistory(Object expression);
}
