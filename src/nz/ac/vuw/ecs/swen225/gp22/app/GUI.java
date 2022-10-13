package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
// import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
// import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.stream.Collectors;
// import java.util.Timer;

import javax.swing.Timer;
// import javax.swing.border.Border;
// import javax.swing.border.LineBorder;
// import javax.swing.plaf.synth.SynthScrollBarUI;
// import javax.swing.AbstractAction;
// import javax.swing.Action;
import javax.swing.JButton;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
import javax.swing.KeyStroke;
// import javax.swing.Renderer;
import javax.swing.Renderer;
import javax.swing.SwingConstants;

import org.jdom2.JDOMException;

import nz.ac.vuw.ecs.swen225.gp22.domain.*;
import nz.ac.vuw.ecs.swen225.gp22.persistency.Persistency;
import nz.ac.vuw.ecs.swen225.gp22.recorder.*;
import nz.ac.vuw.ecs.swen225.gp22.renderer.*;
// import nz.ac.vuw.ecs.swen225.gp22.persistency.*;

/**
 * This class is the GUI of the game.
 * 
 * @author Ecco Competente
 */

public class GUI extends JPanel implements Runnable {

    // menu Dock
    // private JMenuBar menuBar;

    // private KeyStroke exitWindow;
    // private KeyStroke saveGame;
    // private KeyStroke escape;
    // private KeyStroke loadGame;
    // private KeyStroke loadL1;
    // private KeyStroke loadL2;

    // private int pauseKey = KeyEvent.VK_SPACE;

    // private Action exitAction;
    // private Action saveAction;
    // private Action loadAction;
    // private Action loadlevel1;
    // private Action loadlevel2;

    // public final int upArrow = KeyEvent.VK_UP;
    // public final int downArrow = KeyEvent.VK_DOWN;
    // public final int leftArrow = KeyEvent.VK_LEFT;
    // public final int rightArrow = KeyEvent.VK_RIGHT;

    // private JFileChooser fileChooser;
    // // private File l1;
    // // private File l2;
    // private JButton start = new JButton("Start");
    // private JButton exit = new JButton("Exit");
    // private JButton load = new JButton("Load");
    // private JButton save = new JButton("Save");
    // private JButton pause = new JButton("Pause");

    // private static boolean isRecording = false;
    // private boolean isPaused = false;
    // private int lvl;
    // public static RenderMazePanel r1;
    // public static GUI g1;
    // private Recorder recorder = new Recorder();
    // private Timer t1;
    // private Timer t2;
    // public static Chap chap;
    // private int lv1Time = 60;
    // private int lv2Time = 60;

    // public GUI(String title, int width, int height, int level) {
    // super(title);
    // setSize(width, height);
    // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // setResizable(false);
    // setLocationRelativeTo(null);
    // setVisible(true);
    // this.lvl = level;
    // getContentPane().setBackground(new Color(0, 110, 51));
    // setUpLevel();
    // }

    // public void setUpLevel() {
    // if (lvl == 0) {
    // level0();
    // } else if(lvl == 1){
    // loadLv1Timer();
    // addComponents();
    // this.setFocusTraversalKeysEnabled(false);
    // this.addKeyListener(new KeyAdapter() {
    // @Override
    // public void keyPressed(KeyEvent e) {
    // if (e.getKeyCode() == pauseKey) {
    // pause();
    // } else if (isPaused == false) {
    // if (e.getKeyCode() == upArrow) {
    // System.out.println("up");
    // r1.repaint();
    // chap.moveUp();
    // if (isRecording) {
    // recorder.chapMove(Direction.UP); // calling Recorder enum
    // }
    // } else if (e.getKeyCode() == downArrow) {
    // System.out.println("down");
    // r1.repaint();
    // chap.moveDown();
    // if (isRecording) {
    // recorder.chapMove(Direction.DOWN);
    // }
    // } else if (e.getKeyCode() == leftArrow) {
    // System.out.println("left");
    // r1.repaint();
    // chap.moveLeft();
    // if (isRecording) {
    // recorder.chapMove(Direction.LEFT);
    // }
    // } else if (e.getKeyCode() == rightArrow) {
    // System.out.println("right");
    // r1.repaint();
    // chap.moveRight();
    // if (isRecording) {
    // recorder.chapMove(Direction.RIGHT);
    // }
    // }
    // }
    // }
    // });
    // }
    // }

