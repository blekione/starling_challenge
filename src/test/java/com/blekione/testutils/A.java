package com.blekione.testutils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.blekione.rest.client.model.Balance;
import com.blekione.rest.client.model.CreateOrUpdateSavingsGoalResponse;
import com.blekione.rest.client.model.CurrencyAndAmount;
import com.blekione.rest.client.model.ErrorDetail;
import com.blekione.rest.client.model.FeedItem;
import com.blekione.rest.client.model.FeedItems;
import com.blekione.rest.client.model.SavingsGoal;
import com.blekione.rest.client.model.SavingsGoalList;
import com.blekione.rest.client.model.SavingsGoalRequest;
import com.blekione.rest.client.model.SavingsGoalTransferResponse;
import com.blekione.rest.client.model.TopUpRequest;

/**
 * Test items builders.
 *
 * @author mkrg
 *
 */
public class A {

    public static FeedItemsBuilder feedItems = new FeedItemsBuilder();

    public static FeedItemBuilder feedItem = new FeedItemBuilder();

    public static CurrencyAndAmountBuilder currencyAndAmount = new CurrencyAndAmountBuilder();

    public static SavingsGoalListBuilder savingsGoalList = new SavingsGoalListBuilder();

    public static SavingsGoalBuilder savingsGoal = new SavingsGoalBuilder();

    public static SavingsGoalRequestBuilder savingsGoalRequest = new SavingsGoalRequestBuilder();

    public static CreateOrUpdateSavingsGoalResponseBuilder createOrUpdateSavingsGoalResponse = new CreateOrUpdateSavingsGoalResponseBuilder();

    public static TopUpRequestBuilder topUpRequest = new TopUpRequestBuilder();

    public static SavingsGoalTransferResponseBuilder savingsGoalTransferResponse = new SavingsGoalTransferResponseBuilder();

    public static BalanceBuilder balance = new BalanceBuilder();

    public static class FeedItemsBuilder {
        Set<FeedItem> feedItems = new HashSet<>();

        FeedItemsBuilder() {
        }

        public FeedItemsBuilder w(FeedItem... feedItems) {
            this.feedItems = new HashSet<FeedItem>(Arrays.asList(feedItems));
            return this;
        }

        public FeedItems build() {
            return new FeedItems(feedItems);
        }
    }

    public static class FeedItemBuilder {
        String feedItemUid;
        String categoryUid;
        CurrencyAndAmount amount;
        CurrencyAndAmount sourceAmount;
        String direction;
        LocalDateTime updatedAt;
        LocalDateTime transactionTime;
        LocalDateTime settlementTime;
        String source;
        String status;
        String transactingApplicationUserUid;
        String counterPartyType;
        String counterPartyUid;
        String counterPartyName;
        String counterPartySubEntityUid;
        String counterPartySubEntityName;
        String counterPartySubEntityIdentifier;
        String counterPartySubEntitySubIdentifier;
        String reference;
        String country;
        String spendingCategory;
        Boolean hasAttachment;
        Boolean hasReceipt;
        Boolean batchPaymentDetails;

        FeedItemBuilder() {
            this("112326e1-b29c-4658-8128-6f4a3b8526e9", "c254cc12-eb42-4838-bf2f-ba3637edd448",
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(5266L)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(5266L)), "OUT",
                    LocalDateTime.of(2022, 11, 3, 18, 11, 18, 662000000),
                    LocalDateTime.of(2022, 11, 3, 18, 11, 17, 770000000),
                    LocalDateTime.of(2022, 11, 3, 18, 11, 18, 567000000), "FASTER_PAYMENTS_OUT", "SETTLED",
                    "66d452ca-04b3-49d5-8ddf-d82560f7e55c", "PAYEE", "56930d7d-fac6-4d20-98db-5238e79b267c",
                    "Mickey Mouse", "41edac0a-f0d1-44ee-88f9-5e9fbc1a8687", "UK account", "204514", "00000825",
                    "Sim 131356903", "GB", "SHOPPING", false, false, null);
        }

