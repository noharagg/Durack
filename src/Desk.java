import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Desk {
    private List<Card> list;

    public Desk() {
        list = new ArrayList<>();
        Iterator iterator = list.iterator();
        String[] ranks = {"6", "7", "8", "9", "10", "J", "Q", "K", "T"};
        for (int i = 0 ; i < ranks.length; i++){
            list.add(new Card(ranks[i],"♠"));
            list.add(new Card(ranks[i],"♣"));
            list.add(new Card(ranks[i],"♥"));
            list.add(new Card(ranks[i],"♦"));
        }
    }

    public List<Card> getList() {
        return list;
    }
}
