package dmitr.app.dao;

import com.j256.ormlite.dao.Dao;
import dmitr.app.model.Record;

import java.util.List;

public interface RecordDao extends Dao<Record, Long> {

    List<Record> getAll();
    void remove(Record record);

}
