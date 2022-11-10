package com.blekione.rest.client.model;

import java.util.Objects;

public class Balance {
    CurrencyAndAmount clearedBalance;
    CurrencyAndAmount effectiveBalance;
    CurrencyAndAmount pendingTransactions;
    CurrencyAndAmount acceptedOverdraft;
    CurrencyAndAmount amount;
    CurrencyAndAmount totalClearedBalance;
    CurrencyAndAmount totalEffectiveBalance;

    // required by the JSON processor
    public Balance() {
    }

    public Balance(CurrencyAndAmount clearedBalance, CurrencyAndAmount effectiveBalance,
            CurrencyAndAmount pendingTransactions, CurrencyAndAmount acceptedOverdraft, CurrencyAndAmount amount,
            CurrencyAndAmount totalClearedBalance, CurrencyAndAmount totalEffectiveBalance) {
        super();
        this.clearedBalance = clearedBalance;
        this.effectiveBalance = effectiveBalance;
        this.pendingTransactions = pendingTransactions;
        this.acceptedOverdraft = acceptedOverdraft;
        this.amount = amount;
        this.totalClearedBalance = totalClearedBalance;
        this.totalEffectiveBalance = totalEffectiveBalance;
    }

    public CurrencyAndAmount getClearedBalance() {
        return clearedBalance;
    }

    public CurrencyAndAmount getEffectiveBalance() {
        return effectiveBalance;
    }

    public CurrencyAndAmount getPendingTransactions() {
        return pendingTransactions;
    }

    public CurrencyAndAmount getAcceptedOverdraft() {
        return acceptedOverdraft;
    }

    public CurrencyAndAmount getAmount() {
        return amount;
    }

    public CurrencyAndAmount getTotalClearedBalance() {
        return totalClearedBalance;
    }

    public CurrencyAndAmount getTotalEffectiveBalance() {
        return totalEffectiveBalance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptedOverdraft, amount, clearedBalance, effectiveBalance, pendingTransactions,
                totalClearedBalance, totalEffectiveBalance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Balance other = (Balance) obj;
        return Objects.equals(acceptedOverdraft, other.acceptedOverdraft) && Objects.equals(amount, other.amount)
                && Objects.equals(clearedBalance, other.clearedBalance)
                && Objects.equals(effectiveBalance, other.effectiveBalance)
                && Objects.equals(pendingTransactions, other.pendingTransactions)
                && Objects.equals(totalClearedBalance, other.totalClearedBalance)
                && Objects.equals(totalEffectiveBalance, other.totalEffectiveBalance);
    }

    @Override
    public String toString() {
        return "Balance [clearedBalance=" + clearedBalance + ", effectiveBalance=" + effectiveBalance
                + ", pendingTransactions=" + pendingTransactions + ", acceptedOverdraft=" + acceptedOverdraft
                + ", amount=" + amount + ", totalClearedBalance=" + totalClearedBalance + ", totalEffectiveBalance="
                + totalEffectiveBalance + "]";
    }
}
