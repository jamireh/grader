package grader.model.edit;

import grader.model.gradebook.Gradebook;
import grader.model.items.Assignment;
import grader.model.people.Student;

import java.util.Collection;

/**
 * The Find class is responsible for managing the Find operation.
 */
public abstract class Find {
	/**
	 * The currently open Gradebook.
	 */
	Gradebook gradebook;

   /**
    * Finds the Student associated with the given name and course.
    * If courseName is null, the search is performed on all courses.
    * @param name student name
    * @param courseName name of course to search in
    * @return collection of students fitting criteria
    *
    *                                                             <pre>
    pre:
      //
      // The selected Course must either be null or exist in the gradebook.
      //
      courseName == null ||
      exists (Course course; gradebook.courses.contains(course);
              course.name == courseName);

    post:
      //
      // Any students in the returned collection must fit the criteria.
      // Their name must contain the search patterns, and they must be
      // contained in a section within the selected course.
      //
      forall (Student student; return.contains(student);
              exists (Course course; gradebook.courses.contains(course) &&
                            if (courseName != null) course.name == courseName;
                      exists (Section section; course.sections.contains(section);
                              exists (Student student; section.students.contains(student);
                                      student.name.contains(name)))))
    */
   abstract Collection<Student> findStudents(String name, String courseName);

   /**
    * Finds the Assignment associated with the given name and course.
    * If courseName is null, the search is performed on all courses.
    * @param name assignment name
    * @param courseName name of course to search in
    * @return collection of assignments matching the criteria
    *
    *                                                             <pre>
    pre:
      //
      // The selected Course must either be null or exist in the gradebook.
      //
      courseName == null ||
      exists (Course course; gradebook.courses.contains(course);
              course.name == courseName);

    post:
      //
      // Any Assignments in the returned collection must fit the criteria.
      // Their name must contain the search patterns, and they must be
      // contained in a section within the selected course.
      //
      forall (Assignment assignment; return.contains(assignment);
              exists (Course course; gradebook.courses.contains(course) &&
                            if (courseName != null) course.name == courseName;
                      exists (Assignment assignment; course.assignments.contains(assignment);
                              assignment.name.contains(name))));
    */
   abstract Collection<Assignment> findAssignments(String name, String courseName);
}
