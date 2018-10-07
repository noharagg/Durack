
public class Main {
    public static void main(String[] args) {
        Desk desk = new Desk();
        Hand hand = new Hand(desk);

        for (Card card :desk.getList()
             ) {
            System.out.println(card.toString());
        }

        System.out.println();
        for (Card card :hand.getCardList()
        ) {
            System.out.println(card.toString());
        }
    }
}
