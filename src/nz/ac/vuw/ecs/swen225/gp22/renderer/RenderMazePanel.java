package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import nz.ac.vuw.ecs.swen225.gp22.app.GUI;
import nz.ac.vuw.ecs.swen225.gp22.app.Main;
import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.SolidObject;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class RenderMazePanel extends JPanel{
    
    private Tile[][] tiles;
    private SolidObject[][] tileObjects;
    private Level level;

    public RenderMazePanel(Level level){
        this.level = level;
    }

    public void loadAllImages(){
        Stream.of(Images.values()).forEach(img -> img.loadImg(img.getName()));
    }

    private BufferedImage getImg(Tile t){
        return t.getImg().getImg();
    }

    private BufferedImage getImg(SolidObject o){
        return o.getImg().getImg();
    }

    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        tiles = level.getTiles();
        tileObjects = level.getObjects();
        int height = getHeight()/tiles.length;
        int width = (int) ((int) getWidth()/tiles[0].length/1.5);
        // System.out.println("width: " + width + " height: " + height);
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                
                Tile tile = tiles[j][i];
                if(tile!=null){
                BufferedImage img = getImg(tile);
                g.drawImage(img, i*width, j*height, width, height, null);
                SolidObject t = tileObjects[j][i];
                if(t!=null){
                    BufferedImage objImg = getImg(t);
                    g.drawImage(objImg, i*width, j*height, width, height, null);
                } 
                } 
                if(i == GUI.chap.getX()/width && j == GUI.chap.getY()/height && i != 0 && j != 0){
                    g.drawImage(Images.Chap.getImg(), i*width, j*height, width, height, null);
                }
            }
        }
        //g.drawImage(Images.Chap.getImg(), GUI.chap.getX(), GUI.chap.getY(), width, height, null);
    }

    
}
