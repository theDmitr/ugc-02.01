package dmitr.app.controller;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Task;
import dmitr.app.scene.SceneController;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;

public class CreateTaskController {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private TextArea tagsTextArea;

    @FXML
    private DatePicker plannedCompletionDatePicker;

    @FXML
    private void create() {
        if (nameTextField.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Enter a Name!", ButtonType.OK).showAndWait();
            return;
        }

        LocalDate selectedDate = plannedCompletionDatePicker.getValue();

        if (selectedDate == null) {
            new Alert(Alert.AlertType.ERROR, "Select planned completion date!", ButtonType.OK).showAndWait();
            return;
        }

        LocalDate todayDate = LocalDate.now();

        if (selectedDate.isBefore(todayDate)) {
            new Alert(Alert.AlertType.ERROR, "IncorrectDate!", ButtonType.OK).showAndWait();
            return;
        }

        Task created = new Task(Date.valueOf(todayDate),
                nameTextField.getText(), descriptionTextArea.getText(), tagsTextArea.getText(),
                Date.valueOf(selectedDate)
        );

        DatabaseHelper.getInstance().getTaskDao().tryCreate(created);

        cancel();
    }

    @FXML
    private void cancel() {
        SceneController.getStageByScene(nameTextField.getScene()).close();
    }

}
