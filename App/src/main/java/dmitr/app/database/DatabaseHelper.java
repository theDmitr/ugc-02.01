package dmitr.app.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dmitr.app.model.Record;
import dmitr.app.model.Task;

import java.sql.SQLException;

public class DatabaseHelper {

    private static final String DATABASE_NAME = "jdbc:sqlite:db.db";

    private static final DatabaseHelper INSTANCE;

    static {
        try {
            INSTANCE = new DatabaseHelper();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private final ConnectionSource connectionSource;

    private Dao<Record, Integer> recordsDao;
    private Dao<Task, Integer> tasksDao;

    private DatabaseHelper() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connectionSource = new JdbcConnectionSource(DATABASE_NAME);
        setupTables();
        setupDao();
    }

    public static DatabaseHelper getInstance() {
        return INSTANCE;
    }

    private void setupTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Record.class);
        TableUtils.createTableIfNotExists(connectionSource, Task.class);
    }

    private void setupDao() throws SQLException {
        recordsDao = DaoManager.createDao(connectionSource, Record.class);
        tasksDao = DaoManager.createDao(connectionSource, Task.class);
    }

    public Dao<Record, Integer> getRecordsDao() {
        return recordsDao;
    }

    public Dao<Task, Integer> getTasksDao() {
        return tasksDao;
    }

}
