import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AssignmentTest.class, CategoryTest.class, GroupTest.class, NameTest.class, PercentageTest.class, StudentTest.class, WorkSpaceTest.class} )
public final class TestSuite {} // or ModuleFooSuite, and that in AllTests
