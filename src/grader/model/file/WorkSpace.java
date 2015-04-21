package grader.model.file;

import grader.model.gradebook.*;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.*;
import grader.model.curve.*;

import java.util.*;

/**
 * The WorkSpace class is a singleton contains all the information for the
 * current user session, including the open Gradebook, active user,
 * undo/redo history, and the clipboard.
 *
 * @author Gregory Davis
 */
public class WorkSpace extends Observable {
   /**
    * Singleton WorkSpace instance.
    */
   public static final WorkSpace instance = new WorkSpace();

   /**
    * Constructor.
    * Instantiates necessary models.
    */
   private WorkSpace() {
      gradebook = Gradebook.getCannedGradebook();
	   deltas = new ArrayList<RawScore>();
	   futureDeltas = new ArrayList<RawScore>();

      sidebar = new Sidebar(gradebook);
      spreadsheet = new Spreadsheet();
      statistics = new StatsContainer();
      pieChart = new PieChart();
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


	////////////////////////////////////
	/* CLIPBOARD -- MOVE TO NEW CLASS */
	////////////////////////////////////

	/**
	 * The value stored in the clipboard.
	 * Will be null if cut/copy have not been called.
	 */
	public String clipboard;

	/**
	 * The contents of the currently selected item.
	 */
	public String selectedContext;
	////////////////////////////////////


   /////////////////////////////
   /* CURRENT GRADEBOOK SCOPE */
   /////////////////////////////
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
	public List<RawScore> deltas;

	/**
	 * A list of undone deltas for undo/redo operations.
	 */
	public List<RawScore> futureDeltas;
   /////////////////////////////


   //////////////////////
   /* COMPONENT MODELS */
   //////////////////////
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
   //////////////////////


	/////////////////////////////////
	/* QUERY METHODS FOR OBSERVERS */
	/////////////////////////////////
	/**
	 * Returns a reference to the currently open gradebook.
	 */
	public Gradebook getGradebook() {
	   return gradebook;
   }

	/**
	 * Returns a list of students whose grades are being displayed
	 * in the grade spreadsheet.
	 * Returns an empty list if nothing is in scope.
	 */
	public List<Student> getStudents() {
	   if (group != null) return group.getStudents();
	   if (section != null) return section.getStudents();
	   if (course != null) return course.getStudents();
	   return new ArrayList<Student>();
   }

	/**
	 * Returns a map of the assignments of which grades are being
	 * displayed in the grade spreadsheet.
	 * Returns an empty tree if nothing is in scope.
	 */
	public AssignmentTree getAssignmentTree() {
	   if (course != null) return course.getAssignmentTree();
	   return new AssignmentTree();
   }

	/**
	 * Returns the scores object for the scores being displayed
	 * in the grade spreadsheet.
	 * Returns an empty scores object if nothing is in scope.
	 */
	public Scores getScores() {
	   if (scores != null) return scores;
	   return new Scores();
   }

	/**
	 * Returns the grade scheme for the currently selected section.
	 * Returns an empty grade scheme if no section is in scope.
	 */
	public GradeScheme getGradeScheme() {
	   if (section != null) return section.getGradeScheme();
	   return new GradeScheme();
   }
	/////////////////////////////////


   //////////////////////////////////
   /* UPDATE METHODS FOR OBSERVERS */
   //////////////////////////////////
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

      loadScores();

      setChanged();
      notifyObservers();
   }

   /**
    * Creates a delta for a score for the given student and assignment.
    * The temporary Scores object is also updated to reflect current changes.
    * Deltas are not saved to persistent storage until the user saves them.
    */
   public void updateGrade(Student student, Assignment assignment,
                           double score) {
      futureDeltas.clear();
      deltas.add(new RawScore(student, assignment, score));
      scores.updateRawScore(student, assignment, score);
      setChanged();
      notifyObservers();
   }

   /**
    * Reverts all deltas and restores the Scores object to its state prior to
    * the changes.
    */
   public void revertGrades() {
      deltas.clear();
      futureDeltas.clear();

      loadScores();

      setChanged();
      notifyObservers();
   }

   /**
    * Commits all deltas to persistent storage.
    */
   public void saveGrades() {
      Scores gradebookScores = gradebook.getScores();
      for (RawScore raw : deltas) {
         gradebookScores.updateRawScore(
            raw.getStudent(), raw.getAssignment(), raw.getScore());
      }
      deltas.clear();
      futureDeltas.clear();
      loadScores();

      setChanged();
      notifyObservers();
   }

   /**
    * Overwrites the current section's grade scheme if a section is in scope.
    */
   public void updateGradeScheme(GradeScheme gradeScheme) {
      if (section != null) {
          section.setGradeScheme(gradeScheme);
          setChanged();
          notifyObservers();
      }
   }
   //////////////////////////////////

   /////////////////////
   /* EDIT OPERATIONS */
   /////////////////////
   /**
    * Returns whether a change can be undone.
    */
   public boolean canUndo() {
      return !deltas.isEmpty();
   }

   /**
    * Returns whether a change can be redone.
    */
   public boolean canRedo() {
      return !futureDeltas.isEmpty();
   }

   /**
    * Undoes a change by removing a delta.
    */
   public void undo() {
      if (canUndo()) {
         RawScore undoneDelta = deltas.get(deltas.size() - 1);
         deltas.remove(deltas.size() - 1);
         futureDeltas.add(undoneDelta);

         Student student = undoneDelta.getStudent();
         Assignment assignment = undoneDelta.getAssignment();

         // Revert score.
         scores.updateRawScore(student, assignment,
            gradebook.getScores().getRawScore(student, assignment));
      }
   }

   /**
    * Redoes an undone change.
    */
   public void redo() {
      if (canRedo()) {
         RawScore redoneDelta = futureDeltas.get(futureDeltas.size() - 1);
         futureDeltas.remove(futureDeltas.size() - 1);
         deltas.add(redoneDelta);

         // Reupdate score.
         scores.updateRawScore(redoneDelta.getStudent(),
            redoneDelta.getAssignment(), redoneDelta.getScore());
      }
   }

   /**
    * Gets the latest workspace delta.
    * Returns null if cannot undo.
    */
   public RawScore getLatestChange() {
      if (canUndo()) {
         return deltas.get(deltas.size() - 1);
      }
      return null;
   }

   /**
    * Gets the latest undone workspace delta.
    * Returns null if cannot redo.
    */
   public RawScore getLatestUndo() {
      if (canRedo()) {
         return futureDeltas.get(futureDeltas.size() - 1);
      }
      return null;
   }

   /////////////////////


   /////////////////////
   /* PRIVATE METHODS */
   /////////////////////

   /**
    * Loads scores for the students in scope from the gradebook.
    */
   private void loadScores() {
      Scores gradebookScores = gradebook.getScores();
      List<Student> scopedStudents = getStudents();
      scores = new Scores();

      // Add in scores for relevant students.
      for (Student student : scopedStudents) {
         HashMap<Assignment, RawScore> scoresMap = gradebookScores.getScoresMap(student);
         scores.addScoresMap(student, scoresMap);
      }
   }
}
