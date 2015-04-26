package grader.model.gradebook;

import grader.model.file.WorkSpace;
import grader.model.gradebook.*;
import grader.model.people.*;

import java.util.Observable;
import java.util.Observer;

/**
 * The Sidebar class represents the underlying model for the navigational
 * sidebar.
 *
 * @author Gregory Davis
 */
public class Sidebar implements Observer {
   /** Workspace gradebook reference. */
   private Gradebook gradebook;

   /**
    * Selects the scope according to what was selected in the sidebar.
    * @param course course scope
    * @param section section scope
    * @param group group scope
    */
   public void selectScope(Course course, Section section, Group group) {
      WorkSpace.instance.sidebarSelect(course, section, group);
   }

   /**
    * Tells the controller to render the sidebar.
    */
   public void render() {
   }

   /**
    * Update method from the WorkSpace.
    * Gets the workspace gradebook.
    * @param obj unused
    * @param args unused
    */
   public void update(Observable obj, Object args) {
      this.gradebook = WorkSpace.instance.getGradebook();
      render();
   }
}
