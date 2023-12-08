module dmitr.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens dmitr.app to javafx.fxml;
    exports dmitr.app;
    exports dmitr.app.controller;
    opens dmitr.app.controller to javafx.fxml;
}