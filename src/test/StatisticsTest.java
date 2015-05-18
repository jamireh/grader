package test;

import grader.model.gradebook.stats.Statistics;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Test class for the Statistics class.
 * @author Quan Tran
 */
public class StatisticsTest {
    private static final double DELTA = 1e-12;
    private static final int LIST_A_SIZE = 11;
    private static final int LIST_B_SIZE = 100;

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
     * Test the constructor.
     */
    @Test
    public void testConstructor() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertNotNull(statsA);
        assertNotNull(statsB);
    }

    /**
     * Test the minimum values.
     */
    @Test
    public void testMin() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertEquals(statsA.min, aMin, DELTA);
        assertEquals(statsB.min, bMin, DELTA);
    }

    /**
     * Test the maximum values.
     */
    @Test
    public void testMax() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertEquals(statsA.max, aMax, DELTA);
        assertEquals(statsB.max, bMax, DELTA);
    }

    /**
     * Test the mean values.
     */
    @Test
    public void testMean() {
        Statistics statsA = new Statistics(listA);
        Statistics statsB = new Statistics(listB);

        assertEquals(statsA.mean, aMean, DELTA);
        assertEquals(statsB.mean, bMean, DELTA);
    }

    /**
     * Stress test values.
     */
    @Test
    public void stressTest() {
        final int STRESS_COUNT = 10000;

        Statistics[] stats = new Statistics[STRESS_COUNT];
        for (int i = 0; i < STRESS_COUNT; ++i)
            stats[i] = new Statistics(listB);
    }
}