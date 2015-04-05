package grader.model.edit;

/**
 * The Edit class represents the underlying model for the Edit menu.
 * Included are methods for edit operations.
 */
public abstract class Edit {
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
   abstract void undo();

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
   abstract void redo();

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
   abstract void cut();

   /**
    * Copies the selected item.
    *                                                             <pre>
    post:
      //
      // The clipboard must contain the contents of the selected context.
      //
      WorkSpace.clipboard.equals(WorkSpace.selectedContext);
    */
   abstract void copy();

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
   abstract void paste();
}
