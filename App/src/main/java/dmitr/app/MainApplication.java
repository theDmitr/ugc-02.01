package dmitr.app;

import dmitr.app.database.DatabaseHelper;
import dmitr.app.scene.SceneController;
import dmitr.app.scene.StageScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SceneController.init(stage);
        SceneController.setScene(StageScene.MENU);
        var result = DatabaseHelper.getInstance();
    }

}
