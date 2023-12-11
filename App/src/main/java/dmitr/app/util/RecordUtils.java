package dmitr.app.util;

import dmitr.app.model.Record;
import dmitr.app.model.Task;

import java.time.LocalDate;

public class RecordUtils {

    public static long getBetweenDays(Task task) {
        return DateUtils.getDaysBetweenDates(LocalDate.now(), task.getPlannedCompletionDate().toLocalDate());
    }

    public static String completionDateToString(Record record) {
        return record instanceof Task t && t.getCompletionDate() != null ? t.getCompletionDate().toString() : "";
    }

    public static String remainDateToString(Record record) {
        return record instanceof Task t && t.getCompletionDate() == null ? Long.toString(getBetweenDays(t)) : "";
    }

    public static String plannedCompletionDateToString(Record record) {
        return record instanceof Task t ? t.getPlannedCompletionDate().toString() : "";
    }

}
