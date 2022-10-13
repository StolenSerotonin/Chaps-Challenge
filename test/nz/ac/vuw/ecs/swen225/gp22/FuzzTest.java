package nz.ac.vuw.ecs.swen225.gp22;
import nz.ac.vuw.ecs.swen225.gp22.*;
import nz.ac.vuw.ecs.swen225.gp22.app.*;
import java.awt.event.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.util.*;
import java.util.stream.Collectors;
import org.junit.Test;


public class FuzzTest{
    Random rand = new Random();

    @Test
    public void fuzzTest() throws Throwable{
        //GUI gui = new GUI("Chap's Challenge", 800, 600, 1);
        Main.main(null);
        MyRobot rob;
        rob = new MyRobot();
        rob.delay(1000);
        rob.pressAndRelease(KeyEvent.VK_ENTER);
        testLvl1(rob);
    }
     /**
     * @throws AWTException
     * @throws Throwable
     * Tests the first level
     * Runs the game, press random arrow keys
     */
    public void testLvl1 (MyRobot rob) throws AWTException, Throwable{
    //    g.loadLevel1();
        System.out.println("TESTING LEVEL 1\n---------------------------------");
       
        Direction preDir = Direction.values()[rand.nextInt(4)];

        for(int i = 0; i < 120; ++i){ //created robot presses random key 120 times every half second
            Direction key = preDir.notOppDir().get(rand.nextInt(3)); //Never uses opposite direction as previous
            preDir = key;
            rob.pressAndRelease(key.keyInt());
            //System.out.println(i+"\nKEY:"+ key);
            // System.out.println(key.notOppDir());
            // System.out.println("");
        }
    }
    /**
     * @throws AWTException
     * @throws Throwable
     * Tests the second level
     * Runs the game, press random arrow keys
     */
    public void testLvl2(GUI gui, MyRobot rob) throws AWTException, Throwable{
        //gui.loadLevel2();
        System.out.println("\nTESTING LEVEL 2\n---------------------------------");
        Direction preDir = Direction.values()[rand.nextInt(4)];
        for(int i = 0; i < 120; i++){//created robot presses random key 120 times every half second
            Direction key = preDir.notOppDir().get(rand.nextInt(3)); //Never uses opposite direction as previous
            preDir = key;
            rob.pressAndRelease(key.keyInt());
            // System.out.println(i+"\nKEY:"+ key);
            // System.out.println(key.notOppDir());
            // System.out.println("");
        }
    }
}
enum Direction{
    UP{
        int keyInt(){
            return KeyEvent.VK_UP;
        }
        Direction opp(){
            return DOWN;
        }
        void printString(){
            System.out.println("UP");
        }
    },
    DOWN{
        int keyInt(){
            return KeyEvent.VK_DOWN;
        }
        Direction opp(){
            return UP;
        }
        void printString(){
            System.out.println("DOWN");
        }
    },
    RIGHT{
        int keyInt(){
            return KeyEvent.VK_RIGHT;
        }    
        Direction opp(){
            return LEFT;
        }
        void printString(){
            System.out.println("RIGHT");
        }
    },
    LEFT{
        int keyInt(){
            return KeyEvent.VK_LEFT;
        }
        Direction opp(){
            return RIGHT;
        }
        void printString(){
            System.out.println("RIGHT");
        }
    };
    Direction opp(){
        return this.opp();
    }
    int keyInt(){
        return this.keyInt();
    }
    List<Direction> notOppDir(){
        return Arrays.asList(Direction.values()).stream().filter(d -> d != this.opp()).collect(Collectors.toList());
    }
    void printString(){
        this.printString();
    }
}
class MyRobot extends Robot{
   
    public MyRobot() throws AWTException {
        super();
    }
    public void pressAndRelease(int keyCode){
        keyPress(keyCode);
        delay(50);                
        keyRelease(keyCode);
        delay(450);
    }
}