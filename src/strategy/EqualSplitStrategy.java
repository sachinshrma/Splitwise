package strategy;

import model.Expense;
import model.Split;

public class EqualSplitStrategy extends SplitStrategy {

    public EqualSplitStrategy(Expense expense) {
        super(expense);
    }

    @Override
    public void split() {
        double part = expense.getTotalAmount() / expense.getSplits().size();
        for (Split split: expense.getSplits()) {
            split.setValue(part);
        }
    }
}
