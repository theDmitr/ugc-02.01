package dmitr.app.controller;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Record;
import dmitr.app.model.Task;
import dmitr.app.util.TaskUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class ListPageController implements Initializable {

    @FXML
    private TableView<Record> recordsTableView;

    @FXML
    private TableColumn<Record, String> nameColumn;

    @FXML
    private TableColumn<Record, String> tagsColumn;

    @FXML
    private TableColumn<Record, String> createDateColumn;

    @FXML
    private TableColumn<Record, String> plannedCompletionDateColumn;

    @FXML
    private TableColumn<Record, String> remainDaysColumn;

    @FXML
    private TableColumn<Record, String> completionDateColumn;

    @FXML
    private CheckBox typeFilterCheckBox;

    @FXML
    private CheckBox recordsCheckBox;

    @FXML
    private CheckBox tasksCheckBox;

    @FXML
    private CheckBox nameFilterCheckBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private CheckBox tagsFilterCheckBox;

    @FXML
    private TextField tagsTextField;

    @FXML
    private CheckBox creationDateFilter;

    @FXML
    private DatePicker creationDatePicker;

    @FXML
    private CheckBox plannedCompletionDateFilter;

    @FXML
    private DatePicker plannedCompletionDatePicker;

    @FXML
    private CheckBox completionDateFilter;

    @FXML
    private DatePicker completionDatePicker;

    @FXML
    private CheckBox remainDaysFilter;

    @FXML
    private TextField remainDaysTextField;

    private ObservableList<Record> records;

    @FXML
    private void openRecordItem() {

    }

    private void addRecord() {

    }

    private void addTask() {

    }

    @FXML
    private void removeRecord() {
        Record selected = recordsTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.ERROR, "Select item!", ButtonType.OK).showAndWait();
            return;
        }

        if (new Alert(Alert.AlertType.CONFIRMATION, "Remove item?", ButtonType.YES, ButtonType.NO)
                .showAndWait().get() == ButtonType.NO)
            return;

        if (selected instanceof Task t)
            DatabaseHelper.getInstance().getTaskDao().remove(t);
        else
            DatabaseHelper.getInstance().getRecordDao().remove(selected);

        fillRecords();
    }

    @FXML
    private void filterAllRecords() {
        recordsTableView.setItems(records);
    }

    @FXML
    private void filterTodayRecords() {
        recordsTableView.setItems(FXCollections.observableArrayList(records.filtered(record -> record.getCreateDate()
                .equals(Date.valueOf(LocalDate.now())))));
    }

    @FXML
    private void filterYesterdayRecords() {
        recordsTableView.setItems(FXCollections.observableArrayList(records.filtered(record -> record.getCreateDate()
                .equals(Date.valueOf(LocalDate.now().minusDays(1))))));
    }

    private void buildRecordsTable() {
        nameColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getName()));
        tagsColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getTags()));
        createDateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(c.getValue().getCreateDate().toString()));
        plannedCompletionDateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(TaskUtils.plannedCompletionDateToString(c.getValue())));
        remainDaysColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(TaskUtils.remainDateToString(c.getValue())));
        completionDateColumn.setCellValueFactory(c -> new ReadOnlyStringWrapper(TaskUtils.completionDateToString(c.getValue())));
    }

    @FXML
    private void updateRecordsTable() {
        recordsTableView.setItems(FXCollections.observableArrayList(records.stream().filter(record -> {
            boolean isTask = record instanceof Task;

            if (typeFilterCheckBox.isSelected() && (
                    isTask && !tasksCheckBox.isSelected() || !isTask && !recordsCheckBox.isSelected()))
                return false;

            if (nameFilterCheckBox.isSelected() && !record.getName().contains(nameTextField.getText()))
                return false;

            if (tagsFilterCheckBox.isSelected() && !record.getTags().contains(tagsTextField.getText()))
                return false;

            if (creationDateFilter.isSelected() && (
                    record.getCreateDate() == null ||
                            (creationDatePicker.getValue() != null &&
                            !record.getCreateDate().equals(Date.valueOf(creationDatePicker.getValue()))))) {
                return false;
            }

            if (!isTask)
                return true;

            Task task = (Task) record;

            if (plannedCompletionDateFilter.isSelected() && (
                    task.getPlannedCompletionDate() == null ||
                            (plannedCompletionDatePicker.getValue() != null &&
                                    !task.getPlannedCompletionDate().equals(Date.valueOf(plannedCompletionDatePicker.getValue())))))
                return false;

            if (completionDateFilter.isSelected() && (
                    task.getCompletionDate() == null ||
                            (completionDatePicker.getValue() != null &&
                                    !task.getCompletionDate().equals(Date.valueOf(completionDatePicker.getValue())))))
                return false;

            if (remainDaysFilter.isSelected() &&
                    (task.getCompletionDate() == null &&
                            !remainDaysTextField.getText().isEmpty() &&
                            remainDaysTextField.getText().length() < 9 &&
                            Integer.parseInt(remainDaysTextField.getText()) != TaskUtils.getBetweenDays(task)))
                return false;

            return true;
        }).toList()));
    }

    private void fillRecords() {
        this.records = FXCollections.observableArrayList(Stream.concat(
                DatabaseHelper.getInstance().getRecordDao().getAll().stream(),
                DatabaseHelper.getInstance().getTaskDao().getAll().stream()).toList());
        filterAllRecords();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        remainDaysTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*"))
                remainDaysTextField.setText(newValue.replaceAll("[^\\d]", ""));
        });

        buildRecordsTable();
        fillRecords();
    }

}
