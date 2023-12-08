package dmitr.app.model;

import java.sql.Date;
import java.util.List;

public class Task extends Record {

    private Date completionDate;

    public Task(Date createDate, String name, List<String> tags, String description, Date completionDate) {
        super(createDate, name, tags, description);
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

}
