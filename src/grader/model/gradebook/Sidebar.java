package grader.model.gradebook;

import grader.controller.SidebarController;
import grader.model.file.WorkSpace;
import grader.model.items.AssignmentTree;
import grader.model.people.Group;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * The Sidebar class represents the underlying model for the navigational
 * sidebar.
 *
 * @author Gregory Davis
 * @author Jon Amireh
 */
public class Sidebar implements Observer {
    /** Workspace gradebook reference. */
    private Gradebook gradebook;
    private SidebarController controller;
    private HashMap<String, HashMap<String, ArrayList<String>>> viewReference;

    /**
     * Selects the scope according to what was selected in the sidebar.
     * @param course course scope
     * @param section section scope
     * @param group group scope
     */
    public void selectScope(String course, String section, String group) {
        Course cCourse = null;
        Section sSection = null;
        Group gGroup = null;
        if(course != null)
        {
            for(Course c : gradebook.courses)
            {
                if(c.name.equals(course))
                {
                   System.out.println("Found course " + course);
                    AssignmentTree.AssignmentIterator itr =
                       c.getAssignmentTree().getAssignmentIterator();

                     while (itr.hasNext()) {
                         System.out.println(itr.next());
                     }

                    cCourse = c;
                    break;
                }
            }
        }
        if(section != null)
        {
            for (Section s : cCourse.sections)
            {
                if (s.sectionName.equals(section))
                {
                    sSection = s;
                    break;
                }
            }
        }
        if(group != null)
        {
            for(Group g : sSection.groups)
            {
                if(group.equals(g.groupName))
                {
                    gGroup = g;
                    break;
                }
            }
        }
        if(cCourse != null)
        {
            WorkSpace.instance.sidebarSelect(cCourse, sSection, gGroup);
        }
    }

    /**
     * Tells the controller to render the sidebar.
     */
    public void render() {
        generateTreeView();
        //controller hasn't been set yet so do nothing
        if(controller != null)
        {
            controller.render(viewReference);
        }
    }

    private void generateTreeView()
    {
        viewReference = new HashMap<String, HashMap<String, ArrayList<String>>>();

        for(Course c : gradebook.courses)
        {
            HashMap<String, ArrayList<String>> sections = new HashMap<String, ArrayList<String>>();
            for(Section s : c.sections)
            {
                ArrayList<String> group = new ArrayList<String>();
                for(Group g : s.groups)
                {
                    group.add(g.groupName);
                }
                sections.put(s.sectionName, group);
            }
            viewReference.put(c.name, sections);
        }
        System.out.println(viewReference);
    }

    /**
     * Sets the controller bound to this model
     * @param controller Controller to delegate to when the model changes
     */
    public void setController(SidebarController controller)
    {
        this.controller = controller;
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
