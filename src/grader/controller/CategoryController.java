package grader.controller;

import grader.model.gradebook.WorkSpace;
import grader.model.items.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CategoryController {
    @FXML ComboBox cbCatParent;
    @FXML TextField tfCatName;
    @FXML Button bAdd;
    @FXML ComboBox cbWeights;
    @FXML TextField tfWeight;


    @FXML
    public void initialize()
    {
        ObservableList<String> parentOptions = FXCollections.observableArrayList();
        parentOptions.add(WorkSpace.instance.getCourse().name);
        for(Category c : WorkSpace.instance.getCourse().categories)
        {
            parentOptions.add(c.name);
        }
        cbCatParent.setValue(parentOptions.get(0));
        cbCatParent.setItems(parentOptions);
        ObservableList<String> weightOptions =
                FXCollections.observableArrayList(
                        "Evenly within the category",
                        "According to raw scores"
                );
        cbWeights.setValue(weightOptions.get(0));
        cbWeights.setItems(weightOptions);

    }

    public void onAddButtonClick(ActionEvent actionEvent)
    {
        boolean exceptionThrown = false;
        try
        {
            if(WorkSpace.instance.course != null)
            {
                WorkSpace.instance.course.addCategory(new Category(tfCatName.getText(), tfWeight.getText(), cbWeights.getItems().indexOf(cbWeights.getValue()) != 0));
            }
        }
        catch(IllegalArgumentException e)
        {
            exceptionThrown = true;
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Incorrect Input");
            alert.setContentText(e.getMessage());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        }
        if(!exceptionThrown)
        {
            WorkSpace.instance.update();
            Stage stage = (Stage) bAdd.getScene().getWindow();
            stage.close();
        }
    }
}
