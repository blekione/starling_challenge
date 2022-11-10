package com.blekione.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.blekione.engine.RoundingGoalCalculationEngine;
import com.blekione.response.SavingsGoalResponse;
import com.blekione.rest.client.StarlingAccountService;
import com.blekione.rest.client.StarlingFeedService;
import com.blekione.rest.client.model.Balance;
import com.blekione.rest.client.model.CreateOrUpdateSavingsGoalResponse;
import com.blekione.rest.client.model.FeedItems;
import com.blekione.rest.client.model.SavingsGoalList;
import com.blekione.rest.client.model.SavingsGoalRequest;
import com.blekione.rest.client.model.SavingsGoalTransferResponse;
import com.blekione.rest.client.model.TopUpRequest;
import com.blekione.testutils.A;
import com.blekione.testutils.TestUtil;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

@QuarkusTest
public class SavingsGoalServiceTest {

    @InjectMock
    @RestClient
    StarlingFeedService feedService;

    @InjectMock
    @RestClient
    StarlingAccountService accountService;

    @InjectMock
    RoundingGoalCalculationEngine calculationEngine;

    @Inject
    DateTimeFormatter dateTimeFormatter;

    private static Clock clock = TestUtil.getFixedClock();

    private LocalDateTime now = LocalDateTime.now(clock);

