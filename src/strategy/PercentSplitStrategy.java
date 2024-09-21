package strategy;

import model.Expense;
import model.Split;

public class PercentSplitStrategy extends SplitStrategy {

    public PercentSplitStrategy(Expense expense) {
        super(expense);
    }

    @Override
    public void split() {
        for (Split split: expense.getSplits()) {
            double percent = split.getValue();
            split.setValue((percent * expense.getTotalAmount())/100d);
        }
    }
}
