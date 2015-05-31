package grader.model.file;
/**
 * The File class represents the underlying model for the File menu.
 * Included are methods for each of the operations.
 *
 * @author Gregory Davis
 */
public class File {
   /**
    * Creates a new course.
    * Adds the course to the current workspace Gradebook.
    *                                                             <pre>
    pre:
      //
      // The current workspace gradebook must not already contain a course
      // with the given name.
      //
      !exists (Course course; WorkSpace.instance.gradebook.courses.contains(course);
               course.name.equals(name));

    post:
      //
      // The current workspace gradebook must contain the same courses with
      // the addition of the new course.
      //
      forall (Course course; WorkSpace.instance.gradebook.courses'.contains(course)
              iff WorkSpace.instance.gradebook.courses.contains(course)))
      && exists (Course course; WorkSpace.instance.gradebook.courses'.contains(course);
                 course.name.equals(name));
    */
   public static void newCourse(String name) {
   }

   /**
    * Opens the "export" menu.
    * *Planned functionality -- NOT MODELED*
    */
   public static void exportGradebook() {
   }

   /**
    * Opens the "import" menu.
    * *Planned functionality -- NOT MODELED*
    */
   public static void importGradebook() {
   }

   /**
    * Opens the "print" menu.
    * *Planned functionality -- NOT MODELED*
    */
   public static void print() {
   }

   /**
    * Logs the user out, terminating the authenticated session.
    *                                                             <pre>
    pre:
      //
      // The current workspace has a logged in user.
      //
      WorkSpace.instance.user != null;

    post:
      //
      // The current workspace user will be null.
      //
      WorkSpace.instance.user == null;
    */
   public static void logout() {
   }

   /**
    * Quits the application.
    */
   public static void quit() {
       System.exit(0);
   }
}
