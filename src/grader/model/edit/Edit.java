package grader.model.edit;

/**
 * The Edit class represents the underlying model for the Edit menu.
 * Included are methods for edit operations.
 *
 * @author Gregory Davis
 */
public class Edit {
   /**
    * Undoes the most recent change to the gradebook.
    *                                                             <pre>
    pre:
      //
      // A change to the gradebook must have been made during this session.
      //
      WorkSpace.prevGradebook != null;

    post:
      //
      // The current workspace gradebook is reverted to the previous gradebook,
      // and the futureGradebook is set to the old current gradebook.
      //
      WorkSpace.gradebook'.equals(WorkSpace.prevGradebook)
      && WorkSpace.prevGradebook' == null
      && WorkSpace.futureGradebook.equals(WorkSpace.gradebook);
    */
   public void undo() {
       System.out.println("Edit->Undo");
   }

   /**
    * Redoes the most recently undone change to the gradebook.
    *                                                             <pre>
    pre:
      //
      // A call to undo must have been made during this session.
      // The futureGradebook must not be null.
      //
      WorkSpace.futureGradebook != null;

    post:
      //
      // The current workspace gradebook is set to the future gradebook,
      // and the prevGradebook is set to the old current gradebook.
      //
      WorkSpace.gradebook'.equals(WorkSpace.futureGradebook)
      && WorkSpace.futureGradebook' == null
      && WorkSpace.prevGradebook.equals(WorkSpace.gradebook);
    */
    public void redo() {
        System.out.println("Edit->Redo");
    }

   /**
    * Cuts the selected item.
    *                                                             <pre>
    post:
      //
      // The clipboard must contain the contents of the selected context.
      // The selectedContext must also be cleared.
      //
      WorkSpace.clipboard.equals(WorkSpace.selectedContext)
      && WorkSpace.selectedContext'.empty();
    */
    public void cut() {
        System.out.println("Edit->Cut");
    }

   /**
    * Copies the selected item.
    *                                                             <pre>
    post:
      //
      // The clipboard must contain the contents of the selected context.
      //
      WorkSpace.clipboard.equals(WorkSpace.selectedContext);
    */
    public void copy() {
        System.out.println("Edit->Copy");
    }

   /**
    * Pastes the most recently copied/cut item.
    *                                                             <pre>
    pre:
      //
      // The clipboard must not be empty.
      //
      !WorkSpace.clipboard.empty();

    post:
      //
      // The current selected context must contain the contents of the
      // clipboard.
      //
      WorkSpace.selectedContext.equals(WorkSpace.clipboard);
    */
    public void paste() {
        System.out.println("Edit->Paste");
    }
}
