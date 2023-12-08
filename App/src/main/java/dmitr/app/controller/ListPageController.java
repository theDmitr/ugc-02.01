package dmitr.app.controller;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Record;
import dmitr.app.model.Task;
import dmitr.app.util.DateUtils;
import dmitr.app.util.TaskUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

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

    private void openRecordItem() {

    }

    private void addRecord() {

    }
    private void addTask() {

    }

    private void removeRecord() {

    }

    private void applyActions() {
        removeRecordItem.setOnAction(event -> removeRecord());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyActions();
        buildRecordsTable();
    }

}
