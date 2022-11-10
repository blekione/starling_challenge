package com.blekione.engine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TimeUtils {

    public static LocalDate getStartOfTheWeek(LocalDateTime day) {
        int firstFeedDay = day.getDayOfWeek().getValue();
        LocalDate startOfTheWeek = day.toLocalDate().minusDays(firstFeedDay - 1);
        return startOfTheWeek;

    }

    public static LocalDate getStartOfTheFollowingWeek(LocalDateTime date) {
        return getStartOfTheWeek(date).plusDays(7);
    }

    public static List<Week> getWeeks(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalArgumentException("End date [" + endDate + "] cannot be earlier than start date [" + startDate + "].");
        }
        LocalDate firstWeekStart = getStartOfTheWeek(startDate);
        LocalDate lastWeekEnd = getStartOfTheFollowingWeek(endDate).minusDays(1);

        LocalDate weekStart = firstWeekStart;
        LocalDate weekEnd = firstWeekStart.plusDays(6);

        List<Week> weeks = new ArrayList<>();
        while(weekEnd.compareTo(lastWeekEnd) <= 0) {
            weeks.add(Week.between(weekStart, weekEnd));
            weekStart = weekStart.plusDays(7);
            weekEnd = weekEnd.plusDays(7);
        }
        return weeks;
    }

}
