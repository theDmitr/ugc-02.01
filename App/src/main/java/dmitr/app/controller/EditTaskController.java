package dmitr.app.controller;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Task;
import dmitr.app.scene.SceneController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {

    private static Task editable;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextArea tagsTextArea;
    @FXML
    private DatePicker plannedCompletionDatePicker;
    @FXML
    private CheckBox completedCheckBox;

    public static void setEditable(Task record) {
        editable = record;
    }

    @FXML
    private void save() {
        if (nameTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name!", ButtonType.OK).showAndWait();
            return;
        }

        if (plannedCompletionDatePicker.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select a date!", ButtonType.OK).showAndWait();
            return;
        }

        if (plannedCompletionDatePicker.getValue().isBefore(editable.getCreateDate().toLocalDate())) {
            new Alert(Alert.AlertType.ERROR, "Incorrect date!", ButtonType.OK).showAndWait();
            return;
        }

        editable.setName(nameTextField.getText());
        editable.setDescription(descriptionTextArea.getText());
        editable.setTags(tagsTextArea.getText());
        editable.setPlannedCompletionDate(Date.valueOf(plannedCompletionDatePicker.getValue()));

        if (!completedCheckBox.isSelected())
            editable.setCompletionDate(null);

        if (completedCheckBox.isSelected() && editable.getCompletionDate() == null)
            editable.setCompletionDate(Date.valueOf(LocalDate.now()));

        DatabaseHelper.getInstance().getTaskDao().tryUpdate(editable);

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
        plannedCompletionDatePicker.setValue(editable.getPlannedCompletionDate().toLocalDate());
        completedCheckBox.setSelected(editable.getCompletionDate() != null);
    }

}
