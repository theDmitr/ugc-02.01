package dmitr.app.scene;

import dmitr.app.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public enum StageScene {
    MENU("Records/Tasks | Menu", "fxml/Menu.fxml"),
    LIST_PAGE("Records/Tasks | List", "fxml/ListPage.fxml"),
    CREATE_RECORD("Records/Tasks | Create Record", "fxml/CreateRecord.fxml"),
    CREATE_TASK("Records/Tasks | Create Task", "fxml/CreateTask.fxml"),
    EDIT_RECORD("Records/Tasks | Edit Record", "fxml/EditRecord.fxml"),
    EDIT_TASK("Records/Tasks | Edit Task", "fxml/EditTask.fxml");

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

        try {
            return new javafx.scene.Scene(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
