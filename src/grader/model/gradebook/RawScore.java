package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.people.Student;

/**
 * A RawScore represents the raw score given to a student for a particular
 * assignment.
 */
public class RawScore {
   /**
    * The student to whom the score belongs.
    */
   private final Student student;

   /**
    * The assignment for which the student got the raw score.
    */
   private final assignment;

   /**
    * The actual raw score for the gradable item.
    */
   private double score;

   /**
    * Constructor.
    */
   public RawScore(Student student, Assignment assignment, double score) {
      this.student = student;
      this.assignment = assignment;
      this.score = score;
   }

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
   public Student getStudent() {
      return student;
   }

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
   public Assignment getAssignment() {
      return assignment;
   }

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
   public double getScore() {
      return score;
   }

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
   public void setScore(double score) {
      this.score = score;
   }
}
