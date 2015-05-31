package grader.tests;

import grader.model.curve.Entry;
import grader.model.curve.Histogram;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The HistogramTest class is the companion testing class for the
 * Histogram class. This is a line. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test getEntry().
 *
 *    Phase 2: Unit test update()
 *
 *    Phase 3: Repeat phases 1 through 2.
 *	                                       								 </pre>
 *	  @author Mallika Potter
 */
public class HistogramTest {

    /**
     * Unit test getEntry.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1        0.0       new Entry(" ", "0", " ")             Checks default values.
     *
     *                                                  </pre>
     */
    @Test
    public void testGetEntry()
    {
        Histogram histogram = new Histogram();
        //assertEquals(histogram.getEntry(0.0), new Entry(" ", "0.0", " "));
    }

    /**
     * Unit test update.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *
     *                                                  </pre>
     */
    @Test
    public void testUpdate()
    {
        //not sure how to do this
    }


}
