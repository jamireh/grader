package grader.tests;

import grader.model.errors.OverlappingRangeException;
import grader.model.gradebook.WorkSpace;
import grader.model.gradebook.*;
import grader.model.gradebook.gradescheme.GradeScheme;
import grader.model.gradebook.gradescheme.LetterGrade;
import grader.model.gradebook.scores.RawScore;
import grader.model.gradebook.scores.Scores;
import grader.model.gradebook.Section;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.items.Percentage;
import grader.model.people.Group;
import grader.model.people.Name;
import grader.model.people.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The WorkSpaceTest class is the companion testing class for the grader
 * WorkSpace. It implements the following module test plan:
 * <pre>
 *    Phase 1: Unit test the constructor.
 *
 *    Phase 2: Unit test the sidebarSelect method and the getters for the
 *             scope.
 *
 *    Phase 3: Unit test getters for scope related data such as getStudents,
 *             getAssignmentTree, and getGradeScheme.
 *
 *    Phase 4: Unit test updating the grade scheme.
 *
 *    Phase 5: Unit test updating, reverting, and saving grades, along with
 *             undo and redo functionality, including canUndo/canRedo
 *             and getLatestChange and getLatestUndo.
 *
 *    Phase 6: Unit test adding a student when a section is or is not selected,
 *             and adding an assignment when a course is or is not selected.
 *
 *    Phase 7: Unit test selecting score scope, cutting, copying, and pasting.
 * 	                                       								 </pre>
 *
 * @author Gregory Davis
 */
public class WorkSpaceTest {
    /**
     * Phase 1 testing: Constructor.
     * For now, we test the singleton instance for canned instantiation.
     * The Gradebook should be set to the static Canned Gradebook, and the
     * deltas and future deltas should be empty.
     */
    @org.junit.Test
    public void testConstructor() {
        assert (WorkSpace.instance.getGradebook()
                .equals(Gradebook.getCannedGradebook()));
        assert (WorkSpace.instance.deltas.size() == 0);
        assert (WorkSpace.instance.futureDeltas.size() == 0);
        assert (WorkSpace.instance.getPieChart() != null);
        assert (WorkSpace.instance.getHistogram() != null);
    }

    /**
     * Phase 2 testing: Sidebar control.
     * Ensure that the scope is properly changed by the sidebarSelect method.
     *
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1      valid course     null               getter methods should
     *          valid section                       return input values
     *          null group                          (getGroup() == null)
     *   2      valid course     null               getter methods should
     *          valid section                       return input values
     *          valid group                         (none should be null)
     *   3      null course      null               getter methods should
     *          null section                        return input values
     *          null group                          (all should be null)
     *
     */
    @org.junit.Test
    public void testSidebarSelect() throws Exception {
        Gradebook canned = WorkSpace.instance.getGradebook();
        Course course = canned.courses.get(0);
        Section section = course.sections.get(0);

        WorkSpace.instance.sidebarSelect(course, section, null);

        assert (course.equals(WorkSpace.instance.getCourse()));
        assert (section.equals(WorkSpace.instance.getSection()));
        assert (WorkSpace.instance.getGroup() == null);


        WorkSpace.instance.addGroup(new Group("Kewl Kids", ((ArrayList<Student>) section.getStudents())));
        WorkSpace.instance.sidebarSelect(course, section, section.groups.get(0));

        assert (course.equals(WorkSpace.instance.getCourse()));
        assert (section.equals(WorkSpace.instance.getSection()));
        assert (section.groups.equals(WorkSpace.instance.getSection().groups));

        WorkSpace.instance.sidebarSelect(null, null, null);

        assert (WorkSpace.instance.getCourse() == null);
        assert (WorkSpace.instance.getSection() == null);
        assert (WorkSpace.instance.getGroup() == null);
    }

    /**
     * Phase 3 testing: Scope related data.
     * Ensure that scope related data is properly updated with scope changes.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1      null course      empty students     students list should be
     *          null section                        empty
     *          null group
     *   2      valid course     course students    students list should
     *          null section                        contain course students
     *          null group
     *   3      valid course     section students   students list should
     *          valid section                       contain section students
     *          null group                          only
     */
    @org.junit.Test
    public void testScopedData() {
        Gradebook canned = WorkSpace.instance.getGradebook();
        Course course = canned.courses.get(0);
        Section section = course.sections.get(0);

        WorkSpace.instance.sidebarSelect(null, null, null);
        assert (WorkSpace.instance.getStudents().size() == 0);

        WorkSpace.instance.sidebarSelect(course, null, null);
        assert (WorkSpace.instance.getStudents().equals(course.getStudents()));

        WorkSpace.instance.sidebarSelect(course, section, null);
        assert (WorkSpace.instance.getStudents().equals(section.getStudents()));

        assert (WorkSpace.instance.getAssignmentTree().equals(course.getAssignmentTree()));
        assert (WorkSpace.instance.getGradeScheme() != null);
    }

