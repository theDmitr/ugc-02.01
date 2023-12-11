package dmitr.app.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import dmitr.app.model.Record;

import java.sql.SQLException;
import java.util.List;

public class RecordDaoImpl extends BaseDaoImpl<Record, Long> implements RecordDao {

    public RecordDaoImpl(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Record.class);
    }

    @Override
    public List<Record> getAll() {
        try {
            return super.queryForAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void tryDelete(Record record) {
        try {
            super.delete(record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void tryCreate(Record record) {
        try {
            super.create(record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void tryUpdate(Record record) {
        try {
            super.update(record);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
