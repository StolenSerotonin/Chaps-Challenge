package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.FloorTile;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.ElementTraversal;

public class Persistency {

    private static int ROWS = 20;
    private static int COLUMNS = 21;

    //DELETE MAIN METHOD - Only for testing
    public static void main(String[] args) throws JDOMException, IOException {
        Persistency.loadBoard("level1.xml");
    }

    /*
    *  Takes in a file and generates a new level
    */
    public static Level loadBoard(String file) throws JDOMException, IOException{
        //Stores the location of all levels 
        String levelDirectory = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
        
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File(levelDirectory + file));
        Element rootElement = doc.getRootElement();
        
        Level newLevel = new Level(ROWS,COLUMNS,10,9,1);
        //Storing all the rows within the level in a list
        List<Element> rowsList = rootElement.getChildren();
        
        //Iterating through all the rows
        //TODO "Iterate through each each tile per row, check what each tile is .contains (check colour aswell),then make apporitate object for the position in array in newBoard
        String[][] array = new String[ROWS][COLUMNS];
        for(int y = 0; y < ROWS; y++){
            Element row = rowsList.get(y);
            List<Element> tiles = row.getChildren("tile");
            for(int x = 0; x < COLUMNS; x++){
                String tileText = tiles.get(x).getText();
                array[y][x] = tileText;
            }
        }
        return newLevel;
    }

    public static void saveBoard(){
        
    }



}
