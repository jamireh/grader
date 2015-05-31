package grader.controller;

/**
 * @author Mallika Potter
 */

import grader.model.curve.Entry;
import grader.model.file.WorkSpace;
import grader.model.gradebook.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.*;

import java.net.URL;
import java.util.Hashtable;
import java.util.ResourceBundle;


public class HistogramController implements Initializable {

    //private boolean smallData = true;
    private static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object");

    private Section section = new Section();

    private Hashtable<Double, Integer> vals = new Hashtable<Double, Integer>();
    private Entry moving;


    @FXML
    ObservableList<grader.model.curve.Entry> data = FXCollections.observableArrayList();


    @FXML
    private TableView<grader.model.curve.Entry> table = new TableView<grader.model.curve.Entry>();

    @FXML private TableColumn<Entry, String> letterCol;
    @FXML private TableColumn<Entry, String> percentCol;
    @FXML private TableColumn<Entry, String> starCol;

    @FXML private javafx.scene.control.Button cancelButton;
    @FXML private javafx.scene.control.Button saveButton;
    @FXML private javafx.scene.control.Button updateButton;


    @FXML
    private void saveButtonAction(ActionEvent event)
    {
        section.pushGradeScheme();
    }

    @FXML
    private void updateButtonAction(ActionEvent event)
    {
        section.applyHistogram();
    }

    @FXML
    private void cancelButtonAction(ActionEvent event)
    {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //WorkSpace.instance.addObserver(this);


        grader.model.curve.Histogram hist = WorkSpace.instance.getHistogram();

        for (int i = 100; i >= 0; i--) {
            data.add(hist.getEntry((double) i));
        }

        letterCol.setMinWidth(100);
        percentCol.setMinWidth(100);
        starCol.setMinWidth(200);





        letterCol.setCellFactory(cellData -> {
            TableCell<Entry, String> letter = new TableCell<Entry, String>(){
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setStyle("");
                    } else {
                        setText(item);
                    }
                }

            };

            letter.setOnMouseClicked(new EventHandler<MouseEvent>() { //click
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) { // double click

                        String selected = letter.getItem();
                        if (selected != null) {
                        }
                    }
                    }
                }

                );

                letter.setOnDragDetected(new EventHandler<MouseEvent>()

                                         { //drag
                                             @Override
                                             public void handle(MouseEvent event) {
                                                 // drag was detected, start drag-and-drop gesture
                                                 //moving = letter.getSelectionModel().getSelectedItem();
                                                 String selected = letter.getItem();
                                                 if (selected != null) {
                                                     //System.out.println(selected);
                                                     Dragboard db = letter.startDragAndDrop(TransferMode.ANY);
                                                     ClipboardContent content = new ClipboardContent();
                                                     content.putString(selected);
                                                     db.setContent(content);
                                                     event.consume();
                                                 }
                                             }
                                         }

                );

                letter.setOnDragOver(new EventHandler<DragEvent>()

                                     {
                                         @Override
                                         public void handle(DragEvent event) {
                                             // data is dragged over the target
                                             Dragboard db = event.getDragboard();
                                             if (event.getDragboard().hasString()) {
                                                 event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                                             }
                                             event.consume();
                                         }
                                     }

                );

                letter.setOnDragDropped(new EventHandler<DragEvent>()

                {
                    @Override
                    public void handle(DragEvent event) {
                        Dragboard db = event.getDragboard();
                        boolean success = false;
                        if (event.getDragboard().hasString()) {

                            String text = db.getString();
                            //System.out.println(text);
                            //System.out.println(letter.getText());
                            letter.setText(text);
                            //data.add(moving);
                            //table.setItems(data);
                            success = true;
                        }
                        event.setDropCompleted(success);
                        event.consume();
                        //System.out.println("Dropped");
                    }
                });


            return letter;

        });

        letterCol.setCellValueFactory(cellData -> cellData.getValue().letterProperty());
        percentCol.setCellValueFactory(cellData -> cellData.getValue().percentProperty());
        starCol.setCellValueFactory(cellData -> cellData.getValue().starProperty());


        table.setItems(data);


    }

}
