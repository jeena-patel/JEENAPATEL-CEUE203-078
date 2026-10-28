
public class Driver {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("Riya", 101, 30000),
            new PartTime("Amit", 102, 80, 200),
            new Intern("Neha", 103, 8000)
        };

        double total = 0;

        for (Employee e : employees) {
            double salary = e.monthlySalary();

            System.out.println("Name: " + e.name);
            System.out.println("ID: " + e.id);
            System.out.println("Monthly salary: " + salary);

            if (e instanceof Intern) {
                System.out.println("Note: Intern receives a stipend.");
            }

            total += salary;
            System.out.println();
        }

        System.out.println("Total payroll: " + total);
    }
}