    /**
     * Phase 4 testing: GradeScheme updating.
     * Ensure that updating the GradeScheme changes the scoped section's
     * GradeScheme.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1      valid course      null              gradescheme should be null
     *          null section                        since gradeschemes are
     *          null group                          section specific
     *   2      valid course      section           gradescheme should not
     *          valid section     grade scheme      be null
     *          null group
     *   3      valid section     true              gradeSchemeChanged == true
     *          change grade      gradeSchemeChanged
     *          scheme
     *   4      valid section     false             gradeSchemeChanged == false
     *          update grade      gradeSchemeChanged
     *          scheme
     */
    @org.junit.Test
    public void testGradeScheme() {
        Gradebook canned = WorkSpace.instance.getGradebook();
        Course course = canned.courses.get(0);
        Section section = course.sections.get(0);

        // Test for null grade scheme if section is null.
        WorkSpace.instance.sidebarSelect(course, null, null);
        assert (WorkSpace.instance.getGradeScheme() == null);

        // Test proper creation of temporary grade scheme.
        WorkSpace.instance.sidebarSelect(course, section, null);
        GradeScheme scheme = WorkSpace.instance.getGradeScheme();
        assert (scheme != null);

        try {
            scheme.updateGradeRange(LetterGrade.A_MINUS, new Percentage(89));
            WorkSpace.instance.setGradeSchemeChanged();
            assert (WorkSpace.instance.gradeSchemeChanged);
            WorkSpace.instance.updateGradeScheme();
            assert (!WorkSpace.instance.gradeSchemeChanged);
        } catch (OverlappingRangeException e) {
            assert (false);
        }
    }

