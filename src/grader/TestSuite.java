package grader;

import grader.tests.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Used for jar execution. Unless you're Jon, don't touch.
 *
 * @author Jon Amireh
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ AssignmentTest.class, CategoryTest.class, GradeRangeTest.class, GradeSchemeTest.class, GroupTest.class, HistogramTest.class,
        NameTest.class, LetterGradeTest.class, PercentageTest.class, PieChartTest.class, StatisticsTest.class, StudentTest.class, WorkSpaceTest.class, PredictionTest.class} )
public final class TestSuite {}
