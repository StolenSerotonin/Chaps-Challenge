package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    Sound sound = new Sound();

    public static int tileSize = 72; 
    private int screenHeight = 520;
    private int screenWidth = 520; 

    private int worldX;
    private int worldY;

    private int screenX = screenWidth/2 - (tileSize/2);
    private int screenY = screenHeight/2 - (tileSize/2);

    private int maxWorldCol;
    private int maxWorldRow;

    private final int FIRSTINVKEYX= 336; 
    private int invKeyWidth = 59;
    private int invKeyHeight = 58;
    private int sidebarX = screenWidth-10;
    private int sidebarWidth = 290; 
    private int sidebarHeight = 550;

    private JLabel chipsLeft = new JLabel("");
    private JLabel chipTitle = new JLabel("Chips Left:");
    private JLabel timeTitle = new JLabel("Time Left:");
    private int chipsLeftTextX; 
    private Dimension size = new Dimension(200,200); 

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
        tiles = level.getTiles(); 
        tileObjects = level.getObjects(); 
        worldX = GUI.chap.getX() *3;
        worldY = GUI.chap.getY() *3;
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
        g.drawImage(Images.SideBar.getImg(), sidebarX, 0, sidebarWidth, sidebarHeight,null);
        if(level.hasBlueKey()) g.drawImage(Images.BlueKey.getImg(), screenWidth+42, FIRSTINVKEYX, invKeyWidth, invKeyHeight, null);
        if(level.hasRedKey()) g.drawImage(Images.RedKey.getImg(), screenWidth+109, FIRSTINVKEYX, invKeyWidth, invKeyHeight, null);
        if(level.hasGreenKey()) g.drawImage(Images.GreenKey.getImg(), screenWidth+174, FIRSTINVKEYX, invKeyWidth, invKeyHeight, null);
        if(level.hasYellowKey()) g.drawImage(Images.YellowKey.getImg(), screenWidth+41, FIRSTINVKEYX, invKeyWidth, invKeyHeight, null);
        loadChipsLeft();
        
    }

    private JLabel timerText = new JLabel();

    private void loadChipsLeft(){      
        setLayout(null);          
        int chips = level.getChipsRequired() - GUI.chap.getChips(); 
        chipsLeft.setText("" + chips); 
        
        if(chips == level.getChipsRequired()) chipsLeftTextX = 80;
        else chipsLeftTextX = 110; 

        timerText.setText(GUI.timeDString);
        timerText.setBounds(520 + 40, 20, size.width, size.height);
        timerText.setFont(new Font("Verdana", 1, 58)); 
        timerText.setForeground(new Color(196, 180, 133));

        chipsLeft.setBounds(screenWidth + chipsLeftTextX, 120, size.width, size.height);
        chipsLeft.setFont(new Font("Verdana", 1, 70)); 
        chipsLeft.setForeground(new Color(196, 180, 133));
        
        chipTitle.setBounds(screenWidth + 75, 80, size.width, size.height);
        chipTitle.setFont(new Font("Verdana", 1, 20)); 
        chipTitle.setForeground(new Color(196, 180, 133));

        timeTitle.setBounds(screenWidth + 75, -15, size.width, size.height);
        timeTitle.setFont(new Font("Verdana", 1, 20)); 
        timeTitle.setForeground(new Color(196, 180, 133));

        add(chipsLeft);
        add(chipTitle);
        add(timeTitle);
        add(timerText); 

    }

    public void playMusic(){
        sound.setFile(0);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }






}
