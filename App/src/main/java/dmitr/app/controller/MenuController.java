package dmitr.app.controller;

import dmitr.app.scene.SceneController;
import dmitr.app.scene.StageScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button listPageButton;

    private void applyActions() {
        listPageButton.setOnAction(event -> SceneController.setScene(StageScene.LIST_PAGE));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyActions();
    }

}
