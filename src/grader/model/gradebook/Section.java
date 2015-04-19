/**
 * @author Jon Amireh
 * @author Mallika Potter
 */

package grader.model.gradebook;

import grader.model.people.Group;
import grader.model.people.Student;
import grader.model.people.TA;

import java.util.ArrayList;
import java.util.Collection;

import grader.model.curve.*;

/**
*Collection of objects that are necessary for Section functionality.
 *
 * @author Jon Amireh
 * @author Mallika Potter
*/
public class Section
{

    public Section()
    {
        gradeScheme = new GradeScheme();
    }
	/**
	*Scores associated with students and assignments in this Section.
	*/
	public Scores scores;

	/**
	*Collection of all students associated with this Section.
	*/
	public ArrayList<Student> students;

	/**
	*Collection of all groups associated with this section.
	*Groups are quarter-long.
	*/
	Collection<Group> groups;

	/**
	*The TAs associated with this section.
	*May have varying permissions.
	*/
	Collection<TA> tas;

	/**
	*String representing section number.
	*/
	String sectionNumber;

	/**
	*Name associated with section.
	*/
	String sectionName;

	/**
	*GradeScheme associated with this section.
	*/
	public GradeScheme gradeScheme;

    /**
     * Histogram associated with this section.
     */
    Histogram histogram = new Histogram();

	/**
	*Add unenrolled student.
	*pre:
	*	!students.contains(stu);
	*/
	public void addStudent(Student student)
	{
        System.out.println("gradebook/Section.addStudent called");
	}

	/**
	*Add new TA.
	*pre:
	*	!tas.contains(ta);
	*/
	public void addTA(TA ta)
	{

	}

	/**
	 * Returns this section's grade scheme.
	 */
	public GradeScheme getGradeScheme() {
	   return gradeScheme;
   }

   /**
    * Sets the section's grade scheme.
    */
   public void setGradeScheme(GradeScheme gradeScheme) {
      this.gradeScheme = gradeScheme;
   }

    /**
     * Handles updating histogram.
     */
    public void applyHistogram()
    {
        histogram.apply();
    }

    /**
     * Takes in new pushed grade-scheme.
     */
    public void pushGradeScheme()
    {
        gradeScheme = histogram.push();
        System.out.println("Updated GradeScheme saved in Section.");
    }
}
