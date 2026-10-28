
public class Driver {
    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle(2),
            new Rectangle(4, 5),
            new Triangle(6, 3),
            new Circle(3)
        };

        double total = 0;
        double largest = 0;

        for (Shape s : shapes) {
            double a = s.area();

            System.out.printf("%s area: %.2f%n",
                    s.getClass().getSimpleName(), a);

            total += a;

            if (a > largest) {
                largest = a;
            }

            System.out.printf("Running total: %.2f%n", total);
        }

        System.out.printf("Total area: %.2f%n", total);
        System.out.printf("Largest area: %.2f%n", largest);
    }
}
