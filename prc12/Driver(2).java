
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Riya", 950);
        scores.put("Amit", 870);
        scores.put("Jeena", 950);
        scores.put("Karan", 780);
        scores.put("Neha", 870);

        List<Map.Entry<String, Integer>> leaderboard =
            new ArrayList<>(scores.entrySet());

        leaderboard.sort(
            Comparator.comparing(
                (Map.Entry<String, Integer> e) -> e.getValue()
            ).reversed()
            .thenComparing(Map.Entry::getKey)
        );

        int topN = 3;

        System.out.println("TOP " + topN + " PLAYERS");
        System.out.println("----------------");

        for (int i = 0; i < Math.min(topN, leaderboard.size()); i++) {
            Map.Entry<String, Integer> player = leaderboard.get(i);

            System.out.println(
                (i + 1) + ". " + player.getKey()
                + " - " + player.getValue()
            );
        }
    }
}
