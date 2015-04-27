package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.gradebook.Sidebar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SidebarController implements Initializable
{
    @FXML TreeView<String> tvCourses;
    Sidebar sidebar = WorkSpace.instance.sidebar;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        sidebar.setController(this);
        sidebar.update(null, null);
        tvCourses.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TreeItem<String>>() {
                    @Override
                    public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue)
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
                        sidebar.selectScope(course, section, group);
                    }
                });
    }


    public void render(final HashMap<String, HashMap<String, ArrayList<String>>> viewReference)
    {
        TreeItem<String> rootView = new TreeItem<String>();
        rootView.setValue("Courses");
        for(String courseKey : viewReference.keySet())
        {
            TreeItem<String> course = new TreeItem<String>(courseKey);
            for (String  sectionKey : viewReference.get(courseKey).keySet())
            {
                TreeItem<String> section = new TreeItem<String>(sectionKey);
                for(String group : viewReference.get(courseKey).get(sectionKey))
                {
                    section.getChildren().add(new TreeItem<String>(group));
                }
                course.getChildren().add(section);
            }
            rootView.getChildren().add(course);
        }
        tvCourses.setRoot(rootView);
    }
}
