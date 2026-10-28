
public class Driver {
    public static void main(String[] args) {
        Card[] cards = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Queen", "Diamonds"),
            new Card("Ace", "Spades")
        };

        Card[] added = new Card[cards.length];
        int count = 0;

        for (Card card : cards) {
            boolean duplicate = false;

            for (int i = 0; i < count; i++) {
                if (card.equals(added[i])) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("Duplicate found: " + card);
                break;
            }

            added[count] = card;
            count++;
        }
    }
}
