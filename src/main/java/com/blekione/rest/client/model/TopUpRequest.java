package com.blekione.rest.client.model;

import java.util.Objects;

public class TopUpRequest {
    private CurrencyAndAmount amount;

    // required by JSON processor
    public TopUpRequest() {
    }

    public TopUpRequest(CurrencyAndAmount amount) {
        super();
        this.amount = amount;
    }

    public CurrencyAndAmount getAmount() {
        return amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TopUpRequest other = (TopUpRequest) obj;
        return Objects.equals(amount, other.amount);
    }

    @Override
    public String toString() {
        return "TopUpRequest [amount=" + amount + "]";
    }
}
