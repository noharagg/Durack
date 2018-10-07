import java.util.Objects;

public class Card {
    private String rank;
    private String color;


    public Card(String rank, String color) {
        this.rank = rank;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(rank, card.rank) &&
                Objects.equals(color, card.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, color);
    }

    @Override
    public String toString() {
        return rank + color;
    }
}
