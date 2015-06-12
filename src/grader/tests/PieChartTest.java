package grader.tests;

import grader.model.curve.PieChart;
import grader.model.gradebook.*;
import grader.model.items.Percentage;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The PieChart class is the companion testing class for the
 * PieChart class. It implements the following module test plan:
 *									                                       <pre>
 *    Phase 1: Unit test getEntry().
 *
 *    Phase 2: Unit test update()
 *
 *    Phase 3: Repeat phases 1 through 2.
 *	                                       								 </pre>
 *	  @author Mallika Potter
 */
public class PieChartTest {

    /**
     * Unit test the getter methods of the class.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1        -          0             Checks default values.
     *   2        -          0             Checks default values.
     *   3        -          0             Checks default values.
     *   4        -          0             Checks default values.
     *   5        -          0             Checks default values.
     *   6        -          0             Checks default values.
     *   7        -          0             Checks default values.
     *   8        -          0             Checks default values.
     *   9        -          0             Checks default values.
     *   10       -          0             Checks default values.
     *   11       -          0             Checks default values.
     *   12       -          0             Checks default values.
     *   13       -          0             Checks default values.
     *
     *                                                  </pre>
     */
    @Test
    public void testGetters() throws Exception{
        PieChart temp = WorkSpace.instance.getPieChart();
        Gradebook gradeTemp = WorkSpace.instance.getGradebook();
        Course course = gradeTemp.courses.get(0);
        Section sec = course.sections.get(1);
        WorkSpace.instance.sidebarSelect(course, sec, null);

        assertEquals(temp.getNumAPlus(), 100.0, .1);
        assertEquals(temp.getNumA(), 0.0, .1);
        assertEquals(temp.getNumAMinus(), 0.0, .1);
        assertEquals(temp.getNumBPlus(), 0.0, .1);
        assertEquals(temp.getNumB(), 0.0, .1);
        assertEquals(temp.getNumBMinus(), 0.0, .1);
        assertEquals(temp.getNumCPlus(), 0.0, .1);
        assertEquals(temp.getNumC(), 0.0, .1);
        assertEquals(temp.getNumCMinus(), 0.0, .1);
        assertEquals(temp.getNumDPlus(), 0.0, .1);
        assertEquals(temp.getNumD(), 0.0, .1);
        assertEquals(temp.getNumDMinus(), 0.0, .1);
        assertEquals(temp.getNumF(), 0.0, .1);


        //fix
    }

    /**
     * Unit test the update method of the class.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input      Output          Remarks
     * ====================================================================
     *   1        -          0             Checks default values.
     *   2        -          25            Checks adjusted values.
     *
     *                                                  </pre>
     */
    @Test
    public void testUpdate() throws Exception{

        PieChart pie = WorkSpace.instance.getPieChart();

        Gradebook gradeTemp = WorkSpace.instance.getGradebook();
        Course course = gradeTemp.courses.get(0);
        Section sec = course.sections.get(1);
        WorkSpace.instance.sidebarSelect(course, sec, null);

        assertEquals(pie.getNumAPlus(), 100.0, .1);

        pie.addToGrade(new Percentage(95.0));
        pie.addToGrade(new Percentage(91.0));
        pie.addToGrade(new Percentage(89.0));
        pie.addToGrade(new Percentage(85.0));


    }




}
