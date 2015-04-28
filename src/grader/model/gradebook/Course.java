/**
 * @author Jon Amireh
 */

package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.items.AssignmentTree;
import grader.model.items.Category;
import grader.model.people.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Course data structure, holds all relevant info about the course.
 *
 * @author Jon Amireh
 * @author Connor Batch
 */
public class Course
{
	/**
	*Name of course.
	*/
	public String name;

	/**
	*Collectiion of sections of this course taught by the Instructor associated with this Gradebook.
	*/
	public ArrayList<Section> sections;

	/**
	*Collection of the various grade categories associated with this course.
	*Grade categories are fixed at the course level.  All categories must be represented in all sections.
	*/
	public ArrayList<Category> categories;

	/**
	*Collection of assignments and categories.
	*/
	public AssignmentTree assignments;


    public Course() {
        categories = new ArrayList<Category>();
        assignments = new AssignmentTree();
        sections = new ArrayList<Section>();
    }

    public Course(String name)
    {
        categories = new ArrayList<Category>();
        assignments = new AssignmentTree();
        sections = new ArrayList<Section>();
        this.name = name;
    }

	/**
	*Add category to collection.
	*pre:
	*	!categories.contains(cate);
	*/
	public void addCategory(Category cate)
	{
		System.out.println("gradebook.Course.add(Category) called");
		categories.add(cate);
	}

	public void syncRoster()
	{
		System.out.println("gradebook.Course.syncRoster() called");
	}

	/**
	 * Gets all the students in this course.
	 * Crawls through sections to populate list.
	 */
	public List<Student> getStudents() {
	   List<Student> students = new ArrayList<Student>();

	   for (Section section : sections) {
	      students.addAll(section.getStudents());
      }

      return students;
   }

	/**
	 * Adds the given Section to the root of the AssignmentTree
	 * @param section Section to adda
	 */
   public void addSection(Section section) {
      this.sections.add(section);
   }

	/**
	 * Adds the given assignment to the root of the AssignmentTree
	 * @param assignment Assignment to add
	 */
    public void addAssignment(Assignment assignment) {
       System.out.println("gradebook.Course.add(Assignment) called");
       assignments.addTo(null, assignment);
    }

	/**
	 * Retrieves the AssignmentTree for this Course
	 * @return AssignmentTree for this Course
	 */
    public AssignmentTree getAssignmentTree() {
       return assignments;
    }
}
