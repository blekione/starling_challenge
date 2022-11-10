package com.blekione.engine;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.enterprise.context.ApplicationScoped;

import com.blekione.rest.client.model.FeedItem;
import com.blekione.rest.client.model.FeedItems;

@ApplicationScoped
public class RoundingGoalCalculationEngine {

    public BigInteger calcuateRoundValueForTransactionByWeek(FeedItems transactions) {

        if (transactions.getFeedItems().isEmpty()) {
            return BigInteger.ZERO;
        }

        Map<Week, List<FeedItem>> feedsByWeek = groupFeedsByWeeks(transactions);
        return feedsByWeek.entrySet().stream().map(entry -> sum(entry)).map(sum -> calculateRounding(sum)).reduce(BigInteger::add).get();
    }

    private BigInteger sum(Entry<Week, List<FeedItem>> feedItems) {
        return feedItems.getValue().stream().map(feed -> feed.getAmount().getMinorUnits()).reduce(BigInteger::add).get();
    }

    private BigInteger calculateRounding(BigInteger valueToRound) {
        BigInteger roundedUpToMajorUnit = BigDecimal.valueOf(valueToRound.longValue()).divide(BigDecimal.valueOf(100), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(100)).toBigInteger();
        BigInteger rounding = roundedUpToMajorUnit.subtract(valueToRound);
        return rounding;
    }

    private Map<Week, List<FeedItem>> groupFeedsByWeeks(FeedItems transactions) {
        FeedItem firstFeedItem = transactions.getFeedItems().stream()
                .min((feedA, feedB) -> feedA.getTransactionTime().compareTo(feedB.getTransactionTime())).get();
        FeedItem lastFeedItem = transactions.getFeedItems().stream()
                .max((feedA, feedB) -> feedA.getTransactionTime().compareTo(feedB.getTransactionTime())).get();

        List<Week> weeks = TimeUtils.getWeeks(firstFeedItem.getTransactionTime(), lastFeedItem.getTransactionTime());
        return weeks.stream()
                .flatMap(week -> transactions.getFeedItems().stream()
                        .filter(feed -> feed.getTransactionTime().toLocalDate().compareTo(week.getStartDate()) >= 0
                                && feed.getTransactionTime().toLocalDate().compareTo(week.getEndDate()) <= 0)
                        .map(feed -> new AbstractMap.SimpleEntry<>(week, feed)))
                .collect(groupingBy(entry -> entry.getKey(), mapping(Entry::getValue, toList())));
    }

}
