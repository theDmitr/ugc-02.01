package dmitr.app.controller;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Record;
import dmitr.app.scene.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;
import java.time.LocalDate;

public class CreateRecordController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextArea tagsTextArea;

    @FXML
    private void create() {
        if (nameTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name!", ButtonType.OK).showAndWait();
            return;
        }

        Record created = new Record(Date.valueOf(LocalDate.now()),
                nameTextField.getText(), descriptionTextArea.getText(), tagsTextArea.getText()
        );

        DatabaseHelper.getInstance().getRecordDao().tryCreate(created);

        cancel();
    }

    @FXML
    private void cancel() {
        SceneController.getStageByScene(nameTextField.getScene()).close();
    }

}
