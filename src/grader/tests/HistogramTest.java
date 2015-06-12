package grader.tests;

import grader.model.curve.Entry;
import grader.model.curve.Histogram;
import grader.model.gradebook.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The HistogramTest class is the companion testing class for the
 * Histogram class. This is a line. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test getEntry().
 *
 *    Phase 2: Unit test adjustHistogram().
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
     *   1        0.0       new Entry("A-", "90.0", " ")             Checks default values.
     *
     *                                                  </pre>
     */
    @Test
    public void testGetEntry()
    {
        grader.model.curve.Histogram histogram = WorkSpace.instance.getHistogram();
        Gradebook gradeTemp = WorkSpace.instance.getGradebook();
        Course course = gradeTemp.courses.get(0);
        Section sec = course.sections.get(1);
        WorkSpace.instance.sidebarSelect(course, sec, null);

        histogram.apply();
        histogram.push();
        assertEquals(histogram.getEntry(90.0), new Entry("A-", "90.0", ""));

    }

    /**
     * Unit test adjustHistogram.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1       90.0     new Entry("", "90.0", "")
     *   2       91.0     new Entry("A-", "91.0", "")
     *   3       91.0     new Entry("", "91.0", "")
     *   4       89.0     new Entry("A-", "89.0", "")
     *   5       88.0     new Entry("B", "88.0", "")
     *   6       89.0     new Entry("B+", "89.0", "")
     *   7       73.0     new Entry("C+", "73.0", "")
     *   8       72.0     new Entry("C", "72.0", "")
     *                                                  </pre>
     */
    @Test
    public void testUpdate()
    {
        grader.model.curve.Histogram histogram = WorkSpace.instance.getHistogram();
        Gradebook gradeTemp = WorkSpace.instance.getGradebook();
        Course course = gradeTemp.courses.get(0);
        Section sec = course.sections.get(1);
        WorkSpace.instance.sidebarSelect(course, sec, null);

        histogram.adjustHistogram(90.0, 91.0, "A-");
        assertEquals(histogram.getEntry(90.0), new Entry(" ", "90.0", ""));
        assertEquals(histogram.getEntry(91.0), new Entry("A-", "91.0", ""));

        histogram.adjustHistogram(91.0, 89.0, "A-");
        assertEquals(histogram.getEntry(91.0), new Entry(" ", "91.0", ""));
        assertEquals(histogram.getEntry(89.0), new Entry("A-", "89.0", ""));

        histogram.adjustHistogram(83.0, 88.0, "B");
        assertEquals(histogram.getEntry(88.0), new Entry("B", "88.0", ""));
        assertEquals(histogram.getEntry(89.0), new Entry("B+", "89.0", ""));

        histogram.adjustHistogram(77.0, 73.0, "C+");
        assertEquals(histogram.getEntry(73.0), new Entry("C+", "73.0", ""));
        assertEquals(histogram.getEntry(72.0), new Entry("C", "72.0", ""));





    }


}
