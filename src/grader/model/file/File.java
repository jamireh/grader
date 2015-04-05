package grader.model.file;
/**
 * The File class represents the underlying model for the File menu.
 * Included are methods for file operations.
 */
public abstract class File {
   /**
    * Creates a new class.
    * Adds the course to the current workspace Gradebook.
    *                                                             <pre>
    pre:
      //
      // The current workspace gradebook must not already contain a course
      // with the given name.
      //
      !exists (Course course; WorkSpace.gradebook.courses.contains(course);
               course.name.equals(name));

    post:
      //
      // The current workspace gradebook must contain the same courses with
      // the addition of the new course.
      //
      forall (Course course; WorkSpace.gradebook.courses'.contains(course)
              iff WorkSpace.gradebook.courses.contains(course)))
      && exists (Course course; WorkSpace.gradebook.courses'.contains(course);
                 course.name.equals(name));
    */
   abstract void newClass(String name);

   /**
    * Opens the "export" menu.
    * *Planned functionality -- NOT MODELED*
    */
   abstract void exportGradebook();

   /**
    * Opens the "import" menu.
    * *Planned functionality -- NOT MODELED*
    */
   abstract void importGradebook();

   /**
    * Opens the "print" menu.
    * *Planned functionality -- NOT MODELED*
    */
   abstract void print();

   /**
    * Logs the user out, terminating the authenticated session.
    *                                                             <pre>
    pre:
      //
      // The current workspace has a logged in user.
      //
      WorkSpace.user != null;

    post:
      //
      // The current workspace user will be null.
      //
      WorkSpace.user == null;
    */
   abstract void logout();

   /**
    * Quits the application.
    */
   abstract void quit();
}
