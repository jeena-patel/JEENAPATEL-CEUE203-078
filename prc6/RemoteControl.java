
interface Switchable {
    void on();
    void off();

    default void toggle() {
        on();
    }
}

class Fan implements Switchable {
    public void on() {
        System.out.println("Fan is ON");
    }

    public void off() {
        System.out.println("Fan is OFF");
    }
}

class Light implements Switchable {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }
}

@FunctionalInterface
interface SwitchPolicy {
    boolean maySwitchOn(Switchable device, int hour);
}

public class RemoteControl {
    public static void main(String[] args) {
        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        System.out.println("Toggling devices:");

        for (Switchable device : devices) {
            device.toggle();
        }

        SwitchPolicy policy = new SwitchPolicy() {
            @Override
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour < 22;
            }
        };

        System.out.println("\nUsing anonymous class:");
        System.out.println("May switch on at 10: "
                + policy.maySwitchOn(devices[0], 10));

        SwitchPolicy lambdaPolicy =
                (device, hour) -> hour >= 8 && hour < 20;

        System.out.println("\nUsing lambda:");
        System.out.println("May switch on at 21: "
                + lambdaPolicy.maySwitchOn(devices[1], 21));
    }
}
