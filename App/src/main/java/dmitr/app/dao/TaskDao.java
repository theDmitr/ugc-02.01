package dmitr.app.dao;

import com.j256.ormlite.dao.Dao;
import dmitr.app.model.Task;

import java.util.List;

public interface TaskDao extends Dao<Task, Long> {

    List<Task> getAll();
    void remove(Task task);

}
