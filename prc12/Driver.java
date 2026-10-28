
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        String paragraph =
            "java is easy and java is powerful java is popular";

        Map<String, Integer> frequency = new HashMap<>();

        String[] words = paragraph.toLowerCase()
                                  .split("[^a-z0-9]+");

        for (String word : words) {
            if (!word.isEmpty()) {
                frequency.put(
                    word, frequency.getOrDefault(word, 0) + 1
                );
            }
        }

        List<Map.Entry<String, Integer>> list =
            new ArrayList<>(frequency.entrySet());

        list.sort(
            Comparator.comparing(
                (Map.Entry<String, Integer> e) -> e.getValue()
            ).reversed()
            .thenComparing(Map.Entry::getKey)
        );

        System.out.println("Word frequencies:");

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(
                entry.getKey() + " : " + entry.getValue()
            );
        }
    }
}
