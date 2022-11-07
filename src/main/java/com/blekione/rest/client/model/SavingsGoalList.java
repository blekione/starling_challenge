package com.blekione.rest.client.model;

import java.util.List;
import java.util.Objects;

public class SavingsGoalList {

    List<SavingsGoal> savingsGoalList;

    // required by JSON porcessor
    public SavingsGoalList() {
    }

    public SavingsGoalList(List<SavingsGoal> savingsGoalList) {
        super();
        this.savingsGoalList = savingsGoalList;
    }

    public List<SavingsGoal> getSavingsGoalList() {
        return savingsGoalList;
    }

    @Override
    public int hashCode() {
        return Objects.hash(savingsGoalList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SavingsGoalList other = (SavingsGoalList) obj;
        return Objects.equals(savingsGoalList, other.savingsGoalList);
    }

    @Override
    public String toString() {
        return "SavingGoalList [savingsGoalList=" + savingsGoalList + "]";
    }
}
