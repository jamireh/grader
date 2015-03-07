package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    ComboBox cbCatParent;
    @FXML
    TextField tfCatName;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "CPE 309"
                );
        cbCatParent.setPromptText(options.get(0));
        cbCatParent.setItems(options);

    }
}
