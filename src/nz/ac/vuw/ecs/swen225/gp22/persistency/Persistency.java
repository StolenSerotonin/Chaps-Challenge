package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.w3c.dom.ElementTraversal;

public class Persistency {

    private static int ROWS = 13;
    private static int COLUMNS = 13;

    /*
    *  Takes in a file and generates a new level
    */
    public static Level loadBoard(String file) throws JDOMException, IOException{
        //Stores the location of all levels 
        String levelDirectory = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
        
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File(levelDirectory + file));
        Element rootElement = doc.getRootElement();
        
        Level newLevel = new Level(COLUMNS,ROWS,3,3,1);
        //Storing all the rows within the level in a list
        List<Element> rowsList = rootElement.getChildren();
        
        //Iterating through all the rows
        for(int y = 0; y < ROWS; y++){
            Element row = rowsList.get(y);
            List<Element> tiles = row.getChildren("tile");
            for(int x = 0; x < COLUMNS; x++){
                String tileText = tiles.get(x).getText();
                if(tileText.contains("wall") || tileText.contains("floor") || 
                tileText.contains("infoField") || (tileText.contains("exit") &&
                !tileText.contains("exitLock"))){
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
    //TODO Save to recorder directory
    public static void saveBoard(Level l){}

    public static Tile getTile(String tile, int yPos, int xPos){
        Tile tileObject = null;
        switch(tile){
            case "wall":
                tileObject = new WallTile(yPos, xPos);
                break;
            case "floor":
                tileObject = new FloorTile(yPos, xPos);
                break;
            case "exit":
                tileObject = new Exit(yPos, xPos);
                break;
            case "infoField":
                tileObject = new InfoTile(yPos, xPos);
                break;
            default:
                System.out.println("Error Constructing Tile: " + tile + " at " + "X: " + xPos + " Y: " + yPos);
                break;
        }
        return tileObject;
    }
        
    public static SolidObject getSolidObject(String solidObj, int yPos, int xPos){
        SolidObject sObject = null;
        if(solidObj.contains("Key")){
            if(solidObj.contains("yellow")){sObject = new Key(yPos, xPos, Images.YellowKey);}
            else if(solidObj.contains("red")){sObject = new Key(yPos, xPos, Images.RedKey);}
            else if(solidObj.contains("blue")){sObject = new Key(yPos, xPos, Images.BlueKey);}
            else if(solidObj.contains("green")){sObject = new Key(yPos, xPos, Images.GreenKey);}
        }
        else if(solidObj.contains("Door")){
            if(solidObj.contains("Yellow")){sObject = new Door(yPos, xPos, Images.YellowDoor);}
            else if(solidObj.contains("Red")){sObject = new Door(yPos, xPos, Images.RedDoor);}
            else if(solidObj.contains("Blue")){sObject = new Door(yPos, xPos, Images.BlueDoor);}
            else if(solidObj.contains("Green")){sObject = new Door(yPos, xPos, Images.GreenDoor);}
        }
        else if(solidObj.contains("exitLock")){
            sObject = new ExitLock(yPos, xPos);
        }
        else if(solidObj.contains("computerChip")){
            sObject = new ComputerChip(yPos, xPos);
        }
        else{
            System.out.println("Error Constructing Solid object: " + solidObj + " at " + "X: " + xPos + " Y: " + yPos);
        }
        return sObject;
    }


}