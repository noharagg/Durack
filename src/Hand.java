import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cardList;
    final static int SIZE = 6;

    public Hand(Desk desk){
        cardList = new ArrayList<>(6);
        int index = 36;
        for(int i = 0; i < SIZE; i++){
            cardList.add(desk.getList().remove((int)(Math.random() * index--)));
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
