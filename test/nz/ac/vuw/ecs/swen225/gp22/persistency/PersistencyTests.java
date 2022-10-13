package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.junit.Test;


public class PersistencyTests {
    public String levelsURL = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
    public String savedGamesURL = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/savedGames/";
       
    @Test    
    //Testing game loading with level1
        public void test1() throws JDOMException, IOException{
            try {
                Level level1 = Persistency.loadBoard("level1.xml", levelsURL);
                check(level1);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
           
        }
    @Test
    //Testing game saving
    public void test2() {
            try {
                Level level1 = Persistency.loadBoard("level1.xml", levelsURL);
                check(level1);
                Chap chap = new Chap(level1.getStartingX(), level1.getStartingY(), level1);
                Persistency.saveBoard(level1, "savedGame.xml", savedGamesURL, chap);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    }
    
 
    public static void check(Level l) {
        for(int i = 0; i < l.getTiles().length; i++){
            System.out.print("| ");
            for(int j = 0; j < l.getTiles()[i].length; j++){
                if(l.getTile(i, j) != null && l.getObject(i, j) != null){
                    System.out.print(getSolidObject(l.getObject(i, j)) + " | ");   
                }
                else{
                    System.out.print(getTileObject(l.getTile(i, j)) + " | ");   
                }
            }
            System.out.println();
        }
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
