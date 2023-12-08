package dmitr.app;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Record;
import dmitr.app.model.Task;
import dmitr.app.scene.SceneController;
import dmitr.app.scene.StageScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        SceneController.init(stage);
        SceneController.setScene(StageScene.MENU);
        var result = DatabaseHelper.getInstance();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
