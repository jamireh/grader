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
@Suite.SuiteClasses({ AssignmentTest.class, CategoryTest.class, GradeRangeTest.class, GradeSchemeTest.class, GroupTest.class, NameTest.class, LetterGradeTest.class,
        PercentageTest.class, StatisticsTest.class, StudentTest.class, WorkSpaceTest.class} )
public final class TestSuite {}
