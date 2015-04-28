package grader.model.gradebook;

import grader.model.file.WorkSpace;
import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.people.Instructor;
import grader.model.people.Name;
import grader.model.people.Student;
import org.omg.CORBA.DynAnyPackage.Invalid;

import javax.naming.InvalidNameException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
*This class collects all the various components of the grader: Instructor, TA and Student Views.
**/
public class Gradebook {

	/**
	*The courses associated with this gradebook.
	*/
	public ArrayList<Course> courses;

	/**
	*The Instructor associated with this gradebook.
	*No limits on permissions.
	*/
	public Instructor instructor;

	/**
	 * The gradebook's master score table.
	 */
	public Scores scores;

	/**
	*Set Instructor associated with this gradebook.
	*pre:
	*	instructor.equals(null);
	*/
	public void setInstructor(Instructor ins) {
	   this.instructor = ins;
   }

	/**
	*Add courses to collection associated with this gradebook.
	*pre:
	*	!courses.contains(cor);
	*/
	public void addCourse(Course cor) {
	   courses.add(cor);
   }

	/**
	 * Returns the master scores object for the gradebook.
	 */
	public Scores getScores() {
	   return scores;
   }

   private static Gradebook cannedGradebook;

   public Gradebook()
   {
      scores = new Scores();
      courses = new ArrayList<Course>();
   }

   static {
      cannedGradebook = new Gradebook();
      Course course = new Course("CPE 309");

      // Add assignments to course
      String[] assignmentNames = {
         "Project 1",
         "Project 2",
         "Quiz 1",
         "Quiz 2",
         "Midterm 1",
         "Midterm 2",
         "Final",
         "Participation"
      };

      for (String assignmentName : Arrays.asList(assignmentNames)) {
         Assignment assignment = new Assignment(assignmentName);
         course.addAssignment(assignment);
      }

      cannedGradebook.addCourse(course);

      // Create sections
      String[] names = {
         "Brandon Clark",
         "Carmen Dang",
         "Vivian Fong",
         "Nupur Garg",
         "Katelyn Hicks",
         "Helen Hwang",
         "Esha Joshi",
         "Connor Krier",
         "Daniel Lee",
         "Myra Lukens",
         "Blaine Oelkers",
         "Frank Poole",
         "Brian Quezada",
         "Wasae Qureshi",
         "Daniel Toy"
      };

      addCannedSection(cannedGradebook, course, "01", names);

      final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
      final String abc = "abcdefghijklmnopqrstuvwxyZ";
      final int nameLength = 6;
      Random rnd = new Random();

      names = new String[25];
      for (int i = 0; i < names.length; ++i) {
         StringBuilder sb = new StringBuilder( nameLength );
         sb.append( ABC.charAt( rnd.nextInt(ABC.length()) ) );
         for( int letter = 0; letter < nameLength - 1; letter++ )
            sb.append( abc.charAt( rnd.nextInt(abc.length()) ) );
         sb.append(" ");
         sb.append( ABC.charAt( rnd.nextInt(ABC.length()) ) );
         for( int letter = 0; letter < nameLength - 1; letter++ )
            sb.append( abc.charAt( rnd.nextInt(abc.length()) ) );
         names[i] = sb.toString();
      }
      addCannedSection(cannedGradebook, course, "02", names);

      AssignmentTree.AssignmentIterator itr =
         course.getAssignmentTree().getAssignmentIterator();

      System.out.println("Canned gradebook assignments:");
      while (itr.hasNext()) {
          System.out.println(itr.next());
      }
   }

   private static void addCannedSection(Gradebook gradebook, Course course, String number, String[] names) {
      Section section = new Section(number);
      course.addSection(section);

      List<Student> students = new ArrayList<Student>();
      List<Assignment> assignments = new ArrayList<Assignment>();

      for (String name : Arrays.asList(names)) {
         String[] tokens = name.split(" ");
          Student student = null;
          try
          {
              student = new Student(new Name(tokens[0], "", tokens[1]));
          }
          catch (InvalidNameException e)
          {
              System.out.println("name exception occured in gradebook");
          }
         students.add(student);
         section.addStudent(student);
      }

      Random rand = new Random();

      System.out.println("Canned gradebook assignments:");
      for (Student student : students) {

         AssignmentTree.AssignmentIterator itr =
               course.getAssignmentTree().getAssignmentIterator();

         while (itr.hasNext()) {
            Assignment assignment = itr.next();
            double randomScore = rand.nextInt(assignment.rawPoints);
            cannedGradebook.scores.addRawScore(student, assignment, randomScore);
         }
      }
   }

   /**
    * Returns the gradebook with canned data.
    */
   public static Gradebook getCannedGradebook() {
      return cannedGradebook;
   }
}

