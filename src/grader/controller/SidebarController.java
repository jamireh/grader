package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.Course;
import grader.model.gradebook.Gradebook;
import grader.model.gradebook.Section;
import grader.model.people.Group;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;


/**
 * Controller for the Sidebar model
 * @author Jon Amireh
 */
public class SidebarController implements Initializable, Observer
{
    @FXML TreeView<String> tvCourses;

    /** Workspace gradebook reference. */
    private Gradebook gradebook;
    private HashMap<String, HashMap<String, ArrayList<String>>> viewReference;
    private boolean manualSelect = false;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // Add observer once weird FX bullshit is fixed.
        WorkSpace.instance.addObserver(this);

        tvCourses.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TreeItem<String>>() {
                    @Override
                    public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue)
                    {
                        if (newValue != null && !manualSelect)
                        {
                            int level = tvCourses.getTreeItemLevel(newValue);
                            String course = null, section = null, group = null;
                            switch (level)
                            {
                                case 1:
                                    course = newValue.getValue();
                                    section = null;
                                    group = null;
                                    break;
                                case 2:
                                    course = newValue.getParent().getValue();
                                    section = newValue.getValue();
                                    group = null;
                                    break;
                                case 3:
                                    course = newValue.getParent().getParent().getValue();
                                    section = newValue.getParent().getValue();
                                    group = newValue.getValue();
                                    break;
                            }
                            final String finalCourse = course;
                            final String finalSection = section;
                            final String finalGroup = group;
                            //Some weird rule that you can't update the Sidebar while listening to what is currently selected
                            //so it has to be run on a separate thread, idk.
                            Platform.runLater(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    if(WorkSpace.instance.canUndo())
                                    {
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Unsaved Changes");
                                        alert.setHeaderText("Changes have been detected");
                                        alert.setContentText("Choose your option.");

                                        ButtonType buttonTypeRevert = new ButtonType("Revert");
                                        ButtonType buttonTypeSave = new ButtonType("Save");

                                        alert.getButtonTypes().setAll(buttonTypeRevert, buttonTypeSave);

                                        Optional<ButtonType> result = alert.showAndWait();
                                        if (result.get() == buttonTypeRevert) {
                                            WorkSpace.instance.revertGrades();
                                            selectScope(finalCourse, finalSection, finalGroup);
                                        } else if (result.get() == buttonTypeSave) {
                                            WorkSpace.instance.saveGrades();
                                            selectScope(finalCourse, finalSection, finalGroup);
                                        }
                                    }
                                    else
                                    {
                                        selectScope(finalCourse, finalSection, finalGroup);
                                    }
                                }
                            });
                        }
                    }
                });
       update(null, null);
    }

    public void render()
    {
        final HashMap<String, HashMap<String, ArrayList<String>>> viewReference = generateTreeView();
        tvCourses.setRoot(null);
        TreeItem<String> rootView = new TreeItem<String>();
        rootView.setValue("Courses");
        TreeItem<String> pCourse = null, pSection = null, pGroup = null;
        if(WorkSpace.instance.course != null)
        {
            rootView.setExpanded(true);
        }
        for(String courseKey : viewReference.keySet())
        {
            TreeItem<String> course = new TreeItem<String>(courseKey);
            if(WorkSpace.instance.course != null && WorkSpace.instance.section != null && courseKey.equals(WorkSpace.instance.course.name))
            {
                course.setExpanded(true);
            }
            for (String  sectionKey : viewReference.get(courseKey).keySet())
            {
                TreeItem<String> section = new TreeItem<String>(sectionKey);
                if(WorkSpace.instance.section != null && WorkSpace.instance.group != null && sectionKey.equals(WorkSpace.instance.section.sectionName))
                {
                    section.setExpanded(true);
                }
                for(String group : viewReference.get(courseKey).get(sectionKey))
                {
                    TreeItem<String> groupItem = new TreeItem<String>(group);
                    section.getChildren().add(groupItem);
                    if(WorkSpace.instance.group != null && groupItem.getValue().equals(WorkSpace.instance.
                            group.groupName))
                    {
                        pGroup = groupItem;
                    }
                }
                course.getChildren().add(section);
                if(WorkSpace.instance.section != null && section.getValue().equals(WorkSpace.instance
                        .section.sectionName))
                {
                    pSection = section;
                }
            }
            rootView.getChildren().add(course);
            if(WorkSpace.instance.course != null && course.getValue().equals(WorkSpace.instance.course.name))
            {
                pCourse = course;
            }
        }
        tvCourses.setRoot(rootView);
        tvCourses.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        manualSelect = true;
        if(pGroup != null)
            tvCourses.getSelectionModel().select(pGroup);
        else if(pSection != null)
            tvCourses.getSelectionModel().select(pSection);
        else if(pCourse != null)
            tvCourses.getSelectionModel().select(pCourse);
        else
            tvCourses.getSelectionModel().selectFirst();
        manualSelect = false;
    }

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
        WorkSpace.instance.sidebarSelect(cCourse, sSection, gGroup);
    }

    private HashMap<String, HashMap<String, ArrayList<String>>> generateTreeView()
    {
        HashMap<String, HashMap<String, ArrayList<String>>> viewReference =
           new HashMap<String, HashMap<String, ArrayList<String>>>();

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
        return viewReference;
    }

    /**
     * Update method from the WorkSpace.
     * Gets the workspace gradebook.
     * @param obj unused
     * @param args unused
     */
    public void update(Observable obj, Object args) {
        boolean ignoreMe = false;
        if(args != null)
        {
            Class[] toIgnore = ((Class[]) args);
            for(int i = 0; i < toIgnore.length; i++)
            {
                if(toIgnore[i] == getClass())
                {
                    ignoreMe = true;
                    break;
                }
            }
        }
        if(!ignoreMe)
        {
            this.gradebook = WorkSpace.instance.getGradebook();
            render();
        }
    }
}
