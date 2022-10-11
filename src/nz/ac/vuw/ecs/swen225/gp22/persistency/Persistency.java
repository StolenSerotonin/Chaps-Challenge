package nz.ac.vuw.ecs.swen225.gp22.persistency;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.Images;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Persistency {

    private static int ROWS = 21;
    private static int COLUMNS = 21;
    private static int chapStartX = 2;
    private static int chapStartY = 3;
    /**
     * This is used to create a level object. A level object will store the positions 
     * of the tiles and objects on the board.
     * 
     * @param file The name of the file that is to be loaded.
     * @return a level object
     * @throws JDOMException
     * @throws IOException
     */
    public static Level loadBoard(String file) throws JDOMException, IOException{
        //Stores the location of all levels 
        String levelDirectory = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
        
        //Setting up the variables
        SAXBuilder sax = new SAXBuilder();
        Document doc = sax.build(new File(levelDirectory + file));
        Element rootElement = doc.getRootElement();
        int chipsRequired = 0;
        if(file.contains("1")){chipsRequired = 3;}
        else{chipsRequired = 4;}
        Level newLevel = new Level(COLUMNS,ROWS,chapStartX,chapStartY,chipsRequired);
        
        //Storing all the rows within the level tag in a list
        List<Element> rowsList = rootElement.getChildren("row");
        Element infoFieldString =  rootElement.getChild("message");
        //Iterating through all the rows in the list
        for(int y = 0; y < ROWS; y++){
            //Grab the a row from the list and grab all the tile tags embeded within
            Element row = rowsList.get(y);
            List<Element> tiles = row.getChildren("tile");
            for(int x = 0; x < COLUMNS; x++){
                //Check whether the object to be created is of type Tile or of SolidObject
                String tileText = tiles.get(x).getText();
                if(tileText.contains("chap")){
                    newLevel.setTile(x, y, new FloorTile(x, y));
                    newLevel.setStartingPosition(x, y);
                }
                else if(tileText.contains("wall") || tileText.contains("floor") || 
                tileText.contains("infoField") || (tileText.contains("exit") &&
                !tileText.contains("exitLock"))){
                    newLevel.setTile(x, y, getTile(tileText, x, y, infoFieldString));
                }
                else{
                    newLevel.setTile(x, y, new FloorTile(x, y));
                    newLevel.setObject(x, y, getSolidObject(tileText,x, y, infoFieldString));
                }
            }
        }
        
        return newLevel;
    }

    /**
     * This is used to generate a level file to store the recent
     * 
     * @param l
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void saveBoard(Level l) throws FileNotFoundException, IOException{
        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        FileOutputStream fileOutputStream =new FileOutputStream("src/nz/ac/vuw/ecs/swen225/gp22/persistency/savedGames/file.xml");
        Document document = new Document();
        document.setRootElement(new Element("level"));
        Element rootElement = document.getRootElement();
        String infoText = "";
        for(int y = 0; y < ROWS; y++){
            Element row = new Element("row");
            for(int x = 0; x < COLUMNS; x++){
                
                if(l.getObject(x, y) != null){
                    row.addContent(new Element("tile").setText(l.getObject(y, x).toString()));
                }
                else{
                    row.addContent(new Element("tile").setText(l.getTile(y, x).toString()));
                    if(l.getTile(y, x) instanceof InfoTile){
                        infoText = ((InfoTile) l.getTile(y, x)).getInfo();
                    }
                }
            }
            rootElement.addContent(row);
        }
        rootElement.addContent(new Element("message").setText(infoText));
        try {xmlOutputter.output(document, fileOutputStream);}
        catch (Exception e){e.printStackTrace();}
    }
    /**
     * Used to indentify the correct tile object needed and then it returns it.
     * 
     * @param tile Name of the tile stored in a tile tag
     * @param yPos y position to be placed in the Tile 2D array
     * @param xPos x position to be placed in the Tile 2D array
     * @return The correct Tile object needed.
     */
    public static Tile getTile(String tile, int yPos, int xPos, Element tilElement){
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
                tileObject = new InfoTile(yPos, xPos, tilElement.getText());
                System.out.println(tilElement.getText());
                break;
            default://Code for debugging
                System.out.println("Error Constructing Tile: " + tile + " at " + "X: " + xPos + " Y: " + yPos);
                break;
        }
        return tileObject;
    }
    
    /**
     * Used to indentify the correct tile object needed and then it returns it.
     * 
     * @param solidObj Name of the solid object stored in a tile tag
     * @param yPos y position to be placed in the solid object 2D array
     * @param xPos x position to be placed in the solid object 2D array
     * @return The correct SolidObject needed.
     */ 
    public static SolidObject getSolidObject(String solidObj, int yPos, int xPos, Element tilElement){
        SolidObject sObject = null;
        if(solidObj.contains("Key")){
            if(solidObj.contains("yellow")){sObject = new Key(yPos, xPos, Images.YellowKey);}
            else if(solidObj.contains("red")){sObject = new Key(yPos, xPos, Images.RedKey);}
            else if(solidObj.contains("blue")){sObject = new Key(yPos, xPos, Images.BlueKey);}
            else if(solidObj.contains("green")){sObject = new Key(yPos, xPos, Images.GreenKey);}
        }
        else if(solidObj.contains("Door")){
            if(solidObj.contains("yellow")){sObject = new Door(yPos, xPos, Images.YellowDoor);}
            else if(solidObj.contains("red")){sObject = new Door(yPos, xPos, Images.RedDoor);}
            else if(solidObj.contains("blue")){sObject = new Door(yPos, xPos, Images.BlueDoor);}
            else if(solidObj.contains("green")){sObject = new Door(yPos, xPos, Images.GreenDoor);}
        }
        else if(solidObj.contains("exitLock")){
            sObject = new ExitLock(yPos, xPos);
        }
        else if(solidObj.contains("computerChip")){
            sObject = new ComputerChip(xPos, yPos);
        }
        else{//Code for debugging
            System.out.println("Error Constructing Solid object: " + solidObj + " at " + "X: " + xPos + " Y: " + yPos);
        }
        return sObject;
    }


}
