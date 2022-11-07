package com.blekione.testutils;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.blekione.rest.client.model.CurrencyAndAmount;
import com.blekione.rest.client.model.FeedItem;
import com.blekione.rest.client.model.FeedItems;
import com.blekione.rest.client.model.SavingsGoal;

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

    public static SavingGoalBuilder savingGoal = new SavingGoalBuilder();

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

    public static class SavingGoalBuilder {
        String savingGoalUid;
        String name;
        CurrencyAndAmount target;
        CurrencyAndAmount totalSaved;
        int savedPercentage;

        SavingGoalBuilder() {
            this("5b9fab8e-cbbc-4fd6-a3bb-8728ebf4ba1d", "For a car",
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(10_000_00L)),
                    new CurrencyAndAmount("GBP", BigInteger.valueOf(200_00L)), 2);
        }

        SavingGoalBuilder(String savingGoalUid, String name, CurrencyAndAmount target, CurrencyAndAmount totalSaved,
                int savedPercentage) {
            this.savingGoalUid = savingGoalUid;
            this.name = name;
            this.target = target;
            this.totalSaved = totalSaved;
            this.savedPercentage = savedPercentage;
        }

        public SavingGoalBuilder w(String savingGoalUid, String name) {
            this.savingGoalUid = savingGoalUid;
            this.name = name;
            return this;
        }

        public SavingGoalBuilder wTarget(String currency, BigInteger minorUnits) {
            this.target = new CurrencyAndAmount(currency, minorUnits);
            return this;
        }

        public SavingGoalBuilder wTotalSaved(String currency, BigInteger minorUnits) {
            this.totalSaved = new CurrencyAndAmount(currency, minorUnits);
            return this;
        }

        public SavingsGoal build() {
            return new SavingsGoal(this.savingGoalUid, this.name, this.target, this.totalSaved, this.savedPercentage);
        }
    }
}
