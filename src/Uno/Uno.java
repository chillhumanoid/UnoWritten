package Uno;

import java.util.Random;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Uno {
    private static deal play1 = new deal();
    private static deal comp1 = new deal();
    private static deal comp2 = new deal();
    private static deal comp3 = new deal();
    private static deck deck = new deck();
    private static Scanner s = new Scanner(System.in);
    private static Scanner si = new Scanner(System.in);
    private static deal discardPile = new deal();
    private static boolean reverse = false;
    private static boolean fullGame = false;
    private static int numComp = 0;
    private static int play1Score = 0;
    private static int comp1Score = 0;
    private static int comp2Score = 0;
    private static int comp3Score = 0;
    private static String play1Name = null;
    private static String comp1Name = null;
    private static String comp2Name = null;
    private static String comp3Name = null;
    private static String[] compNames = new String[]{"Billy", "Charles","Jillian","Frank","Michelle","Angela","Rachel","Phil"};

    public static void main(String[] args) throws InterruptedException, IOException {
        menu();
    }
    private static void play() throws InterruptedException, IOException{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        deck.shuffleDeck();
        int currentPlayer = 1;
        boolean skip = false;
        boolean draw2 = false;
        boolean draw4 = false;
        boolean uno = false;
        if (numComp == 1) {
            for (int i = 0; i < 7; i++) {
                play1.addCard(deck);
                comp1.addCard(deck);
            }
        } else if (numComp == 2) {
            for (int i = 0; i < 7; i++) {
                play1.addCard(deck);
                comp1.addCard(deck);
                comp2.addCard(deck);
            }
        } else if (numComp == 3) {
            for (int i = 0; i < 7; i++) {
                play1.addCard(deck);
                comp1.addCard(deck);
                comp2.addCard(deck);
                comp3.addCard(deck);
            }
        }
        discardPile.addCard(deck);
        if (Card.getCardNumber(discardPile.getLast()) == 14)
            discardPile.addCard(wildColor(14));
        else if (Card.getCardNumber(discardPile.getLast()) == 13)
            discardPile.addCard(wildColor(13));
        do {
            while (currentPlayer == 1) {
                skip = false;
                draw2 = false;
                draw4 = false;
                sleep(1000);
                boolean unoCalled = false;
                int cardPlayed = 0;
                System.out.println();
                int choice = 0;
                boolean drawCard = false;
                do {
                    uno = checkUno(play1);
                    System.out.println();
                    printHand(play1, drawCard, unoCalled);
                    System.out.println();
                    printDiscard();
                    choice = getCardNumber();
                    while (choice > play1.getSize() + 2 && choice < 5496 || choice < 0) {
                        System.out.println("Invalid Input");
                        choice = getCardNumber();
                    }
                    int elem = choice - 1;
                    if (choice == 5496) {
                        System.exit(0);
                    } else if (choice == (play1.getSize() + 1)) {
                        if (!drawCard) {
                            play1.addCard(deck);
                            drawCard = true;
                            cardPlayed = 0;
                        } else if (drawCard)
                            cardPlayed = 1;
                    } else if (choice == (play1.getSize() + 2)) {
                        if (!unoCalled) {
                            System.out.println();
                            System.out.println(play1Name + " Calls Uno");
                            unoCalled = true;
                        } else if (unoCalled || !uno) {
                            System.out.println();
                            System.out.println("Please select a valid card");
                        }
                    } else if (Card.getCardColor(play1.getCard(elem)) == 'a' && Card.getCardNumber(play1.getCard(elem)) == 13) {
                        discardPile.addCard(wildColor(13));
                        play1.removeCard(elem);
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(elem)) == 'a' && Card.getCardNumber(play1.getCard(elem)) == 14) {
                        discardPile.addCard(wildColor(14));
                        play1.removeCard(elem);
                        draw4 = true;
                        cardPlayed = 1;
                    } else if (Card.getCardColor(play1.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(play1.getCard(elem)) == Card.getCardNumber(discardPile.getLast())) {
                        discardPile.addCard(play1.getCard(elem));
                        if (Card.getCardNumber(play1.getCard(elem)) == 10)
                            skip = true;
                        else if (Card.getCardNumber(play1.getCard(elem)) == 11)
                            draw2 = true;
                        else if (Card.getCardNumber(play1.getCard(elem)) == 12) {
                            if (reverse)
                                reverse = false;
                            else if (!reverse)
                                reverse = true;
                        }
                        play1.removeCard(elem);
                        cardPlayed = 1;
                    }
                } while (cardPlayed == 0);
                if (play1.getSize() == 1 && !unoCalled) {
                    System.out.println(play1Name +  " did not call uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
                if (play1.getSize() > 1 && unoCalled) {
                    System.out.println(play1Name + " falsely called uno. +2");
                    play1.addCard(deck);
                    play1.addCard(deck);
                }
                if (play1.getSize() == 0) {
                    if(fullGame){
                        System.out.println();
                        System.out.println(play1Name + " won the round");
                        scoring(1);
                    }else if(!fullGame) {
                        System.out.println();
                        System.out.println(play1Name + " won");
                        Thread.sleep(2000);
                        hardReset();
                        menu();
                    }
                }
                currentPlayer = nextPlayer(currentPlayer, numComp, reverse, skip, draw2, draw4);
                checkDraw(deck, discardPile);
                System.out.println();
                break;
            }
            while (currentPlayer == 2) {
                int compNumber = 1;
                currentPlayer = computerPlay(comp1, skip, draw2, draw4, uno, compNumber, currentPlayer, numComp);
                checkDraw(deck, discardPile);
                break;
            }
            while (currentPlayer == 3) {
                int compNumber = 2;
                currentPlayer = computerPlay(comp2, skip, draw2, draw4, uno, compNumber, currentPlayer, numComp);
                checkDraw(deck, discardPile);
                break;
            }
            while (currentPlayer == 4) {
                int compNumber = 3;
                currentPlayer = computerPlay(comp3, skip, draw2, draw4, uno, compNumber, currentPlayer, numComp);
                checkDraw(deck, discardPile);
                break;
            }
        } while (true);
    }
    private static void printHandDebug(deal play) {
        int display = 0;
        for (int x = 0; x < play.getSize(); x++) {
            display = x + 1;
            System.out.println(display + ". " + play.getCard(x));
        }
    }
    private static void getNames() throws IOException, InterruptedException{
        Random rn = new Random();
        int range = 7 - 0 + 1;
        int name =  0;
        int temp = 0;
        int temp2 = 0;
        int temp3 = 0;
        System.out.println();
        System.out.println("Please your player name: ");
        play1Name = s.nextLine();
        while(temp == name){
            temp = rn.nextInt(range);
        }
        name = temp;
        comp1Name = compNames[name];
        while(temp2 == name || temp2 == temp){
            temp2 =  rn.nextInt(range);
        }
        name = temp2;
        comp2Name = compNames[name];
        while(temp3 == name || temp3 == temp || temp3 == temp2){
            temp3 =  rn.nextInt(range);
        }
        name = temp3;
        comp3Name = compNames[name];
        play();
    }
    private static void printHand(deal play1, boolean drawCard, boolean unoCalled) {
        int display = 0;
        for (int x = 0; x < play1.getSize(); x++) {
            display = x + 1;
            System.out.println(display + ". " + play1.getCard(x));
        }
        display++;
        if (!drawCard)
            System.out.println(display + ". Draw Card");
        else if (drawCard)
            System.out.println(display + ". End Turn");
        display++;
        if (!unoCalled)
            System.out.println(display + ". Call Uno");
        else if (unoCalled)
            System.out.println("Uno Called");
    }

    private static void printDiscard() {
        System.out.println("Discard Pile: " + discardPile.getLast());
    }

    private static boolean checkUno(deal play) {
        boolean uno = false;
        if (play.getSize() == 2)
            uno = true;
        else
            uno = false;
        return uno;
    }

    private static Card wildColor(int cardNumber) {
        System.out.println("What color do you want the deck to be?: ");
        String input = s.nextLine();
        char color = 'a';
        input = input.toLowerCase();
        if (input.charAt(0) == 'b')
            color = 'b';
        else if (input.charAt(0) == 'r')
            color = 'r';
        else if (input.charAt(0) == 'g')
            color = 'g';
        else if (input.charAt(0) == 'y')
            color = 'y';
        else {
            System.out.println("Type: (b)lue, (g)reen, (r)ed, (y)ellow");
            wildColor(cardNumber);
        }
        return new Card(cardNumber, color);
    }

    private static void checkDraw(deck deck, deal discardPile) {
        if (deck.getSize() <= 4) {
            System.out.println();
            System.out.println("Shuffling Deck");
            System.out.println();
            do {
                if (Card.getCardNumber(discardPile.getLast()) == 13 && Card.getCardColor(discardPile.getLast()) != 'a') {
                    discardPile.removeCard(discardPile.getSize() - 1);
                    deck.addCard(new Card(13, 'a'));
                } else if (Card.getCardNumber(discardPile.getLast()) == 14 && Card.getCardColor(discardPile.getLast()) != 'a') {
                    discardPile.removeCard(discardPile.getSize() - 1);
                    deck.addCard(new Card(14, 'a'));
                } else {
                    deck.addCard(discardPile.getLast());
                    discardPile.removeCard(discardPile.getSize() - 1);
                }
            } while (discardPile.getSize() > 0);
            deck.shuffleDeck();
            discardPile.addCard(deck);
        }
    }

    private static void menu() throws IOException, InterruptedException {
        int choice = 0;
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        try {
            System.out.println("Welcome to Uno v1.1.1.2");
            System.out.println("1. Play");
            System.out.println("2. Exit");
            choice = si.nextInt();
            si.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            si.nextLine();
        }
        if (choice == 1) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            try {
                System.out.println("Game Modes");
                System.out.println("1. Normal(Score based, multiple games)");
                System.out.println("2. Quick(one game, no scores)");
                System.out.println("3. Back");
                choice = si.nextInt();
                si.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                si.nextLine();
            }
            if (choice == 1) {
                fullGame = true;
                compNum();
            } else if (choice == 2) {
                fullGame = false;
                compNum();
            } else if (choice == 3) {
                menu();
            } else if (choice == 2) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }
    private static void compNum() throws InterruptedException, IOException{
        String[] args = {};
        try {
            System.out.print("How many computers do you want to play against?(1-3): ");
            numComp = si.nextInt();
            si.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input");
            si.nextLine();
        }
        if (numComp <= 3 && numComp >= 1) {
            getNames();
        } else {
            System.out.println("Please enter a number between 1 and 3");
            System.out.println();
            compNum();
        }
    }

    private static int getComputerChoice(Card dCard, deal Computer, boolean unoCalled) {
        int choice;
        boolean hWild, hSkip, hReverse, hDTwo, hDFour, hPlayable;
        hWild = hasWild(Computer);
        hSkip = hasSkip(dCard, Computer);
        hReverse = hasReverse(dCard, Computer);
        hDTwo = hasDrawTwo(dCard, Computer);
        hDFour = hasDrawFour(Computer);
        hPlayable = hasPlayable(dCard, Computer);
        if (Computer.getSize() == 2 && !unoCalled) {
            choice = Computer.getSize() + 2;
        } else if (hDTwo) {
            choice = findDTwo(dCard, Computer);
            choice++;
        } else if (hSkip) {
            choice = findSkip(dCard, Computer);
            choice++;
        } else if (hReverse) {
            choice = findReverse(dCard, Computer);
            choice++;
        } else if (hPlayable) {
            choice = findPlayable(dCard, Computer);
            choice++;
        } else if (hWild) {
            choice = findWild(Computer);
            choice++;
        } else if (hDFour) {
            choice = findDrawFour(Computer);
            choice++;
        } else {
            choice = Computer.getSize() + 1;
        }
        return choice;
    }

    private static int findWild(deal Computer) {
        int elem = 0;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 13)
                elem = i;
        }
        return elem;
    }

    private static int findDTwo(Card dCard, deal Computer) {
        int elem = 0;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 11 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }

    private static int findSkip(Card dCard, deal Computer) {
        int elem = 0;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 10 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }

    private static int findReverse(Card dCard, deal Computer) {
        int elem = 0;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 12 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }

    private static int findDrawFour(deal Computer) {
        int elem = 0;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 14)
                elem = i;
        }
        return elem;
    }

    private static int findPlayable(Card dCard, deal Computer) {
        int elem = 0;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) < 10 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                elem = i;
        }
        return elem;
    }

    private static boolean hasWild(deal Computer) {
        boolean hWild = false;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 13)
                hWild = true;
        }
        return hWild;
    }

    private static boolean hasSkip(Card dCard, deal Computer) {
        boolean hSkip = false;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 10 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                hSkip = true;
        }
        return hSkip;
    }

    private static boolean hasReverse(Card dCard, deal Computer) {
        boolean hReverse = false;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 12 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                hReverse = true;
        }
        return hReverse;
    }

    private static boolean hasDrawTwo(Card dCard, deal Computer) {
        boolean hDTwo = false;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 11 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                hDTwo = true;
        }
        return hDTwo;
    }

    private static boolean hasDrawFour(deal Computer) {
        boolean hDFour = false;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) == 14)
                hDFour = true;
        }
        return hDFour;
    }

    private static boolean hasPlayable(Card dCard, deal Computer) {
        boolean hPlayable = false;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardNumber(Computer.getCard(i)) < 10 && Card.getCardColor(Computer.getCard(i)) == Card.getCardColor(dCard))
                hPlayable = true;
        }
        return hPlayable;
    }

    private static Card wildComputerColor(int cardNumber, deal Computer) {
        int blue = 0;
        int red = 0;
        int yellow = 0;
        int green = 0;
        char cColor;
        for (int i = 0; i < Computer.getSize(); i++) {
            if (Card.getCardColor(Computer.getCard(i)) == 'b')
                blue++;
            else if (Card.getCardColor(Computer.getCard(i)) == 'r')
                red++;
            else if (Card.getCardColor(Computer.getCard(i)) == 'y')
                yellow++;
            else if (Card.getCardColor(Computer.getCard(i)) == 'g')
                green++;
        }
        if (blue > green && blue > yellow && blue > red)
            cColor = 'b';
        else if (green > blue && green > yellow && green > red)
            cColor = 'g';
        else if (yellow > green && yellow > blue && yellow > red)
            cColor = 'y';
        else if (red > yellow && red > green && red > blue)
            cColor = 'r';
        else
            cColor = 'r';
        return new Card(cardNumber, cColor);
    }

    private static int computerPlay(deal comp, boolean skip, boolean draw2, boolean draw4, boolean uno, int compNumber, int currentPlayer, int numComp) throws InterruptedException, IOException {
        skip = false;
        draw2 = false;
        draw4 = false;
        String name = null;
        int choice;
        int cardPlayed = 0;
        boolean drawCard = false;
        boolean unoCalled = false;
        if(compNumber == 1)
            name = comp1Name;
        else if(compNumber == 2)
            name = comp2Name;
        else if(compNumber == 3)
            name = comp3Name;
        do {
            sleep(2000);
            uno = checkUno(comp);
            Card dCard = discardPile.getLast();
            choice = getComputerChoice(dCard, comp, unoCalled);
            int elem = choice - 1;
            if (choice == (comp.getSize()) + 1) {
                if (!drawCard) {
                    System.out.println(name + " has drawn a card");
                    comp.addCard(deck);
                    drawCard = true;
                } else {
                    System.out.println(name + " has ended their turn without playing a card");
                    cardPlayed = 1;
                }
            } else if (choice == comp.getSize() + 2) {
                if (!unoCalled) {
                    System.out.println(name + " Calls Uno");
                    unoCalled = true;
                }
            } else if (Card.getCardNumber(comp.getCard(elem)) == 13) {
                discardPile.addCard(wildComputerColor(13, comp));
                System.out.println(name + " played " + discardPile.getLast());
                comp.removeCard(elem);
                cardPlayed = 1;
            } else if (Card.getCardNumber(comp.getCard(elem)) == 14) {
                discardPile.addCard(wildComputerColor(14, comp));
                System.out.println(name + " played " + discardPile.getLast());
                draw4 = true;
                comp.removeCard(elem);
                cardPlayed = 1;
            } else if (Card.getCardColor(comp.getCard(elem)) == Card.getCardColor(discardPile.getLast()) || Card.getCardNumber(comp.getCard(elem)) == Card.getCardNumber(discardPile.getLast())) {
                int cardNumber = Card.getCardNumber(comp.getCard(elem));
                discardPile.addCard(comp.getCard(elem));
                if (cardNumber == 10)
                    skip = true;
                else if (cardNumber == 11)
                    draw2 = true;
                else if (cardNumber == 12) {
                    if (reverse)
                        reverse = false;
                    else
                        reverse = true;
                }
                System.out.println(name + " played " + discardPile.getLast());
                comp.removeCard(elem);
                cardPlayed = 1;
            }
        } while (cardPlayed == 0);
        if (comp.getSize() == 1 && !unoCalled) {
            System.out.println(name + " did not call uno. + 2");
        } else if (comp.getSize() == 0) {
            if(fullGame){
                System.out.println();
                System.out.println(name + " won the round!");
                int cWon = compNumber++;
                scoring(cWon);
            }else if(!fullGame){
                System.out.println();
                System.out.println(name + " won!");
                Thread.sleep(2000);
                hardReset();
                menu();
            }
        }
        int cPlayer = nextPlayer(currentPlayer, numComp, reverse, skip, draw2, draw4);
        return cPlayer;
    }
    private static void scoring(int won) throws InterruptedException, IOException{
        if(won == 1){
            addPlay1(comp1);
            if(numComp == 2) {
                addPlay1(comp2);
            }else if(numComp == 3) {
                addPlay1(comp2);
                addPlay1(comp3);
            }
        }else if(won == 2){
            addComp1(play1);
            if(numComp == 2){
                addComp1(comp2);
            }else if(numComp == 3) {
                addComp1(comp2);
                addComp1(comp3);
            }
        }else if(won == 3){
            addComp2(play1);
            addComp2(comp1);
            if(numComp == 3)
                addComp2(comp3);
        }else if(won == 4){
            addComp3(play1);
            addComp3(comp1);
            addComp3(comp2);
        }
        if(play1Score >= 500){
            System.out.println();
            System.out.println(play1Name + " has won!");
            Thread.sleep(2000);
            hardReset();
            menu();
        }
        if(comp1Score >= 500){
            System.out.println();
            System.out.println(comp1Name + " has won!");
            Thread.sleep(2000);
            hardReset();
            menu();
        }
        if(comp2Score >= 500){
            System.out.println();
            System.out.println(comp2Name + " has won!");
            Thread.sleep(2000);
            hardReset();
            menu();
        }
        if(comp3Score >= 500){
            System.out.println();
            System.out.println(comp3Name + " has won!");
            Thread.sleep(2000);
            hardReset();
            menu();
        }
        System.out.println();
        System.out.println("Scores");
        System.out.println(play1Name + ": " + play1Score);
        System.out.println(comp1Name + ": " + comp1Score);
        if(numComp == 2){
            System.out.println(comp2Name + ": " + comp2Score);
        }else if(numComp == 3){
            System.out.println(comp2Name + ": " + comp2Score);
            System.out.println(comp3Name + ": " + comp3Score);
        }
        Thread.sleep(3000);
        softReset();
    }
    private static void addPlay1(deal play){
        for(int i = 0; i < play.getSize();i++){
            int cardNumber = Card.getCardNumber(play.getCard(i));
            if(cardNumber <= 9){
                play1Score = play1Score + cardNumber;
            }else if(cardNumber == 10){
                play1Score = play1Score + 20;
            }else if(cardNumber == 11){
                play1Score = play1Score + 20;
            }else if(cardNumber == 12){
                play1Score = play1Score + 20;
            }else if(cardNumber == 13){
                play1Score = play1Score + 50;
            }else if(cardNumber == 14){
                play1Score = play1Score + 50;
            }
        }
    }
    private static void addComp1(deal play){
        for(int i = 0; i < play.getSize();i++){
            int cardNumber = Card.getCardNumber(play.getCard(i));
            if(cardNumber <= 9){
                comp1Score = comp1Score + cardNumber;
            }else if(cardNumber == 10){
                comp1Score = comp1Score + 20;
            }else if(cardNumber == 11){
                comp1Score = comp1Score + 20;
            }else if(cardNumber == 12){
                comp1Score = comp1Score + 20;
            }else if(cardNumber == 13){
                comp1Score = comp1Score + 50;
            }else if(cardNumber == 14){
                comp1Score = comp1Score + 50;
            }
        }
    }
    private static void addComp2(deal play){
        for(int i = 0; i < play.getSize();i++){
            int cardNumber = Card.getCardNumber(play.getCard(i));
            if(cardNumber <= 9){
                comp2Score = comp2Score + cardNumber;
            }else if(cardNumber == 10){
                comp2Score = comp2Score + 20;
            }else if(cardNumber == 11){
                comp2Score = comp2Score + 20;
            }else if(cardNumber == 12){
                comp2Score = comp2Score + 20;
            }else if(cardNumber == 13){
                comp2Score = comp2Score + 50;
            }else if(cardNumber == 14){
                comp2Score = comp2Score + 50;
            }
        }
    }
    private static void addComp3(deal play){
        for(int i = 0; i < play.getSize();i++){
            int cardNumber = Card.getCardNumber(play.getCard(i));
            if(cardNumber <= 9){
                comp3Score = comp3Score + cardNumber;
            }else if(cardNumber == 10){
                comp3Score = comp3Score + 20;
            }else if(cardNumber == 11){
                comp3Score = comp3Score + 20;
            }else if(cardNumber == 12){
                comp3Score = comp3Score + 20;
            }else if(cardNumber == 13){
                comp3Score = comp3Score + 50;
            }else if(cardNumber == 14){
                comp3Score = comp3Score + 50;
            }
        }
    }
    private static void dTwo(int currentPlayer) {
        if (currentPlayer == 1) {
            for (int i = 0; i < 2; i++)
                play1.addCard(deck);
        } else if (currentPlayer == 2) {
            for (int i = 0; i < 2; i++)
                comp1.addCard(deck);
        } else if (currentPlayer == 3) {
            for (int i = 0; i < 2; i++)
                comp2.addCard(deck);
        } else if (currentPlayer == 4) {
            for (int i = 0; i < 2; i++)
                comp3.addCard(deck);
        }
    }

    private static void dFour(int currentPlayer) {
        if (currentPlayer == 1) {
            for (int i = 0; i < 4; i++)
                play1.addCard(deck);
        } else if (currentPlayer == 2) {
            for (int i = 0; i < 4; i++)
                comp1.addCard(deck);
        } else if (currentPlayer == 3) {
            for (int i = 0; i < 4; i++)
                comp2.addCard(deck);
        } else if (currentPlayer == 4) {
            for (int i = 0; i < 4; i++)
                comp3.addCard(deck);
        }
    }

    private static int nextPlayer(int currentPlayer, int numComp, boolean reverse, boolean skip, boolean draw2, boolean draw4) {
        if (numComp == 1) {
            if (reverse && skip || !reverse && skip) {

            } else {
                if (currentPlayer == 1)
                    currentPlayer = 2;
                else if (currentPlayer == 2)
                    currentPlayer = 1;
            }
        } else if (numComp == 2) {
            int temp = currentPlayer;
            if (reverse && skip) {
                System.out.println(temp);
                temp = temp - 1;
                if (temp <= 0)
                    temp = 3;
                currentPlayer = temp;
            } else if (!reverse & skip) {
                temp++;
                if (temp > 3)
                    temp = 1;
                temp++;
                if (temp > 3)
                    temp = 1;
                currentPlayer = temp;
            } else if (reverse) {
                temp--;
                if (temp <= 0)
                    temp = 3;
                currentPlayer = temp;
            } else {
                temp++;
                if (temp > 3)
                    temp = 1;
                currentPlayer = temp;
            }
        } else if (numComp == 3) {
            int temp = currentPlayer;
            if (reverse && skip || !reverse && skip) {
                temp++;
                if (temp > 4)
                    temp = 1;
                temp++;
                if (temp > 4)
                    temp = 1;
                currentPlayer = temp;
            } else if (!reverse) {
                temp++;
                if (temp > 4)
                    temp = 1;
                currentPlayer = temp;
            } else {
                temp--;
                if (temp <= 0)
                    temp = 4;
                currentPlayer = temp;
            }
        }
        if (draw2) {
            dTwo(currentPlayer);
        } else if (draw4) {
            dFour(currentPlayer);
        }
        return currentPlayer;
    }

    private static int getCardNumber() {
        int choice = 0;
        do {
            try {
                System.out.print("Which card do you want to play: ");
                choice = si.nextInt();
                si.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input");
                si.nextLine();
            }
        } while (choice == 0);
        return choice;
    }

    private static void softReset() throws InterruptedException, IOException{
        play1 = new deal();
        comp1 = new deal();
        comp2 = new deal();
        comp3 = new deal();
        deck = new deck();
        discardPile = new deal();
        reverse = false;
        play();
    }
    private static void hardReset() throws InterruptedException, IOException{
        play1 = new deal();
        comp1 = new deal();
        comp2 = new deal();
        comp3 = new deal();
        deck = new deck();
        discardPile = new deal();
        reverse = false;
        fullGame = false;
        numComp = 0;
        play1Score = 0;
        comp1Score = 0;
        comp2Score = 0;
        comp3Score = 0;
        play1Name = null;
        comp1Name = null;
        comp2Name = null;
        comp3Name = null;
    }
}