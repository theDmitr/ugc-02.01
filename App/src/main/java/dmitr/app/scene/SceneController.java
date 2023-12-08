package dmitr.app.scene;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    private static Stage stage;

    public static void init(Stage stage) {
        SceneController.stage = stage;
    }

    public static void setScene(StageScene stageScene) {
        Scene scene = stageScene.getScene();

        stage.setTitle(stageScene.getCaption());
        stage.setScene(scene);
        stage.show();
    }

}
