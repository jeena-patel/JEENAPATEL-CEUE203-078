
public class Driver {
    public static void main(String[] args) {
        Media[] items = {
            new Book("Java Programming", 3),
            new DVD("Movie DVD", 2),
            new Magazine("Science Magazine", 4)
        };

        double totalFees = 0;

        for (Media item : items) {
            double fee = item.lateFee();

            System.out.println("Title: " + item.title);
            System.out.println("Late fee: Rs. " + fee);

            totalFees += fee;
        }

        System.out.println("Total late fees: Rs. " + totalFees);
    }
}
