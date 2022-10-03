package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import nz.ac.vuw.ecs.swen225.gp22.app.GUI;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.SolidObject;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

/**
 * This class is used to render the maze
 * 
 * @author livapurane
 *
 */
public class RenderMazePanel extends JPanel{
    private Tile[][] tiles;
    private SolidObject[][] tileObjects;
    private Level level;

    /**
     * Constructor for the RenderMazePanel
     * 
     * @param level the level stores the tiles and tileObjects used for the render
     */
    public RenderMazePanel(Level level){
        this.level = level;
    }

    /**
     * Preload all the images used in the game
     * 
     * @param g the graphics object used to draw the maze
     */
    public void loadAllImages(){
        Stream.of(Images.values()).forEach(img -> img.loadImg(img.getName())); //Stream through all of the images and load them
    }

    /**
     * Return the BufferedImage of the tile
     * 
     * @param t the tile to get the image of
     */
    private BufferedImage getImg(Tile t){
        return t.getImg().getImg();
    }

    /**
     * Return the BufferedImage of the tileObject
     * 
     * @param o the solid Object that needs to get the image of
     */
    private BufferedImage getImg(SolidObject o){
        return o.getImg().getImg();
    }

    /**
     * Paint the maze to the screen using the graphics object and the tile and tileObject arrays
     * 
     * @param g the graphics object used to draw the maze
     */
    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g); //Call the super class paintComponent method
        tiles = level.getTiles(); //Get the tiles from the level
        tileObjects = level.getObjects(); //Get the tileObjects from the level
        
        //Set the size of the tiles  
        int height = getHeight()/tiles.length;
        int width = (int) ((int) getWidth()/tiles[0].length/1.5);

        //Loop through the tiles and Objects and draw them to the screen using their images
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                Tile tile = tiles[j][i];
                SolidObject object = tileObjects[j][i];
                
                if(tile != null) g.drawImage(getImg(tile), i*width, j*height, width, height, null);
                if(object != null) g.drawImage(getImg(object), i*width, j*height, width, height, null);
                
                if(GUI.chap.getX() != 0 && GUI.chap.getY() != 0 && i != 0 && j != 0 && width != 0 && height != 0) 
                    if(i == GUI.chap.getX()/width && j == GUI.chap.getY()/height) 
                        g.drawImage(Images.Chap.getImg(), i*width, j*height, width, height, null);
            }
        }
    }
}