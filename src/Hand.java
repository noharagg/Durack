import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cardList;
    final static int SIZE = 6;

    public Hand() {
        cardList = new ArrayList<>();
    }

    public Hand(Desk desk){
        if(Desk.size < SIZE){
            return;
        }
        cardList = new ArrayList<>(6);
        for(int i = 0; i < SIZE; i++){
            cardList.add(desk.getList().remove((int)(Math.random() * Desk.size--)));
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
