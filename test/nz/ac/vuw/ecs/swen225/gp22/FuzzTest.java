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

public class FuzzTest{
     int keys [] = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    
    Random rand = new Random();
    @Test
    public void FuzzTest(){      
        try {
            test1();
            test2();
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("ERROR");
        }
           
    }

    public void test1 () throws AWTException, Throwable{
        GUI gui1 = new GUI("Chap's Challenge", 800, 600, 1);
        System.out.println("TESTING LEVEL 1\n---------------------------------");
        Robot rob = new Robot();

        for(int i = 0; i < 50; ++i){
            // gui1.getKeyListeners()[0].keyPressed(keyEvents1[rand.nextInt(4)]);
            int key = keys[rand.nextInt(4)];
            System.out.println(i+"\nKEY:"+ key);
            rob.delay(200);
            rob.keyPress(key);
            //System.out.println(key);
            
        }
    }
    public void test2() throws AWTException, Throwable{
        GUI gui2 = new GUI("Chap's Challenge", 800, 600, 2);
        System.out.println("\nTESTING LEVEL 2\n---------------------------------");
        Robot rob = new Robot();
       
            for(int i = 0; i < 50; i++){
              
                int key = keys[rand.nextInt(4)];
                System.out.println(i+"\nKEY:"+ key);
                rob.delay(200);
                rob.keyPress(key);
                //System.out.println(key);
                
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

        // KeyEvent[] keyEvents1 = {
        //     new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP,'A'),
        //     new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN,'B'),
        //     new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT,'C'),
        //     new KeyEvent(gui1, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT,'D')
        // };
        
        // Helper helper1 = new Helper();
        // Timer timer1 = new Timer();
        // timer1.schedule(helper1, 1000, 10000);