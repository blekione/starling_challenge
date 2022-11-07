package com.blekione.rest.client.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class FeedItem {
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

    public FeedItem(String feedItemUid, String categoryUid, CurrencyAndAmount amount, CurrencyAndAmount sourceAmount,
            String direction, LocalDateTime updatedAt, LocalDateTime transactionTime, LocalDateTime settlementTime,
            String source, String status, String transactingApplicationUserUid, String counterPartyType,
            String counterPartyUid, String counterPartyName, String counterPartySubEntityUid,
            String counterPartySubEntityName, String counterPartySubEntityIdentifier,
            String counterPartySubEntitySubIdentifier, String reference, String country, String spendingCategory,
            Boolean hasAttachment, Boolean hasReceipt, Boolean batchPaymentDetails) {
        super();
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

    public String getFeedItemUid() {
        return feedItemUid;
    }

    public String getCategoryUid() {
        return categoryUid;
    }

    public CurrencyAndAmount getAmount() {
        return amount;
    }

    public CurrencyAndAmount getSourceAmount() {
        return sourceAmount;
    }

    public String getDirection() {
        return direction;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public String getSource() {
        return source;
    }

    public String getStatus() {
        return status;
    }

    public String getTransactingApplicationUserUid() {
        return transactingApplicationUserUid;
    }

    public String getCounterPartyType() {
        return counterPartyType;
    }

    public String getCounterPartyUid() {
        return counterPartyUid;
    }

    public String getCounterPartyName() {
        return counterPartyName;
    }

    public String getCounterPartySubEntityUid() {
        return counterPartySubEntityUid;
    }

    public String getCounterPartySubEntityName() {
        return counterPartySubEntityName;
    }

    public String getCounterPartySubEntityIdentifier() {
        return counterPartySubEntityIdentifier;
    }

    public String getCounterPartySubEntitySubIdentifier() {
        return counterPartySubEntitySubIdentifier;
    }

    public String getReference() {
        return reference;
    }

    public String getCountry() {
        return country;
    }

    public String getSpendingCategory() {
        return spendingCategory;
    }

    public Boolean getHasAttachment() {
        return hasAttachment;
    }

    public Boolean getHasReceipt() {
        return hasReceipt;
    }

    public Boolean getBatchPaymentDetails() {
        return batchPaymentDetails;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, batchPaymentDetails, categoryUid, counterPartyName, counterPartySubEntityIdentifier,
                counterPartySubEntityName, counterPartySubEntitySubIdentifier, counterPartySubEntityUid,
                counterPartyType, counterPartyUid, country, direction, feedItemUid, hasAttachment, hasReceipt,
                reference, settlementTime, source, sourceAmount, spendingCategory, status,
                transactingApplicationUserUid, transactionTime, updatedAt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FeedItem other = (FeedItem) obj;
        return Objects.equals(amount, other.amount) && Objects.equals(batchPaymentDetails, other.batchPaymentDetails)
                && Objects.equals(categoryUid, other.categoryUid)
                && Objects.equals(counterPartyName, other.counterPartyName)
                && Objects.equals(counterPartySubEntityIdentifier, other.counterPartySubEntityIdentifier)
                && Objects.equals(counterPartySubEntityName, other.counterPartySubEntityName)
                && Objects.equals(counterPartySubEntitySubIdentifier, other.counterPartySubEntitySubIdentifier)
                && Objects.equals(counterPartySubEntityUid, other.counterPartySubEntityUid)
                && Objects.equals(counterPartyType, other.counterPartyType)
                && Objects.equals(counterPartyUid, other.counterPartyUid) && Objects.equals(country, other.country)
                && Objects.equals(direction, other.direction) && Objects.equals(feedItemUid, other.feedItemUid)
                && Objects.equals(hasAttachment, other.hasAttachment) && Objects.equals(hasReceipt, other.hasReceipt)
                && Objects.equals(reference, other.reference) && Objects.equals(settlementTime, other.settlementTime)
                && Objects.equals(source, other.source) && Objects.equals(sourceAmount, other.sourceAmount)
                && Objects.equals(spendingCategory, other.spendingCategory) && Objects.equals(status, other.status)
                && Objects.equals(transactingApplicationUserUid, other.transactingApplicationUserUid)
                && Objects.equals(transactionTime, other.transactionTime) && Objects.equals(updatedAt, other.updatedAt);
    }

    @Override
    public String toString() {
        return "FeedItem [feedItemUid=" + feedItemUid + ", categoryUid=" + categoryUid + ", amount=" + amount
                + ", sourceAmount=" + sourceAmount + ", direction=" + direction + ", updatedAt=" + updatedAt
                + ", transactionTime=" + transactionTime + ", settlementTime=" + settlementTime + ", source=" + source
                + ", status=" + status + ", transactingApplicationUserUid=" + transactingApplicationUserUid
                + ", counterPartyType=" + counterPartyType + ", counterPartyUid=" + counterPartyUid
                + ", counterPartyName=" + counterPartyName + ", counterPartySubEntityUid=" + counterPartySubEntityUid
                + ", counterPartySubEntityName=" + counterPartySubEntityName + ", counterPartySubEntityIdentifier="
                + counterPartySubEntityIdentifier + ", counterPartySubEntitySubIdentifier="
                + counterPartySubEntitySubIdentifier + ", reference=" + reference + ", country=" + country
                + ", spendingCategory=" + spendingCategory + ", hasAttachment=" + hasAttachment + ", hasReceipt="
                + hasReceipt + ", batchPaymentDetails=" + batchPaymentDetails + "]";
    }

}