    @Test
    void addsEmptySavingGoalToAccountWhenNoTransactionsFromSubscriptionDate() throws Exception {
        // given
        String accountUid = "111-222-333";
        String categoryUid = "aaa-bbb-ccc";
        LocalDateTime fromDate = LocalDateTime.of(2022, 11, 8, 0, 0, 0);
        SavingsGoalRequest savingGoal = A.savingsGoalRequest.w("Rounding Savings Goal").build();

        var createSavingsGoalResponse = new CompletableFuture<CreateOrUpdateSavingsGoalResponse>()
                .completeAsync(() -> A.createOrUpdateSavingsGoalResponse.build());
        when(accountService.createSavingsGoal(accountUid, savingGoal)).thenReturn(createSavingsGoalResponse);

        var existingSavingsAccountGoals = new CompletableFuture<SavingsGoalList>()
                .completeAsync(() -> A.savingsGoalList.build());
        when(accountService.getSavingsGoals(accountUid)).thenReturn(existingSavingsAccountGoals);

        var balance = new CompletableFuture<Balance>().completeAsync(() -> A.balance.wEffective(100).build());
        when(accountService.getBalance(accountUid)).thenReturn(balance);

        FeedItems transactions = A.feedItems.build();
        CompletionStage<FeedItems> transactionsCS = new CompletableFuture<FeedItems>()
                .completeAsync(() -> transactions);

        when(calculationEngine.calcuateRoundValueForTransactionByWeek(transactions)).thenReturn(BigInteger.valueOf(0));

        when(feedService.getByAccount(accountUid, categoryUid, fromDate.format(dateTimeFormatter),
                now.format(dateTimeFormatter))).thenReturn(transactionsCS);

        // when
        SavingsGoalResponse actualResponse = new SavingsGoalService(accountService, feedService, calculationEngine,
                clock, dateTimeFormatter).createGoal(accountUid, categoryUid, fromDate.toLocalDate())
                        .toCompletableFuture().completeOnTimeout(null, 2, TimeUnit.SECONDS).get();

        // then
        verify(feedService, times(1)).getByAccount(accountUid, categoryUid, fromDate.format(dateTimeFormatter),
                now.format(dateTimeFormatter));
        SavingsGoalResponse expectedResponse = new SavingsGoalResponse(true, Optional.of(BigInteger.ZERO),
                Optional.of("b76311af-a8ee-4a7c-9e34-42da8c2e7bcb"));
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    void addsSavingGoalToAccountWithRoundedTransactionsFromSubscriptionDate() throws Exception {
        // given
        String accountUid = "111-222-333";
        String categoryUid = "aaa-bbb-ccc";
        LocalDateTime fromDate = LocalDateTime.of(2022, 11, 1, 0, 0, 0);
        SavingsGoalRequest savingGoal = A.savingsGoalRequest.w("Rounding Savings Goal").build();

        var createSavingsGoalResponse = new CompletableFuture<CreateOrUpdateSavingsGoalResponse>()
                .completeAsync(() -> A.createOrUpdateSavingsGoalResponse.build());
        when(accountService.createSavingsGoal(accountUid, savingGoal)).thenReturn(createSavingsGoalResponse);

        var existingSavingsAccountGoals = new CompletableFuture<SavingsGoalList>()
                .completeAsync(() -> A.savingsGoalList.build());
        when(accountService.getSavingsGoals(accountUid)).thenReturn(existingSavingsAccountGoals);

        var transferResponse = new CompletableFuture<SavingsGoalTransferResponse>()
                .completeAsync(() -> A.savingsGoalTransferResponse.build());
        when(accountService.addMoneyToSavingGoal(anyString(), anyString(), anyString(), any(TopUpRequest.class)))
                .thenReturn(transferResponse);

        var balance = new CompletableFuture<Balance>().completeAsync(() -> A.balance.wEffective(100).build());
        when(accountService.getBalance(accountUid)).thenReturn(balance);

        FeedItems feedItems = A.feedItems.w(A.feedItem.w("a1").w(12_83).w(LocalDateTime.of(2022, 11, 1, 10, 0)).build(),
                A.feedItem.w("a2").w(5_34).w(LocalDateTime.of(2022, 11, 4, 10, 0)).build(),
                A.feedItem.w("a3").w(18_16).w(LocalDateTime.of(2022, 11, 8, 10, 0)).build()).build();
        CompletionStage<FeedItems> transactions = new CompletableFuture<FeedItems>().completeAsync(() -> feedItems);
        when(feedService.getByAccount(accountUid, categoryUid, fromDate.format(dateTimeFormatter),
                now.format(dateTimeFormatter))).thenReturn(transactions);

        when(calculationEngine.calcuateRoundValueForTransactionByWeek(any(FeedItems.class)))
                .thenReturn(BigInteger.valueOf(100));

        // when
        SavingsGoalResponse actualResponse = new SavingsGoalService(accountService, feedService, calculationEngine,
                clock, dateTimeFormatter).createGoal(accountUid, categoryUid, fromDate.toLocalDate())
                        .toCompletableFuture().completeOnTimeout(null, 2, TimeUnit.SECONDS).get();

        // then
        verify(feedService, times(1)).getByAccount(accountUid, categoryUid, fromDate.format(dateTimeFormatter),
                now.format(dateTimeFormatter));
        SavingsGoalResponse expectedResponse = new SavingsGoalResponse(true, Optional.of(BigInteger.valueOf(100)),
                Optional.of("b76311af-a8ee-4a7c-9e34-42da8c2e7bcb"));
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    void returnsFailIfTheSavingGoalAlreadyExistsWhenAdding() throws Exception {
        // given
        String accountUid = "111-222-333";
        String categoryUid = "aaa-bbb-ccc";
        String savingsGoalName = "Rounding Savings Goal";
        SavingsGoalRequest savingGoal = A.savingsGoalRequest.w(savingsGoalName).build();
        LocalDateTime fromDate = LocalDateTime.of(2022, 11, 8, 0, 0, 0);

        var createSavingsGoalResponse = new CompletableFuture<CreateOrUpdateSavingsGoalResponse>()
                .completeAsync(() -> A.createOrUpdateSavingsGoalResponse.build());
        when(accountService.createSavingsGoal(accountUid, savingGoal)).thenReturn(createSavingsGoalResponse);

        var existingSavingsAccountGoals = new CompletableFuture<SavingsGoalList>()
                .completeAsync(() -> new A.SavingsGoalListBuilder()
                        .w(new A.SavingsGoalBuilder().w("999-888-777", savingsGoalName).build()).build());

        when(accountService.getSavingsGoals(accountUid)).thenReturn(existingSavingsAccountGoals);

        when(calculationEngine.calcuateRoundValueForTransactionByWeek(any(FeedItems.class)))
                .thenReturn(BigInteger.valueOf(100));

        // when
        SavingsGoalResponse actualResponse = new SavingsGoalService(accountService, feedService, calculationEngine,
                clock, dateTimeFormatter).createGoal(accountUid, categoryUid, fromDate.toLocalDate())
                        .toCompletableFuture().completeOnTimeout(null, 2, TimeUnit.SECONDS).get();

        // then
        SavingsGoalResponse expectedResponse = new SavingsGoalResponse(false);
        assertThat(actualResponse, equalTo(expectedResponse));
    }

    @Test
    void doesntCreateTheGoalWhenAccountBalanceIsLessThanTransactionsRoundingValue() throws Exception {
        // given
        String accountUid = "111-222-333";
        String categoryUid = "aaa-bbb-ccc";
        LocalDateTime fromDate = LocalDateTime.of(2022, 11, 1, 0, 0, 0);
        SavingsGoalRequest savingGoal = A.savingsGoalRequest.w("Rounding Savings Goal").build();

        var createSavingsGoalResponse = new CompletableFuture<CreateOrUpdateSavingsGoalResponse>()
                .completeAsync(() -> A.createOrUpdateSavingsGoalResponse.build());
        when(accountService.createSavingsGoal(accountUid, savingGoal)).thenReturn(createSavingsGoalResponse);

        var existingSavingsAccountGoals = new CompletableFuture<SavingsGoalList>()
                .completeAsync(() -> A.savingsGoalList.build());
        when(accountService.getSavingsGoals(accountUid)).thenReturn(existingSavingsAccountGoals);

        FeedItems feedItems = A.feedItems.w(A.feedItem.w("a1").w(12_83).w(LocalDateTime.of(2022, 11, 1, 10, 0)).build(),
                A.feedItem.w("a2").w(5_34).w(LocalDateTime.of(2022, 11, 4, 10, 0)).build(),
                A.feedItem.w("a3").w(18_16).w(LocalDateTime.of(2022, 11, 8, 10, 0)).build()).build();
        CompletionStage<FeedItems> transactions = new CompletableFuture<FeedItems>().completeAsync(() -> feedItems);
        when(feedService.getByAccount(accountUid, categoryUid, fromDate.format(dateTimeFormatter),
                now.format(dateTimeFormatter))).thenReturn(transactions);
        var balance = new CompletableFuture<Balance>().completeAsync(() -> A.balance.wEffective(90).build());
        when(accountService.getBalance(accountUid)).thenReturn(balance);

        when(calculationEngine.calcuateRoundValueForTransactionByWeek(any(FeedItems.class)))
                .thenReturn(BigInteger.valueOf(100));

        // when
        SavingsGoalResponse actualResponse = new SavingsGoalService(accountService, feedService, calculationEngine,
                clock, dateTimeFormatter).createGoal(accountUid, categoryUid, fromDate.toLocalDate())
                        .toCompletableFuture().completeOnTimeout(null, 2, TimeUnit.SECONDS).get();

        // then
        SavingsGoalResponse expectedResponse = new SavingsGoalResponse(false);
        assertThat(actualResponse, equalTo(expectedResponse));
    }

}
