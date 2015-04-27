package grader.model.edit;

import grader.model.file.WorkSpace;

/**
 * The Edit class represents the underlying model for the Edit menu.
 * Included are methods for each of the edit operations.
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
      WorkSpace.instance.canUndo();

    post:
      //
      // The last item in the WorkSpace's deltas is moved to its future deltas.
      //
      WorkSpace.instance.getLatestChange().equals(
            WorkSpace.instance'.getLatestUndo())
      && !WorkSpace.instance'.getLatestChange().equals(
            WorkSpace.instance.getLatestChange())
      && !WorkSpace.instance'.getLatestUndo().equals(
            WorkSpace.instance.getLatestUndo())
    */
   public void undo() {
      WorkSpace.instance.undo();
      System.out.println("Edit->Undo");
   }

   /**
    * Redoes the most recently undone change to the gradebook.
    *                                                             <pre>
    pre:
      //
      // A change to the gradebook must have been undone during this session.
      //
      WorkSpace.instance.canRedo();

    post:
      //
      // The last item in the WorkSpace's future deltas is moved to its deltas.
      //
      WorkSpace.instance'.getLatestChange().equals(
            WorkSpace.instance.getLatestUndo())
      && !WorkSpace.instance'.getLatestChange().equals(
            WorkSpace.instance.getLatestChange())
      && !WorkSpace.instance'.getLatestUndo().equals(
            WorkSpace.instance.getLatestUndo())
    */
    public void redo() {
       WorkSpace.instance.redo();
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
