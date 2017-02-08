package Uno;
import java.util.ArrayList;
/**
 * Created by jonathan thorne on 2/6/2017.
 */
public class deal {
    private ArrayList<Card> hand;
    public deal(){
        hand = new ArrayList<>();
    }
    public void addCard(deck deck){
        hand.add(deck.getLast());
        deck.removeLast();
    }
    public void addCard(Card addCard){
        hand.add(addCard);
    }
    public void removeCard(int elem){
        hand.remove(elem);
    }
    public Card getCard(int elem){
        return hand.get(elem);
    }
    public int getSize(){
        return hand.size();
    }
    public void printArray(){
        System.out.println(hand.toString());
    }
    public Card getLast(){
        return hand.get(hand.size()-1);
    }
}
