
class SharedTotal {
    long total = 0;

    void addUnsafe(long value) {
        total += value;
    }

    synchronized void addSafe(long value) {
        total += value;
    }
}

public class Driver {
    static final int SIZE = 2_000_000;
    static final int THREADS = 8;
    static int[] numbers = new int[SIZE];

    static {
        for (int i = 0; i < SIZE; i++) {
            numbers[i] = 1;
        }
    }

    static long sharedSum(boolean safe) throws InterruptedException {
        SharedTotal shared = new SharedTotal();
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            final int start = i * SIZE / THREADS;
            final int end = (i + 1) * SIZE / THREADS;

            threads[i] = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (safe) {
                        shared.addSafe(numbers[j]);
                    } else {
                        shared.addUnsafe(numbers[j]);
                    }
                }
            });

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        return shared.total;
    }

    static long localSum() throws InterruptedException {
        Thread[] threads = new Thread[THREADS];
        long[] partial = new long[THREADS];

        for (int i = 0; i < THREADS; i++) {
            final int id = i;
            final int start = i * SIZE / THREADS;
            final int end = (i + 1) * SIZE / THREADS;

            threads[i] = new Thread(() -> {
                long sum = 0;

                for (int j = start; j < end; j++) {
                    sum += numbers[j];
                }

                partial[id] = sum;
            });

            threads[i].start();
        }

        long total = 0;

        for (Thread t : threads) {
            t.join();
        }

        for (long sum : partial) {
            total += sum;
        }

        return total;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Expected total: " + SIZE);

        long start = System.nanoTime();
        long wrong = sharedSum(false);
        long time1 = System.nanoTime() - start;

        start = System.nanoTime();
        long fixed1 = sharedSum(true);
        long time2 = System.nanoTime() - start;

        start = System.nanoTime();
        long fixed2 = localSum();
        long time3 = System.nanoTime() - start;

        System.out.println("Without synchronization: " + wrong);
        System.out.println("With synchronization: " + fixed1);
        System.out.println("With local partial sums: " + fixed2);

        System.out.println("Unsafe time: " + time1 / 1_000_000.0 + " ms");
        System.out.println("Synchronized time: " + time2 / 1_000_000.0 + " ms");
        System.out.println("Local sums time: " + time3 / 1_000_000.0 + " ms");
    }
}
