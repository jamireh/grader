package grader;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {
    public static URL courseResource = Main.class.getResource("view/course.fxml");
    public static URL assignmentsResource = Main.class.getResource("view/assignments.fxml");
    public static URL categoriesResource = Main.class.getResource("view/categories.fxml");
    public static URL gradeSchemeResource = Main.class.getResource("view/gradescheme.fxml");
    public static URL histogramResource = Main.class.getResource("view/histogram.fxml");
    public static URL piechartResource = Main.class.getResource("view/piechart.fxml");
    public static URL studentsResource = Main.class.getResource("view/students.fxml");
    public static URL findResource = Main.class.getResource("view/find.fxml");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/overview.fxml"));
        primaryStage.setTitle("Grader");
        primaryStage.setScene(new Scene(root, 1420, 622));
        primaryStage.show();

        /*primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
                System.out.println("Width: " + newSceneWidth);
            }
        });
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                System.out.println("Height: " + newSceneHeight);
            }
        });*/
    }


    public static void main(String[] args) {
        launch(args);
    }
}
