package com.blekione.rest.client.model;

import java.util.Objects;

public class ErrorDetail {
    private String message;

    // required by JSON processor
    public ErrorDetail() {
    }

    public ErrorDetail(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ErrorDetail other = (ErrorDetail) obj;
        return Objects.equals(message, other.message);
    }

    @Override
    public String toString() {
        return "ErrorDetail [message=" + message + "]";
    }
}
