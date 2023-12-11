package dmitr.app;

import atlantafx.base.theme.PrimerDark;
import dmitr.app.database.DatabaseHelper;
import dmitr.app.model.Record;
import dmitr.app.model.Task;
import dmitr.app.scene.SceneController;
import dmitr.app.scene.StageScene;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        SceneController.init(stage);
        SceneController.setScene(StageScene.MENU);

        DatabaseHelper.getInstance().getRecordDao().createOrUpdate(new Record(Date.valueOf(LocalDate.now()), "asd", "123 123 123", "123123123123123123"));
        DatabaseHelper.getInstance().getTaskDao().createOrUpdate(new Task(Date.valueOf(LocalDate.now().minusDays(1)), "asd", "123 123 123", "123123123123123123", new Date(2, 2, 2)));
    }

}
