package grader.controller;

import grader.Main;
import grader.model.edit.Edit;
import grader.model.file.File;
import grader.model.file.WorkSpace;
import grader.model.gradebook.Course;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenubarController implements Observer
{
    private Stage lockedStage;
    private Stage histogramStage;
    private Stage piechartStage;
    private Stage gradeschemeStage;
    @FXML Menu fileMenu;
    @FXML Menu editMenu;
    @FXML Menu studentMenu;
    @FXML Menu gradesMenu;
    @FXML Menu curveMenu;
    @FXML MenuItem editGroupMenuItem;

    @FXML
    public void initialize()
    {
        WorkSpace.instance.addObserver(this);
        update(null, null);
        lockedStage = new Stage();
        lockedStage.initModality(Modality.APPLICATION_MODAL);
        piechartStage = new Stage();
        histogramStage = new Stage();
        gradeschemeStage = new Stage();
    }

    @Override
    public void update(Observable o, Object arg)
    {
        refreshItems();
    }

    private void refreshItems()
    {

        fileMenu.setDisable(false);
        editMenu.setDisable(false);
        studentMenu.setDisable(false);
        gradesMenu.setDisable(false);
        curveMenu.setDisable(false);
        editGroupMenuItem.setDisable(true);
        
        if(WorkSpace.instance.course == null)
        {
            studentMenu.setDisable(true);
            gradesMenu.setDisable(true);
            curveMenu.setDisable(true);
        }
        if(WorkSpace.instance.section == null)
        {
            studentMenu.setDisable(true);
            curveMenu.setDisable(true);
        }
        if(WorkSpace.instance.group != null)
        {
            editGroupMenuItem.setDisable(false);
        }
    }

    public void setStageWithFocus(Parent root, String title)
    {
        lockedStage.setTitle(title);
        lockedStage.setScene(new Scene(root));
        lockedStage.show();
    }
    /* FILE MENU COMMANDS */

    /**
     * Controller method for File->New Course.
     */
    public void fileNewCourse() throws IOException
    {
        Parent root = FXMLLoader.load(Main.courseResource);
        setStageWithFocus(root, "New Course");
    }

    /**
     * Controller method for File->Export.
     */
    public void fileExport() {
        File.exportGradebook();
    }

    /**
     * Controller method for File->Import.
     */
    public void fileImport() {
        File.importGradebook();
    }

    /**
     * Controller method for File->Print.
     */
    public void filePrint() {
        File.print();
    }

    /**
     * Controller method for File->Logout.
     */
    public void fileLogout() {
        File.logout();
    }

    /**
     * Controller method for File->Quit.
     * Exits the application.
     */
    public void fileQuit() {
        File.quit();
    }

    /* EDIT MENU COMMANDS */

    /**
     * Controller method for Edit->Undo.
     */
    public void editUndo() {
        Edit.undo();
    }

    /**
     * Controller method for Edit->Redo.
     */
    public void editRedo() {
        Edit.redo();
    }

    /**
     * Controller method for Edit->Cut.
     */
    public void editCut() {
        Edit.cut();
    }

    /**
     * Controller method for Edit->Copy.
     */
    public void editCopy() {
        Edit.copy();
    }

    /**
     * Controller method for Edit->Paste.
     */
    public void editPaste() {
        Edit.paste();
    }

    /**
     * Controller method for Edit->Find.
     */
    public void editFind() throws IOException {
        Parent root = FXMLLoader.load(Main.findResource);
        setStageWithFocus(root, "");
    }

    /* STUDENTS MENU COMMANDS */

    /**
     * Controller method for Students->Add New.
     */
    public void studentsAddNew() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.studentsResource);
        fxmlLoader.setController(new StudentController());
        Parent root;
        try
        {
            root = fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
        setStageWithFocus(root, "Add Student");
    }

    /**
     * Controller method for Students->Add New.
     */
    public void editStudent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.studentsResource);
        fxmlLoader.setController(new EditStudentController());
        Parent root;
        try
        {
            root = fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
        setStageWithFocus(root, "Edit Student");
    }

    /**
     * Controller method for Students->Sync Roster.
     */
    public void studentsSyncRoster() {
        Course course = WorkSpace.instance.getCourse();
        if (course != null) course.syncRoster();
    }

    /**
     * Controller method for Students->Edit Roster.
     */
    public void studentsEditRoster() {
    }

    /**
     * Controller method for Students->Create Group.
     */
    public void studentsCreateGroup() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.groupsResource);
        fxmlLoader.setController(new GroupsController());
        Parent root;
        try
        {
            root = fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
        setStageWithFocus(root, "Add New Group");
    }

    /**
     * Controller method for Students->Edit Groups.
     */
    public void studentsEditGroups()
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.groupsResource);
        fxmlLoader.setController(new EditGroupController());
        Parent root;
        try
        {
            root = fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException();
        }
        setStageWithFocus(root, "Edit Group");
    }

    /* GRADES MENU COMMANDS */

    /**
     * Controller method for Grades->Add Category.
     */
    public void gradesAddCategory() throws IOException {
        Parent root = FXMLLoader.load(Main.categoriesResource);
        setStageWithFocus(root, "Add Category");
    }

    /**
     * Controller method for Grades->Add Assignment.
     */
    public void gradesAddAssignment() throws IOException {
        Parent root = FXMLLoader.load(Main.assignmentsResource);
        setStageWithFocus(root, "Add Assignment");
    }

    /**
     * Controller method for Grades->Manage.
     */
    public void gradesManage() {
    }

    /**
     * Controller method for Grades->Publish.
     */
    public void gradesPublish() {
    }

    /* CURVE MENU COMMANDS */

    /**
     * Controller method for Curve->Pie Chart.
     */
    public void curvePieChart() throws IOException {
        Parent root = FXMLLoader.load(Main.piechartResource);
        piechartStage.setTitle("Pie Chart");
        piechartStage.setScene(new Scene(root));
        piechartStage.show();
    }

    /**
     * Controller method for Curve->Histogram.
     */
    public void curveHistogram() throws IOException {
        Parent root = FXMLLoader.load(Main.histogramResource);
        histogramStage.setTitle("Histogram");
        histogramStage.setScene(new Scene(root));
        histogramStage.show();
    }

    /**
     * Controller method for Curve->Grade Scheme.
     */
    public void curveGradeScheme() throws IOException {
        Parent root = FXMLLoader.load(Main.gradeSchemeResource);
        gradeschemeStage.setTitle("Grade Scheme");
        gradeschemeStage.setScene(new Scene(root));
        gradeschemeStage.show();
    }
}
