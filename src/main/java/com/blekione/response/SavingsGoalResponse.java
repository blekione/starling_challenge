package com.blekione.response;

import java.util.Objects;


public class SavingsGoalResponse {

    boolean success;

    public SavingsGoalResponse(boolean success) {
        super();
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public int hashCode() {
        return Objects.hash(success);
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
        return success == other.success;
    }



}
