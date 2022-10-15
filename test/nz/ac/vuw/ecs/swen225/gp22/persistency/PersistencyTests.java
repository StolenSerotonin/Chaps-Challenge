package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PersistencyTests {
    public String levelsURL = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
    public String savedGamesURL = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/savedGames/";

    @Test
    //Testing game loading with level1
    public void test1() throws JDOMException, IOException{
        String expected =
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | W | W | W | W | F | W | W | W | W | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | F | F | W | W | W | F | F | F | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | C | F | W | E | W | F | C | F | W | F | F | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | D | W | L | W | D | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | K | F | D | F | F | F | F | F | D | F | K | F | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | I | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | C | F | F | F | C | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | F | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | F | F | F | D | F | F | F | F | F | D | F | F | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | W | D | W | D | W | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | F | W | F | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | C | W | C | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | K | F | W | K | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | W | W | W | W | W | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n";
        String actual = "";
        try {
            Level level1 = Persistency.loadBoard("level1.xml", levelsURL);
            actual = makeStringBoard(level1);
            assertEquals("Actual matches expected",expected, actual);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    //Testing game saving
    public void test2() {
        String expected =
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | W | W | W | W | F | W | W | W | W | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | F | F | W | W | W | F | F | F | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | C | F | W | E | W | F | C | F | W | F | F | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | D | W | L | W | D | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | K | F | D | F | F | F | F | F | D | F | K | F | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | I | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | C | F | F | F | C | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | F | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | F | F | F | D | F | F | F | F | F | D | F | F | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | W | D | W | D | W | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | F | W | F | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | C | W | C | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | K | F | W | K | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | W | W | W | W | W | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n";
        String actual = "";
        try {
            Level level1 = Persistency.loadBoard("level1.xml", levelsURL);
            Chap chap = new Chap(level1.getStartingX(), level1.getStartingY(), level1);
            Persistency.saveBoard(level1, "savedGame.xml", savedGamesURL, chap);
            Level savedGame = Persistency.loadBoard("savedGame.xml", savedGamesURL);
            actual = makeStringBoard(savedGame);
            assertEquals("Actual matches expected",actual, expected);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    //Testing game loading with level1
    public void test3() throws JDOMException, IOException{
        String expected =
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | W | W | W | W | F | W | W | W | W | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | F | F | W | W | W | F | F | F | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | C | F | W | E | W | F | C | F | W | F | F | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | D | W | L | W | D | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | K | F | D | F | F | F | F | F | D | F | K | F | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | I | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | C | F | F | F | C | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | F | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | F | F | F | D | F | F | F | F | F | D | F | F | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | W | D | W | D | W | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | F | W | F | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | C | W | C | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | K | F | W | K | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | W | W | W | W | W | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n";
        String actual = "";
        try {
            Level level1 = Persistency.loadBoard("level1.xml", levelsURL);
            actual = makeStringBoard(level1);
            assertEquals("Actual matches expected",expected, actual);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    //Testing game saving
    public void test4() {
        String expected =
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | W | W | W | W | F | W | W | W | W | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | F | F | W | W | W | F | F | F | W | F | F | F | F | F | \n" +
        "| F | F | F | F | F | W | F | C | F | W | E | W | F | C | F | W | F | F | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | D | W | L | W | D | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | K | F | D | F | F | F | F | F | D | F | K | F | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | I | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | C | F | F | F | C | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | W | F | C | F | W | K | F | F | F | K | W | F | C | F | W | F | F | F | \n" +
        "| F | F | F | W | F | F | F | D | F | F | F | F | F | D | F | F | F | W | F | F | F | \n" +
        "| F | F | F | W | W | W | W | W | W | D | W | D | W | W | W | W | W | W | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | F | W | F | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | F | C | W | C | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | K | F | W | K | F | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | W | W | W | W | W | W | W | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n" +
        "| F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | F | \n";
        String actual = "";
        try {
            Level level1 = Persistency.loadBoard("level1.xml", levelsURL);
            Chap chap = new Chap(level1.getStartingX(), level1.getStartingY(), level1);
            Persistency.saveBoard(level1, "savedGame.xml", savedGamesURL, chap);
            Level savedGame = Persistency.loadBoard("savedGame.xml", savedGamesURL);
            actual = makeStringBoard(savedGame);
            assertEquals("Actual matches expected",actual, expected);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String makeStringBoard(Level l) {
        String board = "";
        for(int i = 0; i < l.getTiles().length; i++){
            board += "| ";
            for(int j = 0; j < l.getTiles()[i].length; j++){
                if(l.getTile(i, j) != null && l.getObject(i, j) != null){
                    board += getSolidObject(l.getObject(i, j)) + " | ";
                }
                else{
                    board += getTileObject(l.getTile(i, j)) + " | ";
                }
            }
            board += "\n";
        }
        System.out.println(board);
        return board;
    }
    public static String getTileObject(Tile tO) {
        String tileObjectStr = "";
        if(tO instanceof WallTile){
            tileObjectStr = "W";
        }
        else if(tO instanceof FloorTile){
            tileObjectStr = "F";
        }
        else if(tO instanceof Exit){
            tileObjectStr =  "E";
        }
        else if(tO instanceof InfoTile){
            tileObjectStr =  "I";
        }
        else if(tO instanceof WaterTile){
            tileObjectStr =  "A";
        }
        return tileObjectStr;
    }
    public static String getSolidObject(SolidObject sO) {
        String solidObjectStr = "";
        if(sO instanceof Key){
            solidObjectStr = "K";
        }
        else if(sO instanceof ExitLock){
            solidObjectStr = "L";
        }
        else if(sO instanceof Door){
            solidObjectStr =  "D";
        }
        else if(sO instanceof ComputerChip){
            solidObjectStr = "C";
        }
        return solidObjectStr;
    }

}
