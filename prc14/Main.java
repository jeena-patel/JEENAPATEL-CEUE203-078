
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountService service = new AccountService();

        while (true) {
            System.out.println("\n--- Mini Account App ---");
            System.out.println("1. Add account");
            System.out.println("2. Show account count");
            System.out.println("3. Test invalid action");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter balance: ");
                        double balance =
                            Double.parseDouble(sc.nextLine());

                        service.addAccount(
                            new Account(name, balance)
                        );

                        System.out.println("Account added.");
                        break;

                    case "2":
                        System.out.println(
                            "Account count: " + service.getCount()
                        );
                        break;

                    case "3":
                        service.performAction("DELETE");
                        break;

                    case "4":
                        System.out.println("Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid menu choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
