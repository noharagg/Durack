import java.util.Scanner;

public class Main {

    private static boolean stepIsBot = false;

    public static void main(String[] args) {
        Desk desk = new Desk();
        Hand handBot = new Hand(desk);
        Hand handPlayer = new Hand(desk);
        Hand handTable = new Hand();
        Card trumpCard = desk.getTrumpCard();
        while(true){
            System.out.println();
            System.out.println("TrumpCard : "  + trumpCard + " Card in desk : " + desk.getSize() +  "  ");
            printSymbol();
            printHand(handBot);
            System.out.print(" TABLE :  ");
            printHand(handTable);
            printHand(handPlayer);
            printSymbol();
            System.out.println("Number card  : ");
            if (stepIsBot){
                numberCard(3);
            } else if (handPlayer.getCardList().size() != 0){
                handTable.getCardList().add(handPlayer.getCardList().remove(numberCard(handPlayer.getCardList().size()) - 1));

            }else {
                stepIsBot = true;
                for(int i = handPlayer.getCardList().size() ; i < 6 ; i++) {
                    takeCardsFromDesk(handPlayer,desk);
                    desk.setSize(desk.getSize() - 1);
                }
                handTable.getCardList().clear();
            }
        }
    }

    public static void printHand(Hand hand){
        for (Card card: hand.getCardList()
             ) {
            System.out.print(card.toString() + " " );
        }
        System.out.println();
        System.out.println();
    }

    public static void printSymbol(){
        int index = 30 ;
        while(index-- > 0) {
            System.out.print("$");
        }
        System.out.println();
    }

    public static int numberCard(int size){
        int select = 0;
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt() ){
            select = sc.nextInt();
            if(select > size || select <1){
                System.out.println("Incorrect input");
                return numberCard(size);
            }
        }else{
            System.out.println("Incorrect input");
            return numberCard(size);
        }
        return select;
    }

    public static void takeCardsFromDesk(Hand hand, Desk desk){
        hand.getCardList().add(desk.getList().remove((int)(Math.random() * desk.getSize() - 1)));

    }

}
