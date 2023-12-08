package dmitr.app.controller;

import dmitr.app.model.Record;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class ListPageController implements Initializable {

    @FXML
    private TableColumn<Record, String> nameColumn;

    @FXML
    private TableColumn<Record, String> tagsColumn;

    @FXML
    private TableColumn<Record, String> createDateColumn;

    @FXML
    private TableColumn<Record, String> completionDateColumn;

    private void applyActions() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyActions();
    }

}
