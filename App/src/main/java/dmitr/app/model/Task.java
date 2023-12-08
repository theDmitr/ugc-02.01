package dmitr.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;

@DatabaseTable(tableName = "tasks")
public class Task extends Record {

    @DatabaseField(canBeNull = false)
    private Date plannedCompletionDate;

    @DatabaseField()
    private Date completionDate;

    public Task() {

    }

    public Task(Date createDate, String name, String tags, String description, Date plannedCompletionDate) {
        super(createDate, name, tags, description);
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public Date getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public void setPlannedCompletionDate(Date plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

}
