package grader.model.file;
/**
 * The File class represents the underlying model for the File menu.
 * Included are methods for file operations.
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
   public void newCourse(String name) {
       System.out.println("File->New Course:  " + name);
   }

   /**
    * Opens the "export" menu.
    * *Planned functionality -- NOT MODELED*
    */
   public void exportGradebook() {
       System.out.println("File->Export");
   }

   /**
    * Opens the "import" menu.
    * *Planned functionality -- NOT MODELED*
    */
   public void importGradebook() {
       System.out.println("File->Import");
   }

   /**
    * Opens the "print" menu.
    * *Planned functionality -- NOT MODELED*
    */
   public void print() {
       System.out.println("File->Print");
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
   public void logout() {
       System.out.println("File->Logout");
   }

   /**
    * Quits the application.
    */
   public void quit() {
       System.out.println("File->Quit");
   }
}
