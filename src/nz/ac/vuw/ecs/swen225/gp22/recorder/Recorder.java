package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.util.ArrayList;

/**
 * Recorder class code
 *
 * @author Kevin Lee
 */

 
public class Recorder {
    private static ArrayList<Move> moves = new ArrayList<>(); 
    private static double replaySpeed;

    public record Move (String player, Direction dir){}

    /**
     * 
     * @param speed
     */
    public static void setReplaySpeed(double speed) {
        replaySpeed = speed;
    }

    /**
     * 
     */
    public static void loadRecording () { // load from xml file
        moves.clear(); 
        System.out.println("Loading recording from xml file");
    }

    /**
     * 
     */
    public static void saveRecording () { // save to xml file 
        System.out.println("Saved Recording : ");
        moves.stream().forEach(m->System.out.println(m.player + " " + m.dir()));
    }
    /**
     * 
     */
    public static void singleMove () {
        if (moves.size() < 1) {return;} // ideally throw exception
        MockGame.move(moves.get(0).player, moves.get(0).dir);
        moves.remove(0);
    }

    public static void autoPlay () {
        int count = moves.size(); // ideally use App's runnable peform replay using the set replay speed.
        for (int i=0; i<count; i++) {
            singleMove();
        }
    }

    public void chapMove (Direction dir) {
        moves.add(new Move("chap", dir));
    }

    public void enemyMove (Direction dir) {
        moves.add(new Move("enemy", dir));
    }

}
