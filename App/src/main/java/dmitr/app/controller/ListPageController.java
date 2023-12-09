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
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
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
    private MenuItem openRecordItem;

    @FXML
    private MenuItem addRecordItem;

    @FXML
    private MenuItem addTaskItem;

    @FXML
    private MenuItem removeRecordItem;

    @FXML
    private CheckBox recordsCheckBox;

    @FXML
    private CheckBox tasksCheckBox;

    private void openRecordItem() {

    }

    private void addRecord() {

    }

    private void addTask() {

    }

    private void removeRecord() {
        Record selected = recordsTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            new Alert(Alert.AlertType.ERROR, "Select item!", ButtonType.OK).showAndWait();
            return;
        }

        if (
                new Alert(Alert.AlertType.CONFIRMATION, "Select item!", ButtonType.YES, ButtonType.NO)
                .showAndWait().get() == ButtonType.NO
        )
            return;

        if (selected.getClass() == Task.class) {
            DatabaseHelper.getInstance().getTaskDao().remove((Task) selected);
        } else {
            DatabaseHelper.getInstance().getRecordDao().remove(selected);
        }

        updateRecordsTable();
    }

    private void applyActions() {
        removeRecordItem.setOnAction(event -> removeRecord());
        recordsCheckBox.setOnAction(event -> updateRecordsTable());
        tasksCheckBox.setOnAction(event -> updateRecordsTable());
    }

    private void buildRecordsTable() {
        nameColumn.setCellValueFactory(
                c -> new ReadOnlyStringWrapper(c.getValue().getName())
        );
        tagsColumn.setCellValueFactory(
                c -> new ReadOnlyStringWrapper(c.getValue().getTags())
        );
        createDateColumn.setCellValueFactory(
                c -> new ReadOnlyStringWrapper(c.getValue().getCreateDate().toString())
        );
        plannedCompletionDateColumn.setCellValueFactory(
                c -> new ReadOnlyStringWrapper(
                        c.getValue().getClass() == Task.class ? TaskUtils.plannedCompletionDateToString((Task) c.getValue()) : ""
                )
        );
        remainDaysColumn.setCellValueFactory(
                c -> new ReadOnlyStringWrapper(
                        c.getValue().getClass() == Task.class ? TaskUtils.remainDateToString((Task) c.getValue()) : ""
                )
        );
        completionDateColumn.setCellValueFactory(
                c -> new ReadOnlyStringWrapper(
                        c.getValue().getClass() == Task.class ? TaskUtils.completionDateToString((Task) c.getValue()) : ""
                )
        );
    }

    private void updateRecordsTable() {
        boolean recordsShow = recordsCheckBox.isSelected();
        boolean tasksShow = tasksCheckBox.isSelected();

        List<Record> records = recordsShow ? DatabaseHelper.getInstance().getRecordDao().getAll() : new ArrayList<>();
        List<Task> tasks = tasksShow ? DatabaseHelper.getInstance().getTaskDao().getAll() : new ArrayList<>();
        List<Record> concat = Stream.concat(records.stream(), tasks.stream()).toList();

        ObservableList<Record> items = FXCollections.observableList(concat);
        recordsTableView.setItems(items);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyActions();
        buildRecordsTable();
        updateRecordsTable();
    }

}
