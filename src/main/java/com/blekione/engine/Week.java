package com.blekione.engine;

import java.time.LocalDate;
import java.util.Objects;

public class Week {

    private LocalDate startDate;
    private LocalDate endDate;

    private Week() {
    }

    private Week(LocalDate startDate, LocalDate endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Week between(LocalDate startDate, LocalDate endDate) {
        return new Week(startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(endDate, startDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Week other = (Week) obj;
        return Objects.equals(endDate, other.endDate) && Objects.equals(startDate, other.startDate);
    }

    @Override
    public String toString() {
        return "Week [startDate=" + startDate + ", endDate=" + endDate + "]";
    }

}
