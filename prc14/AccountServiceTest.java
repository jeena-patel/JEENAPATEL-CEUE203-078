
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    @Test
    void addingAccountIncreasesCount() {
        AccountService service = new AccountService();

        service.addAccount(new Account("Jeena", 1000));

        assertEquals(1, service.getCount());
    }

    @Test
    void addingTwoAccountsGivesCountTwo() {
        AccountService service = new AccountService();

        service.addAccount(new Account("Jeena", 1000));
        service.addAccount(new Account("Riya", 2000));

        assertEquals(2, service.getCount());
    }

    @Test
    void invalidActionThrowsException() {
        AccountService service = new AccountService();

        assertThrows(
            IllegalArgumentException.class,
            () -> service.performAction("DELETE")
        );
    }
}
