package com.blekione.engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.blekione.testutils.A;

public class RoundingGoalCalculationEngineTest {

    @Test
    void calculatesRoundingValueFromTransactionsInEachWeek() throws Exception {
        // given
        var feedItems = A.feedItems.w(A.feedItem.w(LocalDateTime.of(2022, 10, 11, 12, 30)).w(1_54).build(),
                A.feedItem.w(LocalDateTime.of(2022, 10, 12, 11, 31)).w(9_17).build(),
                A.feedItem.w(LocalDateTime.of(2022, 10, 12, 20, 01)).w(18_24).build(),
                A.feedItem.w(LocalDateTime.of(2022, 10, 16, 14, 01)).w(7_36).build(),
                A.feedItem.w(LocalDateTime.of(2022, 10, 17, 8, 50)).w(31_09).build(),
                A.feedItem.w(LocalDateTime.of(2022, 10, 23, 23, 59)).w(11_20).build(),
                A.feedItem.w(LocalDateTime.of(2022, 10, 24, 00, 00)).w(3_17).build()).build();

        // when
        var actualValue = new RoundingGoalCalculationEngine().calcuateRoundValueForTransactionByWeek(feedItems);

        // then
        var expectedValue = BigInteger.valueOf(223);
        assertThat(actualValue, equalTo(expectedValue));
    }

    @Test
    void returnZeroWhenEmptyFeedList() throws Exception {
        // given
        var feedItems = A.feedItems.build();

        // when
        var actualValue = new RoundingGoalCalculationEngine().calcuateRoundValueForTransactionByWeek(feedItems);

        // then
        var expectedValue = BigInteger.ZERO;
        assertThat(actualValue, equalTo(expectedValue));
    }
}
