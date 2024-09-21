package service;

import model.Expense;
import model.Split;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class BalanceService {
    Map<String, Map<String, Double>> userBalances;

    UserService userService;

    public BalanceService(UserService userService) {
        this.userBalances = new HashMap<>();
        this.userService = userService;
    }

    public void updateBalances(Expense expense) {
        User paidBy = expense.getPaidBy();

        for (Split split: expense.getSplits()) {
            User taker = split.getUser();

            if (!paidBy.equals(taker)) {
                Map<String, Double> balances = userBalances.getOrDefault(taker.getId(), new HashMap<>());
                balances.put(paidBy.getId(), balances.getOrDefault(paidBy.getId(), 0d) - split.getValue());
                userBalances.put(taker.getId(), balances);

                balances = userBalances.getOrDefault(paidBy.getId(), new HashMap<>());
                balances.put(taker.getId(), balances.getOrDefault(taker.getId(), 0d) + split.getValue());
                userBalances.put(paidBy.getId(), balances);
            }
        }
    }

    public void showBalance(User user) {
        System.out.println(user.getName() + " Balance :" );
        Map<String, Double> balances = userBalances.getOrDefault(user.getId(), new HashMap<>());
        for (Map.Entry<String, Double> entry: balances.entrySet()) {
            User balanceUser = userService.getUser(entry.getKey());
            if (balanceUser != null) {
                System.out.println(balanceUser.getName() + " : " + entry.getValue());
            }
        }
    }

    public void settleBalance(User user1, User user2) {
        double balance = userBalances.getOrDefault(user1.getId(), new HashMap<>()).getOrDefault(user2.getId(), 0d);
        if (balance != 0) {
            userBalances.get(user1.getId()).remove(user2.getId());
        }

        balance = userBalances.getOrDefault(user2.getId(), new HashMap<>()).getOrDefault(user1.getId(), 0d);
        if (balance != 0) {
            userBalances.get(user2.getId()).remove(user1.getId());
        }
    }
}