        FeedItemBuilder(String feedItemUid, String categoryUid, CurrencyAndAmount amount,
                CurrencyAndAmount sourceAmount, String direction, LocalDateTime updatedAt,
                LocalDateTime transactionTime, LocalDateTime settlementTime, String source, String status,
                String transactingApplicationUserUid, String counterPartyType, String counterPartyUid,
                String counterPartyName, String counterPartySubEntityUid, String counterPartySubEntityName,
                String counterPartySubEntityIdentifier, String counterPartySubEntitySubIdentifier, String reference,
                String country, String spendingCategory, Boolean hasAttachment, Boolean hasReceipt,
                Boolean batchPaymentDetails) {
            this.feedItemUid = feedItemUid;
            this.categoryUid = categoryUid;
            this.amount = amount;
            this.sourceAmount = sourceAmount;
            this.direction = direction;
            this.updatedAt = updatedAt;
            this.transactionTime = transactionTime;
            this.settlementTime = settlementTime;
            this.source = source;
            this.status = status;
            this.transactingApplicationUserUid = transactingApplicationUserUid;
            this.counterPartyType = counterPartyType;
            this.counterPartyUid = counterPartyUid;
            this.counterPartyName = counterPartyName;
            this.counterPartySubEntityUid = counterPartySubEntityUid;
            this.counterPartySubEntityName = counterPartySubEntityName;
            this.counterPartySubEntityIdentifier = counterPartySubEntityIdentifier;
            this.counterPartySubEntitySubIdentifier = counterPartySubEntitySubIdentifier;
            this.reference = reference;
            this.country = country;
            this.spendingCategory = spendingCategory;
            this.hasAttachment = hasAttachment;
            this.hasReceipt = hasReceipt;
            this.batchPaymentDetails = batchPaymentDetails;
        }

        public FeedItemBuilder w(String feedItemUid) {
            this.feedItemUid = feedItemUid;
            return this;
        }

        public FeedItemBuilder w(long minorUnits) {
            return w("GBP", BigInteger.valueOf(minorUnits));
        }

        public FeedItemBuilder w(LocalDateTime trasactionTime) {
            this.transactionTime = trasactionTime;
            return this;
        }

        public FeedItemBuilder w(String currency, BigInteger minorUnits) {
            this.amount = new CurrencyAndAmount(currency, minorUnits);
            this.sourceAmount = new CurrencyAndAmount(currency, minorUnits);
            return this;
        }

