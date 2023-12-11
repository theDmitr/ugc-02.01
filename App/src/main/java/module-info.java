module dmitr.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires ormlite.jdbc;

    requires atlantafx.base;

    opens dmitr.app to javafx.fxml;

    exports dmitr.app;
    exports dmitr.app.controller;
    exports dmitr.app.model to ormlite.jdbc;

    opens dmitr.app.controller to javafx.fxml;
    opens dmitr.app.model to ormlite.jdbc;
}