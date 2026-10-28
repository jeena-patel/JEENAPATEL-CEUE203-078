
import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private final int capacity = 3;

    public synchronized void put(int value)
            throws InterruptedException {
        while (buffer.size() == capacity) {
            wait();
        }

        buffer.add(value);
        System.out.println("Produced: " + value);

        notifyAll();
    }

    public synchronized int take()
            throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }

        int value = buffer.remove();
        System.out.println("Consumed: " + value);

        notifyAll();
        return value;
    }
}

public class Driver {
    public static void main(String[] args)
            throws InterruptedException {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.put(i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.take();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed.");
    }
}
