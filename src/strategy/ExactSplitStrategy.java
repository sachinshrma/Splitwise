package strategy;

import model.Expense;
import model.Split;

public class ExactSplitStrategy extends SplitStrategy {

    public ExactSplitStrategy(Expense expense) {
        super(expense);
    }

    @Override
    public void split() {
        // Do nothing
    }
}
