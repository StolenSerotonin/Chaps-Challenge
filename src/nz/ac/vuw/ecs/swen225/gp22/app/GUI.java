package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.synth.SynthScrollBarUI;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
// import javax.swing.Renderer;
import javax.swing.Renderer;
import javax.swing.SwingConstants;

import org.jdom2.JDOMException;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.recorder.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.*;

/**
 * This class is the GUI of the game.
 * 
 * @author Ecco Competente
 */

public class GUI extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;

    public short gameState;
    public final short playState = 1;
    public final short pauseState = 2;
    public final short menuState = 3;
    public final short gameOverState = 4;
    public final short infoState = 5;
    public final short resumeState = 6;

    private boolean isRecording = false;
    public boolean isPaused = false;

    public KeyInput keyIn = new KeyInput(this);
    public Display disp = new Display(this);
    public static Chap chap;
    public static Recorder recorder;
    public static Level l1;
    public static Renderer renderer;
    public static RenderMazePanel renderMazePanel = null;

    public short gameLevel;
    public short level1 = 1;
    public short level2 = 2;

    // Menu Buttons
    public JButton startButton = new JButton("Start");
    public JButton loadButton = new JButton("Load");
    public JButton exitButton = new JButton("Exit");
    public JButton saveButton = new JButton("Save");
    public JButton pauseButton = new JButton("Pause");

    private JMenuBar menuBar;

    private JMenuItem exitItem;
    private JMenuItem saveItem;
    private JMenuItem rulesItem;
    private JMenuItem loadItem;
    private JMenuItem lvl1;
    private JMenuItem lvl2;

    private JMenuItem startRecording;
    private JMenuItem stopRecording;
    private JMenuItem replaySpeedx125;
    private JMenuItem replaySpeedx150;
    private JMenuItem replaySpeedx200;

    private JMenu recordGame;
    private JMenu replayGame;

    Thread threadGame;

    public GUI() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyIn);
        this.setFocusable(true);
        BorderLayout l = new BorderLayout();
        this.setLayout(l);

    }

    public void setUpThread() {
        threadGame = new Thread(this);
        threadGame.run();
    }

    public void setup() {
        gameState = menuState;
        this.setBackground(new Color(69, 58, 47));
        JPanel panel = new JPanel();
        panel.setLocation(200, 200);
        panel.setSize(new Dimension(600, 450));
        panel.setBackground(new Color(69, 58, 47));
        JLabel title1 = new JLabel("<html><p><br/><br/>Welcome to<p/></html>", SwingConstants.CENTER); //
        JLabel title2 = new JLabel("<html><p>Chap's Challenge<p/></html>", SwingConstants.CENTER);
        JLabel startText = new JLabel("<html><p><br/><br/><br/><br/><br/>Press ENTER To Play...<p/></html>",
                SwingConstants.CENTER);
        ArrayList<JLabel> labels = new ArrayList<>(Arrays.asList(title1, title2));
        labels.forEach(l -> l.setFont(new Font("Verdana", 1, 70)));
        startText.setFont(new Font("Verdana", 1, 30));
        labels.add(startText);
        labels.forEach(l -> l.setForeground(new Color(196, 180, 133)));
        labels.forEach(l -> panel.add(l));
        this.setVisible(true);
        this.add(panel, BorderLayout.CENTER);
    }



    public void clearPanel() {
        Main.gui.removeAll();
        Main.gui.revalidate();
        Main.gui.repaint();
    }

    @Override
    public void run() {
        long timer = 0;
        long prevTime = System.nanoTime();
        long currTime;
        long elapseTime;
        double intDraw = 1000000000 / 20;
        double d = 0;

        while (threadGame != null) {

            currTime = System.nanoTime();
            elapseTime = currTime - prevTime;
            d += elapseTime / intDraw;
            timer += elapseTime;
            prevTime = currTime;

            if (d >= 1) {
                updateGame();

                // repaint
                d--;
            }
            timer = timer >= 1000000000 ? 0 : timer;
        }
    }

    public void setUpLevel() {
        clearPanel();
        System.out.println("SetUpLevel");
        if (gameState == playState) {
            System.out.println("Game State");
            if (gameLevel == level1 || gameLevel == level2) {
                System.out.println("Loading Levels");
                addButtons();
                addMenu();
                if (gameLevel == level1) {
                    System.out.println("LOADED LEVEL 1");
                }
            }
        }
    }

    public void updateGame() {
        if (gameState == playState) {
            // if (chap != null) {
                moveChap();
                if (renderMazePanel != null) {
                    // System.out.println("repainting");
                    // renderMazePanel.paintComponent(this.getGraphics());
                    // renderMazePanel.repaint();
                }
            // }
        } else if (gameState == pauseState) {
            // System.out.println("Game State is pause");
        } else if (gameState == gameOverState) {
            // System.out.println("Game State is gameOverState");
        }
    }

    public void moveChap() {
        if (keyIn.up == 1) {
            System.out.println("chap up");
            //chap.moveUp();
            if (isRecording == true) {
                recorder.chapMove(Direction.UP);
            }
        } else if (keyIn.down == 1) {
            System.out.println("chap down");
           // chap.moveDown();
            if (isRecording) {
                recorder.chapMove(Direction.DOWN);
            }
        } else if (keyIn.left == 1) {
            System.out.println("chap left");
           // chap.moveLeft();
            if (isRecording) {
                recorder.chapMove(Direction.LEFT);
            }
        } else if (keyIn.right == 1) {
            System.out.println("chap right");
            if (isRecording) {
                recorder.chapMove(Direction.RIGHT);
            }
        }
    }

    public void addButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        var buttonPanel = new JPanel();
        buttonPanel.setFocusable(false);
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.setBackground(new Color(0, 110, 51));
        buttons.addAll(Arrays.asList(pauseButton, saveButton, loadButton, exitButton));
        for (JButton jb : buttons) {
            buttonPanel.add(jb);
            jb.setFocusable(false);
            jb.setFocusCycleRoot(false);
            jb.addActionListener(e -> {
                if (jb.getText().equals("Pause")) {// if pause pressed
                    isPaused = !(isPaused);
                    gameState = isPaused ? pauseState : playState;
                } else if (jb.getText().equals("Save")) {
                } else if (jb.getText().equals("Load")) {
                } else if (jb.getText().equals("Exit")) {
                }
            });
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    public void addMenu() {
        menuBar = new JMenuBar();
        var Game = new JMenu("Game");
        var Options = new JMenu("Options");
        var Level = new JMenu("Level");
        var Help = new JMenu("Help");
        menuBar.add(Game);
        menuBar.add(Options);
        menuBar.add(Level);
        menuBar.add(Help);

        recordGame = new JMenu("Record Game");
        replayGame = new JMenu("Replay Game");
        exitItem = new JMenuItem();
        saveItem = new JMenuItem();
        rulesItem = new JMenuItem();
        loadItem = new JMenuItem();
        lvl1 = new JMenuItem();
        lvl2 = new JMenuItem();
        replaySpeedx125 = new JMenuItem("x1.25");
        replaySpeedx150 = new JMenuItem("x1.5");
        replaySpeedx200 = new JMenuItem("x2");
        startRecording = new JMenuItem("Start Recording");
        stopRecording = new JMenuItem("Stop Recording");
        startRecording.addActionListener(e -> startRecording());
        stopRecording.addActionListener(e -> stopRecording());

        populateMenuItems(saveItem, "Save", KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(exitItem, "Exit", KeyEvent.VK_X,
                InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(rulesItem, "Rules", KeyEvent.VK_H,
                InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(loadItem, "Load", KeyEvent.VK_R,
                InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(lvl1, "Load Level 1", KeyEvent.VK_1,
                InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(lvl2, "Load Level 2", KeyEvent.VK_2,
                InputEvent.CTRL_DOWN_MASK);

        recordGame.add(startRecording);
        recordGame.add(stopRecording);
        replayGame.add(replaySpeedx125);
        replayGame.add(replaySpeedx150);
        replayGame.add(replaySpeedx200);
        Game.add(saveItem);
        Game.add(exitItem);
        Options.add(loadItem);
        Options.add(recordGame);
        Options.add(replayGame);
        Help.add(rulesItem);
        Level.add(lvl1);
        Level.add(lvl2);
        add(menuBar, BorderLayout.NORTH);
    }

    public void startRecording() {
    }

    public void stopRecording() {
    }

    public void populateMenuItems(JMenuItem item, String title, int keyEvent, int inputEvent) {
        item.setText(title);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (item.getText().equals("Exit")) {

                } else if (item.getText().equals("Save")) {
                    // isRecording = false;
                } else if (item.getText().equals("Rules")) {
                } else if (item.getText().equals("Load")) {
                } else if (item.getText().equals("Load Level 1")) {
                } else if (item.getText().equals("Load Level 2")) {
                }
                
            }
        });
    }

    public void exitQ(){

    }

}
