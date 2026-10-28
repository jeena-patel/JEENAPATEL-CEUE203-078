
import java.util.concurrent.CountDownLatch;

public class Driver {
    static void unsafeTask(
            Object first, Object second, CountDownLatch ready) {

        synchronized (first) {
            ready.countDown();

            try {
                ready.await();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (second) {
                System.out.println("Unsafe task completed.");
            }
        }
    }

    static void safeTask(Object first, Object second) {
        // Both threads acquire locks in the same order
        synchronized (first) {
            synchronized (second) {
                System.out.println(
                    Thread.currentThread().getName()
                    + " completed safely."
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Object lockA = new Object();
        Object lockB = new Object();
        CountDownLatch ready = new CountDownLatch(2);

        System.out.println("Demonstrating deadlock...");

        Thread t1 = new Thread(
            () -> unsafeTask(lockA, lockB, ready), "Thread-1");

        Thread t2 = new Thread(
            () -> unsafeTask(lockB, lockA, ready), "Thread-2");

        // Daemon threads allow the program to exit
        // even while the demonstration threads are deadlocked.
        t1.setDaemon(true);
        t2.setDaemon(true);

        t1.start();
        t2.start();

        ready.await();
        Thread.sleep(200);

        System.out.println(
            "Deadlock demonstrated: both threads are blocked."
        );

        System.out.println("\nFixing deadlock...");

        Object safeA = new Object();
        Object safeB = new Object();

        Thread t3 = new Thread(
            () -> safeTask(safeA, safeB), "Safe-Thread-1");

        Thread t4 = new Thread(
            () -> safeTask(safeA, safeB), "Safe-Thread-2");

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        System.out.println("Both safe tasks completed.");
    }
}
