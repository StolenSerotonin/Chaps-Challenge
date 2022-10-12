package nz.ac.vuw.ecs.swen225.gp22.recorder;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.junit.platform.commons.util.LruCache;

import nz.ac.vuw.ecs.swen225.gp22.app.GUI;
import nz.ac.vuw.ecs.swen225.gp22.app.KeyInput;

/**
 * Recorder class code which holds the code for basic xml saving of moves and
 * replaying.
 *
 * @author Kevin Lee
 */

public class Recorder {
    private static ArrayList<Move> moves = new ArrayList<>();
    private static double replaySpeed = 1;
    private static int count = 0;

    public record Move(String player, Direction dir, int index) {
    }

    /**
     * 
     * @param speed
     */
    public static void setReplaySpeed(double speed) {
        replaySpeed = speed;
    }

    /**
     * Loading xml files with the moves in them
     * 
     * 
     * @param speed<?xml version="1.0" encoding="UTF-8"?>
     * @throws IOException
     */
    public static void loadRecording() throws JDOMException, IOException { // load from xml file
        moves.clear(); // clearing the moves
        count = 0;
        System.out.println("Loading recording from xml file");

        // loading File:
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder
                .build(new File("src/nz/ac/vuw/ecs/swen225/gp22/recorder/recordedFiles/recording.xml"));

        Element root = document.getRootElement(); // move from xml
        List<Element> nodes = root.getChildren();

        for (Element element : nodes) {
            int moveId = Integer.parseInt(element.getAttributeValue("id"));
            String direction = element.getChildText("direction");
            String player = element.getChildText("player");

            Direction dir = Direction.DOWN;
            if (direction.equals("UP"))
                dir = Direction.UP;
            else if (direction.equals("DOWN"))
                dir = Direction.DOWN;
            else if (direction.equals("LEFT"))
                dir = Direction.LEFT;
            else if (direction.equals("RIGHT"))
                dir = Direction.RIGHT;
            // int index = Integer.valueOf(moveId);

            Move move = new Move(player, dir, moveId);
            moves.add(move);
        }

        System.out.println("Loaded Recording : ");
        for (Move m : moves) { // debugging
            System.out.println(m.player() + " " + m.dir());
        }

    }

    /**
     * Saves the recorded moves into an xml file
     * 
     * @throws FileNotFoundException
     * 
     */
    public static void saveRecording() throws JDOMException, FileNotFoundException { // save to xml file
        // output to Console :
        System.out.println("Saved Recording : ");
        moves.stream().forEach(m -> System.out.println(m.player + " " + m.dir()));

        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        FileOutputStream fileOutputStream = new FileOutputStream(
                "src/nz/ac/vuw/ecs/swen225/gp22/recorder/recordedFiles/recording.xml");
        Document document = new Document();
        document.setRootElement(new Element("Moves"));
        Element root = document.getRootElement();

        for (Move move : moves) {
            Element action = new Element("move");
            action.setAttribute("id", "" + move.index()); // id is the order that move was played
            action.addContent(new Element("direction").setText("" + move.dir()));
            action.addContent(new Element("player").setText(move.player()));
            root.addContent(action);
        }
        // Write file out through xml outputter
        try {
            outputter.output(document, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveBoard(Object board) throws IOException {
        // TODO Persistency Save Board
    }

    public static void runAutoReplay(GUI app) {

        app.setupReplay();
        // System.out.println("BRUH");
        // app.clearPanel();
        System.out.println("MOVESSSS:::::::::::::::::::::::::" + moves);
        Runnable runnable = () -> {
            System.out.println("AUTO REPLAY RUNNING");
            while (!moves.isEmpty()) {
                try {
                    if (moves.size() > 0) {
                        Thread.sleep(250 / (long) replaySpeed); // TODO field with GUI
                    }
                    // singleMove(app);
                    try {
                        if (moves.size() > 0) {
                            Move move = moves.get(0);
                            if (move.player().equals("chap")) {
                                app.replayChap(move.dir());
                                moves.remove(0);
                                // } else if (move.player().equals("enemy")) {
                                // System.out.println("Enemy Moves");
                            } else {
                                System.out.println("Error finding chap");
                            }
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Index out of Bounds on Single Move");
                    }
                } catch (InterruptedException e) {
                    System.out.println("ERROR");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    /** 
     * 
     */
    public static void runStepReplay(GUI app) {
        // app.setupReplay();

        // if (app.gameState == app.pauseState && app.gameLevel == app.replay && app.replayType == app.manual) {
        //     // app.moveChap();
        //     try {
        //         if (moves.size() > 0) {
        //             Move move = moves.get(0);
        //             if (move.player().equals("chap")) {
        //                 if (app.keyIn.replaying == 1) {
        //                     app.replayChap(move.dir());
        //                     moves.remove(0);
        //                 }
        //                 // } else if (move.player().equals("enemy")) {
        //                 // System.out.println("Enemy Moves");
        //             } else {
        //                 System.out.println("Error finding chap");
        //             }
        //         }
        //     } catch (IndexOutOfBoundsException e) {
        //         System.out.println("Index out of Bounds on Single Move");
        //     }
        // }
    }

    // /**
    // * Auto plays the moves loaded from the xml file
    // */
    // public static void autoPlay () {
    // int count = moves.size(); // ideally use App's runnable peform replay using
    // the set replay speed.
    // for (int i=0; i<count; i++) {
    // singleMove();
    // }
    // }

    public static void chapMove(Direction dir) {
        moves.add(new Move("chap", dir, count));
        count++;
    }

    public static void enemyMove(Direction dir) {
        moves.add(new Move("enemy", dir, count));
        count++;
    }
}
