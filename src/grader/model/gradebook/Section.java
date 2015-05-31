/**
 * @author Jon Amireh
 * @author Mallika Potter
 */

package grader.model.gradebook;

import grader.model.curve.Histogram;
import grader.model.gradebook.gradescheme.GradeScheme;
import grader.model.people.Group;
import grader.model.people.Student;

import java.util.ArrayList;
import java.util.List;

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

    public Section(String number) {
        this.sectionNumber = number;
        this.sectionName = "Section " + number;
        students = new ArrayList<Student>();
        gradeScheme = new GradeScheme();
        groups = new ArrayList<Group>();
    }

	/**
	*Collection of all students associated with this Section.
	*/
	public ArrayList<Student> students;

	/**
	*Collection of all groups associated with this section.
	*Groups are quarter-long.
	*/
	public ArrayList<Group> groups;

	/**
	*String representing section number.
	*/
	public String sectionNumber;

	/**
	*Name associated with section.
	*/
	public String sectionName;

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
	    students.add(student);
	}

    /**
     * Adds a new group to this section.
     * @param group to add
     */
    public void addGroup(Group group)
    {
        groups.add(group);
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
    }

    /**
     * Returns this section's students.
     */
    public List<Student> getStudents() {
       return students;
    }
}
