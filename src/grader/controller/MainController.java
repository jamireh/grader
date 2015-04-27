package grader.controller;

import grader.Main;
import grader.model.StudentEntry;
import grader.model.edit.Edit;
import grader.model.file.File;
import grader.model.gradebook.Course;
import grader.model.gradebook.Gradebook;
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

    public MainController() {
        stage = new Stage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sidebar.setVisible(true);
        sidebar.setVisible(true);
        vbContainer.setVisible(true);
        setupGradebook();
    }

    /* FILE MENU COMMANDS */
    /**
     * Controller method for File->New Course.
     */
    public void fileNewCourse() throws IOException {
        course = Gradebook.getCannedGradebook().courses.get(0);
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

    public void setupGradebook()
    {
        TableView table = new TableView();

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

        TableColumn nameCol = new TableColumn("Name");
        TableColumn groupCol = new TableColumn("Group");
        TableColumn projectCol = new TableColumn("Projects");
        TableColumn quizCol = new TableColumn("Quizzes");
        TableColumn midtermCol = new TableColumn("Midterms");
        TableColumn finalCol = new TableColumn("Final");
        TableColumn participationCol = new TableColumn("Participation");
        TableColumn totalCol = new TableColumn("Total");
        TableColumn letterGradeCol = new TableColumn("Letter Grade");

        TableColumn firstProjectCol = new TableColumn("Project 1");
        TableColumn secondProjectCol = new TableColumn("Project 2");
        projectCol.getColumns().addAll(firstProjectCol, secondProjectCol);

        TableColumn firstQuizCol = new TableColumn("Quiz 1");
        TableColumn secondQuizCol = new TableColumn("Quiz 2");
        quizCol.getColumns().addAll(firstQuizCol, secondQuizCol);

        TableColumn firstMidtermCol = new TableColumn("Midterm 1");
        TableColumn secondMidtermCol = new TableColumn("Midterm 2");
        midtermCol.getColumns().addAll(firstMidtermCol, secondMidtermCol);


        nameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.12));
        groupCol.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        projectCol.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        firstProjectCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        secondProjectCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        quizCol.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        firstQuizCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        secondQuizCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        midtermCol.prefWidthProperty().bind(table.widthProperty().multiply(0.10));
        firstMidtermCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        secondMidtermCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        finalCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        participationCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));
        totalCol.prefWidthProperty().bind(table.widthProperty().multiply(0.05));
        letterGradeCol.prefWidthProperty().bind(table.widthProperty().multiply(0.08));

        final ObservableList<StudentEntry> data = FXCollections.observableArrayList(
                new StudentEntry("Clark, Brandon J.", "Team 1", 19, 18, 8, 13, 32, 30, 89, 1, 85, "B+"),
                new StudentEntry("Dang, Carmen", "Team 2", 19, 20, 8, 12, 35, 50, 92, 4, 78, "C+"),
                new StudentEntry("Fong, Vivian", "Team 3", 16, 15, 9, 12, 37, 46, 80, 2, 68, "D+"),
                new StudentEntry("Garg, Nupur", "Team 4", 20, 16, 8, 17, 45, 35, 72, 2, 78, "C+"),
                new StudentEntry("Hicks, Katelyn C.", "Team 5", 18, 22, 8, 14, 34, 36, 93, 2, 66, "D+"),
                new StudentEntry("Hwang, Helen", "Team 1", 24, 16, 7, 10, 32, 39, 89, 5, 80, "B-"),
                new StudentEntry("Joshi, Esha", "Team 2", 15, 19, 8, 19, 47, 32, 95, 4, 85, "B"),
                new StudentEntry("Krier, Connor M.", "Team 3", 17, 24, 6, 10, 39, 30, 75, 2, 83, "B"),
                new StudentEntry("Lee, Daniel H.", "Team 4", 25, 21, 6, 14, 30, 46, 95, 5, 74, "C"),
                new StudentEntry("Lukens, Myra C.", "Team 5", 21, 20, 8, 19, 39, 39, 95, 1, 75, "C"),
                new StudentEntry("Oelkers, Blaine P.", "Team 1", 21, 15, 7, 17, 37, 38, 73, 2, 77, "C+"),
                new StudentEntry("Poole V, Frank", "Team 2", 20, 25, 9, 19, 30, 38, 93, 5, 78, "C+"),
                new StudentEntry("Quezada, Brian J.", "Team 3", 16, 22, 6, 19, 48, 31, 93, 2, 63, "D-"),
                new StudentEntry("Qureshi, Wasae Abdul", "Team 4", 25, 24, 9, 14, 48, 45, 80, 3, 78, "C+"),
                new StudentEntry("Toy, Daniel L.", "Team 5", 24, 16, 7, 15, 39, 47, 77, 5, 85, "B")
        );


        nameCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("name")
        );
        groupCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("group")
        );
        firstProjectCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("projects1")
        );
        secondProjectCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("projects1")
        );
        firstQuizCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("quizzes1")
        );
        secondQuizCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("quizzes2")
        );
        firstMidtermCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("midterm1")
        );
        secondMidtermCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("midterm2")
        );
        finalCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("finalExam")
        );
        participationCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("participation")
        );
        totalCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("total")
        );
        letterGradeCol.setCellValueFactory(
                new PropertyValueFactory<StudentEntry, String>("letterGrade")
        );

        table.setItems(data);
        table.getColumns().addAll(nameCol, groupCol,
                projectCol, quizCol, midtermCol, finalCol, participationCol, totalCol, letterGradeCol);

        hbTable.setSpacing(5);

        hbTable.getChildren().addAll(table);
    }
}
