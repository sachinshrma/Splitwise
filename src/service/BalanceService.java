package service;

import model.Expense;
import model.Split;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    Map<String, Double> userBalances;

    public BalanceService() {
        this.userBalances = new HashMap<>();
    }

    public void updateBalances(Expense expense) {
        User paidBy = expense.getPaidBy();

        for (Split split: expense.getSplits()) {
            User taker = split.getUser();
            if (!paidBy.equals(taker)) {
                double takerbalance = userBalances.getOrDefault(taker.getId(), 0d);
                userBalances.put(taker.getId(), takerbalance - split.getValue());

                double paidByUserBalance = userBalances.getOrDefault(paidBy.getId(), 0d);
                userBalances.put(paidBy.getId(), paidByUserBalance + split.getValue());
            }
        }
    }

    public void showBalance(User user) {
        System.out.println(user.getName() + " Balance :" + userBalances.get(user.getId()));
    }
}
