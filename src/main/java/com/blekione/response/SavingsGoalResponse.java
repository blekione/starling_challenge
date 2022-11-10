package com.blekione.response;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class SavingsGoalResponse {

    private boolean success;
    private Optional<BigInteger> roundingSavings;
    private Optional<String> savingsGoalUid;

    // required by JSON processor;
    public SavingsGoalResponse() {
    }

    public SavingsGoalResponse(boolean success) {
        this(success, Optional.empty(), Optional.empty());
    }

    public SavingsGoalResponse(boolean success, Optional<BigInteger> roundingSavings, Optional<String> savingsGoalUid) {
        super();
        this.success = success;
        this.roundingSavings = roundingSavings;
        this.savingsGoalUid = savingsGoalUid;
    }

    public boolean isSuccess() {
        return success;
    }

    public Optional<BigInteger> getRoundingSavings() {
        return roundingSavings;
    }

    public Optional<String> getSavingsGoalUid() {
        return savingsGoalUid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundingSavings, savingsGoalUid, success);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SavingsGoalResponse other = (SavingsGoalResponse) obj;
        return Objects.equals(roundingSavings, other.roundingSavings)
                && Objects.equals(savingsGoalUid, other.savingsGoalUid) && success == other.success;
    }

    @Override
    public String toString() {
        return "SavingsGoalResponse [success=" + success + ", roundingSavings=" + roundingSavings + ", savingsGoalUid="
                + savingsGoalUid + "]";
    }

}
