package grader.controller;

import grader.find;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController
{
    private Stage stage;

    public MainController()
    {
        stage = new Stage();
    }

    /* FILE MENU COMMANDS */
    public void fileNewClass() {
        System.out.println("File->New Class clicked!");
    }

    public void fileExport() {
        System.out.println("File->Export clicked!");
    }

    public void fileImport() {
        System.out.println("File->Import clicked!");
    }

    public void filePrint() {
        System.out.println("File->Print clicked!");
    }

    public void fileLogout() {
        System.out.println("File->Logout clicked!");
    }

    public void fileQuit() {
        System.out.println("File->Quit clicked!");
    }

    /* EDIT MENU COMMANDS */
    public void editUndo() {
        System.out.println("Edit->Undo clicked!");
    }

    public void editRedo() {
        System.out.println("Edit->Redo clicked!");
    }

    public void editCut() {
        System.out.println("Edit->Cut clicked!");
    }

    public void editCopy() {
        System.out.println("Edit->Copy clicked!");
    }

    public void editPaste() {
        System.out.println("Edit->Paste clicked!");
    }

    public void editFind() {
        System.out.println("Edit->Find clicked!");
        find dialog = new find();
        dialog.pack();
        dialog.setVisible(true);
    }

    /* STUDENTS MENU COMMANDS */
    public void studentsAddNew() {
        System.out.println("Students->Add New clicked!");
    }

    public void studentsSyncRoster() {
        System.out.println("Students->Sync Roster clicked!");
    }

    public void studentsEditRoster() {
        System.out.println("Students->Edit Roster clicked!");
    }

    public void studentsCreateGroup() {
        System.out.println("Students->Create Group clicked!");
    }

    public void studentsEditGroups() {
        System.out.println("Students->Edit Groups clicked!");
    }

    /* GRADES MENU COMMANDS */
    public void gradesAddCategory() throws IOException {
        System.out.println("Grades->Add Category clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("../view/categories.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void gradesAddAssignment() throws IOException {
        System.out.println("Grades->Add Assignment clicked!");
        Parent root = FXMLLoader.load(getClass().getResource("../view/assignments.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void gradesManage() {
        System.out.println("Grades->Manage clicked!");
    }

    public void gradesPublish() {
        System.out.println("Grades->Publish clicked!");
    }

    /* CURVE MENU COMMANDS */
    public void curvePieChart() {
        System.out.println("Grades->Pie Chart clicked!");
    }

    public void curveHistogram() {
        System.out.println("Grades->Histogram clicked!");
    }

    public void curveGradeScheme() {
        System.out.println("Grades->Grade Scheme clicked!");
    }
}
