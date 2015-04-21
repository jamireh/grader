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
      WorkSpace.instance.prevGradebook != null;

    post:
      //
      // The current workspace gradebook is reverted to the previous gradebook,
      // and the futureGradebook is set to the old current gradebook.
      //
      WorkSpace.instance.gradebook'.equals(WorkSpace.instance.prevGradebook)
      && WorkSpace.instance.prevGradebook' == null
      && WorkSpace.instance.futureGradebook.equals(WorkSpace.instance.gradebook);
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
      WorkSpace.instance.futureGradebook != null;

    post:
      //
      // The current workspace gradebook is set to the future gradebook,
      // and the prevGradebook is set to the old current gradebook.
      //
      WorkSpace.instance.gradebook'.equals(WorkSpace.instance.futureGradebook)
      && WorkSpace.instance.futureGradebook' == null
      && WorkSpace.instance.prevGradebook.equals(WorkSpace.instance.gradebook);
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
      WorkSpace.instance.clipboard.equals(WorkSpace.instance.selectedContext)
      && WorkSpace.instance.selectedContext'.empty();
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
      WorkSpace.instance.clipboard.equals(WorkSpace.instance.selectedContext);
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
      !WorkSpace.instance.clipboard.empty();

    post:
      //
      // The current selected context must contain the contents of the
      // clipboard.
      //
      WorkSpace.instance.selectedContext.equals(WorkSpace.instance.clipboard);
    */
    public void paste() {
        System.out.println("Edit->Paste");
    }
}