    // public void level0() {
    // JPanel panel = new JPanel();
    // // array of buttons
    // JButton[] lvl0Buttons = { start, exit, load };
    // panel.setLayout(new GridLayout(1, 1));
    // panel.add(start);
    // panel.add(load);
    // panel.add(exit);
    // panel.setBackground(new Color(0, 110, 51));
    // add(panel, BorderLayout.SOUTH);
    // // add action listeners to buttons using lambda expressions
    // for (JButton b : lvl0Buttons) {
    // b.addActionListener(e -> {
    // if (e.getSource() == start) {
    // try {
    // loadLevel1();

    // } catch (JDOMException e1) {
    // // print error message
    // e1.printStackTrace();
    // } catch (IOException e1) {
    // e1.printStackTrace();
    // }
    // // setUpLevel();
    // } else if (e.getSource() == exit) {
    // exit();
    // } else if (e.getSource() == load) {
    // load();
    // }
    // });
    // }
    // populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X,
    // InputEvent.CTRL_DOWN_MASK);
    // populateShortCuts(loadGame, loadAction, "Load", KeyEvent.VK_R,
    // InputEvent.CTRL_DOWN_MASK);
    // }

    // public void loadLevel1() throws JDOMException, IOException {
    // this.dispose();
    // g1 = new GUI("Level 1", 800, 600, 1);
    // g1.setVisible(true);

    // Level l1 = Persistency.loadBoard("level1.xml");
    // r1 = new RenderMazePanel(l1);
    // chap = new Chap(l1.getStartingX(), l1.getStartingY());
    // r1.loadAllImages();
    // g1.add(r1);

    // r1.paintComponent(g1.getGraphics());
    // lvl = 1;
    // this.dispose();
    // }

    // public void loadLevel2() {
    // this.dispose();
    // GUI g2 = new GUI("Level 2", 800, 600, 2);
    // g2.setVisible(true);
    // lvl = 2;
    // this.dispose();
    // }

    // public void addComponents() {
    // addButtons();
    // addMenu();
    // populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X,
    // InputEvent.CTRL_DOWN_MASK);
    // populateShortCuts(saveGame, saveAction, "Save", KeyEvent.VK_S,
    // InputEvent.CTRL_DOWN_MASK);
    // populateShortCuts(loadGame, loadAction, "Load", KeyEvent.VK_R,
    // InputEvent.CTRL_DOWN_MASK);
    // populateShortCuts(loadL1, loadlevel1, "Load Level 1", KeyEvent.VK_1,
    // InputEvent.CTRL_DOWN_MASK);
    // populateShortCuts(loadL2, loadlevel2, "Load Level 2", KeyEvent.VK_2,
    // InputEvent.CTRL_DOWN_MASK);

    // }

    // public void addMenu() {
    // menuBar = new JMenuBar();
    // var Game = new JMenu("Game");
    // var Options = new JMenu("Options");
    // var Level = new JMenu("Level");
    // var Help = new JMenu("Help");
    // menuBar.add(Game);
    // menuBar.add(Options);
    // menuBar.add(Level);
    // menuBar.add(Help);

    // recordGame = new JMenu("Record Game");
    // replayGame = new JMenu("Replay Game");
    // exitItem = new JMenuItem();
    // saveItem = new JMenuItem();
    // rulesItem = new JMenuItem();
    // loadItem = new JMenuItem();
    // lvl1 = new JMenuItem();
    // lvl2 = new JMenuItem();
    // replaySpeedx125 = new JMenuItem("x1.25");
    // replaySpeedx150 = new JMenuItem("x1.5");
    // replaySpeedx200 = new JMenuItem("x2");
    // startRecording = new JMenuItem("Start Recording");
    // stopRecording = new JMenuItem("Stop Recording");
    // startRecording.addActionListener(e -> startRecording());
    // stopRecording.addActionListener(e -> stopRecording());

    // populateMenuItems(saveItem, "Save", KeyEvent.VK_S,
    // InputEvent.CTRL_DOWN_MASK);
    // populateMenuItems(exitItem, "Exit", KeyEvent.VK_X,
    // InputEvent.CTRL_DOWN_MASK);
    // populateMenuItems(rulesItem, "Rules", KeyEvent.VK_H,
    // InputEvent.CTRL_DOWN_MASK);
    // populateMenuItems(loadItem, "Load", KeyEvent.VK_R,
    // InputEvent.CTRL_DOWN_MASK);
    // populateMenuItems(lvl1, "Load Level 1", KeyEvent.VK_1,
    // InputEvent.CTRL_DOWN_MASK);
    // populateMenuItems(lvl2, "Load Level 2", KeyEvent.VK_2,
    // InputEvent.CTRL_DOWN_MASK);

