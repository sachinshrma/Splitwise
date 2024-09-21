package model;

import strategy.EqualSplitStrategy;
import strategy.PercentSplitStrategy;
import strategy.SplitStrategy;

import java.util.List;

public class Expense {
    String id;
    String name;
    User paidBy;
    SplitStrategy splitStrategy;
    List<Split> splits;
    double totalAmount;

    public Expense(String id, String name, User paidBy, SplitTypeEnum splitTypeEnum, List<Split> splits, double totalAmount) {
        this.id = id;
        this.name = name;
        this.paidBy = paidBy;
        this.splits = splits;
        this.totalAmount = totalAmount;

        setSplitStrategy(splitTypeEnum);
    }

    private void setSplitStrategy(SplitTypeEnum splitTypeEnum) {
        switch (splitTypeEnum) {
            case EQUAL:
                this.splitStrategy = new EqualSplitStrategy(this);
                break;
            case PERCENT:
                this.splitStrategy = new PercentSplitStrategy(this);
                break;
            default:
                    throw new RuntimeException("Invalid split type");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public SplitStrategy getSplitType() {
        return splitStrategy;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
