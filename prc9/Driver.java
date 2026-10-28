
class Counter {
    int count = 0;

    // First run: without synchronization
    void increment() {
        count++;
    }

    // Second run: use this synchronized method instead
    synchronized void safeIncrement() {
        count++;
    }
}

public class Driver {
    static final int THREADS = 5;
    static final int INCREMENTS = 100000;

    static void runTest(boolean safe) throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    if (safe) {
                        counter.safeIncrement();
                    } else {
                        counter.increment();
                    }
                }
            });
            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println(
            (safe ? "With synchronization: " : "Without synchronization: ")
            + counter.count
        );
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Expected total: " + (THREADS * INCREMENTS));

        runTest(false);
        runTest(true);
    }
}