    // recordGame.add(startRecording);
    // recordGame.add(stopRecording);
    // replayGame.add(replaySpeedx125);
    // replayGame.add(replaySpeedx150);
    // replayGame.add(replaySpeedx200);
    // Game.add(saveItem);
    // Game.add(exitItem);
    // Options.add(loadItem);
    // Options.add(recordGame);
    // Options.add(replayGame);
    // Help.add(rulesItem);
    // Level.add(lvl1);
    // Level.add(lvl2);
    // add(menuBar, BorderLayout.NORTH);
    // }

    // public void startRecording() {
    // isRecording = true;
    // save.setEnabled(isRecording);
    // System.out.println("Recording started");
    // }

    // public void stopRecording() {
    // isRecording = false;
    // save.setEnabled(isRecording);
    // System.out.println("Recording stopped");
    // try {
    // save();
    // } catch (FileNotFoundException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

    // public void addButtons() {
    // ArrayList<JButton> buttons = new ArrayList<>();
    // var buttonPanel = new JPanel();
    // buttonPanel.setFocusable(false);
    // buttonPanel.setLayout(new GridLayout(1, 4));
    // buttonPanel.setBackground(new Color(0, 110, 51));
    // buttons.addAll(Arrays.asList(pause, save, load, exit));
    // for (JButton jb : buttons) {
    // buttonPanel.add(jb);
    // jb.setFocusable(false);
    // }
    // for (JButton b : buttons) {
    // b.setFocusCycleRoot(false);
    // b.addActionListener(e -> {
    // if (b.getText().equals("Pause")) {
    // pause();
    // } else if (b.getText().equals("Save")) {
    // try {
    // save();
    // } catch (FileNotFoundException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    // } else if (b.getText().equals("Load")) {
    // load();
    // } else if (b.getText().equals("Exit")) {
    // exit();
    // }
    // });
    // }

    // add(buttonPanel, BorderLayout.SOUTH);
    // }

    // public void startGame() {
    // start.addActionListener(e -> {
    // try {
    // loadLevel1();
    // } catch (JDOMException e1) {
    // e1.printStackTrace();
    // } catch (IOException e1) {
    // e1.printStackTrace();
    // }
    // });
    // start.setVisible(false);
    // }

    // public void exit() {
    // System.exit(0);
    // }

    // public void save() throws FileNotFoundException {
    // if (isRecording == false) {
    // JOptionPane.showMessageDialog(this, "Game is not being recorded");
    // } else {
    // try {
    // Recorder.saveRecording();
    // } catch (JDOMException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // JOptionPane.showMessageDialog(this, "Game Saved");
    // }
    // }

    // public File load() {
    // // System.out.println("Game Loaded");
    // fileChooser = new JFileChooser(".");
    // int res = fileChooser.showOpenDialog(this);
    // if (res == JFileChooser.APPROVE_OPTION) {
    // return fileChooser.getSelectedFile();
    // }
    // return null;
    // }

    // public void pause() {
    // isPaused = true;
    // t1.stop();
    // ArrayList<JButton> buttons = new ArrayList<>();
    // JPanel pausePanel = new JPanel();
    // pausePanel.setFocusable(false);
    // pausePanel.setFocusCycleRoot(false);
    // JDialog pauseWindow = new JDialog();
    // var p = new JLabel("Game Paused");
    // var resumeButton = new JButton("Resume");
    // var saveButton = new JButton("Save & Exit");
    // var exitUnsaved = new JButton("Exit without Saving");
    // populatePopUp(pauseWindow, "Pause", 400, 200, false);
    // p.setHorizontalAlignment(JLabel.CENTER);
    // pauseWindow.add(p, BorderLayout.CENTER);
    // buttons.add(resumeButton);
    // buttons.add(saveButton);
    // buttons.add(exitUnsaved);
    // for (JButton button : buttons) {

    // button.addActionListener((ActionEvent e) -> {
    // if (button.getText().equals("Resume")) {
    // isPaused = false;
    // t1.start();
    // pauseWindow.dispose();
    // System.out.println("Game Resumed " + isPaused);
    // } else if (button.getText().equals("Save & Exit")) {
    // if (isRecording == true) {
    // try {
    // save();
    // } catch (FileNotFoundException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    // isPaused = false;
    // pauseWindow.dispose();
    // exit();
    // } else {
    // JOptionPane.showMessageDialog(this, "Game is not being recorded");
    // isPaused = false;
    // pauseWindow.dispose();
    // }
    // } else if (button.getText().equals("Exit without Saving")) {
    // pauseWindow.dispose();
    // exit();
    // }
    // });
    // pausePanel.add(button);
    // }
    // escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
    // pauseWindow.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape,
    // "ESCAPE");
    // pauseWindow.getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // isPaused = false;
    // t1.start();
    // pauseWindow.dispose();
    // }
    // });
    // pauseWindow.add(pausePanel, BorderLayout.SOUTH);

