package Uno;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

/**
 * Created by jonat on 2/16/2017.
 */
public class Users {
    private static  String userName;
    private static int normGamesPlayed;
    private static int normGamesWon;
    private static int handsPlayed;
    private static int unoCalled;
    private static int handsWon;
    private static int wildPlayed;
    private static int draw4Played;
    private static int draw2Played;
    private static int skipPlayed;
    private static int reversePlayed;
    private static int cardsDrawn;
    private static int cardsForcedDrawn;
    private static int errorsMade;



    Users(String uName){
        userName = uName;
    }
    public void newUser() throws IOException{
        normGamesWon = 0;
        normGamesPlayed = 0;
        handsPlayed = 0;
        wildPlayed = 0;
        unoCalled = 0;
        handsWon = 0;
        draw4Played = 0;
        draw2Played = 0;
        skipPlayed = 0;
        reversePlayed = 0;
        cardsDrawn = 0;
        cardsForcedDrawn = 0;
        errorsMade = 0;
        BufferedWriter writer = new BufferedWriter(new FileWriter("Users/" + userName + ".txt"));
        writer.write(Integer.toString(normGamesPlayed));
        writer.newLine();
        writer.write(Integer.toString(normGamesWon));
        writer.newLine();
        writer.write(Integer.toString(handsPlayed));
        writer.newLine();
        writer.write(Integer.toString(unoCalled));
        writer.newLine();
        writer.write(Integer.toString(handsWon));
        writer.newLine();
        writer.write(Integer.toString(draw4Played));
        writer.newLine();
        writer.write(Integer.toString(draw2Played));
        writer.newLine();
        writer.write(Integer.toString(wildPlayed));
        writer.newLine();
        writer.write(Integer.toString(reversePlayed));
        writer.newLine();
        writer.write(Integer.toString(skipPlayed));
        writer.newLine();
        writer.write(Integer.toString(cardsDrawn));
        writer.newLine();
        writer.write(Integer.toString(cardsForcedDrawn));
        writer.newLine();
        writer.write(Integer.toString(errorsMade));
        writer.close();
    }
    public void oldUser() throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader("Users/" + userName + ".txt"));
        String l1 = reader.readLine();
        normGamesPlayed = Integer.parseInt(l1);
        String l2 = reader.readLine();
        normGamesWon = Integer.parseInt(l2);
        String l3 = reader.readLine();
        handsPlayed = Integer.parseInt(l3);
        String lUno = reader.readLine();
        unoCalled = Integer.parseInt(lUno);
        String l4 = reader.readLine();
        handsWon = Integer.parseInt(l4);
        String l5 = reader.readLine();
        draw4Played = Integer.parseInt(l5);
        String l6 = reader.readLine();
        draw2Played = Integer.parseInt(l6);
        String l7 = reader.readLine();
        wildPlayed = Integer.parseInt(l7);
        String l8 = reader.readLine();
        reversePlayed = Integer.parseInt(l8);
        String l9 = reader.readLine();
        skipPlayed = Integer.parseInt(l9);
        String l10 = reader.readLine();
        cardsDrawn = Integer.parseInt(l10);
        String l11 = reader.readLine();
        cardsForcedDrawn = Integer.parseInt(l11);
        String l12 = reader.readLine();
        errorsMade = Integer.parseInt(l12);
        reader.close();
    }
    public void writeToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("Users/" + userName + ".txt"));
        writer.write(Integer.toString(normGamesPlayed));
        writer.newLine();
        writer.write(Integer.toString(normGamesWon));
        writer.newLine();
        writer.write(Integer.toString(handsPlayed));
        writer.newLine();
        writer.write(Integer.toString(unoCalled));
        writer.newLine();
        writer.write(Integer.toString(handsWon));
        writer.newLine();
        writer.write(Integer.toString(draw4Played)); //draw4
        writer.newLine();
        writer.write(Integer.toString(draw2Played)); //draw2
        writer.newLine();
        writer.write(Integer.toString(wildPlayed)); //wild
        writer.newLine();
        writer.write(Integer.toString(reversePlayed)); //reverse
        writer.newLine();
        writer.write(Integer.toString(skipPlayed)); //skip
        writer.newLine();
        writer.write(Integer.toString(cardsDrawn));
        writer.newLine();
        writer.write(Integer.toString(cardsForcedDrawn));
        writer.newLine();
        writer.write(Integer.toString(errorsMade));
        writer.close();
    }
    public String getUserName(){return userName;}
    public int getNormGamesPlayed(){return normGamesPlayed;}
    public int getNormGamesWon(){return normGamesWon;}
    public int getHandsPlayed(){return handsPlayed;}
    public int getUnoCalled(){return unoCalled;}
    public int getHandsWon(){return handsWon;}
    public int getWildPlayed(){return wildPlayed;}
    public int getDraw4Played(){return draw4Played;}
    public int getDraw2Played(){return draw2Played;}
    public int getSkipPlayed(){return skipPlayed;}
    public int getReversePlayed(){return reversePlayed;}
    public int getCardsDrawn(){return cardsDrawn;}
    public int getCardsForcedDrawn(){return cardsForcedDrawn;}
    public int getErrorsMade(){return errorsMade;}
    public void reset() throws IOException{
        normGamesPlayed = 0;
        normGamesWon = 0;
        handsPlayed = 0;
        unoCalled = 0;
        handsWon = 0;
        draw4Played = 0;
        draw2Played = 0;
        reversePlayed = 0;
        skipPlayed = 0;
        wildPlayed = 0;
        cardsDrawn = 0;
        cardsForcedDrawn = 0;
        errorsMade = 0;
        writeToFile();
    }
    public void addNormGamePlayed()throws IOException{normGamesPlayed++;writeToFile();}
    public void addNormGameWon()throws IOException{normGamesWon++;writeToFile();}
    public void addHandPlayed()throws IOException{handsPlayed++;writeToFile();}
    public void addUnoCalled()throws IOException{unoCalled++;writeToFile();}
    public void addHandsWon()throws IOException{handsWon++;writeToFile();}
    public void addDraw4Played()throws IOException{draw4Played++;writeToFile();}
    public void addDraw2Played()throws IOException{draw2Played++;writeToFile();}
    public void addWildPlayed()throws IOException{wildPlayed++;writeToFile();}
    public void addReversePlayed()throws IOException{reversePlayed++;writeToFile();}
    public void addSkipPlayed()throws IOException{skipPlayed++;writeToFile();}
    public void addCardsDrawn()throws IOException{cardsDrawn++;writeToFile();}
    public void addCardForcedDrawn()throws IOException{cardsForcedDrawn++;writeToFile();}
    public void addErrorsMade()throws IOException{errorsMade++;writeToFile();}
}
