package dmitr.app.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@DatabaseTable(tableName = "records")
public class Record {

    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField(canBeNull = false)
    private Date createDate;

    @DatabaseField(canBeNull = false)
    private String name;

    @DatabaseField(canBeNull = false)
    private String tags;

    @DatabaseField(canBeNull = false)
    private String description;

    public Record() {

    }

    public Record(Date createDate, String name, String tags, String description) {
        this.createDate = createDate;
        this.name = name;
        this.tags = tags;
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {
        return name;
    }

    public String getTags() {
        return tags;
    }

    public String getDescription() {
        return description;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
