package grader.controller;

import grader.Main;
import grader.model.gradebook.WorkSpace;
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

/**
 * Controller for the top level menubar.
 * @author Jon Amireh
 */
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
    @FXML MenuItem editStudentMenuItem;
    @FXML MenuItem undoMenuItem;
    @FXML MenuItem redoMenuItem;
    @FXML MenuItem cutMenuItem;
    @FXML MenuItem copyMenuItem;
    @FXML MenuItem pasteMenuItem;
    @FXML MenuItem predictionMenuItem;

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
        editStudentMenuItem.setDisable(true);
        predictionMenuItem.setDisable(true);
        undoMenuItem.setDisable(!WorkSpace.instance.canUndo());
        redoMenuItem.setDisable(!WorkSpace.instance.canRedo());
        cutMenuItem.setDisable(!WorkSpace.instance.canCopy());
        copyMenuItem.setDisable(!WorkSpace.instance.canCopy());
        pasteMenuItem.setDisable(!WorkSpace.instance.canPaste());

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
        if(WorkSpace.instance.selectedStudent != null)
        {
            editStudentMenuItem.setDisable(false);
        }
        if(WorkSpace.instance.selectedScore != null)
        {
            predictionMenuItem.setDisable(false);
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
    }

    /**
     * Controller method for File->Import.
     */
    public void fileImport() {
    }

    /**
     * Controller method for File->Print.
     */
    public void filePrint() {
    }

    /**
     * Controller method for File->Logout.
     */
    public void fileLogout() {
    }

    /**
     * Controller method for File->Quit.
     * Exits the application.
     */
    public void fileQuit() {
        System.exit(0);
    }

    /* EDIT MENU COMMANDS */

    /**
     * Controller method for Edit->Undo.
     */
    public void editUndo() {
        WorkSpace.instance.undo();
    }

    /**
     * Controller method for Edit->Redo.
     */
    public void editRedo() {
        WorkSpace.instance.redo();
    }

    /**
     * Controller method for Edit->Cut.
     */
    public void editCut() {
        WorkSpace.instance.cut();
    }

    /**
     * Controller method for Edit->Copy.
     */
    public void editCopy() {
        WorkSpace.instance.copy();
    }

    /**
     * Controller method for Edit->Paste.
     */
    public void editPaste() {
        WorkSpace.instance.paste();
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
            throw new RuntimeException(e.getMessage());
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

    /**
     * Controller method for Grades->Prediction.
     */
    public void gradesPrediction() throws IOException {
        Parent root = FXMLLoader.load(Main.predictionResource);
        setStageWithFocus(root, "Prediction");
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
