package service;

import model.*;

import java.util.*;

public class ExpenseService {
    Map<String, List<Expense>> groupExpenses;
    Map<String, List<Expense>> userExpenses;
    BalanceService balanceService;

    public ExpenseService(BalanceService balanceService) {
        this.groupExpenses = new HashMap<>();
        this.userExpenses = new HashMap<>();
        this.balanceService = balanceService;
    }

    public void addExpense(Group group, String name, User paidBy, SplitTypeEnum splitTypeEnum, List<Split> splits, double totalAmount) {
        Expense expense = new Expense(UUID.randomUUID().toString(), name, paidBy, splitTypeEnum, splits, totalAmount);
        expense.getSplitType().split();
        expense.getSplitType().validate();

        if (group != null) {
            List<Expense> currExpenses = groupExpenses.getOrDefault(group.getId(), new ArrayList<>());
            currExpenses.add(expense);
            groupExpenses.put(group.getId(), currExpenses);
        }

        for (Split split: splits) {
            List<Expense> currExpenses = userExpenses.getOrDefault(split.getUser().getId(), new ArrayList<>());
            currExpenses.add(expense);
            userExpenses.put(split.getUser().getId(), currExpenses);
        }

        balanceService.updateBalances(expense);

        // TODO: balanceService.simplify();
    }

    public void showExpenses(User user) {
        for (Expense expense: userExpenses.get(user.getId())) {
            System.out.println(expense.getName());
        }
    }

    public void showExpenses(Group group) {
        for (Expense expense: groupExpenses.get(group.getId())) {
            System.out.println(expense.getName());
        }
    }
}
