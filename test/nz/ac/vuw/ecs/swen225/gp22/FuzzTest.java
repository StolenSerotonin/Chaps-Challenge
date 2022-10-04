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
public class FuzzTest{
    Random rand = new Random();
    @Test
    public void FuzzTest(){
        try {
            testLvl1();
            testLvl2();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
    public void testLvl1 () throws AWTException, Throwable{
        GUI gui1 = new GUI("Chap's Challenge", 800, 600, 1);
        System.out.println("TESTING LEVEL 1\n---------------------------------");
        Robot rob = new Robot();
        Direction preDir = Direction.values()[rand.nextInt(4)];
        for(int i = 0; i < 120; ++i){ //created robot presses random key 120 times every half second
            Direction key = preDir.notOppDir().get(rand.nextInt(3)); //Never uses opposite direction as previous
            preDir = key;
            System.out.println(i+"\nKEY:"+ key);
            rob.delay(500);
            rob.keyPress(key.keyInt());
            System.out.println(key.notOppDir());
            System.out.println("");
        }
    }
    /**
     * @throws AWTException
     * @throws Throwable
     * Tests the second level
     * Runs the game, press random arrow keys
     */
    public void testLvl2() throws AWTException, Throwable{
        GUI gui2 = new GUI("Chap's Challenge", 800, 600, 2);
        System.out.println("\nTESTING LEVEL 2\n---------------------------------");
        Robot rob = new Robot();
        Direction preDir = Direction.values()[rand.nextInt(4)];
            for(int i = 0; i < 120; i++){//created robot presses random key 120 times every half second
                Direction key = preDir.notOppDir().get(rand.nextInt(3)); //Never uses opposite direction as previous
                preDir = key;
                System.out.println(i+"\nKEY:"+ key);
                rob.delay(500);
                rob.keyPress(key.keyInt());
                System.out.println(key.notOppDir());
                System.out.println("");

                

            }
    }
}