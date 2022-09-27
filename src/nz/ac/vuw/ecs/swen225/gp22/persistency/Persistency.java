package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.domain.Door.DoorC;
import nz.ac.vuw.ecs.swen225.gp22.domain.Key.KeyC;

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
        String[][] array = new String[ROWS][COLUMNS];
        for(int y = 0; y < ROWS; y++){
            Element row = rowsList.get(y);
            List<Element> tiles = row.getChildren("tile");
            for(int x = 0; x < COLUMNS; x++){
                String tileText = tiles.get(x).getText();
                if(tileText.contains("wall") || tileText.contains("floor") || tileText.contains("exit")){
                    newLevel.setTile(y, x, getTile(tileText, y, x));
                }
                else{
                    newLevel.setTile(y, x, new FloorTile(y, x));
                    newLevel.setObject(y, x, getSolidObject(tileText, y, x));
                }
            }
        }
        return newLevel;
    }

    public static void saveBoard(){}

    public static Tile getTile(String tile, int xPos, int yPos){
        Tile tileObject = null;
        switch(tile){
            case "wall":
                tileObject = new WallTile(xPos, yPos);
                break;
            case "floor":
                tileObject = new FloorTile(xPos, yPos);
                break;
            case "exit":
                tileObject = new Exit(xPos, yPos);
                break;
            default:
                System.out.println("Error Constructing Tile: " + tile + " at " + "X: " + xPos + " Y: " + yPos);
                break;
        }
        return tileObject;
    }
        
    public static SolidObject getSolidObject(String solidObj, int xPos, int yPos){
        SolidObject sObject = null;
        if(solidObj.contains("Key")){
            if(solidObj.contains("Yellow")){sObject = new Key(xPos, yPos, KeyC.YELLOW);}
            else if(solidObj.contains("Red")){sObject = new Key(xPos, yPos, KeyC.RED);}
            else if(solidObj.contains("Blue")){sObject = new Key(xPos, yPos, KeyC.BLUE);}
            else if(solidObj.contains("Green")){sObject = new Key(xPos, yPos, KeyC.GREEN);}
        }
        else if(solidObj.contains("Door")){
            if(solidObj.contains("Yellow")){sObject = new Door(xPos, yPos, DoorC.YELLOW);}
            else if(solidObj.contains("Red")){sObject = new Door(xPos, yPos, DoorC.RED);}
            else if(solidObj.contains("Blue")){sObject = new Door(xPos, yPos, DoorC.BLUE);}
            else if(solidObj.contains("Green")){sObject = new Door(xPos, yPos, DoorC.GREEN);}
        }
        else if(solidObj.contains("exitLock")){
            sObject = new ExitLock(xPos, yPos);
        }
        else if(solidObj.contains("computerChip")){
            sObject = new ComputerChip(xPos, yPos);
        }
        else{
            System.out.println("Error Constructing Solid object: " + solidObj + " at " + "X: " + xPos + " Y: " + yPos);
        }
        return sObject;
    }


}
