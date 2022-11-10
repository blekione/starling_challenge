package com.blekione.rest.client.model;

import java.util.List;
import java.util.Objects;

public class SavingsGoalTransferResponse {
    private String transferUid;
    private boolean success;
    private List<ErrorDetail> errors;

    // required by JSON processor
    public SavingsGoalTransferResponse() {
    }

    public SavingsGoalTransferResponse(String transferUid, boolean success, List<ErrorDetail> errors) {
        super();
        this.transferUid = transferUid;
        this.success = success;
        this.errors = errors;
    }

    public String getTransferUid() {
        return transferUid;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<ErrorDetail> getErrors() {
        return errors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(errors, success, transferUid);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SavingsGoalTransferResponse other = (SavingsGoalTransferResponse) obj;
        return Objects.equals(errors, other.errors) && success == other.success
                && Objects.equals(transferUid, other.transferUid);
    }

    @Override
    public String toString() {
        return "SavingsGoalTransferResponse [transferUid=" + transferUid + ", success=" + success + ", errors=" + errors
                + "]";
    }
}
