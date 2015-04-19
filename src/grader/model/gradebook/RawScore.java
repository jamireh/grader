package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.people.Student;

/**
 * A RawScore represents the raw score given to a student for a particular
 * assignment.
 *
 * @author Gregory Davis
 */
public abstract class RawScore implements Comparable<RawScore> {
   /**
    * The student to whom the score belongs.
    */
   Student student;

   /**
    * The assignment for which the student got the raw score.
    */
   Assignment assignment;

   /**
    * The actual raw score for the gradable item.
    */
   public double score;

   /**
    * Gets the student associated with this score.
    * @return Student.
    *                                                             <pre>
    post:
      //
      // The returned student must be this raw score's student.
      //
      return.equals(this.student);
    */
   abstract Student getStudent();

   /**
    * Gets the gradable item associated with this score.
    * @return assignment
    *                                                             <pre>
    post:
      //
      // The returned assignment must be this raw score's assignment.
      //
      return.equals(this.assignment);
   	*/
   abstract Assignment getAssignment();

   /**
    * Gets the raw numerical score.
    * @return raw numerical score.
    *                                                             <pre>
    post:
      //
      // The returned score must be equal to this raw score's score.
      //
      return == this.score;
    */
   abstract double getScore();

   /**
    * Sets the raw numerical score.
    * @param score new numerical score.
    *                                                             <pre>
    post:
      //
      // This raw score's score must equal the parameter.
      //
      this.score' == score;
    */
   abstract void setScore(double score);

    /**
     * Compares this RawScore to another by the score it wraps.
     * @param other the other raw score
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
   @Override
   public int compareTo(RawScore other) {
       return (int) (other.getScore() - score);
   }
}
