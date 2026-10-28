
class MyResource implements AutoCloseable {

    public MyResource() {
        System.out.println("Resource opened.");
    }

    public void use() {
        System.out.println("Using resource.");
    }

    @Override
    public void close() {
        System.out.println("Resource closed automatically.");
    }
}

public class Driver {
    public static void main(String[] args) {
        try (MyResource resource = new MyResource()) {
            resource.use();

            throw new RuntimeException("Original error occurred!");

        } catch (RuntimeException e) {
            System.out.println("Caught error: " + e.getMessage());
        }

        System.out.println("Program continues.");
    }
}
