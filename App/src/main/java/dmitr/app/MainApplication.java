package dmitr.app;

import atlantafx.base.theme.PrimerDark;
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
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        SceneController.init(stage);
        SceneController.setScene(StageScene.MENU);
    }

}
