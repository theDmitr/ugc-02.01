package dmitr.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.util.List;

@DatabaseTable(tableName = "tasks")
public class Task extends Record {

    @DatabaseField(canBeNull = false)
    private Date completionDate;

    public Task() {

    }

    public Task(Date createDate, String name, String tags, String description, Date completionDate) {
        super(createDate, name, tags, description);
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

}
