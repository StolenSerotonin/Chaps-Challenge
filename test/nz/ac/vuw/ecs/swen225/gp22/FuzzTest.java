package nz.ac.vuw.ecs.swen225.gp22;
import nz.ac.vuw.ecs.swen225.gp22.*;
import nz.ac.vuw.ecs.swen225.gp22.app.GUI;
import java.awt.event.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.Test;

enum Direction{
    UP{
        int direction(){
            return KeyEvent.VK_UP;
        }
        Direction opp(){
            return DOWN;
        }
    },
    DOWN{
        int direction(){
            return KeyEvent.VK_DOWN;
        }
        Direction opp(){
            return UP;
        }
    },
    RIGHT{
        int direction(){
            return KeyEvent.VK_RIGHT;
        }    
        Direction opp(){
            return LEFT;
        }
    },
    LEFT{
        int direction(){
            return KeyEvent.VK_LEFT;
        }
        Direction opp(){
            return RIGHT;
        }
    };

    Direction opp(){
        return this.opp();
    }
    int direction(){
        return this.direction();
    }
    List<Direction> directionsNotOpp(){
        return Arrays.asList(Direction.values()).stream().filter(d -> d != this.opp()).collect(Collectors.toList());
    }
}

public class FuzzTest{


    int keys [] = { KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
    
    Random rand = new Random();
    @Test
    public void FuzzTest(){
        try {
            test1();
            test2();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }

    public void test1 () throws AWTException, Throwable{
        GUI gui1 = new GUI("Chap's Challenge", 800, 600, 1);
        System.out.println("TESTING LEVEL 1\n---------------------------------");
        Robot rob = new Robot();
        for(int i = 0; i < 120; ++i){
            int key = keys[rand.nextInt(4)];
            System.out.println(i+"\nKEY:"+ key);
            rob.delay(500);
            rob.keyPress(key);
        }
    }
    public void test2() throws AWTException, Throwable{
        GUI gui2 = new GUI("Chap's Challenge", 800, 600, 2);
        System.out.println("\nTESTING LEVEL 2\n---------------------------------");
        Robot rob = new Robot();
            for(int i = 0; i < 120; i++){
                int key = keys[rand.nextInt(4)];
                System.out.println(i+"\nKEY:"+ key);
                rob.delay(500);
                rob.keyPress(key);
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