        public FeedItem build() {
            return new FeedItem(this.feedItemUid, this.categoryUid, this.amount, this.sourceAmount, this.direction,
                    this.updatedAt, this.transactionTime, this.settlementTime, this.source, this.status,
                    this.transactingApplicationUserUid, this.counterPartyType, this.counterPartyUid,
                    this.counterPartyName, this.counterPartySubEntityUid, this.counterPartySubEntityName,
                    this.counterPartySubEntityIdentifier, this.counterPartySubEntitySubIdentifier, this.reference,
                    this.country, this.spendingCategory, this.hasAttachment, this.hasReceipt, this.batchPaymentDetails);
        }
    }

    public static class CurrencyAndAmountBuilder {

    }

    public static class SavingsGoalBuilder {
        String savingGoalUid;
        String name;
        CurrencyAndAmount target;
        CurrencyAndAmount totalSaved;
        int savedPercentage;

        public SavingsGoalBuilder() {
            this("5b9fab8e-cbbc-4fd6-a3bb-8728ebf4ba1d", "For a car",
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(10_000_00L)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(200_00L)), 2);
        }

        SavingsGoalBuilder(String savingGoalUid, String name, CurrencyAndAmount target, CurrencyAndAmount totalSaved,
                int savedPercentage) {
            this.savingGoalUid = savingGoalUid;
            this.name = name;
            this.target = target;
            this.totalSaved = totalSaved;
            this.savedPercentage = savedPercentage;
        }

        public SavingsGoalBuilder w(String savingGoalUid, String name) {
            this.savingGoalUid = savingGoalUid;
            this.name = name;
            return this;
        }

        public SavingsGoalBuilder wTarget(String currency, BigInteger minorUnits) {
            this.target = new CurrencyAndAmount(currency, minorUnits);
            return this;
        }

        public SavingsGoalBuilder wTotalSaved(String currency, BigInteger minorUnits) {
            this.totalSaved = new CurrencyAndAmount(currency, minorUnits);
            return this;
        }

        public SavingsGoal build() {
            return new SavingsGoal(this.savingGoalUid, this.name, this.target, this.totalSaved, this.savedPercentage);
        }
    }

    public static class SavingsGoalListBuilder {
        private List<SavingsGoal> savingsGoalList = new ArrayList<SavingsGoal>();

        public SavingsGoalListBuilder() {
            this(new SavingsGoalBuilder().build());
        }

        SavingsGoalListBuilder(SavingsGoal... savingsGoals) {
            this(new ArrayList<SavingsGoal>(Arrays.asList(savingsGoals)));
        }

        SavingsGoalListBuilder(List<SavingsGoal> savingsGoalsList) {
            this.savingsGoalList = savingsGoalsList;
        }

        public SavingsGoalListBuilder w(SavingsGoal savingGoal) {
            savingsGoalList.add(savingGoal);
            return this;
        }

        public SavingsGoalList build() {
            return new SavingsGoalList(this.savingsGoalList);
        }

    }

    public static class SavingsGoalRequestBuilder {
        private String name;
        private String currency;
        private CurrencyAndAmount target;

        SavingsGoalRequestBuilder() {
            this("test goal", "GBP", new CurrencyAndAmount("GBP", BigInteger.valueOf(200_00)));
        }

        SavingsGoalRequestBuilder(String name, String currency, CurrencyAndAmount target) {
            this.name = name;
            this.currency = currency;
            this.target = target;
        }

        public SavingsGoalRequestBuilder w(String name) {
            this.name = name;
            return this;
        }

        public SavingsGoalRequest build() {
            return new SavingsGoalRequest(this.name, this.currency, this.target);
        }
    }

    public static class CreateOrUpdateSavingsGoalResponseBuilder {
        private String savingsGoalUid;
        private boolean success;
        private List<ErrorDetail> errors = new ArrayList<>();

        CreateOrUpdateSavingsGoalResponseBuilder() {
            this("b76311af-a8ee-4a7c-9e34-42da8c2e7bcb", true, Collections.emptyList());
        }

        CreateOrUpdateSavingsGoalResponseBuilder(String savingsGoalUid, boolean success, List<ErrorDetail> errors) {
            this.savingsGoalUid = savingsGoalUid;
            this.success = success;
            this.errors = errors;
        }

        public CreateOrUpdateSavingsGoalResponse build() {
            return new CreateOrUpdateSavingsGoalResponse(this.savingsGoalUid, this.success, this.errors);
        }
    }

    public static class TopUpRequestBuilder {
        private CurrencyAndAmount amount;

        TopUpRequestBuilder() {
            this(new CurrencyAndAmount("GBP", BigInteger.valueOf(10)));
        }

        TopUpRequestBuilder(CurrencyAndAmount amount) {
            this.amount = amount;
        }

        public TopUpRequestBuilder w(long amount) {
            this.amount = new CurrencyAndAmount("GBP", BigInteger.valueOf(amount));
            return this;
        }

        public TopUpRequest build() {
            return new TopUpRequest(amount);
        }
    }

    public static class SavingsGoalTransferResponseBuilder {
        private String transferUid;
        private boolean success;
        private List<ErrorDetail> errors;

        SavingsGoalTransferResponseBuilder() {
            this("aaa-bbb-111", true, new ArrayList<ErrorDetail>());
        }

        SavingsGoalTransferResponseBuilder(String transferUid, boolean success, ArrayList<ErrorDetail> errors) {
            this.transferUid = transferUid;
            this.success = success;
            this.errors = errors;
        }

        public SavingsGoalTransferResponse build() {
            return new SavingsGoalTransferResponse(this.transferUid, this.success, this.errors);
        }
    }

    public static class BalanceBuilder {
        private CurrencyAndAmount clearedBalance;
        private CurrencyAndAmount effectiveBalance;
        private CurrencyAndAmount pendingTransactions;
        private CurrencyAndAmount acceptedOverdraft;
        private CurrencyAndAmount amount;
        private CurrencyAndAmount totalClearedBalance;
        private CurrencyAndAmount totoalEffectiveBalance;

        BalanceBuilder() {
            this(new CurrencyAndAmount("GBP", BigInteger.valueOf(254277)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(254277)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(0)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(0)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(254277)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(254287)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(254287)));
        }

        BalanceBuilder(CurrencyAndAmount clearedBalance, CurrencyAndAmount effectiveBalance,
                CurrencyAndAmount pendingTransactions, CurrencyAndAmount acceptedOverdraft, CurrencyAndAmount amount,
                CurrencyAndAmount totalClearedBalance, CurrencyAndAmount totoalEffectiveBalance) {
            this.clearedBalance = clearedBalance;
            this.effectiveBalance = effectiveBalance;
            this.pendingTransactions = pendingTransactions;
            this.acceptedOverdraft = acceptedOverdraft;
            this.amount = amount;
            this.totalClearedBalance = totalClearedBalance;
            this.totoalEffectiveBalance = totoalEffectiveBalance;
        }

        public BalanceBuilder wEffective(long effectiveBalance) {
            this.effectiveBalance = new CurrencyAndAmount("GBP", BigInteger.valueOf(effectiveBalance));
            return this;
        }

        public Balance build() {
            return new Balance(this.clearedBalance, this.effectiveBalance, this.pendingTransactions,
                    this.acceptedOverdraft, this.amount, this.totalClearedBalance, this.totoalEffectiveBalance);
        }
    }
}
