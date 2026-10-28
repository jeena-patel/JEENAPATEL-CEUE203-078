
import java.util.NoSuchElementException;

class MyStack<T> {
    private Object[] items;
    private int top = 0;

    MyStack(int capacity) {
        items = new Object[capacity];
    }

    public void push(T value) {
        if (top == items.length) {
            throw new IllegalStateException("Stack is full.");
        }

        items[top++] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        T value = (T) items[--top];
        items[top] = null;
        return value;
    }

    public boolean isEmpty() {
        return top == 0;
    }
}

public class Driver {
    public static void main(String[] args) {
        MyStack<Integer> numberStack = new MyStack<>(5);

        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);

        System.out.println("Integer stack:");
        while (!numberStack.isEmpty()) {
            System.out.println(numberStack.pop());
        }

        MyStack<String> nameStack = new MyStack<>(5);

        nameStack.push("Jeena");
        nameStack.push("Riya");

        System.out.println("\nString stack:");
        while (!nameStack.isEmpty()) {
            System.out.println(nameStack.pop());
        }

        try {
            numberStack.pop();
        } catch (NoSuchElementException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }
}