    /**
     * Phase 5 testing: Score updating.
     * Test updating scores by exercising the deltas and futureDeltas.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1      valid student     null              update accepted
     *          valid assignment                    canUndo == true
     *          score1
     *   2      valid student     null              update accepted
     *          valid assignment                    canUndo == true
     *          score2
     *   3      null              null              deltas size == 2,
     *                                              future deltas size == 0,
     *                                              detlas.get(0) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw1,
     *                                              detlas.get(1) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw2.
     *   4      undo              null              deltas size == 1,
     *                                              future deltas size == 1,
     *                                              detlas.get(0) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw1
     *                                              futureDeltas.get(0) corresponds,
     *                                              to input student, assignment,
     *                                              and has score raw2,
     *                                              canRedo == true.
     *   5      undo              null              deltas size == 0,
     *                                              future deltas size == 2,
     *                                              futureDeltas.get(1) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw1,
     *                                              futureDeltas.get(0) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw2,
     *                                              canRedo == true.
     *   6      redo twice        null              deltas size == 2,
     *                                              future deltas size == 0,
     *                                              deltas.get(0) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw1,
     *                                              deltas.get(1) corresponds
     *                                              to input student, assignment,
     *                                              and has score raw2,
     *                                              canRedo == false.
     *   7      save grades       null              deltas size == 0,
     *                                              future deltas size == 0,
     *                                              canRedo == false,
     *                                              canUndo == false,
     *                                              getRawScore(student, assignment)
     *                                                == score2.
     *   8      update grade      null              deltas size == 1,
     *          to score1                           future deltas size == 0,
     *                                              getLatestChange() corresponds
     *                                                to student, assignment, score1
     *   9      undo              null              getLatestUndo() corresponds
     *                                                to student, assignment, score1
     *   10     revert grades     null              canUndo == false,
     *                                              canRedo == false,
     *                                              getLatestChange() == null,
     *                                              getLatestUndo() == null.
     */
    @org.junit.Test
    public void testGradeChanges() {
        Gradebook canned = WorkSpace.instance.getGradebook();
        Course course = canned.courses.get(0);
        Section section = course.sections.get(0);
        Student student = section.getStudents().get(0);
        Assignment assignment = course.getAssignmentTree().getAssignmentIterator().next();
        double score1 = 99.9;
        double score2 = 88.8;

        WorkSpace.instance.sidebarSelect(course, section, null);

        // Update grade twice.
        WorkSpace.instance.updateGrade(student, assignment, score1);
        WorkSpace.instance.updateGrade(student, assignment, score2);
        assert (WorkSpace.instance.canUndo());

        // Check deltas.
        assert (WorkSpace.instance.deltas.size() == 2);
        assert (WorkSpace.instance.futureDeltas.size() == 0);
        RawScore raw1 = WorkSpace.instance.deltas.get(0);
        assert (raw1.getStudent().equals(student));
        assert (raw1.getAssignment().equals(assignment));
        assert (raw1.getScore() == score1);
        RawScore raw2 = WorkSpace.instance.deltas.get(1);
        assert (raw2.getStudent().equals(student));
        assert (raw2.getAssignment().equals(assignment));
        assert (raw2.getScore() == score2);

        // Undo change.
        WorkSpace.instance.undo();
        assert (WorkSpace.instance.canUndo());
        assert (WorkSpace.instance.deltas.size() == 1);
        assert (WorkSpace.instance.futureDeltas.size() == 1);
        raw1 = WorkSpace.instance.deltas.get(0);
        assert (raw1.getStudent().equals(student));
        assert (raw1.getAssignment().equals(assignment));
        assert (raw1.getScore() == score1);
        raw2 = WorkSpace.instance.futureDeltas.get(0);
        assert (raw2.getStudent().equals(student));
        assert (raw2.getAssignment().equals(assignment));
        assert (raw2.getScore() == score2);

        // Undo second change.
        WorkSpace.instance.undo();
        assert (!WorkSpace.instance.canUndo());
        assert (WorkSpace.instance.deltas.size() == 0);
        assert (WorkSpace.instance.futureDeltas.size() == 2);
        raw1 = WorkSpace.instance.futureDeltas.get(1);
        assert (raw1.getStudent().equals(student));
        assert (raw1.getAssignment().equals(assignment));
        assert (raw1.getScore() == score1);
        raw2 = WorkSpace.instance.futureDeltas.get(0);
        assert (raw2.getStudent().equals(student));
        assert (raw2.getAssignment().equals(assignment));
        assert (raw2.getScore() == score2);

        // Redo changes.
        assert (WorkSpace.instance.canRedo());
        WorkSpace.instance.redo();
        WorkSpace.instance.redo();
        assert (!WorkSpace.instance.canRedo());
        assert (WorkSpace.instance.canUndo());
        assert (WorkSpace.instance.deltas.size() == 2);
        assert (WorkSpace.instance.futureDeltas.size() == 0);

        raw1 = WorkSpace.instance.deltas.get(0);
        assert (raw1.getStudent().equals(student));
        assert (raw1.getAssignment().equals(assignment));
        assert (raw1.getScore() == score1);
        raw2 = WorkSpace.instance.deltas.get(1);
        assert (raw2.getStudent().equals(student));
        assert (raw2.getAssignment().equals(assignment));
        assert (raw2.getScore() == score2);

        // Save grades.
        WorkSpace.instance.saveGrades();
        assert (WorkSpace.instance.deltas.size() == 0);
        assert (WorkSpace.instance.futureDeltas.size() == 0);
        assert (!WorkSpace.instance.canRedo());
        assert (!WorkSpace.instance.canUndo());
        Scores scores = canned.getScores();
        assert (scores.getRawScore(student, assignment) == score2);

        // Second pass
        WorkSpace.instance.updateGrade(student, assignment, score1);
        raw1 = WorkSpace.instance.getLatestChange();
        assert (raw1.getStudent().equals(student));
        assert (raw1.getAssignment().equals(assignment));
        assert (raw1.getScore() == score1);
        WorkSpace.instance.undo();
        raw2 = WorkSpace.instance.getLatestUndo();
        assert (raw1.equals(raw2));

        WorkSpace.instance.revertGrades();
        assert (!WorkSpace.instance.canUndo());
        assert (!WorkSpace.instance.canRedo());
        assert (WorkSpace.instance.getLatestChange() == null);
        assert (WorkSpace.instance.getLatestUndo() == null);
    }

