
package discount;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> prices = Arrays.asList(
                100.0, 200.0, 500.0
        );

        System.out.println("Choose discount:");
        System.out.println("1. 10% discount");
        System.out.println("2. 20% discount");
        System.out.println("3. Rs. 50 discount");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();

        DiscountRule rule;

        switch (choice) {
            case 1:
                rule = price -> price * 0.90;
                break;
            case 2:
                rule = price -> price * 0.80;
                break;
            case 3:
                rule = price -> Math.max(0, price - 50);
                break;
            default:
                System.out.println("Invalid choice!");
                sc.close();
                return;
        }

        double total = 0;

        System.out.println("\nDiscounted prices:");

        for (double price : prices) {
            double discounted = rule.apply(price);
            System.out.printf("%.2f -> %.2f%n",
                    price, discounted);
            total += discounted;
        }

        System.out.printf("Total after discount: %.2f%n", total);

        sc.close();
    }
}
