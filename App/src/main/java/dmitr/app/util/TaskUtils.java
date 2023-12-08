package dmitr.app.util;

import dmitr.app.model.Task;

import java.sql.Date;
import java.time.LocalDate;

public class TaskUtils {

    public static long getBetweenDays(Task task) {
        return DateUtils.getDaysBetweenDates(LocalDate.now(), task.getPlannedCompletionDate().toLocalDate());
    }

    public static String completionDateToString(Task task) {
        Date date = task.getCompletionDate();
        return date != null ? date.toString() : "";
    }

    public static String remainDateToString(Task task) {
        return task.getCompletionDate() != null ? "" : Long.toString(TaskUtils.getBetweenDays(task));
    }

    public static String plannedCompletionDateToString(Task task) {
        return task.getPlannedCompletionDate().toString();
    }

}
