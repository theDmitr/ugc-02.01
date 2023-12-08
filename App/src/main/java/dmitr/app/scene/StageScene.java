package dmitr.app.scene;

import dmitr.app.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public enum StageScene {
    MENU("Records/Tasks | Menu", "fxml/Menu.fxml"),
    LIST_PAGE("Records/Tasks | List", "fxml/ListPage.fxml");

    private final String caption;
    private final URL sceneFile;

    StageScene(String caption, String filePath) {
        this.caption = caption;
        this.sceneFile = MainApplication.class.getResource(filePath);
    }

    public String getCaption() {
        return caption;
    }

    public Scene getScene() {
        FXMLLoader loader = new FXMLLoader(sceneFile);

        javafx.scene.Scene stageScene;

        try {
            stageScene = new javafx.scene.Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stageScene;
    }

}
