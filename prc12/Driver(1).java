
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        List<String> logins = Arrays.asList(
            "jeena", "riya", "amit", "jeena",
            "karan", "riya", "neha", "amit"
        );

        HashSet<String> uniqueUsers = new HashSet<>();
        LinkedHashSet<String> orderedUsers = new LinkedHashSet<>();

        for (String user : logins) {
            uniqueUsers.add(user);
            orderedUsers.add(user);
        }

        System.out.println("Total login entries: " + logins.size());
        System.out.println("Unique visitors: " + uniqueUsers.size());

        System.out.println("First three distinct visitors:");

        int count = 0;
        for (String user : orderedUsers) {
            if (count == 3) {
                break;
            }

            System.out.println(user);
            count++;
        }
    }
}
