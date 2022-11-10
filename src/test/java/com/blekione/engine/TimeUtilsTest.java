package com.blekione.engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TimeUtilsTest {

    @ParameterizedTest
    @CsvSource({ "2022-10-11T12:15:04.789, 2022-10-10", "2022-10-10T00:00:00.000, 2022-10-10",
            "2022-10-16T23:59:59.999, 2022-10-10" })
    void returnsFirstDayOfTheWeekForTheGivenDate(LocalDateTime date, LocalDate expectedWeekStartDate) throws Exception {
        // when
        LocalDate actualResult = TimeUtils.getStartOfTheWeek(date);

        // then
        assertThat(actualResult, equalTo(expectedWeekStartDate));
    }

    @ParameterizedTest
    @CsvSource({ "2022-10-11T12:15:04.789, 2022-10-17", "2022-10-10T00:00:00.000, 2022-10-17",
            "2022-10-16T23:59:59.999, 2022-10-17" })
    void returnsFirstDayOfTheFollowingWeekForTheGivenDate(LocalDateTime date, LocalDate expectedWeekStartDate)
            throws Exception {
        // when
        LocalDate actualResult = TimeUtils.getStartOfTheFollowingWeek(date);

        // then
        assertThat(actualResult, equalTo(expectedWeekStartDate));
    }

    @Test
    void returnsWeeksBetweenStartAndEndDate() throws Exception {
        // given
        LocalDateTime startDate = LocalDateTime.of(2022, 10, 11, 12, 15, 4);
        LocalDateTime endDate = LocalDateTime.of(2022, 10, 28, 19, 55, 4);

        // when
        List<Week> actualWeeks = TimeUtils.getWeeks(startDate, endDate);
        // then
        List<Week> expectedWeeks = List.of(Week.between(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 16)),
                Week.between(LocalDate.of(2022, 10, 17), LocalDate.of(2022, 10, 23)),
                Week.between(LocalDate.of(2022, 10, 24), LocalDate.of(2022, 10, 30)));
        assertThat(actualWeeks, equalTo(expectedWeeks));
    }

    @Test
    void returnsAWeekWhenStartAndEndDatesAreTheSame() throws Exception {
        // given
        LocalDateTime startDate = LocalDateTime.of(2022, 10, 11, 12, 15, 4);
        LocalDateTime endDate = LocalDateTime.of(2022, 10, 11, 12, 15, 4);

        // when
        List<Week> actualWeeks = TimeUtils.getWeeks(startDate, endDate);
        // then
        List<Week> expectedWeeks = List.of(Week.between(LocalDate.of(2022, 10, 10), LocalDate.of(2022, 10, 16)));
        assertThat(actualWeeks, equalTo(expectedWeeks));
    }

    @Test
    void throwsExceptionWhenEndDateIsBeforeStartDate() throws Exception {
        // given
        LocalDateTime startDate = LocalDateTime.of(2022, 10, 11, 12, 15, 4);
        LocalDateTime endDate = LocalDateTime.of(2022, 10, 10, 12, 15, 4);

        // then
        var expectedException = assertThrows(IllegalArgumentException.class,
                () -> TimeUtils.getWeeks(startDate, endDate));
        assertThat(expectedException.getMessage(),
                equalTo("End date [2022-10-10T12:15:04] cannot be earlier than start date [2022-10-11T12:15:04]."));
    }
}