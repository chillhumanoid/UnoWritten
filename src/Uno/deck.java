package Uno;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by jonathan thorne on 2/6/2017.
 */
public class deck {
    public ArrayList<Card> cards;
    public ArrayList<Card> drawPile;
    public deck(){
        cards = new ArrayList<>();
        cards.add(new Card(0,'r'));
        cards.add(new Card(1,'r'));
        cards.add(new Card(1,'r'));
        cards.add(new Card(2,'r'));
        cards.add(new Card(2,'r'));
        cards.add(new Card(3,'r'));
        cards.add(new Card(3,'r'));
        cards.add(new Card(4,'r'));
        cards.add(new Card(4,'r'));
        cards.add(new Card(5,'r'));
        cards.add(new Card(5,'r'));
        cards.add(new Card(6,'r'));
        cards.add(new Card(6,'r'));
        cards.add(new Card(7,'r'));
        cards.add(new Card(7,'r'));
        cards.add(new Card(8,'r'));
        cards.add(new Card(8,'r'));
        cards.add(new Card(9,'r'));
        cards.add(new Card(9,'r'));
        cards.add(new Card(10,'r'));
        cards.add(new Card(10,'r'));
        cards.add(new Card(11,'r'));
        cards.add(new Card(11,'r'));
        cards.add(new Card(12,'r'));
        cards.add(new Card(12,'r'));
        cards.add(new Card(13,'a'));
        cards.add(new Card(14,'a'));
        cards.add(new Card(0,'b'));
        cards.add(new Card(1,'b'));
        cards.add(new Card(1,'b'));
        cards.add(new Card(2,'b'));
        cards.add(new Card(2,'b'));
        cards.add(new Card(3,'b'));
        cards.add(new Card(3,'b'));
        cards.add(new Card(4,'b'));
        cards.add(new Card(4,'b'));
        cards.add(new Card(5,'b'));
        cards.add(new Card(5,'b'));
        cards.add(new Card(6,'b'));
        cards.add(new Card(6,'b'));
        cards.add(new Card(7,'b'));
        cards.add(new Card(7,'b'));
        cards.add(new Card(8,'b'));
        cards.add(new Card(8,'b'));
        cards.add(new Card(9,'b'));
        cards.add(new Card(9,'b'));
        cards.add(new Card(10,'b'));
        cards.add(new Card(10,'b'));
        cards.add(new Card(11,'b'));
        cards.add(new Card(11,'b'));
        cards.add(new Card(12,'b'));
        cards.add(new Card(12,'b'));
        cards.add(new Card(13,'a'));
        cards.add(new Card(14,'a'));
        cards.add(new Card(0,'g'));
        cards.add(new Card(1,'g'));
        cards.add(new Card(1,'g'));
        cards.add(new Card(2,'g'));
        cards.add(new Card(2,'g'));
        cards.add(new Card(3,'g'));
        cards.add(new Card(3,'g'));
        cards.add(new Card(4,'g'));
        cards.add(new Card(4,'g'));
        cards.add(new Card(5,'g'));
        cards.add(new Card(5,'g'));
        cards.add(new Card(6,'g'));
        cards.add(new Card(6,'g'));
        cards.add(new Card(7,'g'));
        cards.add(new Card(7,'g'));
        cards.add(new Card(8,'g'));
        cards.add(new Card(8,'g'));
        cards.add(new Card(9,'g'));
        cards.add(new Card(9,'g'));
        cards.add(new Card(10,'g'));
        cards.add(new Card(10,'g'));
        cards.add(new Card(11,'g'));
        cards.add(new Card(11,'g'));
        cards.add(new Card(12,'g'));
        cards.add(new Card(12,'g'));
        cards.add(new Card(13,'a'));
        cards.add(new Card(14,'a'));
        cards.add(new Card(0,'y'));
        cards.add(new Card(1,'y'));
        cards.add(new Card(1,'y'));
        cards.add(new Card(2,'y'));
        cards.add(new Card(2,'y'));
        cards.add(new Card(3,'y'));
        cards.add(new Card(3,'y'));
        cards.add(new Card(4,'y'));
        cards.add(new Card(4,'y'));
        cards.add(new Card(5,'y'));
        cards.add(new Card(5,'y'));
        cards.add(new Card(6,'y'));
        cards.add(new Card(6,'y'));
        cards.add(new Card(7,'y'));
        cards.add(new Card(7,'y'));
        cards.add(new Card(8,'y'));
        cards.add(new Card(8,'y'));
        cards.add(new Card(9,'y'));
        cards.add(new Card(9,'y'));
        cards.add(new Card(10,'y'));
        cards.add(new Card(10,'y'));
        cards.add(new Card(11,'y'));
        cards.add(new Card(11,'y'));
        cards.add(new Card(12,'y'));
        cards.add(new Card(12,'y'));
        cards.add(new Card(13,'a'));
        cards.add(new Card(14,'a'));
    }
    public void shuffleDeck(){
        Collections.shuffle(cards);
        Collections.shuffle(cards);
    }
    public Card getLast(){
        return cards.get(cards.size()-1);
    }
    public void addCard(Card addedCard){
        cards.add(addedCard);
    }
    public void printDeck(){
        for(int i = 0; i < cards.size(); i++){
            Card sd = new Card(cards.get(i).getCardNumber(),cards.get(i).getCardColor());
            System.out.println(sd.toString());
        }
    }
    public int getSize(){
        return cards.size();
    }
    public void removeLast(){
        cards.remove(cards.size()-1);
    }
}
