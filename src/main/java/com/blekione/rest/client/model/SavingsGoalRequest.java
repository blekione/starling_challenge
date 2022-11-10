package com.blekione.rest.client.model;

import java.util.Objects;

public class SavingsGoalRequest {
    private String name;
    private String currency;
    private CurrencyAndAmount target;

    // required by JSON processor
    public SavingsGoalRequest() {
    }

    public SavingsGoalRequest(String name, String currency, CurrencyAndAmount target) {
        this.name = name;
        this.currency = currency;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public CurrencyAndAmount getTarget() {
        return target;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, name, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SavingsGoalRequest other = (SavingsGoalRequest) obj;
        return Objects.equals(currency, other.currency) && Objects.equals(name, other.name)
                && Objects.equals(target, other.target);
    }

    @Override
    public String toString() {
        return "SavingGoalRequest [name=" + name + ", currency=" + currency + ", target=" + target + "]";
    }
}
