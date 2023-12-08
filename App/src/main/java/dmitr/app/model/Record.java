package dmitr.app.model;

import java.sql.Date;
import java.util.List;

public class Record {

    private Date createDate;
    private String name;
    private List<String> tags;
    private String description;

    public Record(Date createDate, String name, List<String> tags, String description) {
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

    public List<String> getTags() {
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

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