    // }
    // public void rules() {
    // JPanel rulesPanel = new JPanel();
    // JDialog rulesWindow = new JDialog();
    // var tArea = new JTextArea();
    // JScrollPane scroll = new JScrollPane(tArea);
    // var okButton = new JButton("OK");
    // populatePopUp(rulesWindow, "Rules", 300, 200, true);
    // tArea.setEditable(false);
    // tArea.setLineWrap(true);
    // tArea.setWrapStyleWord(true);
    // tArea.setText(
    // "Rules of Chap's Challenge:\n\n1. Move Chap around the maze using the arrow
    // keys.\n\n2. Collect all the keys to unlock the door.\n\n3. Collect all the
    // gems to win the game.\n\n4. Avoid the ghosts and the fire.\n\n5. Press space
    // to pause the game.\n\n6. Press escape to exit the game.\n\n7. Press ctrl + s
    // to save the game.\n\n8. Press ctrl + x to exit the game.\n\n9. Press ctrl + h
    // to view the rules.\n\n10. Press ctrl + r to resume a saved game -.\n\n11.
    // Press ctrl + 1 to start a new game at level 1.\n\n12. Press ctrl + 2 to start
    // a new game at level 2.");
    // scroll.getVerticalScrollBar().setValue(0);
    // scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    // rulesWindow.add(scroll, BorderLayout.CENTER);
    // okButton.addActionListener((ActionEvent e) -> {
    // rulesWindow.dispose();
    // });
    // rulesWindow.add(rulesPanel, BorderLayout.SOUTH);
    // }

    // public void populatePopUp(JDialog window, String title, int width, int
    // height, boolean resizable) {
    // window.setSize(width, height);
    // window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    // window.setResizable(resizable);
    // window.setLocationRelativeTo(null);
    // window.setVisible(true);
    // window.setTitle(title);
    // }

    // /**
    // *
    // * @param kStroke - KeyStroke to be added to the input map
    // * @param action - Action to be performed when the key is pressed
    // * @param actionName - Name of the action
    // * @param keyEvent - KeyEvent to be added to the input map
    // * @param inputEvent - InputEvent to be added to the input map
    // *
    // * This method is used to add key bindings to the game.
    // * It takes in a key stroke, an action, an action name, a key
    // * event and an input event.
    // * It then adds the key stroke to the input map and the action
    // * to the action map.
    // *
    // */

    // public void populateShortCuts(KeyStroke kStroke, Action action, String
    // actionName, int keyEvent, int inputEvent) {
    // kStroke = KeyStroke.getKeyStroke(keyEvent, inputEvent);
    // action = new AbstractAction(actionName) {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // if (actionName.equals("Exit")) {
    // exit();
    // } else if (actionName.equals("Save")) {
    // try {
    // save();
    // } catch (FileNotFoundException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    // isRecording = false;
    // } else if (actionName.equals("Load")) {
    // load();
    // } else if (actionName.equals("Load Level 1")) {
    // try {
    // loadLevel1();
    // } catch (JDOMException e1) {
    // e1.printStackTrace();
    // } catch (IOException e1) {
    // e1.printStackTrace();
    // }
    // } else if (actionName.equals("Load Level 2")) {
    // loadLevel2();
    // }
    // }
    // };
    // getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(kStroke,
    // actionName);
    // getRootPane().getActionMap().put(actionName, action);
    // }

    // /**
    // *
    // * @param item - the item to be added to the inventory
    // * @param title - the title of the item
    // * @param keyEvent - the key event to be used for the shortcut
    // * @param inputEvent - the input event to be used for the shortcut
    // *
    // * This method populates the menu bar with the menu items and
    // * shortcuts
    // */
    // public void populateMenuItems(JMenuItem item, String title, int keyEvent, int
    // inputEvent) {
    // item.setText(title);
    // item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent));
    // item.addActionListener(new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // if (item.getText().equals("Exit")) {
    // exit();
    // } else if (item.getText().equals("Save")) {
    // try {
    // save();
    // } catch (FileNotFoundException e1) {
    // // TODO Auto-generated catch block
    // e1.printStackTrace();
    // }
    // isRecording = false;
    // } else if (item.getText().equals("Rules")) {
    // rules();
    // } else if (item.getText().equals("Load")) {
    // load();
    // } else if (item.getText().equals("Load Level 1")) {
    // try {
    // loadLevel1();
    // } catch (JDOMException e1) {
    // e1.printStackTrace();
    // } catch (IOException e1) {
    // e1.printStackTrace();
    // }
    // } else if (item.getText().equals("Load Level 2")) {
    // loadLevel2();
    // }
    // }
    // });
    // }

