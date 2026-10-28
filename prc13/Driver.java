
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

class Pair<A, B> {
    private A first;
    private B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public void display() {
        System.out.println("First: " + first);
        System.out.println("Second: " + second);
    }
}

public class Driver {

    // Generic method
    public static <T> T firstElement(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[0];
    }

    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>();
        intBox.set(100);
        System.out.println("Box value: " + intBox.get());

        Box<String> stringBox = new Box<>();
        stringBox.set("Hello Java");
        System.out.println("String box: " + stringBox.get());

        Pair<String, Integer> pair =
            new Pair<>("Jeena", 20);
        pair.display();

        String[] names = {"Riya", "Amit", "Neha"};
        System.out.println(
            "First name: " + firstElement(names)
        );

        Integer[] numbers = {10, 20, 30};
        System.out.println(
            "First number: " + firstElement(numbers)
        );
    }
}
