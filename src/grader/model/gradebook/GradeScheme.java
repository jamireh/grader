package grader.model.gradebook;
import java.util.*;

/**
 * The model containing the range breakdown for each letter-grade 
 * as well as the associated colors.
 */
public class GradeScheme
{   
    /**
     * Comprised of every available grade range.
     */
    public Collection<DivisionBar> divisions;

    /**
     * Upon changing of the data, changes the modified division
     * as well as adjusts the other ranges accordingly.
     * pre:
     *    forall(DivisionBar bar;
     *    divisions.contains(bar);
     *    bar != null);
     */
    public void updateDivisions()
    {

    }

}
