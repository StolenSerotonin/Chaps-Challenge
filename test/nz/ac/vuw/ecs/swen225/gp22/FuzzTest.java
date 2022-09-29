package nz.ac.vuw.ecs.swen225.gp22;
import nz.ac.vuw.ecs.swen225.gp22.*;
import nz.ac.vuw.ecs.swen225.gp22.app.GUI;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.management.timer.TimerNotification;

import org.junit.Test;

public class FuzzTest {
    
    Random rand = new Random();
    @Test
    public void FuzzTest(){
        new GUI("Chap's Challenge", 800, 600, 2);
        test1();
        test2();
    }

    public void test1(){
        GUI gui1 = new GUI("Chap's Challenge", 800, 600, 1);
        System.out.println("TESTING LEVEL 1\n---------------------------------");

        KeyEvent[] keyEvents1 = {
            new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP,'A'),
            new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN,'B'),
            new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT,'C'),
            new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT,'D')
        };
        
        // Helper helper1 = new Helper();
        // Timer timer1 = new Timer();
        // timer1.schedule(helper1, 1000, 10000);
        for(int i = 0; i < 100; i++){
            gui1.getKeyListeners()[0].keyPressed(keyEvents1[rand.nextInt(4)]);
            System.out.println(i);
            
        }
    }
    public void test2(){
        GUI gui2 = new GUI("Chap's Challenge", 800, 600, 2);
        System.out.println("\nTESTING LEVEL 2\n---------------------------------");
        KeyEvent[] keyEvents2 = {
            new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP,'A'),
            new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN,'B'),
            new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT,'C'),
            new KeyEvent(gui2, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT,'D')
        };
        // Helper helper2 = new Helper();
        // Timer timer1 = new Timer();
        // timer1.schedule(helper2, 1000, 10000);
        //while(helper2.getLvlTime()){
        for(int i = 0; i < 100 ;i++){
            gui2.getKeyListeners()[0].keyPressed(keyEvents2[rand.nextInt(4)]);
            System.out.println(i);
        
        }
    }
    class Helper extends TimerTask{
        boolean lvlTime = true;
        @Override
        public void run() {
            lvlTime = false;
        } 
        private boolean getLvlTime(){
            return lvlTime;
        }


    }
}
