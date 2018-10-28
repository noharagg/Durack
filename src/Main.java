import java.util.Scanner;

public class Main {

    private static boolean stepIsBot = false;
    private static boolean deskIsEmpty = true;

    public static void main(String[] args) {
        Desk desk = new Desk();
        Hand handBot = new Hand(desk);
        Hand handPlayer = new Hand(desk);
        Hand handTable = new Hand();
        Card trumpCard = desk.getTrumpCard();
        Card botCard = null;
        String trumpColor = trumpCard.getColor();
        int number ;
        while (true) {
            if (stepIsBot) {
                botCard = botAttack(handBot, handTable, trumpColor);
                if (botCard == null) {
                    stepIsBot = false;
                    if (deskIsEmpty) {
                        takeCards(handBot, desk, trumpCard);
                    } else if (handBot.getCardList().size() == 0) {
                        System.out.println("You LOSS");
                        break;
                    }
                    if (deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                        handTable.getCardList().clear();
                    }
                }
                System.out.println();
                System.out.println("TrumpCard : " + trumpCard + " Card in desk : " + desk.getSize() + "  ");
                printSymbol();
                printBotHand(handBot.getCardList().size());
                System.out.print(" TABLE :  ");
                printHand(handTable);
                printHand(handPlayer);
                printSymbol();
                System.out.println("Number card (0 for Pass or Take) : ");
                if (stepIsBot) {
                    if (!PlayerDefence(botCard, handPlayer, handTable, trumpColor)) {
                        for (Card card : handTable.getCardList()) {
                            handPlayer.getCardList().add(card);
                        }
                        handTable.getCardList().clear();
                        if (deskIsEmpty) takeCards(handBot, desk, trumpCard);
                    }
                } else if (handPlayer.getCardList().size() != 0) {
                    Card attackCard;
                    if (handTable.getCardList().size() == 0) {
                        attackCard = handPlayer.getCardList().remove(numberCard(handPlayer.getCardList().size(), false) - 1);
                    } else {
                        number = correctNumber(handPlayer, handTable);
                        if (number == 0) {
                            stepIsBot = true;
                            if (deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                            if (deskIsEmpty) takeCards(handBot, desk, trumpCard);
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
                            if (deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                            System.out.println(" Bot did't defence");
                        } else if (handBot.getCardList().size() == 0) {
                            if (!deskIsEmpty) {
                                System.out.println("YOU LOSS");
                                break;
                            }
                            stepIsBot = true;
                            if (deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                            if (deskIsEmpty) takeCards(handBot, desk, trumpCard);
                            System.out.println(" Bot defened. bot's turn");
                        }
                    } else {
                        stepIsBot = true;
                        if (!deskIsEmpty) {
                            System.out.println("YOU WIN");
                            break;
                        }
                        if (deskIsEmpty) takeCards(handPlayer, desk, trumpCard);
                        if (deskIsEmpty) takeCards(handBot, desk, trumpCard);
                        handTable.getCardList().clear();
                    }

            }
        }


    public static int correctNumber(Hand hand,Hand tableHand ){
        int correctNum = numberCard(hand.getCardList().size() , true);
        boolean b = false;
        while(true){
            if(correctNum == 0){
                return 0;
            }

            for (Card card: tableHand.getCardList()) {
                if(hand.getCardList().get(correctNum- 1).getRank().equals(card.getRank())){
                    b = true;
                    break;
                }
            }

            if(!b){
                System.out.println("You can not throw this card");
            }else {
                break;
            }
            correctNum = numberCard(hand.getCardList().size() , true);

        }
        return correctNum;
    }

    public static boolean PlayerDefence(Card attackCard, Hand handPlayer, Hand handTable , String color){
        int correctNum = numberCard(handPlayer.getCardList().size() , true);
        boolean b = false;
        while(true){
            if(correctNum == 0){
                return false;
            }
            Card defenceCard  = handPlayer.getCardList().get(correctNum- 1);

            if((defenceCard.getStrong() > attackCard.getStrong() && defenceCard.getColor().equals(attackCard.getColor())) || defenceCard.getColor().equals(color)){
                b = true;
                handPlayer.getCardList().remove(defenceCard);
                handTable.getCardList().add(defenceCard);
            }

            if(!b){
                System.out.println("You can not throw this card");
            }else {
                break;
            }
            correctNum = numberCard(handPlayer.getCardList().size() , true);

        }
        return true;
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

    public static void printBotHand(int size){
        for (int i = 0 ; i< size;i++){
            System.out.print("\uD83C\uDCA0  ");
        }
        System.out.println();
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

    public  static void takeCards(Hand hand,Desk desk, Card trumpCard){

        for(int i = hand.getCardList().size() ; i < 6 ; i++) {
            if(desk.getSize() == 0){
                hand.getCardList().add(trumpCard);
                deskIsEmpty = false;
                trumpCard = null;
                return;
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

    public static Card botAttack(Hand hand, Hand tableHand, String color){
        Card card = null;
        if(hand.getCardList().size() == 0){
            return null;
        }
        if(tableHand.getCardList().size() == 0){
            card = hand.getCardList().get(0);
            for (Card card1 : hand.getCardList()) {
                if(card.getStrong() > card1.getStrong()){
                    if(!card1.getColor().equals(color)){
                        card = card1;
                    }else if(card.getColor().equals(color)){
                        card = card1;
                    }
                }else if(card.getColor().equals(color) && !card1.getColor().equals(color)){
                    card = card1;
                }
            }
        }else{
            boolean b = false;
            for (Card cardTable : tableHand.getCardList()) {
                for (Card cardBot : hand.getCardList()) {
                    if(cardBot.getRank().equals(cardTable.getRank())){
                        card = cardBot;
                        b = true;
                        break;
                    }
                }
                if (b){
                    break;
                }
            }
        }
        if(card != null){
            tableHand.getCardList().add(card);
            hand.getCardList().remove(card);
        }
        return card;
    }


}
