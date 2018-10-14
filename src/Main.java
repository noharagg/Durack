import java.util.Scanner;

public class Main {

    private static boolean stepIsBot = false;

    public static void main(String[] args) {
        Desk desk = new Desk();
        Hand handBot = new Hand(desk);
        Hand handPlayer = new Hand(desk);
        Hand handTable = new Hand();
        Card trumpCard = desk.getTrumpCard();
        String trumpColor = trumpCard.getColor();
        boolean deskIsEmpty = true;
        int number ;
        while(true){
            System.out.println();
            System.out.println("TrumpCard : "  + trumpCard + " Card in desk : " + desk.getSize() +  "  ");
            printSymbol();
            printHand(handBot);
            System.out.print(" TABLE :  ");
            printHand(handTable);
            printHand(handPlayer);
            printSymbol();
            System.out.println("Number card (0 for Pass) : ");
            try {
                if (stepIsBot) {
                    //numberCard(3);
                } else if (handPlayer.getCardList().size() != 0) {
                    Card attackCard;
                    if(handTable.getCardList().size() == 0){
                        attackCard = handPlayer.getCardList().remove( numberCard(handPlayer.getCardList().size(), false)- 1);
                    }else{
                        number = correctNumber(handPlayer,handTable);
                        if(number == 0){
                            if(deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                            if(deskIsEmpty) takeCards(handBot, desk, trumpCard);
                            handTable.getCardList().clear();
                            continue;
                        }
                        attackCard = handPlayer.getCardList().remove(number - 1);
                    }
                    handTable.getCardList().add(attackCard);
                    if (!botDefense(attackCard, handBot, trumpColor, handTable)) {
                        for (Card card : handTable.getCardList()) {
                            handBot.getCardList().add(card);
                        }
                        handTable.getCardList().clear();
                        if(deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                        System.out.println(" Bot did't defence");
                    } else if (handBot.getCardList().size() == 0) {
                        if(!deskIsEmpty) {
                            System.out.println("YOU LOSS");
                            break;
                        }
                        //  stepIsBot = true;
                        if(deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                        if(deskIsEmpty) takeCards(handBot, desk, trumpCard);
                        System.out.println(" Bot defened. bot's turn");
                    }
                } else {
                    //stepIsBot = true;
                    if(!deskIsEmpty) {
                        System.out.println("YOU WIN");
                        break;
                    }
                    if(deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                    if(deskIsEmpty) takeCards(handBot, desk, trumpCard);
                    handTable.getCardList().clear();
                }
            } catch (Exception e){
                deskIsEmpty = false;
                trumpCard = null;
            }
        }
    }

    public static int correctNumber(Hand hand,Hand tableHand ){
        int correctNumber = numberCard(hand.getCardList().size() , true);
        boolean b = false;
        while(true){
            if(correctNumber == 0){
                return 0;
            }
            for (Card card: tableHand.getCardList()) {
                if(hand.getCardList().get(correctNumber - 1).getRank().equals(card.getRank())){
                    b = true;
                    break;
                }
            }
            if(!b){
                System.out.println("You can not throw this card");
            }else {
                break;
            }
            correctNumber = numberCard(hand.getCardList().size() , true);

        }
        return correctNumber;
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

    public static int numberCard(int size, boolean b){
        int select = 0;
        Scanner sc = new Scanner(System.in);
        if(sc.hasNextInt() ){
            select = sc.nextInt();
            if(select == 0 &&  b){
                return select;
            }
            if(select > size || select <1){
                System.out.println("Incorrect input");
                return numberCard(size, b);
            }
        }else{
            System.out.println("Incorrect input");
            return numberCard(size, b);
        }
        return select;
    }

    public  static void takeCards(Hand hand,Desk desk, Card trumpCard) throws Exception{

        for(int i = hand.getCardList().size() ; i < 6 ; i++) {
            if(desk.getSize() == 0){
                hand.getCardList().add(trumpCard);
                throw new Exception();
            }
            hand.getCardList().add(desk.getList().remove((int)(Math.random() * desk.getSize() - 1)));
            desk.setSize(desk.getSize() - 1);
        }
    }

    public static boolean botDefense(Card attackCard, Hand botHand, String trumpColor, Hand handTable){
        Card bestCard = null;
        int index = 0 , value = 0;
        for (Card card: botHand.getCardList()) {
            if(attackCard.getColor().equals(card.getColor()) && attackCard.getStrong() < card.getStrong()){
                if (bestCard == null || bestCard.getStrong() > card.getStrong()) {
                    bestCard = card;
                    index = value;
                }
            }
            value++;
        }

        if(bestCard == null && !attackCard.getColor().equals(trumpColor)){
            value = 0;
            for (Card card: botHand.getCardList()) {
                if(card.getColor().equals(trumpColor)){
                    if(bestCard == null || bestCard.getStrong() > card.getStrong()){
                        bestCard = card;
                        index = value;

                    }
                }
                value++;
            }
        }
        if(bestCard == null){
            return false;
        }else {
            handTable.getCardList().add(botHand.getCardList().remove(index));
        }
        return true;
    }

}
