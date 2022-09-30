package nz.ac.vuw.ecs.swen225.gp22.renderer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Images {
    Wall("wall"),
    Floor("floor"),
    Exit("exit"),                     //Portal
    ExitLock("exit_lock"),            //Locked Portal Door 

    ComputerChip("computer_chip"),

    Chap("chap"),
    ChapDead("chap_dead"),     
    
    //All the coloured doors
    RedDoor("red_door"),
    GreenDoor("green_door"),
    BlueDoor("blue_door"),
    YellowDoor("yellow_door"),

    //All the coloured keys
    RedKey("red_key"),
    GreenKey("green_key"),
    BlueKey("blue_key"),
    YellowKey("yellow_key"),
    ;
    
    private BufferedImage img;
    private String name;


    private Images(String name){
        this.name = name; 
        this.img = loadImg(name);
    }
    public BufferedImage getImg(){
        return img;
    }

    public String getName(){
        return name;
    }

    /**
     * load the image from the disk
     * 
     * @param path the path to where the image is stored
     * @return BufferedImage of the object 
     */
    public BufferedImage loadImg(String path){
        try {
            File file = new File("images/" + path + ".png");
            BufferedImage img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            throw new RuntimeException(e);}
    }
}
