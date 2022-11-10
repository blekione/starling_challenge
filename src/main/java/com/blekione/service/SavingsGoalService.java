package com.blekione.service;

import java.math.BigInteger;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.blekione.engine.RoundingGoalCalculationEngine;
import com.blekione.response.SavingsGoalResponse;
import com.blekione.rest.client.StarlingAccountService;
import com.blekione.rest.client.StarlingFeedService;
import com.blekione.rest.client.model.Balance;
import com.blekione.rest.client.model.CurrencyAndAmount;
import com.blekione.rest.client.model.SavingsGoal;
import com.blekione.rest.client.model.SavingsGoalList;
import com.blekione.rest.client.model.SavingsGoalRequest;
import com.blekione.rest.client.model.TopUpRequest;

@ApplicationScoped
public class SavingsGoalService {
    private static final String ROUNDING_SAVINGS_GOAL_NAME = "Rounding Savings Goal";
    private StarlingAccountService accountService;
    private StarlingFeedService feedService;
    private RoundingGoalCalculationEngine calculationEngine;

    private Clock defaultClock;

    private DateTimeFormatter dateTimeFormatter;

    @Inject
    public SavingsGoalService(@RestClient StarlingAccountService accountService,
            @RestClient StarlingFeedService feedService, RoundingGoalCalculationEngine calculationEngine,
            Clock defaultClock, DateTimeFormatter dateTimeFormatter) {
        this.accountService = accountService;
        this.feedService = feedService;
        this.calculationEngine = calculationEngine;
        this.defaultClock = defaultClock;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    /**
     *
     * @param accountUid
     * @param categoryUid
     * @param fromDate
     * @return
     */
    public CompletionStage<SavingsGoalResponse> createGoal(String accountUid, String categoryUid, LocalDate fromDate) {
        CompletionStage<Balance> balanceCS = accountService.getBalance(accountUid);

        return accountService.getSavingsGoals(accountUid).thenCompose(response -> {
            if (response == null) {
                throw new IllegalArgumentException("response cannot be null");
            }
            Optional<SavingsGoal> existingRoundingGoal = findExistingRoundingGoal(response);
            if (existingRoundingGoal.isPresent()) {
                return new CompletableFuture<SavingsGoalResponse>().completeAsync(() -> new SavingsGoalResponse(false));
            }
            return createNewGoal(accountUid, categoryUid, fromDate, balanceCS);
        });
    }

    private Optional<SavingsGoal> findExistingRoundingGoal(SavingsGoalList response) {
        return response.getSavingsGoalList().stream()
                .filter(savingGoal -> ROUNDING_SAVINGS_GOAL_NAME.equals(savingGoal.getName())).findAny();
    }

    private CompletionStage<SavingsGoalResponse> createNewGoal(String accountUid, String categoryUid,
            LocalDate fromDate, CompletionStage<Balance> balanceCS) {
        return feedService
                .getByAccount(accountUid, categoryUid, fromDate.atStartOfDay().format(dateTimeFormatter),
                        LocalDateTime.now(defaultClock).format(dateTimeFormatter))
                .thenApply(calculationEngine::calcuateRoundValueForTransactionByWeek)
                .thenCompose(roundingValue -> createGoal(accountUid, roundingValue, balanceCS))
                .thenCompose(savingGoalAmount -> topUpSavingGoalWithAmount(accountUid, savingGoalAmount));
//        return new CompletableFuture<SavingsGoalResponse>().completeAsync(() -> new SavingsGoalResponse(true));
//                .thenApply(r -> new SavingsGoalResponse(false));
    }

    private CompletionStage<SavingsGoalAmount> createGoal(String accountUid, BigInteger roundingValue,
            CompletionStage<Balance> balanceCS) {
        return balanceCS.thenCompose(balance -> {
            if (roundingValue.compareTo(balance.getEffectiveBalance().getMinorUnits()) > 0) {
                return new CompletableFuture<SavingsGoalAmount>()
                        .completeAsync(() -> new SavingsGoalAmount(roundingValue, Optional.empty(), false));
            }
            return accountService
                    .createSavingsGoal(accountUid,
                            new SavingsGoalRequest(ROUNDING_SAVINGS_GOAL_NAME, "GBP",
                                    new CurrencyAndAmount("GBP", BigInteger.valueOf(200_00L))))
                    .thenApply(response -> new SavingsGoalAmount(roundingValue,
                            Optional.of(response.getSavingsGoalUid()), true));
        });
    }

    private CompletionStage<SavingsGoalResponse> topUpSavingGoalWithAmount(String accountUid,
            SavingsGoalAmount savingGoalAmount) {
        if (!savingGoalAmount.isGoalCreated()) {
            return new CompletableFuture<SavingsGoalResponse>().completeAsync(() -> new SavingsGoalResponse(false));
        }
        if (BigInteger.valueOf(0).equals(savingGoalAmount.getAmount())) {
            // this won't make a API call if value is 0, but still success
            return new CompletableFuture<SavingsGoalResponse>().completeAsync(() -> new SavingsGoalResponse(true,
                    Optional.of(BigInteger.ZERO), savingGoalAmount.getSavingGoalUid()));
        }
        return accountService
                .addMoneyToSavingGoal(accountUid, savingGoalAmount.getSavingGoalUid().get(),
                        UUID.randomUUID().toString(),
                        new TopUpRequest(new CurrencyAndAmount("GBP", savingGoalAmount.getAmount())))
                .thenApply(addMoneyResponse -> {
                    return new SavingsGoalResponse(true, Optional.of(savingGoalAmount.getAmount()), savingGoalAmount.getSavingGoalUid());
                });
    }

    private static class SavingsGoalAmount {
        BigInteger amount;
        Optional<String> savingGoalUid;
        boolean goalCreated;

        public SavingsGoalAmount(BigInteger amount, Optional<String> savingGoalUid, boolean goalCreated) {
            this.amount = amount;
            this.savingGoalUid = savingGoalUid;
            this.goalCreated = goalCreated;
        }

        public BigInteger getAmount() {
            return amount;
        }

        public Optional<String> getSavingGoalUid() {
            return savingGoalUid;
        }

        public boolean isGoalCreated() {
            return goalCreated;
        }
    }

}
