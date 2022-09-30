package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.jdom2.JDOMException;
import org.junit.Test;


public class PersistencyTests {
    @Test
    public void test1() throws JDOMException, IOException{
        Level level1 = Persistency.loadBoard("Level1.xml");
        String expected = 
          "|W|W|W|W|W|W|W|W|W|W|W|W|W|\n"+
          "|W|F|F|F|F|W|E|W|F|F|F|F|W|\n"+
          "|W|F|F|F|F|W|F|W|F|F|C|F|W|\n"+
          "|W|F|F|F|F|W|L|W|F|F|F|F|W|\n"+
          "|W|F|I|F|F|F|F|F|F|F|F|F|W|\n"+
          "|W|F|F|F|F|F|F|F|F|F|F|F|W|\n"+
          "|W|W|W|W|W|F|F|F|W|W|D|W|W|\n"+
          "|W|F|F|F|W|F|F|F|W|F|F|F|W|\n"+
          "|W|F|F|F|D|F|F|F|W|C|F|F|W|\n"+
          "|W|C|F|F|W|F|F|F|W|F|F|F|W|\n"+
          "|W|F|F|F|W|F|K|F|W|F|K|F|W|\n"+
          "|W|F|F|F|W|F|F|F|W|F|F|F|W|\n"+
          "|W|W|W|W|W|W|W|W|W|W|W|W|W|\n";
        check(level1, expected);
    }

    public static void check(Level l, String expected) {
        String actual = ""; 
        for(int i = 0; i < l.getTiles().length; i++){
            actual += "|";
            for(int j = 0; j < l.getTiles()[i].length; j++){
                if(l.getTile(i, j) != null && l.getObject(i, j) != null){
                    actual += getSolidObject(l.getObject(i, j)) + "|";  
                }
                else{
                    actual += getTileObject(l.getTile(i, j)) + "|";   
                }
            }
            actual += "\n";
        }
        assertEquals(expected, actual);
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
