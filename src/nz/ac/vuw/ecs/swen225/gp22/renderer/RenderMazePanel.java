package nz.ac.vuw.ecs.swen225.gp22.renderer;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.util.stream.Stream;

import nz.ac.vuw.ecs.swen225.gp22.domain.Level;
import nz.ac.vuw.ecs.swen225.gp22.domain.SolidObject;
import nz.ac.vuw.ecs.swen225.gp22.domain.Tile;

public class RenderMazePanel extends JPanel{
    
    private Tile[][] tiles;
    private SolidObject[][] tileObjects;

    private Level level;
    //private domain_controler con
    public RenderMazePanel(/*domain_controler con*/ Level level){
        //this.con = con
        this.level = level;
    }

    public void loadAllImages(){
        Stream.of(Images.values()).forEach(img -> img.loadImg(img.getName()));
    }

    private BufferedImage getImg(Tile t){
        return Images.Wall.getImg();
    }

    private BufferedImage getImg(SolidObject o){
        return Images.Wall.getImg();
    }

    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        //maze = con.getMaze();
        tiles = level.getTiles();
        tileObjects = level.getObjects();

        int height = getWidth()/tiles.length;
        int width = getHeight()/tiles[0].length;

        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                
                Tile tile = tiles[i][j];
                if(tile!=null){
                BufferedImage img = getImg(tile);
                System.out.println("tile: " + tile.getImg().getName());
                g.drawImage(img, i*width, j*height, width, height, null);
                //might have to error check here
                SolidObject t = tileObjects[i][j];
                if(t!=null){
                    BufferedImage objImg = getImg(t);
                    System.out.println("tile: " + t.getImg().getName());
                    g.drawImage(objImg, i*width, j*height, width, height, null);
                } else {
                    System.out.println("obj is null");
                }
                } else{
                    System.out.println("tile is null");
                }
                
                
            }
        }
    }

    
}
