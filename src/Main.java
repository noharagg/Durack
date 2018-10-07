import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Desk desk = new Desk();
        Hand handBot = new Hand(desk);
        Hand handPlayer = new Hand(desk);
        Hand handTable = new Hand();
        Card trumpCard = desk.getTrumpCard();
        int select;
        while(true){
            System.out.println();
            System.out.println("TrumpCard : "  + trumpCard + " Card in desk : " + desk.getList().size() +  "  ");
            printSymbol();
            printHand(handBot);
            printHand(handTable);
            printHand(handPlayer);
            printSymbol();
            System.out.println("Number card (1-6) : ");
            select = sc.nextInt();
        }












//        for (Card card :desk.getList()
//             ) {
//            System.out.println(card.toString());
//        }
//
//        System.out.println();
//
//        for (Card card :handBot.getCardList()
//        ) {
//            System.out.println(card.toString());
//        }
//        System.out.println();
//
//        for (Card card :handPlayer.getCardList()
//        ) {
//            System.out.println(card.toString());
//        }

    }
    public static void printHand(Hand hand){
        for (Card card: hand.getCardList()
             ) {
            System.out.print(card.toString() + " " );
        }
        System.out.println();
    }

    public static void printSymbol(){
        int index = 30 ;
        while(index-- > 0) {
            System.out.print("$");
        }
        System.out.println();
    }
}