    // public void loadLv1Timer(){
    // t1 = new Timer(1000, new ActionListener() {
    // @Override
    // public void actionPerformed(ActionEvent e) {
    // if (lv1Time > 0) {
    // lv1Time--;
    // //format lv1Time to display as mm:ss
    // String timeDString = String.format("%02d:%02d", lv1Time / 60, lv1Time % 60);
    // System.out.print("\r " + timeDString);
    // } else {
    // t1.stop();
    // JOptionPane.showMessageDialog(null, "Time's Up!");
    // exit();
    // }
    // }
    // });
    // t1.start();
    // }

    // //method that restarts the timer
    // public void restartTimer(){
    // if(lvl == 1)t1.restart();
    // else if(lvl == 2)t2.restart();
    // else t1.restart(); t2.restart();
    // }

    public short gameState;
    public final short playState = 1;
    public final short pauseState = 2;
    public final short menuState = 3;
    public final short gameOverState = 4;
    public final short infoState = 5;

    public short replayType;
    public final short manual = 1;
    public final short auto = 2;

    private boolean isRecording = false;
    public boolean isPaused = false;

    public KeyInput keyIn = new KeyInput(this);
    // public Display disp = new Display(this);
    public static Chap chap;
    public static Recorder recorder;
    public static Level l1;
    public static Renderer renderer;
    public static RenderMazePanel renderMazePanel = null;
    public static Persistency persistency;
    public Timer timer;

    public static int time = 60;
    public ArrayList<JButton> buttons = new ArrayList<>();

    public short gameLevel;
    public short level1 = 1;
    public short level2 = 2;
    public short replay = 3;
    public short loadState = 4;

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
    private JMenuItem startR;

    private JMenuItem startRecording;
    private JMenuItem stopRecording;
    private JMenuItem replaySpeedx1;
    private JMenuItem replaySpeedx150;
    private JMenuItem replaySpeedx200;

    private JMenu recordGame;
    private JMenu replayGame;
    private JMenu mReplayGame;

    private JFileChooser fileChooser;
    
    public String levelsURL = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
    public String savedGamesURL = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/savedGames/"; 
    // private ArrayList<JButton> allButtons = new ArrayList<>();

    Thread threadGame;

