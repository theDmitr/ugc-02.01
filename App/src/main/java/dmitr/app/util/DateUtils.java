package dmitr.app.util;

import java.time.LocalDate;

import static java.util.concurrent.TimeUnit.DAYS;

public class DateUtils {

    public static long getDaysBetweenDates(LocalDate date1, LocalDate date2) {
        return DAYS.toChronoUnit().between(date1, date2);
    }

}
