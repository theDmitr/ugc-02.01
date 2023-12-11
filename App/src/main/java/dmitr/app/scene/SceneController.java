package dmitr.app.scene;

import javafx.scene.Scene;
import javafx.stage.Modality;
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

    public static Stage createChildStage(StageScene stageScene) {
        Stage created = new Stage();

        created.setTitle(stageScene.getCaption());
        created.setScene(stageScene.getScene());

        created.initModality(Modality.WINDOW_MODAL);
        created.initOwner(stage);

        return created;
    }

    public static Stage getStageByScene(Scene scene) {
        return (Stage) scene.getWindow();
    }

}
