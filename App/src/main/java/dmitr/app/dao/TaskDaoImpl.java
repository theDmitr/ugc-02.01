package dmitr.app.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import dmitr.app.model.Task;

import java.sql.SQLException;
import java.util.List;

public class TaskDaoImpl extends BaseDaoImpl<Task, Long> implements TaskDao {

    public TaskDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Task.class);
    }

    @Override
    public List<Task> getAll() {
        try {
            return super.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void tryDelete(Task task) {
        try {
            super.delete(task);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void tryCreate(Task task) {
        try {
            super.create(task);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void tryUpdate(Task task) {
        try {
            super.update(task);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
