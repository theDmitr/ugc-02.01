package dmitr.app.controller;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Record;
import dmitr.app.scene.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditRecordController implements Initializable {

    private static Record editable;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea tagsTextArea;

    public static void setEditable(Record record) {
        editable = record;
    }

    @FXML
    private void save() {
        if (nameTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name!", ButtonType.OK).showAndWait();
            return;
        }

        editable.setName(nameTextField.getText());
        editable.setDescription(descriptionTextArea.getText());
        editable.setTags(tagsTextArea.getText());

        DatabaseHelper.getInstance().getRecordDao().tryUpdate(editable);

        cancel();
    }

    @FXML
    private void cancel() {
        SceneController.getStageByScene(nameTextField.getScene()).close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameTextField.setText(editable.getName());
        descriptionTextArea.setText(editable.getDescription());
        tagsTextArea.setText(editable.getTags());
    }

}
