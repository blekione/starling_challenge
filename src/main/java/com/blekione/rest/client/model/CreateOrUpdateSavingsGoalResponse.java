package com.blekione.rest.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateOrUpdateSavingsGoalResponse {
    private String savingsGoalUid;
    private boolean success;
    private List<ErrorDetail> errors = new ArrayList<>();

    // required by JSON processor
    public CreateOrUpdateSavingsGoalResponse() {
    }

    public CreateOrUpdateSavingsGoalResponse(String savingsGoalUid, boolean success, List<ErrorDetail> errors) {
        super();
        this.savingsGoalUid = savingsGoalUid;
        this.success = success;
        this.errors = errors;
    }

    public String getSavingsGoalUid() {
        return savingsGoalUid;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, savingsGoalUid, success);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CreateOrUpdateSavingsGoalResponse other = (CreateOrUpdateSavingsGoalResponse) obj;
        return Objects.equals(errors, other.errors) && Objects.equals(savingsGoalUid, other.savingsGoalUid)
                && success == other.success;
    }

    @Override
    public String toString() {
        return "CreateOrUpdateSavingsGoalResponse [savingsGoalUid=" + savingsGoalUid + ", success=" + success
                + ", errors=" + errors + "]";
    }
}
