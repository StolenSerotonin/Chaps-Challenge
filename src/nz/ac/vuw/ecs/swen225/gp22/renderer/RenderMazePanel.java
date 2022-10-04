package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.JPanel;

import java.awt.Color;
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

    public static int tileSize = 60; 
    private int screenHeight = 520;
    private int screenWidth = 520; 

    private int worldX;
    private int worldY;

    private int screenX = screenWidth/2 - (tileSize/2);
    private int screenY = screenHeight/2 - (tileSize/2);

    private int maxWorldCol;
    private int maxWorldRow;

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
     * Paint the maze to the screen using the graphics object and the tile and tileObject arrays
     * 
     * @param g the graphics object used to draw the maze
     */
    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g); 
        this.setBackground(new Color(34,30,28));
        tiles = level.getTiles(); 
        tileObjects = level.getObjects(); 
        worldX = GUI.chap.getX();
        worldY = GUI.chap.getY();
        maxWorldCol = tiles.length;
        maxWorldRow = tiles[0].length; 
        for(int worldRow = 0; worldRow < maxWorldRow; worldRow++){
            for(int worldCol = 0; worldCol < maxWorldCol; worldCol++){
                Tile tile = tiles[worldRow][worldCol];
                SolidObject object = tileObjects[worldRow][worldCol];

                int wX = worldCol * tileSize;
                int wY = worldRow * tileSize; 
                int sX = wX - worldX + screenX;
                int sY = wY - worldY + screenY; 

                if(wX + (tileSize*2) > worldX - screenX && 
                    wX < worldX + screenX && 
                    wY + (tileSize*2)> worldY - screenX && 
                    wY - (tileSize*2)< worldY + screenY){
                    g.drawImage(tile.getImg().getImg(), sX, sY, tileSize, tileSize, null);
                    if(object != null) g.drawImage(object.getImg().getImg(), sX, sY, tileSize, tileSize, null);
                }
            }
        }
        g.drawImage(Images.Chap.getImg(), screenX, screenY, tileSize, tileSize, null);
    }
}
