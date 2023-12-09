package dmitr.app.database;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dmitr.app.dao.RecordDao;
import dmitr.app.dao.RecordDaoImpl;
import dmitr.app.dao.TaskDao;
import dmitr.app.dao.TaskDaoImpl;
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

    private RecordDaoImpl recordDao;
    private TaskDao taskDao;

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
        recordDao = new RecordDaoImpl(connectionSource);
        taskDao = new TaskDaoImpl(connectionSource);
    }

    public RecordDao getRecordDao() {
        return recordDao;
    }

    public TaskDao getTaskDao() {
        return taskDao;
    }

}
