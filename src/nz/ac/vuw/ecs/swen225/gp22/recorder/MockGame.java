package nz.ac.vuw.ecs.swen225.gp22.recorder;
import org.jdom2.*;

public class MockGame {
    static Recorder recorder = new Recorder();

    public static void move (String player, Direction d) {
        System.out.println(player + "moves" + d);
        if (player.equals("chap")) { 
            recorder.chapMove(d);
        }
        else {
            recorder.enemyMove(d);
        }
    }


}

class Main{
    public static void main (String [] args) {
        MockGame game = new MockGame();
        // basic test case scenarios 
        game.move("chap", Direction.UP);
        game.move("chap", Direction.DOWN);
        game.move("enemy", Direction.LEFT);
        game.move("chap", Direction.RIGHT);
        try{Recorder.saveRecording();}
        catch (Exception e ) {System.out.println("ERROR : Error Saving xml file");}        

    }

}

