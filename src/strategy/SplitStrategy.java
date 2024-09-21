package strategy;

import model.Expense;
import model.Split;

public abstract class SplitStrategy {
    protected Expense expense;

    public SplitStrategy(Expense expense) {
        this.expense = expense;
    }

    abstract public void split();

    public void validate() {
        double sum = 0;
        for (Split split: expense.getSplits()) {
            sum += split.getValue();
        }
        if (sum != expense.getTotalAmount()) {
            throw new RuntimeException("Split validation failed. Recheck the assigned split values");
        }
    }
}
