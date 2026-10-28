
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class SeatBooking {
    private int seatsLeft = 5;
    private AtomicInteger successfulBookings = new AtomicInteger(0);

    // Unsafe booking: check and update are not synchronized
    void bookUnsafe(CountDownLatch start) {
        try {
            start.await();

            if (seatsLeft > 0) {
                // Give other threads time to check the same seat count
                Thread.sleep(100);

                seatsLeft--;
                successfulBookings.incrementAndGet();
                System.out.println("Booking successful!");
            } else {
                System.out.println("Booking failed: No seats left.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Safe booking: check and update happen under one lock
    synchronized void bookSafe(CountDownLatch start) {
        try {
            start.await();

            if (seatsLeft > 0) {
                seatsLeft--;
                successfulBookings.incrementAndGet();
                System.out.println("Booking successful!");
            } else {
                System.out.println("Booking failed: No seats left.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    int getSuccessfulBookings() {
        return successfulBookings.get();
    }

    int getSeatsLeft() {
        return seatsLeft;
    }
}

public class Driver {
    static void runTest(boolean safe) throws InterruptedException {
        SeatBooking booking = new SeatBooking();
        CountDownLatch start = new CountDownLatch(1);
        Thread[] threads = new Thread[10];

        System.out.println("\n" + (safe
                ? "With synchronization:"
                : "Without synchronization:"));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                if (safe) {
                    booking.bookSafe(start);
                } else {
                    booking.bookUnsafe(start);
                }
            });
            threads[i].start();
        }

        start.countDown();

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Successful bookings: "
                + booking.getSuccessfulBookings());
        System.out.println("Seats left: " + booking.getSeatsLeft());
    }

    public static void main(String[] args) throws InterruptedException {
        runTest(false);
        runTest(true);
    }
}
