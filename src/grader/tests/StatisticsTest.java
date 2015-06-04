package grader.tests;

import grader.model.gradebook.stats.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test class for the Statistics class.
 * It implements the following module test plan:
 *
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test min value access and computation.
 *
 *    Phase 3: Unit test max value access and computation.
 *
 *    Phase 4: Unit test mean value access and computation.
 *
 *    Phase 5: Stress test with expected input * 10.
 *
 * @author Quan Tran
 */
public class StatisticsTest {
    private static final double DELTA = 1e-12;
    private static final int LIST_A_SIZE = 11;
    private static final int LIST_B_SIZE = 100;
    private static final int STRESS_COUNT = 10000;

    private List<Double> listA, listB;
    private double aMax, aMean, aMin, bMax, bMean, bMin;

    /**
     * Sets up the lists for the tests.
     */
    @Before
    public void setUp() {
        double total = 0.0;
        aMax = bMin = 100.0;
        aMin = bMax = 0.0;

        // create a list of multiples of ten
        listA = new ArrayList<Double>();
        for (int i = 0; i < LIST_A_SIZE; ++i) {
            total += 10.0 * i;
            listA.add(10.0 * i);
        }
        aMean = total / LIST_A_SIZE;

        // create a list of random doubles
        listB = new ArrayList<Double>();
        Random rand = new Random();
        total = 0.0;

        // populate the list
        for (int i = 0; i < LIST_B_SIZE; ++i) {
            double next = rand.nextDouble() % 100.0;

            // update min and max
            if (next > bMax)
                bMax = next;
            else if (next < bMin)
                bMin = next;

            total += next;
            listB.add(next);
        }
        bMean = total / LIST_B_SIZE;
    }

    /**
     * 1. Test the constructor.
     */
    @Test
    public void testConstructor() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertNotNull(statsA);
        assertNotNull(statsB);
    }

    /**
     * 2. Test the minimum values.
     */
    @Test
    public void testMin() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertEquals(statsA.min, aMin, DELTA);
        assertEquals(statsB.min, bMin, DELTA);
    }

    /**
     * 3. Test the maximum values.
     */
    @Test
    public void testMax() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertEquals(statsA.max, aMax, DELTA);
        assertEquals(statsB.max, bMax, DELTA);
    }

    /**
     * 4. Test the mean values.
     */
    @Test
    public void testMean() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertEquals(statsA.mean, aMean, DELTA);
        assertEquals(statsB.mean, bMean, DELTA);
    }

    /**
     * 5. Stress test values.
     */
    @Test
    public void stressTest() {
        Statistics[] stats = new Statistics[STRESS_COUNT];
        for (int i = 0; i < STRESS_COUNT; ++i)
            stats[i] = new Statistics(listB);
    }
}