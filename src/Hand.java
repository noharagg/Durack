import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cardList;
    final static int SIZE = 6;

    public Hand() {
        cardList = new ArrayList<>();
    }

    public Hand(Desk desk){
        if(desk.getSize() < SIZE){
            return;
        }
        cardList = new ArrayList<>(6);
        for(int i = 0; i < SIZE; i++){
            cardList.add(desk.getList().remove((int)(Math.random() * desk.getSize())));
            desk.setSize(desk.getSize() - 1);
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
