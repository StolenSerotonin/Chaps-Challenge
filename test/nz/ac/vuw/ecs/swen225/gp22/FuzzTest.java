package nz.ac.vuw.ecs.swen225.gp22;
import nz.ac.vuw.ecs.swen225.gp22.*;
import nz.ac.vuw.ecs.swen225.gp22.app.GUI;
import java.awt.event.*;
import java.awt.*;


import org.junit.Test;
public class FuzzTest {
 
    @Test
    public void FuzzTest(){
        new GUI("Chap's Challenge", 800, 600, 2);
        test1();
        test2();
      
    }

    public void test1(){
        GUI gui1 = new GUI("Chap's Challenge", 800, 600, 1);
        System.out.println("TESTING LEVEL 1\n---------------------------------");
        KeyEvent up = new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP,'A');
        KeyEvent down = new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN,'B');
        KeyEvent left = new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT,'C');
        KeyEvent right = new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT,'D');
        for(int i = 0; i < 16; i++){
            gui1.getKeyListeners()[0].keyPressed(up);
            gui1.getKeyListeners()[0].keyPressed(down);
            gui1.getKeyListeners()[0].keyPressed(left);
            gui1.getKeyListeners()[0].keyPressed(right);
        }
    }
    public void test2(){
        GUI gui2 = new GUI("Chap's Challenge", 800, 600, 2);
        System.out.println("\nTESTING LEVEL 2\n---------------------------------");
        KeyEvent up = new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP,'A');
        KeyEvent down = new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN,'B');
        KeyEvent left = new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT,'C');
        KeyEvent right = new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT,'D');
        for(int i = 0; i < 16; i++){
            gui2.getKeyListeners()[0].keyPressed(up);
            gui2.getKeyListeners()[0].keyPressed(down);
            gui2.getKeyListeners()[0].keyPressed(left);
            gui2.getKeyListeners()[0].keyPressed(right);
        }
    }
}