    /**
     * Phase 6 testing: Adding a Student and Assignment
     * Test adding a student when a section is selected and when one isn't.
     * Test adding an assignment when a course is selected and when one isn't.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1      add new student     null            getStudents returns empty
     *          when no course                      list
     *          section or group
     *          is selected
     *   2      add new assignment  null            getAssignmentTree returns
     *          when no course is                   empty tree
     *          selected
     *   3      add new student     null            getStudents contains the
     *          when a valid course                 added student
     *          and section is
     *          selected
     *   4      add new assignment  null            getAssignmentTree contains
     *          when a valid course                 added assignment
     *          is selected
     */
    @org.junit.Test
    public void testAdd() {
        WorkSpace.instance.sidebarSelect(null, null, null);
        WorkSpace.instance.update();
        assert (WorkSpace.instance.getCourse() == null);
        assert (WorkSpace.instance.getSection() == null);
        assert (WorkSpace.instance.getGroup() == null);
        assert (WorkSpace.instance.getStudents().isEmpty());

        Gradebook canned = WorkSpace.instance.getGradebook();
        Course course = canned.courses.get(0);
        Section section = course.sections.get(0);
        Student newStudent = new Student(new Name("Test", "J", "Student"));
        Assignment newAssignment = new Assignment("Test Assignment");

        // Add a student when no section is selected.
        WorkSpace.instance.addStudent(newStudent);
        assert (WorkSpace.instance.getStudents().isEmpty());

        // Add an assignment when no course is selected.
        WorkSpace.instance.addAssignment(null, newAssignment);
        assert (!WorkSpace.instance.getAssignmentTree().getAssignmentIterator().hasNext());

        // Add a student when a section is selected.
        WorkSpace.instance.sidebarSelect(course, section, null);
        WorkSpace.instance.addStudent(newStudent);
        assert (WorkSpace.instance.getStudents().contains(newStudent));

        // Add an assignment when a course is selected.
        WorkSpace.instance.addAssignment(null, newAssignment);
        List<Assignment> assignments = new ArrayList<Assignment>();
        AssignmentTree.AssignmentIterator iter = WorkSpace.instance.getAssignmentTree().getAssignmentIterator();
        while (iter.hasNext()) assignments.add(iter.next());
        assert (assignments.contains(newAssignment));
    }


    /**
     * Phase 7 testing: Selecting score scope, cutting, copying, and pasting.
     *                                                                    <pre>
     *  Test
     *  Case    Input            Output             Remarks
     * ====================================================================
     *   1      set selected     null               selectedStudent == student
     *          student
     *   2      set selected     null               canCopy() == false,
     *          score to null                       canPaste() == false,
     *                                              validCopy == false
     *   3      set selected     null               copiedScore == score
     *          score to valid
     *          score and copy
     *   4      set selected     null               score should be updated
     *          score, copy score,                  for second selected score
     *          set selected score                  to the first score
     *          to a different score,
     *          and paste
     *   5      cut score        null               selectedScore.getScore() == 0.0,
     *                                              copiedScore = score
     */
    @org.junit.Test
    public void testCopyPaste() {
        Gradebook canned = WorkSpace.instance.getGradebook();
        Course course = canned.courses.get(0);
        Section section = course.sections.get(0);
        WorkSpace.instance.sidebarSelect(course, section, null);

        Assignment newAssignment = new Assignment("Test Assignment");
        Student newStudent = new Student(new Name("Test", "J", "Student"));

        // Test set selected student.
        WorkSpace.instance.setSelectedStudent(newStudent);
        assert (newStudent.equals(WorkSpace.instance.selectedStudent));

        // Test set selected score to null, which should disable copy and paste.
        WorkSpace.instance.setSelectedScore(null);
        assert (!WorkSpace.instance.canCopy() && !WorkSpace.instance.canPaste());
        WorkSpace.instance.copy();
        WorkSpace.instance.cut();
        assert (!WorkSpace.instance.validCopy);

        // Extract and select an arbitrary score.
        Scores scores = WorkSpace.instance.getScores();
        Map<Assignment, RawScore> map = scores.getScoresMap(WorkSpace.instance.getStudents().get(0));
        RawScore score = map.get(map.keySet().iterator().next());
        WorkSpace.instance.setSelectedScore(score);

        // Copy the selected score.
        WorkSpace.instance.copy();
        assert (WorkSpace.instance.copiedScore == score.getScore());

        // Select a new score, copy it, select the old, and paste the score.
        double newScore = 99.9;
        WorkSpace.instance.setSelectedScore(new RawScore(newStudent, newAssignment, newScore));
        WorkSpace.instance.copy();
        WorkSpace.instance.setSelectedScore(score);
        WorkSpace.instance.paste();
        assert (score.getScore() == newScore);

        // Cut the score.
        WorkSpace.instance.cut();
        assert (WorkSpace.instance.selectedScore.getScore() == 0.0);
        assert (WorkSpace.instance.copiedScore == newScore);

        WorkSpace.instance.deltas.clear();
        WorkSpace.instance.futureDeltas.clear();
    }
}
