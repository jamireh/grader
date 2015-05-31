package grader.controller;

import grader.model.file.WorkSpace;
import grader.model.items.Assignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignmentController implements Initializable {
    @FXML ComboBox cbCatParent;
    @FXML TextField tfCatName;
    @FXML TextField tfWeight;
    @FXML TextField tfRawScore;
    @FXML DatePicker dpDueDate;
    @FXML CheckBox chbHandin;
    @FXML Label lHandin;
    @FXML TextField tfHandin;
    @FXML Button bAdd;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> parentOptions =
                FXCollections.observableArrayList(
                        "CPE 309",
                        "Tests",
                        "Projects",
                        "Quizzes",
                        "Midterms"
                );
        cbCatParent.setValue(parentOptions.get(0));
        cbCatParent.setItems(parentOptions);

    }

    public void onAddButtonPress(ActionEvent actionEvent)
    {
        boolean exceptionThrown = false;

        if(cbCatParent.getValue().equals("CPE 309"))
        {
            try
            {
                if(WorkSpace.instance.course != null)
                {
                    WorkSpace.instance.course.addAssignment(new Assignment(tfCatName.getText(), dpDueDate.getValue(), tfRawScore.getText(), tfWeight.getText()));
                }

            }
            catch(RuntimeException e)
            {
                exceptionThrown = true;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Incorrect Input");
                alert.setContentText(e.getMessage());
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.showAndWait();
            }
        }
        if(!exceptionThrown)
        {
            Stage stage = (Stage) bAdd.getScene().getWindow();
            stage.close();
        }
    }

    public void onHandinChecked(ActionEvent event)
    {
        lHandin.setDisable(!chbHandin.isSelected());
        tfHandin.setDisable(!chbHandin.isSelected());
    }
}
