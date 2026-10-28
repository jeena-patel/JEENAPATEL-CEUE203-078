
@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

public class Notifications {
    public static void main(String[] args) {

        Notifier email = message ->
                System.out.println("Email: " + message);

        Notifier sms = message ->
                System.out.println("SMS: " + message);

        class UrgentEmail implements Notifier, Urgent {
            public void send(String message) {
                System.out.println("URGENT EMAIL: " + message);
            }
        }

        Notifier urgentEmail = new UrgentEmail();

        Notifier[] senders = {
            email,
            sms,
            urgentEmail
        };

        String message = "Meeting at 10 AM";

        System.out.println("Broadcasting message:");

        for (Notifier sender : senders) {
            sender.send(message);
        }

        System.out.println("\nSending urgent notifications:");

        for (Notifier sender : senders) {
            if (sender instanceof Urgent) {
                sender.send(message);
                sender.send(message);
            }
        }
    }
}
