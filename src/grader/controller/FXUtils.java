package grader.controller;

import javafx.scene.Node;
import javafx.scene.Parent;

public class FXUtils
{
    /**
     * Returns a Node with the specified id from the specfied parent
     * @param parent View containing the child
     * @param id String representing the id of the child
     * @return Child requested or null if child not found
     */
    public static Node findChildById(Parent parent, String id)
    {
        Node toReturn = null;
        for(Node child : parent.getChildrenUnmodifiable())
        {
            if(child.getId() != null && child.getId().equals(id))
            {
                toReturn = child;
                break;
            }
        }
        return toReturn;
    }
}