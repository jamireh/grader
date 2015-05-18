import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.*;

/**
 * Used for jar execution. Unless you're Jon, don't touch.
 *
 * @author Jon Amireh
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ AssignmentTest.class, CategoryTest.class, GroupTest.class, NameTest.class, PercentageTest.class, StatisticsTest.class, StudentTest.class, WorkSpaceTest.class} )
public final class TestSuite {}
