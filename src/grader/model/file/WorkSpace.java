package grader.model.file;

import grader.model.gradebook.*;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.*;
import grader.model.curve.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * The WorkSpace class is a singleton contains all the information for the
 * current user session, including the open Gradebook, active user,
 * undo/redo history, and the clipboard.
 *
 * @author Gregory Davis
 */
public class WorkSpace extends Observable {
   public static final WorkSpace instance = new WorkSpace();

   /**
    * Constructor.
    * Instantiates necessary models.
    */
   private WorkSpace() {
      gradebook = Gradebook.getCannedGradebook();
      sidebar = new Sidebar();
      spreadsheet = new Spreadsheet();
      statistics = new StatsContainer(null, null, null);
      pieChart = new PieChart();;
      histogram = new Histogram();

      addObserver(sidebar);
      addObserver(spreadsheet);
      addObserver(statistics);
      addObserver(pieChart);
      addObserver(histogram);
   }

	/**
	 * The currently logged in user.
	 */
	public Person user;

	/**
	 * The previous Gradebook for undoing.
	 * Will be null if no changes have been made this session.
	 * This will reset to null when undo is called, since history only goes back
	 * one operation.
	 */
	public Gradebook prevGradebook;

	/**
	 * The future Gradebook for redoing.
	 * Will be null if undo has not been called.
	 * This will reset to null when redo is called, since history only goes back
	 * one operation.
	 */
	public Gradebook futureGradebook;

	/**
	 * The value stored in the clipboard.
	 * Will be null if cut/copy have not been called.
	 */
	public String clipboard;

	/**
	 * The contents of the currently selected item.
	 */
	public String selectedContext;

	//////////////////////////////

	/**
	 * The currently open Gradebook.
	 */
	public Gradebook gradebook;

	/**
	 * The Course currently selected in the sidebar.
	 */
	public Course course;

	/**
	 * The Section currently selected in the sidebar.
	 */
	public Section section;

	/**
	 * The Group currently selected in the sidebar.
	 */
	public Group group;

	/**
	 * The Scores currently displayed in the grade spreadsheet.
	 */
	public Scores scores;

	/**
	 * A list of new raw score changes to be applied to the
	 * grade spreadsheet.
	 */
	public List<RawScore> deltas = new ArrayList<RawScore>();

    /**
     * The gradebook sidebar model.
     */
    public Sidebar sidebar;

	/**
	 * The grade spreadsheet model.
	 */
	public Spreadsheet spreadsheet;

	/**
	 * The statistics model.
	 */
	public StatsContainer statistics;

	/**
	 * The pie chart model.
	 */
	public PieChart pieChart;

	/**
	 * The histogram model.
	 */
	public Histogram histogram;

	/**
	 * Returns a list of students whose grades are being displayed
	 * in the grade spreadsheet.
	 */
	public List<Student> getStudents() {
	   if (group != null) return group.getStudents();
	   if (section != null) return section.getStudents();
	   if (course != null) return course.getStudents();
	   return null;
   }

	/**
	 * Returns a map of the assignments of which grades are being
	 * displayed in the grade spreadsheet.
	 */
	public AssignmentTree getAssignmentTree() {
	   if (course != null) return course.getAssignmentTree();
	   else return null;
   }

	/**
	 * Returns the scores object for the scores being displayed
	 * in the grade spreadsheet.
	 */
	public Scores getScores() {
	   return scores;
   }

	/**
	 * Returns a reference to the currently open gradebook.
	 */
	public Gradebook getGradebook() {
	   return gradebook;
   }

	/**
	 * Returns the gradescheme for the currently selected section.
	 */
	public GradeScheme getGradeScheme() {
	   if (section == null) return null;
	   return section.getGradeScheme();
   }

   /**
    * Selects a given course, section, and group for the current spreadsheet.
    * Parameters can be null, but only from the bottom up.  For example, if a
    * section is null, the group must also be null.  This would mean that the
    * course is selected in the sidebar, but not any of its specific sections.
    */
   public void sidebarSelect(Course course, Section section,
                                    Group group) {
      this.course = course;
      this.section = section;
      this.group = group;
      setChanged();
      notifyObservers();
   }

   /**
    * Creates a delta for a score for the given student and assignment.
    * Deltas are not saved to persistent storage until the user saves them.
    */
   public void updateGrade(Student student, Assignment assignment,
                                  double score) {
      deltas.add(new RawScore(student, assignment, score));
   }

   /**
    * Reverts all deltas and restores the Scores object to its state prior to
    * the changes.
    */
   public void revertGrades() {
      deltas = new ArrayList<RawScore>();
      // restore scores
       setChanged();
       notifyObservers();
   }

   /**
    * Commits all deltas to persistent storage.
    */
   public void saveGrades() {
       setChanged();
       notifyObservers();
   }

   /**
    * Overwrites the current section's grade scheme.
    */
   public void updateGradeScheme(GradeScheme gradeScheme) {
      if (section != null) {
          section.setGradeScheme(gradeScheme);
          setChanged();
          notifyObservers();
      }
   }
}
