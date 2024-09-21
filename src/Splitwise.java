import model.Group;
import model.Split;
import model.SplitTypeEnum;
import model.User;
import service.BalanceService;
import service.ExpenseService;
import service.GroupService;
import service.UserService;

import java.util.List;

public class Splitwise {
    public static void main(String[] args) {
        UserService userService = new UserService();
        GroupService groupService = new GroupService();
        BalanceService balanceService = new BalanceService();
        ExpenseService expenseService = new ExpenseService(balanceService);

        User user1 = userService.createUser("Sachin", "sachin@gmail.com");
        User user2 = userService.createUser("Harshu", "harshu@gmail.com");
        User user3 = userService.createUser("Akshit", "akshit@gmail.com");

        Group group = groupService.createGroup("F.R.I.E.N.D.S");
        groupService.addMember(group.getId(), user1);
        groupService.addMember(group.getId(), user2);
        groupService.addMember(group.getId(), user3);

        Split split1 = new Split(user1, 25);
        Split split2 = new Split(user2, 35);
        Split split3 = new Split(user3, 40);

        expenseService.addExpense(group, "Blinkit", user1, SplitTypeEnum.PERCENT, List.of(split1, split2, split3), 100);
        balanceService.showBalance(user1);
        balanceService.showBalance(user2);
        balanceService.showBalance(user3);

        expenseService.addExpense(null, "Zepto", user1, SplitTypeEnum.EQUAL, List.of(split1, split2), 400);

        expenseService.addExpense(group, "Zomato", user1, SplitTypeEnum.EQUAL, List.of(split1, split2), 400);

        expenseService.showExpenses(user1);
        expenseService.showExpenses(group);
    }
}
