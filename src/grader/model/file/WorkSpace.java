package grader.model.file;

import grader.model.gradebook.*;
import grader.model.items.Assignment;
import grader.model.people.*;
import grader.model.curve.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The WorkSpace class is a singleton contains all the information for the
 * current user session, including the open Gradebook, active user,
 * undo/redo history, and the clipboard.
 *
 * @author Gregory Davis
 */
public class WorkSpace {
	/**
	 * The currently logged in user.
	 */
	public static Person user;

	/**
	 * The previous Gradebook for undoing.
	 * Will be null if no changes have been made this session.
	 * This will reset to null when undo is called, since history only goes back
	 * one operation.
	 */
	public static Gradebook prevGradebook;

	/**
	 * The future Gradebook for redoing.
	 * Will be null if undo has not been called.
	 * This will reset to null when redo is called, since history only goes back
	 * one operation.
	 */
	public static Gradebook futureGradebook;

	/**
	 * The value stored in the clipboard.
	 * Will be null if cut/copy have not been called.
	 */
	public static String clipboard;

	/**
	 * The contents of the currently selected item.
	 */
	public static String selectedContext;

	//////////////////////////////

	/**
	 * The currently open Gradebook.
	 */
	public static Gradebook gradebook;

	/**
	 * The Course currently selected in the sidebar.
	 */
	public static Course course;

	/**
	 * The Section currently selected in the sidebar.
	 */
	public static Section section;

	/**
	 * The Group currently selected in the sidebar.
	 */
	public static Group group;

	/**
	 * The Scores currently displayed in the grade spreadsheet.
	 */
	public static Scores scores;

	/**
	 * A list of new raw score changes to be applied to the
	 * grade spreadsheet.
	 */
	public static List<RawScore> deltas = new ArrayList<RawScore>();

	/**
	 * The grade spreadsheet model.
	 */
	public static Spreadsheet spreadsheet;

	/**
	 * The statistics model.
	 */
	//public static Statistics statistics;

	/**
	 * The pie chart model.
	 */
	public static PieChart pieChart;

	/**
	 * The histogram model.
	 */
	public static Histogram histogram;

	/**
	 * Returns a list of students whose grades are being displayed
	 * in the grade spreadsheet.
	 */
	public static List<Student> getStudents() {
	   if (group != null) return group.getStudents();
	   if (section != null) return section.getStudents();
	   if (course != null) return course.getStudents();
	   return null;
   }

	/**
	 * Returns a map of the assignments of which grades are being
	 * displayed in the grade spreadsheet.
	 */
	//public static AssignmentTree getAssignmentTree();

	/**
	 * Returns the scores object for the scores being displayed
	 * in the grade spreadsheet.
	 */
	public static Scores getScores() {
	   return scores;
   }

	/**
	 * Returns a reference to the currently open gradebook.
	 */
	public static Gradebook getGradebook() {
	   return gradebook;
   }

	/**
	 * Returns the gradescheme for the currently selected section.
	 */
	public static GradeScheme getGradeScheme() {
	   if (section == null) return null;
	   return section.getGradeScheme();
   }

   /**
    * Selects a given course, section, and group for the current spreadsheet.
    * Parameters can be null, but only from the bottom up.  For example, if a
    * section is null, the group must also be null.  This would mean that the
    * course is selected in the sidebar, but not any of its specific sections.
    */
   public static void sidebarSelect(Course course, Section section,
                                    Group group) {
      WorkSpace.course = course;
      WorkSpace.section = section;
      WorkSpace.group = group;
   }

   /**
    * Creates a delta for a score for the given student and assignment.
    * Deltas are not saved to persistent storage until the user saves them.
    */
   public static void updateGrade(Student student, Assignment assignment,
                                  double score) {
      deltas.add(new RawScore(student, assignment, score));
   }

   /**
    * Reverts all deltas and restores the Scores object to its state prior to
    * the changes.
    */
   public static void revertGrades() {
      deltas = new ArrayList<RawScore>();
      // restore scores
   }

   /**
    * Commits all deltas to persistent storage.
    */
   public static void saveGrades() {
   }

   /**
    * Overwrites the current section's grade scheme.
    */
   public static void updateGradeScheme(GradeScheme gradeScheme) {
      if (section != null) section.setGradeScheme(gradeScheme);
   }
}
