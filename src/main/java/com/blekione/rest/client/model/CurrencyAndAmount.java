package com.blekione.rest.client.model;

import java.math.BigInteger;
import java.util.Objects;

public class CurrencyAndAmount {
    String currency;
    BigInteger minorUnits;

    public CurrencyAndAmount(String currency, BigInteger minorUnits) {
        super();
        this.currency = currency;
        this.minorUnits = minorUnits;
    }

    public String getCurrency() {
        return currency;
    }

    public BigInteger getMinorUnits() {
        return minorUnits;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, minorUnits);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CurrencyAndAmount other = (CurrencyAndAmount) obj;
        return Objects.equals(currency, other.currency) && Objects.equals(minorUnits, other.minorUnits);
    }

    @Override
    public String toString() {
        return "CurrencyAndAmount [currency=" + currency + ", minorUnits=" + minorUnits + "]";
    }
}
