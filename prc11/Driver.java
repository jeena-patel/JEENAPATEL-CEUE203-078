
import java.io.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    int id;
    String name;
    transient String password;

    Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Password: " + password);
        System.out.println();
    }
}

public class Driver {
    public static void main(String[] args) {
        Student[] students = {
            new Student(1, "Jeena", "abc123"),
            new Student(2, "Riya", "xyz456")
        };

        // Save objects to a file
        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("students.dat"))) {

            out.writeObject(students);
            System.out.println("Objects saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving objects: " + e.getMessage());
            return;
        }

        // Read objects back from the file
        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("students.dat"))) {

            Student[] loaded =
                (Student[]) in.readObject();

            System.out.println("\nLoaded student details:");

            for (Student s : loaded) {
                s.display();
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading objects: " + e.getMessage());
        }
    }
}
