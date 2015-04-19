package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.people.Student;

import java.util.Collection;


/**
 * The Scores class represents a collection of raw scores for assignments.
 *
 * @author Gregory Davis
 */
public abstract class Scores {
   /**
    * Collection of raw scores, each associated with a student and assignments.
    */
   Collection<RawScore> rawScores;

   /**
    * Retrieves the raw score for an assignment given to a particular student.
    * @param student student
    * @param assignment assignment
    * @return raw score associated with the given student and assignment.
    *                                                             <pre>
    post:
      //
      // If a RawScore was found, the returned RawScore should be in the
      // rawScores collection.  If not, return is null.
      // Either way, rawScores should not have been changed.
      //
      (return == null || rawScores.contains(return))
      && forall (RawScore score; rawScores'.contains(score)
                                 iff rawScores.contains(score));
    */
   abstract public double getRawScore(Student student, Assignment assignment);

   /**
    * Enters a raw score for an assignment for a particular student.
    * @param student student
    * @param assignment assignment
    * @param score raw score
    *                                                             <pre>
    pre:
      //
      // There must not be a RawScore in the rawScores collection corresponding
      // to the given Student and Assignment.
      //
      !exists (RawScore score; rawScores.contains(score);
               score.getStudent().equals(student) &&
               score.getAssignment().equals(assignment));

    post:
      //
      // The rawScores collection should be unchanged except for the addition
      // of the new RawScore.
      //
      exists (RawScore score; rawScores.contains(score);
              score.getStudent().equals(student)
              && score.getAssignment().equals(assignment))
      && forall (RawScore score; rawScores'.contains(score)
                     iff ((score.getStudent().equals(Student)
                           && score.getAssignment().equals(assignment))
                          || rawScores.contains(score)));
 
    */
   abstract void addRawScore(Student student, Assignment assignment,
                             double score);

   /**
    * Updates a raw score for an assignment for a particular student.
    * @param student student
    * @param assignment assignment
    * @param newScore new raw score
    *                                                             <pre>
    pre:
      //
      // There must be a RawScore in the rawScores collection corresponding
      // to the given Student and Assignment.
      //
      exists (RawScore score; rawScores.contains(score);
              score.getStudent().equals(student) &&
              score.getAssignment().equals(assignment));

    post:
      //
      // The rawScores collection should be unchanged except for the RawScore
      // corresponding to the given Student and Assignment, which should
      // reflect the new score.
      //
      exists (RawScore score; rawScores.contains(score);
              score.getStudent().equals(student)
              && score.getAssignment().equals(assignment)
              && score.getScore() == newScore)
      && forall (RawScore score; rawScores'.contains(score)
                     iff ((score.getStudent().equals(Student)
                           && score.getAssignment().equals(assignment)
                           && score.getScore() == newScore)
                          || rawScores.contains(score)));
    */
   abstract void updateRawScore(Student student, Assignment assignment,
                                double newScore);

   /**
    * Removes a raw score for an assignment for a particular student.
    * @param student student
    * @param assignment assignment
    *                                                             <pre>
    pre:
      //
      // There must be a RawScore in the rawScores collection corresponding
      // to the given Student and Assignment.
      //
      exists (RawScore score; rawScores.contains(score);
              score.getStudent().equals(student) &&
              score.getAssignment().equals(assignment));

    post:
      //
      // The rawScores collection should be unchanged except for the removal of
      // the RawScore corresponding to the given Student and Assignment.
      //
      !exists (RawScore score; rawScores.contains(score);
               score.getStudent().equals(student)
               && score.getAssignment().equals(assignment))
      && forall (RawScore score; rawScores'.contains(score)
                 iff (rawScores.contains(score)
                      && !(score.getStudent().equals(student)
                            && score.getAssignment().equals(assignment))));
    */
   abstract void removeRawScore(Student student, Assignment assignment);

   /**
    * Gets the average of all of the raw scores for a given assignment.
    * @param assignment assignment to compute average for
    * @return average raw score
    *                                                             <pre>
    pre:
      //
      // There must be at least one RawScore in the rawScores collection
      // corresponding to the given Assignment.
      //
      exists (RawScore score; rawScores.contains(score);
              score.getAssignment().equals(assignment));

    post:
      //
      // The rawScores collection should be unchanged.
      //
      forall (RawScore score; rawScores'.contains(score)
                     iff rawScores.contains(score));
    */
   abstract double getAverageScore(Assignment assignment);
}
