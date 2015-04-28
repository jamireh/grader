package grader.controller;

import grader.Main;
import grader.model.StudentEntry;
import grader.model.edit.Edit;
import grader.model.file.*;
import grader.model.gradebook.Course;
import grader.model.gradebook.Gradebook;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Main controller class for the Grader application.
 *
 * @author Gregory Davis
 */
public class MainController implements Initializable
{
    private Stage stage;

    @FXML Node sidebar;
    @FXML HBox hbTable;
    @FXML VBox vbContainer;
    @FXML Button bSave;
    @FXML Button bRevert;
    @FXML Button bSavePost;

    @FXML SidebarController sidebarController;

    static Course course;

    /**
     * Constructor.
     */
    public MainController() {
        stage = new Stage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebar.setVisible(true);
        sidebar.setVisible(true);
        vbContainer.setVisible(true);
        course = WorkSpace.instance.getCourse();
        StatsController stats = new StatsController();
        WorkSpace.instance.spreadsheet.setController(this);
    }

    /* FILE MENU COMMANDS */
    /**
     * Controller method for File->New Course.
     */
    public void fileNewCourse() throws IOException {
        Parent root = FXMLLoader.load(Main.courseResource);
        stage.setTitle("New Course");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for File->Export.
     */
    public void fileExport() { File.exportGradebook(); }

    /**
     * Controller method for File->Import.
     */
    public void fileImport() { File.importGradebook(); }

    /**
     * Controller method for File->Print.
     */
    public void filePrint() { File.print(); }

    /**
     * Controller method for File->Logout.
     */
    public void fileLogout() { File.logout(); }

    /**
     * Controller method for File->Quit.
     * Exits the application.
     */
    public void fileQuit() { File.quit(); }

    /* EDIT MENU COMMANDS */
    /**
     * Controller method for Edit->Undo.
     */
    public void editUndo() { Edit.undo(); }

    /**
     * Controller method for Edit->Redo.
     */
    public void editRedo() { Edit.redo(); }

    /**
     * Controller method for Edit->Cut.
     */
    public void editCut() { Edit.cut(); }

    /**
     * Controller method for Edit->Copy.
     */
    public void editCopy() { Edit.copy(); }

    /**
     * Controller method for Edit->Paste.
     */
    public void editPaste() { Edit.paste(); }

    /**
     * Controller method for Edit->Find.
     */
    public void editFind() throws IOException {
        System.out.println("Edit->Find clicked!");
        Parent root = FXMLLoader.load(Main.findResource);
        stage.setScene(new Scene(root));
        stage.show();
    }

    /* STUDENTS MENU COMMANDS */
    /**
     * Controller method for Students->Add New.
     */
    public void studentsAddNew() throws IOException {
        System.out.println("Students->Add New clicked!");
        Parent root = FXMLLoader.load(Main.studentsResource);
        stage.setTitle("Add Student");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Students->Sync Roster.
     */
    public void studentsSyncRoster() {
        System.out.println("Students->Sync Roster clicked!");
        Course course = WorkSpace.instance.getCourse();
        if (course != null) course.syncRoster();
    }

    /**
     * Controller method for Students->Edit Roster.
     */
    public void studentsEditRoster() {
        System.out.println("Students->Edit Roster clicked!");
    }

    /**
     * Controller method for Students->Create Group.
     */
    public void studentsCreateGroup() throws IOException {
        System.out.println("Students->Create Group clicked!");
        Parent root = FXMLLoader.load(Main.groupsResource);
        stage.setTitle("Add New Group");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Students->Edit Groups.
     */
    public void studentsEditGroups() {
        System.out.println("Students->Edit Groups clicked!");
    }

    /* GRADES MENU COMMANDS */
    /**
     * Controller method for Grades->Add Category.
     */
    public void gradesAddCategory() throws IOException {
        System.out.println("Grades->Add Category clicked!");
        Parent root = FXMLLoader.load(Main.categoriesResource);
        stage.setTitle("Add Category");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Grades->Add Assignment.
     */
    public void gradesAddAssignment() throws IOException {
        System.out.println("Grades->Add Assignment clicked!");
        Parent root = FXMLLoader.load(Main.assignmentsResource);
        stage.setTitle("Add Assignment");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Grades->Manage.
     */
    public void gradesManage() {
        System.out.println("Grades->Manage clicked!");
    }

    /**
     * Controller method for Grades->Publish.
     */
    public void gradesPublish() {
        System.out.println("Grades->Publish clicked!");
    }

    /* CURVE MENU COMMANDS */
    /**
     * Controller method for Curve->Pie Chart.
     */
    public void curvePieChart() throws IOException {
        System.out.println("Grades->Pie Chart clicked!");
        Parent root = FXMLLoader.load(Main.piechartResource);
        stage.setTitle("Pie Chart");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Curve->Histogram.
     */
    public void curveHistogram() throws IOException {
        System.out.println("Grades->Histogram clicked!");
        Parent root = FXMLLoader.load(Main.histogramResource);
        stage.setTitle("Histogram");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Curve->Grade Scheme.
     */
    public void curveGradeScheme() throws IOException {
        System.out.println("Grades->Grade Scheme clicked!");
        Parent root = FXMLLoader.load(Main.gradeSchemeResource);
        stage.setTitle("Grade Scheme");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Controller method for Revert Grades.
     * @param event action event
     */
    public void onRevertButtonClicked(ActionEvent event)
    {
        System.out.println("Revert button clicked");
    }

    /**
     * Controller method for Save Grades.
     * @param event action event
     */
    public void onSaveButtonClicked(ActionEvent event)
    {
        System.out.println("Save button clicked");
    }

    /**
     * Controller method for Save and Post Grades.
     * @param event action event
     */
    public void onSavePostButtonClicked(ActionEvent event)
    {
        System.out.println("Save & Post button clicked");
    }

    public void setupGradebook(String[] headers, String[][] grades)
    {
        TableView<String[]> table = new TableView<String[]>();

        final Button b1 = new Button("Revert");
        b1.setFont(new Font("Arial", 16));
        final Button b2 = new Button("Save");
        b2.setFont(new Font("Arial", 16));
        final Button b3 = new Button("Save and Publish");
        b3.setFont(new Font("Arial", 16));

        table.setEditable(true);
        table.setMinWidth(1200);
        table.setMaxWidth(1200);
        table.setMaxHeight(600);

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(grades));

        for (int i = 0; i < headers.length; i++) {
            TableColumn tc = new TableColumn(headers[i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);

        hbTable.setSpacing(5);

        hbTable.getChildren().addAll(table);
    }
}
