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
                String key = getBalanceKey(taker, paidBy);
                balances.put(key, balances.getOrDefault(key, 0d) - split.getValue());
                userBalances.put(taker.getId(), balances);

                balances = userBalances.getOrDefault(paidBy.getId(), new HashMap<>());
                key = getBalanceKey(paidBy, taker);
                balances.put(key, balances.getOrDefault(key, 0d) + split.getValue());
                userBalances.put(paidBy.getId(), balances);
            }
        }
    }

    private String getBalanceKey(User user1, User user2) {
        return user1.getId() + ":" + user2.getId();
    }

    public void showBalance(User user) {
        System.out.println(user.getName() + " Balance :" );
        Map<String, Double> balances = userBalances.getOrDefault(user.getId(), new HashMap<>());
        for (Map.Entry<String, Double> entry: balances.entrySet()) {
            System.out.println(userService.getUser(entry.getKey().split(":")[1]).getName() + " -- " + entry.getValue());
        }
    }

    public void settleBalance(User user1, User user2) {
        String key = getBalanceKey(user1, user2);
        double balance = userBalances.getOrDefault(user1.getId(), new HashMap<>()).getOrDefault(key, 0d);
        if (balance != 0) {
            userBalances.get(user1.getId()).remove(key);
        }

        key = getBalanceKey(user2, user1);
        balance = userBalances.getOrDefault(user2.getId(), new HashMap<>()).getOrDefault(key, 0d);
        if (balance != 0) {
            userBalances.get(user2.getId()).remove(key);
        }
    }
}
