package com.blekione.rest.client.model;

import java.util.Objects;

public class SavingsGoal {
    private String savingsGoalUid;
    private String name;
    private CurrencyAndAmount target;
    private CurrencyAndAmount totalSaved;
    private int savedPercentage;

    // required by JSON processor
    public SavingsGoal() {
    }

    public SavingsGoal(String savingsGoalUid, String name, CurrencyAndAmount target, CurrencyAndAmount totalSaved,
            int savedPercentage) {
        super();
        this.savingsGoalUid = savingsGoalUid;
        this.name = name;
        this.target = target;
        this.totalSaved = totalSaved;
        this.savedPercentage = savedPercentage;
    }

    public String getSavingsGoalUid() {
        return savingsGoalUid;
    }

    public String getName() {
        return name;
    }

    public CurrencyAndAmount getTarget() {
        return target;
    }

    public CurrencyAndAmount getTotalSaved() {
        return totalSaved;
    }

    public int getSavedPercentage() {
        return savedPercentage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, savedPercentage, savingsGoalUid, target, totalSaved);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SavingsGoal other = (SavingsGoal) obj;
        return Objects.equals(name, other.name) && savedPercentage == other.savedPercentage
                && Objects.equals(savingsGoalUid, other.savingsGoalUid) && Objects.equals(target, other.target)
                && Objects.equals(totalSaved, other.totalSaved);
    }

    @Override
    public String toString() {
        return "SavingsGoal [savingsGoalUid=" + savingsGoalUid + ", name=" + name + ", target=" + target
                + ", totalSaved=" + totalSaved + ", savedPercentage=" + savedPercentage + "]";
    }
}
