
import java.util.ArrayList;
import java.util.List;

public class AccountService {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException(
                "Account cannot be null."
            );
        }

        accounts.add(account);
    }

    public int getCount() {
        return accounts.size();
    }

    public void performAction(String action) {
        if (!"ADD".equalsIgnoreCase(action)) {
            throw new IllegalArgumentException(
                "Invalid action: " + action
            );
        }

        addAccount(new Account("New Customer", 0));
    }
}
