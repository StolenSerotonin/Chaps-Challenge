package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;
import java.awt.Graphics2D;
import java.awt.Graphics;

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

    public static int tileSize = 72; 
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
    public void paintComponent(Graphics g){
        super.paintComponent(g); //Call the super class paintComponent method
        Graphics2D g2d = (Graphics2D) g; //Cast the graphics object to a Graphics2d object
        // g.drawImage(Images.BG.getImg(), 0, 0, 800, screenHeight, null);
        tiles = level.getTiles(); 
        tileObjects = level.getObjects(); 
        worldX = GUI.chap.getX() *3;
        worldY = GUI.chap.getY() *3;
        System.out.println("worldX " + worldX + " worldY " + worldY);
        maxWorldCol = tiles.length;
        maxWorldRow = tiles[0].length; 
        for(int worldRow = 0; worldRow < maxWorldRow; worldRow++){
            for(int worldCol = 0; worldCol < maxWorldCol; worldCol++){
                Tile tile = tiles[worldCol][worldRow];
                SolidObject object = tileObjects[worldCol][worldRow];

                int wX = worldCol * tileSize;
                int wY = worldRow * tileSize; 
                int sX = wX - worldX + screenX;
                int sY = wY - worldY + screenY; 

                //Draw only if in view
                if(wX + (tileSize*2) > worldX - screenX && 
                    wX - (tileSize) < worldX + screenX && 
                    wY + (tileSize*2)> worldY - screenX && 
                    wY - (tileSize*1)< worldY + screenY){
                        g2d.drawImage(tile.getImg().getImg(), sX, sY, tileSize, tileSize, null);
                    if(object != null) g2d.drawImage(object.getImg().getImg(), sX, sY, tileSize, tileSize, null);
                }
            }
        }
        g2d.drawImage(Images.Chap.getImg(), screenX, screenY, tileSize, tileSize, null);
        drawSidebarPanel(g2d);
    }

    public void drawSidebarPanel(java.awt.Graphics g){
        g.drawImage(Images.SideBar.getImg(), screenWidth-10, 0, 280+10, 521,null); 
    }

    // /**
    //  * Paint the maze to the screen using the graphics object and the tile and tileObject arrays
    //  * 
    //  * @param g the graphics object used to draw the maze
    //  */
    // @Override
    // public void paintComponent(Graphics g){
    //     Graphics2D g2d = (Graphics2D) g;
    //     super.paintComponent(g2d); 
    //     this.setBackground(new Color(34,30,28));
    //     tiles = level.getTiles(); 
    //     tileObjects = level.getObjects(); 
    //     worldX = GUI.chap.getX();
    //     worldY = GUI.chap.getY();
    //     maxWorldCol = tiles.length;
    //     maxWorldRow = tiles[0].length; 
    //     for(int worldRow = 0; worldRow < maxWorldRow; worldRow++){
    //         for(int worldCol = 0; worldCol < maxWorldCol; worldCol++){
    //             Tile tile = tiles[worldRow][worldCol];
    //             SolidObject object = tileObjects[worldRow][worldCol];

    //             int wX = worldCol * tileSize;
    //             int wY = worldRow * tileSize; 
    //             int sX = wX - worldX + screenX;
    //             int sY = wY - worldY + screenY+10; 

    //             if(wX + (tileSize*2) > worldX - screenX && 
    //                 wX < worldX + screenX && 
    //                 wY + (tileSize*2)> worldY - screenX && 
    //                 wY - (tileSize*2)< worldY + screenY){
    //                 g2d.drawImage(tile.getImg().getImg(), sX, sY, tileSize, tileSize, null);
    //                 if(object != null) g2d.drawImage(object.getImg().getImg(), sX, sY, tileSize, tileSize, null);
    //             }
    //         }
    //     }
    //     g2d.drawImage(Images.Chap.getImg(), screenX, screenY, tileSize, tileSize, null);
    // }

}
