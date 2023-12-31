package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import org.jdom2.*;
import org.jdom2.output.XMLOutputter;


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
     * @param speed<?xml version="1.0" encoding="UTF-8"?>
     */
    public static void loadRecording () { // load from xml file
        moves.clear(); 
        System.out.println("Loading recording from xml file");
    }

    /**
     * @throws FileNotFoundException
     * 
     */
    public static void saveRecording () throws JDOMException, FileNotFoundException { // save to xml file 
        // output to Console :
        System.out.println("Saved Recording : ");
        moves.stream().forEach(m->System.out.println(m.player + " " + m.dir()));

        
        XMLOutputter outputter = new XMLOutputter();
        System.out.println("BEFORE file stream"); // debugging code 
        FileOutputStream fileOutputStream = new FileOutputStream("src/nz/ac/vuw/ecs/swen225/gp22/recorder/recordedFiles/recording.xml");
        Document document = new Document();
        document.setRootElement(new Element("Moves"));
        System.out.println("AFTER file Stream"); // debugging code
        Element root = document.getRootElement();

        for (Move move : moves) {
            System.out.println("In the LOOP!");// debugging code
            Element action = new Element ("move");
            action.setAttribute("id",""+moves.indexOf(move)); // id is the order that move was played
            action.addContent(new Element("direction").setText(""+move.dir()));
            action.addContent(new Element("player").setText(""+move.player()));
            root.addContent(action);
        }

        // Write file out through xml outputter
        try {outputter.output(document, fileOutputStream);}
        catch (Exception e){e.printStackTrace();}


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
