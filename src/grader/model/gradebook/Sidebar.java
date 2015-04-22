package grader.model.gradebook;

import java.util.Observable;
import java.util.Observer;

/**
 * The Sidebar class represents the underlying model for the navigational
 * sidebar.
 *
 * @author Gregory Davis
 */
public class Sidebar implements Observer {
   private Gradebook gradebook;

   public Sidebar(Gradebook gradebook) {
      this.gradebook = gradebook;
   }

   public void update(Observable obj, Object args) {}
}
