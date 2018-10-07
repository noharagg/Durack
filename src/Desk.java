import java.util.ArrayList;
import java.util.List;

public class Desk {
    private List<Card> list;
    private Card trumpCard;
    public static int size = 36;
    public Desk() {
        list = new ArrayList<>();
        String[] ranks = {"6", "7", "8", "9", "10", "J", "Q", "K", "T"};
        for (int i = 0 ; i < ranks.length; i++){
            list.add(new Card(ranks[i],"♠", i));
            list.add(new Card(ranks[i],"♣", i));
            list.add(new Card(ranks[i],"♥", i));
            list.add(new Card(ranks[i],"♦", i));
        }
        searchTrumpCard();
    }

    public List<Card> getList() {
        return list;
    }

    private void searchTrumpCard(){
        trumpCard = list.remove((int)(Math.random()* 37));
        size--;
    }

    public Card getTrumpCard() {
        return trumpCard;
    }
}