    /**
     * Constructor for the Game class
     * 
     */
    public GUI() {
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyIn);
        BorderLayout l = new BorderLayout();
        this.setLayout(l);
        this.setFocusable(true);
    }

    /**
     * This method is used to start thread of the game
     */
    public void setUpThread() {
        threadGame = new Thread(this);
        threadGame.start();
    }

    /**
     * This method is used to setup the game and start the game
     */
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

    /**
     * This method restarts the game panel
     */
    public void clearPanel() {
        Main.gui.removeAll();
        Main.gui.revalidate();
        Main.gui.repaint();

        // remove timer and restart it
        if (timer != null) {
            timer.stop();
            time = 60;
        }
    }

    public void setupReplay() {
        try {
            Recorder.loadRecording();
        } catch (JDOMException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        System.out.println("LOADED REPLAY");
        try {
            l1 = Persistency.loadBoard("recorderBoard.xml", "src/nz/ac/vuw/ecs/swen225/gp22/recorder/recordedFiles/");
        } catch (JDOMException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        chap = new Chap(l1.getStartingX(), l1.getStartingY(), l1);
        renderMazePanel = new RenderMazePanel(l1);
        renderMazePanel.loadAllImages();
        renderMazePanel.paintComponent(getGraphics());
        add(renderMazePanel);
    }

    /**
     * This method run is used to run the game multiple times constantly checking
     * and updating the game
     */
    @Override
    public void run() {
        long timer = 0; // timer for the game
        long prevTime = System.nanoTime(); // previous time
        long currTime; // current time
        long elapseTime; // time elapsed
        double intDraw = 1000000000 / 15; // interval to draw
        double d = 0; // delta

        while (threadGame != null) {

            currTime = System.nanoTime();
            elapseTime = currTime - prevTime; // time elapsed
            d += elapseTime / intDraw;
            timer += elapseTime;
            prevTime = currTime;

            if (d >= 1) {
                updateGame();
                this.requestFocus();
                // repaint();
                // System.out.println("GUI RUNNING");
                d--;
            }
            timer = timer >= 1000000000 ? 0 : timer;
        }

    }

    /**
     * This method is used to update base on the level of the game
     */
    public void setUpLevel() {
        clearPanel(); // clear the panel

        if (gameState == playState) {
            // if the game is in play state
            System.out.println("Game State: " + gameState);
            addButtons(); // add buttons
            addMenu(); // add menu
            if (gameLevel == level1) { // if the game level is level 1
                System.out.println("LOADED LEVEL1");
                loadLv1Timer();
                try {
                    String url = "src/nz/ac/vuw/ecs/swen225/gp22/persistency/levels/";
                    l1 = Persistency.loadBoard("level1.xml", levelsURL);
                } catch (JDOMException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                chap = new Chap(l1.getStartingX(), l1.getStartingY(), l1); // pass level
                renderMazePanel = new RenderMazePanel(l1);
                renderMazePanel.loadAllImages();
                renderMazePanel.paintComponent(getGraphics());
                renderMazePanel.playMusic();

                add(renderMazePanel);
            } else if (gameLevel == level2) { // if the game level is level 2
                System.out.println("LOADED LEVEL 2");
                loadLv1Timer();
            } else if (gameLevel == loadState) {
                try {
                    l1 = Persistency.loadBoard("savedGame.xml",
                            savedGamesURL);
                    chap = new Chap(l1.getStartingX(), l1.getStartingY(), l1); // pass level
                    renderMazePanel = new RenderMazePanel(l1);
                    renderMazePanel.loadAllImages();
                    renderMazePanel.paintComponent(getGraphics());
                    add(renderMazePanel);
                    timer.start();
                } catch (JDOMException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } else if (gameState == pauseState && gameLevel == replay) {
            if (replayType == manual) {
                Recorder.runAutoReplay(this);
            } else if (replayType == auto) {
                Recorder.runAutoReplay(this);
            }
        } else if (gameState == menuState) {
            setUpMenu();
        }
    }

    public void setUpGameOver() {
        clearPanel();
        System.out.println("Game Over");
        if (gameState == gameOverState) {
            System.out.println("gameover");
            JPanel panel = new JPanel();
            panel.setLocation(200, 200);
            panel.setSize(new Dimension(600, 450));
            panel.setBackground(new Color(69, 58, 47));
            JLabel title1 = new JLabel("<html><p><br/><br/>NICE COCK<p/></html>", SwingConstants.CENTER); //
            JLabel title2 = new JLabel("<html><p>GOOD SIZE<p/></html>", SwingConstants.CENTER);
            JLabel startText = new JLabel("<html><p><br/><br/><br/><br/><br/>Press ENTER To Retry...<p/></html>",
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
    }

    public void setUpMenu() {
        clearPanel();
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

    /**
     * This method is used to update the game
     */
    public void updateGame() {
        if (gameState == playState) {
            // if (chap != null) {
            // move chap
            if (renderMazePanel != null) {
                // System.out.println("repainting");
                // renderMazePanel.paintComponent(this.getGraphics());
                // renderMazePanel.repaint();
                moveChap();
            }
        } else if (gameState == pauseState && gameLevel != replay) {
            timer.stop(); // stop timer
        } else if (gameState == gameOverState) {
            // System.out.println("Game State is gameOverState");
        } else if (gameLevel == replay) {
            // Recorder.runAutoReplay(this);
        }
    }

    /**
     * This method is used to move the chap base on the input of player and records
     * movement
     */
    public void moveChap() {
        try {
            if (keyIn.up == 1) { // up
                System.out.println("chap up");
                chap.moveUp();
                renderMazePanel.repaint();
                if (isRecording == true) { // record movement
                    Recorder.chapMove(Direction.UP); // record right
                }
            } else if (keyIn.down == 1) { // down
                System.out.println("chap down");
                chap.moveDown();
                renderMazePanel.repaint();
                if (isRecording) { // record movement
                    Recorder.chapMove(Direction.DOWN); // record down
                }
            } else if (keyIn.left == 1) { // left
                System.out.println("chap left");
                chap.moveLeft();
                renderMazePanel.repaint();
                if (isRecording) { // record movement
                    Recorder.chapMove(Direction.LEFT); // record left
                }
            } else if (keyIn.right == 1) { // right
                System.out.println("chap right");
                chap.moveRight();
                renderMazePanel.repaint();
                if (isRecording) { // record movement
                    Recorder.chapMove(Direction.RIGHT); // record right
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void replayChap(Direction dir) {

            try {
                if (dir == Direction.UP) {
                    chap.moveUp();
                } else if (dir == Direction.DOWN) {
                    chap.moveDown();
                    renderMazePanel.repaint();
                } else if (dir == Direction.LEFT) {
                    chap.moveLeft();
                    renderMazePanel.repaint();
                } else if (dir == Direction.RIGHT) {
                    chap.moveRight();
                    renderMazePanel.repaint();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }

    /**
     * This method is used to add the buttons to the game
     */
    public void addButtons() {
        var buttonPanel = new JPanel(); // panel for buttons
        buttonPanel.setFocusable(false); // set focusable to false
        buttonPanel.setLayout(new GridLayout(1, 4)); // set layout to grid layout
        buttonPanel.setBackground(new Color(0, 110, 51)); // set background color
        buttons.addAll(Arrays.asList(pauseButton, saveButton, loadButton, exitButton)); // add buttons to arraylist
        for (JButton jb : buttons) {
            buttonPanel.add(jb); // add buttons to panel
            jb.setFocusable(false);
            jb.setFocusCycleRoot(false);
            jb.addActionListener(e -> {
                if (jb.getText().equals("Pause") || jb.getText().equals("Resume")) {// if pause pressed or resumed
                    isPaused = !(isPaused); // change pause state
                    gameState = isPaused ? pauseState : playState; // change game state
                    if (isPaused) { // if paused
                        jb.setText("Resume"); // change text to resume
                        timer.stop(); // stop timer
                    } else {
                        jb.setText("Pause"); // change text to pause
                        timer.start(); // start timer
                    }
                } else if (jb.getText().equals("Save")) { // if save pressed
                    save(l1, chap); // save game
                } else if (jb.getText().equals("Load")) {
                    load();
                } else if (jb.getText().equals("Exit")) { // if exit pressed
                    exitQ(); // exit game popup
                }
            });
            add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    /**
     * This method is used to add the menu to the game
     */
    public void addMenu() {
        menuBar = new JMenuBar(); // menu bar
        var Game = new JMenu("Game"); // game menu
        var Options = new JMenu("Options"); // options menu
        var Level = new JMenu("Level"); // level menu
        var Help = new JMenu("Help");// help menu
        menuBar.add(Game); // add game menu to menu bar
        menuBar.add(Options); // add options menu to menu bar
        menuBar.add(Level); // add level menu to menu bar
        menuBar.add(Help); // add help menu to menu bar

        recordGame = new JMenu("Record Game"); // record game menu
        replayGame = new JMenu("Auto Replay"); // replay game menu
        mReplayGame = new JMenu("Manual Replay");
        exitItem = new JMenuItem(); // exit menu item
        saveItem = new JMenuItem(); // save menu item
        rulesItem = new JMenuItem(); // rules menu item
        loadItem = new JMenuItem(); // load menu item
        startR = new JMenuItem("Start Replay");
        lvl1 = new JMenuItem(); // levels menu item
        lvl2 = new JMenuItem();
        replaySpeedx1 = new JMenuItem("x1"); // replay speed menu item
        replaySpeedx150 = new JMenuItem("x1.5");
        replaySpeedx200 = new JMenuItem("x2");
        startRecording = new JMenuItem("Start Recording"); // start and stop recording menu item
        stopRecording = new JMenuItem("Stop Recording");
        startRecording.addActionListener(e -> startRecording()); // start and stop recording
        stopRecording.addActionListener(e -> stopRecording());
        replaySpeedx1.addActionListener(e -> setSpeed1());
        replaySpeedx150.addActionListener(e -> setSpeed150());
        replaySpeedx200.addActionListener(e -> setSpeed200());
        startR.addActionListener(e -> runMreplay());

        // populate the menu items and key bindings
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

        // add menu items to menu
        recordGame.add(startRecording);
        recordGame.add(stopRecording);
        replayGame.add(replaySpeedx1);
        replayGame.add(replaySpeedx150);
        replayGame.add(replaySpeedx200);
        mReplayGame.add(startR);
        Game.add(saveItem);
        Game.add(exitItem);
        Options.add(loadItem);
        Options.add(recordGame);
        Options.add(replayGame);
        Options.add(mReplayGame);
        Help.add(rulesItem);
        Level.add(lvl1);
        Level.add(lvl2);
        add(menuBar, BorderLayout.NORTH); // add menu bar to gui
    }

    /**
     * This method is used to set recording to true
     */
    public void startRecording() {
        isRecording = true;
        try {
            Recorder.saveBoard(l1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * This method is used to set recording to false
     */
    public void stopRecording() {
        if (isRecording) { // if recording
            try {
                Recorder.saveRecording(); // save recording
                isRecording = false; // set recording to false
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JDOMException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "You must be recording to save a recording");
        }
    }

    public void aReplayGame() {
        // if (isRecording) {
        // JOptionPane.showMessageDialog(null, "You must stop recording to replay");
        // } else{
        // gameState = pauseState;
        // gameLevel = replay;
        // }
        gameState = pauseState;
        gameLevel = replay;
        setUpLevel();
    }

    public void setSpeed1() {
        Recorder.setReplaySpeed(1);
        gameState = pauseState;
        gameLevel = replay;
        replayType = auto;
        setUpLevel();
    }

    public void setSpeed150() {
        Recorder.setReplaySpeed(1.50);
        gameState = pauseState;
        gameLevel = replay;
        replayType = auto;
        setUpLevel();
    }

    public void setSpeed200() {
        Recorder.setReplaySpeed(2.0);
        gameState = pauseState;
        gameLevel = replay;
        replayType = auto;
        setUpLevel();
    }

    public void runMreplay() {
        replayType = manual;
        gameState = pauseState;
        gameLevel = replay;
        setUpLevel();
    }

    /**
     * This method is used to populate the menu items
     *
     * @param item       the menu item
     * @param title      the text of the menu item
     * @param keyEvent   the key of the menu item
     * @param inputEvent the mask of the menu item
     */
    public void populateMenuItems(JMenuItem item, String title, int keyEvent, int inputEvent) {
        item.setText(title); // set text
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent)); // set key binding
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                // TODO Auto-generated method stub
                if (item.getText().equals("Exit")) { // if exit shortcut pressed
                    exitQ();
                } else if (item.getText().equals("Save")) { // if save shortcut pressed
                    save(l1, chap);

                } else if (item.getText().equals("Rules")) {
                } else if (item.getText().equals("Load")) {
                    load();
                } else if (item.getText().equals("Load Level 1")) {
                    if (gameLevel == level1 || gameLevel == level2) {
                        // reset game
                        gameLevel = level1;
                        renderMazePanel.stopMusic();
                        setUpLevel();
                    }
                } else if (item.getText().equals("Load Level 2")) {
                    // should be in level 2
                    if (gameLevel == 2) {
                        // reset game
                    }
                }
            }
        });
    }

    /**
     * This method is used to show a popup when the user tries to exit the game
     */
    public void exitQ() {
        gameState = pauseState; // pause game
        System.out.println("exit");
        int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit",
                JOptionPane.YES_NO_OPTION);
        if (exit == JOptionPane.YES_OPTION) { // if yes
            System.exit(0); // exit game
        } else {
            gameState = playState; // if no change game state to playState
            timer.start(); // start timer
        }
    }

    /**
     * This method is used to save the game
     */
    public void save(Level l, Chap c) {
        System.out.println("Saved");
        try {
            Persistency.saveBoard(l, "savedGame.xml", savedGamesURL, c);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // exit
        System.exit(0);
    }

    public static String timeDString; 

    /**
     * This method is used to set up timer
     */
    public void loadLv1Timer() {
        timer = new Timer(1000, new ActionListener() { // populate timer
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (time > 0) { // if time is greater than 0
                    time--; // decrement time
                    // format lv1Time to display as mm:ss
                    timeDString = String.format("%02d:%02d", time / 60, time % 60);
                    System.out.print("\r " + timeDString);
                } else {
                    timer.stop(); // stop timer so that there is no leak
                    JOptionPane.showMessageDialog(null, "Time's Up!"); // show popup
                    gameState = gameOverState; // change game state to game over
                    renderMazePanel.stopMusic();
                    setUpGameOver();
                }
            }
        });
        timer.start();// start timer
    }

    public void load() {
        // try {
        // Level newL = Persistency.loadBoard("savedGame.xml",
        // "src/nz/ac/vuw/ecs/swen225/gp22/persistency/savedGames/");
        // chap = new Chap(newL.getStartingX(), newL.getStartingY(), newL); //pass level
        // renderMazePanel = new RenderMazePanel(newL);
        // renderMazePanel.loadAllImages();
        // renderMazePanel.paintComponent(getGraphics());
        // add(renderMazePanel);
        // } catch (JDOMException | IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
        // System.out .println("Game Lofaded");
        // // fileChooser = new JFileChooser(".");
        // // int res = fileChooser.showOpenDialog(this);
        // // if (res == JFileChooser.APPROVE_OPTION) {
        // // System.out.println(fileChooser.getSelectedFile().getPath());
        // // return fileChooser.getSelectedFile();
        // // }
        // return null;

        System.out.println("GAME LOADED");
        gameLevel = loadState;
        setUpLevel();

    }

    private void remove(Graphics graphics) {
    }

    // //method that deletes current time and replaces it with new time
    // public void updateTimer(int time) {
    // this.timer.stop();
    // this.time = time;
    // }
}
