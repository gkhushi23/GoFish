import java.util.*;

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        for (String rank : ranks) {
            for (String suit : suits) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.isEmpty() ? null : cards.remove(0);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

class Player {
    private String name;
    private List<Card> hand = new ArrayList<>();
    private int sets = 0;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public void removeCardsByRank(String rank) {
        System.out.println("Before removing cards: " + hand);
        hand.removeIf(card -> card.getRank().equals(rank));
        System.out.println("After removing cards: " + hand);
    }

    public boolean hasCardWithRank(String rank) {
        System.out.println("Checking for rank " + rank + " in hand: " + hand);
        return hand.stream().anyMatch(card -> card.getRank().equals(rank));
    }

    public int checkSets() {
        Map<String, Integer> rankCount = new HashMap<>();
        for (Card card : hand) {
            rankCount.put(card.getRank(), rankCount.getOrDefault(card.getRank(), 0) + 1);
        }

        int setsFound = 0;
        for (String rank : rankCount.keySet()) {
            if (rankCount.get(rank) == 4) {
                removeCardsByRank(rank);
                setsFound++;
            }
        }
        sets += setsFound;
        return setsFound;
    }

    public int getSets() {
        return sets;
    }

    public List<Card> getHand() {
        return hand;
    }
}

class Game {
    private Deck deck;
    private Player player1;
    private Player player2;

    public Game(String player1Name, String player2Name) {
        deck = new Deck();
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        dealInitialCards();
    }

    private void dealInitialCards() {
        for (int i = 0; i < 7; i++) {
            player1.addCard(deck.draw());
            player2.addCard(deck.draw());
        }
    }

    public void play() {
        Player currentPlayer = player1;
        Player opponent = player2;

        while (!deck.isEmpty()) {
            System.out.println(currentPlayer.getName() + "'s turn:");
            System.out.println("Your hand: " + currentPlayer.getHand());

            Scanner scanner = new Scanner(System.in);
            System.out.print("Ask for a rank: ");
            String rank = scanner.nextLine();

            if (opponent.hasCardWithRank(rank)) {
                System.out.println("Opponent has the card! You get their cards.");
                opponent.removeCardsByRank(rank);
                for (Card card : opponent.getHand()) {
                    if (card.getRank().equals(rank)) {
                        currentPlayer.addCard(card);
                    }
                }
            } else {
                System.out.println("Go Fish!");
                Card drawnCard = deck.draw();
                if (drawnCard != null) {
                    currentPlayer.addCard(drawnCard);
                }
            }

            int setsFound = currentPlayer.checkSets();
            if (setsFound > 0) {
                System.out.println("You completed " + setsFound + " set(s)!");
            }

            Player temp = currentPlayer;
            currentPlayer = opponent;
            opponent = temp;
        }

        System.out.println("Game over!");
        System.out.println(player1.getName() + " completed sets: " + player1.getSets());
        System.out.println(player2.getName() + " completed sets: " + player2.getSets());

        if (player1.getSets() > player2.getSets()) {
            System.out.println(player1.getName() + " wins!");
        } else if (player2.getSets() > player1.getSets()) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}

public class GoFish {
    public static void main(String[] args) {
        Game game = new Game("Player 1", "Player 2");
        game.play();
    }
}
