package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Timer;

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
import javax.swing.JTextField;
import javax.swing.KeyStroke;
// import javax.swing.Renderer;

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

public class GUI extends JFrame {

    // menu Dock
    private JMenuBar menuBar;

    private KeyStroke exitWindow;
    private KeyStroke saveGame;
    private KeyStroke escape;
    private KeyStroke loadGame;
    private KeyStroke loadL1;
    private KeyStroke loadL2;

    private int pauseKey = KeyEvent.VK_SPACE;

    private Action exitAction;
    private Action saveAction;
    private Action loadAction;
    private Action loadlevel1;
    private Action loadlevel2;

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

    public final int upArrow = KeyEvent.VK_UP;
    public final int downArrow = KeyEvent.VK_DOWN;
    public final int leftArrow = KeyEvent.VK_LEFT;
    public final int rightArrow = KeyEvent.VK_RIGHT;

    private JFileChooser fileChooser;
    // private File l1;
    // private File l2;
    private JButton start = new JButton("Start");
    private JButton exit = new JButton("Exit");
    private JButton load = new JButton("Load");
    private JButton save = new JButton("Save");
    private JButton pause = new JButton("Pause");

    private static boolean isRecording = false;
    private boolean isPaused = false;
    private int lvl;
    public static RenderMazePanel r1;
    public static GUI g1;
    private Recorder recorder = new Recorder();
    private Timer t1;
    private Timer t2;
    public static Chap chap;
    private int lv1Time = 60;
    private int lv2Time = 60;

    public GUI(String title, int width, int height, int level) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        this.lvl = level;
        getContentPane().setBackground(new Color(0, 110, 51));
        setUpLevel();
    }

    public void setUpLevel() {
        if (lvl == 0) {
            level0();
        } else if(lvl == 1){
            loadLv1Timer();
            addComponents();
            this.setFocusTraversalKeysEnabled(false);
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == pauseKey) {
                        pause();
                    } else if (isPaused == false) {
                        if (e.getKeyCode() == upArrow) {
                            System.out.println("up");
                            r1.repaint();
                            chap.moveUp(); 
                            if (isRecording) {
                                recorder.chapMove(Direction.UP); // calling Recorder enum
                            }
                        } else if (e.getKeyCode() == downArrow) {
                            System.out.println("down");
                            r1.repaint();
                            chap.moveDown();
                            if (isRecording) {
                                recorder.chapMove(Direction.DOWN);
                            }
                        } else if (e.getKeyCode() == leftArrow) {
                            System.out.println("left");
                            r1.repaint();
                            chap.moveLeft();
                            if (isRecording) {
                                recorder.chapMove(Direction.LEFT);
                            }
                        } else if (e.getKeyCode() == rightArrow) {
                            System.out.println("right");
                            r1.repaint();
                            chap.moveRight();
                            if (isRecording) {
                                recorder.chapMove(Direction.RIGHT);
                            }
                        }
                    }
                }
            });
        }
    }
    

    public void level0() {
        JPanel panel = new JPanel();
        // array of buttons
        JButton[] lvl0Buttons = { start, exit, load };
        panel.setLayout(new GridLayout(1, 1));
        panel.add(start);
        panel.add(load);
        panel.add(exit);
        panel.setBackground(new Color(0, 110, 51));
        add(panel, BorderLayout.SOUTH);
        // add action listeners to buttons using lambda expressions
        for (JButton b : lvl0Buttons) {
            b.addActionListener(e -> {
                if (e.getSource() == start) {
                    try {
                        loadLevel1();
                        
                    } catch (JDOMException e1) {
                        // print error message
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    // setUpLevel();
                } else if (e.getSource() == exit) {
                    exit();
                } else if (e.getSource() == load) {
                    load();
                }
            });
        }
        populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadGame, loadAction, "Load", KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    }

    public void loadLevel1() throws JDOMException, IOException {
        this.dispose();
        g1 = new GUI("Level 1", 800, 600, 1);
        g1.setVisible(true);
     
        Level l1 = Persistency.loadBoard("level1.xml");
        r1 = new RenderMazePanel(l1);
        chap = new Chap(l1.getStartingX(), l1.getStartingY());
        r1.loadAllImages();
        g1.add(r1);

        r1.paintComponent(g1.getGraphics());
        lvl = 1;
        this.dispose();
    }

    public void loadLevel2() {
        this.dispose();
        GUI g2 = new GUI("Level 2", 800, 600, 2);
        g2.setVisible(true);
        lvl = 2;
        this.dispose();
    }

    public void addComponents() {
        addButtons();
        addMenu();
        populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(saveGame, saveAction, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadGame, loadAction, "Load", KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadL1, loadlevel1, "Load Level 1", KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadL2, loadlevel2, "Load Level 2", KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK);

        
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

        populateMenuItems(saveItem, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(exitItem, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(rulesItem, "Rules", KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(loadItem, "Load", KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(lvl1, "Load Level 1", KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(lvl2, "Load Level 2", KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK);

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
        isRecording = true;
        save.setEnabled(isRecording);
        System.out.println("Recording started");
    }

    public void stopRecording() {
        isRecording = false;
        save.setEnabled(isRecording);
        System.out.println("Recording stopped");
        save();
    }

    public void addButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        var buttonPanel = new JPanel();
        buttonPanel.setFocusable(false);
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.setBackground(new Color(0, 110, 51));
        buttons.addAll(Arrays.asList(pause, save, load, exit));
        for (JButton jb : buttons) {
            buttonPanel.add(jb);
            jb.setFocusable(false);
        }
        for (JButton b : buttons) {
            b.setFocusCycleRoot(false);
            b.addActionListener(e -> {
                if (b.getText().equals("Pause")) {
                    pause();
                } else if (b.getText().equals("Save")) {
                    save();
                } else if (b.getText().equals("Load")) {
                    load();
                } else if (b.getText().equals("Exit")) {
                    exit();
                }
            });
        }

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void startGame() {
        start.addActionListener(e -> {
            try {
                loadLevel1();
            } catch (JDOMException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        start.setVisible(false);
    }

    public void exit() {
        System.exit(0);
    }

    public void save() {
        if (isRecording == false) {
            JOptionPane.showMessageDialog(this, "Game is not being recorded");
        } else {
            Recorder.saveRecording();
            JOptionPane.showMessageDialog(this, "Game Saved");
        }
    }

    public File load() {
        // System.out.println("Game Loaded");
        fileChooser = new JFileChooser(".");
        int res = fileChooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public void pause() {
        isPaused = true;
        t1.stop();
        ArrayList<JButton> buttons = new ArrayList<>();
        JPanel pausePanel = new JPanel();
        pausePanel.setFocusable(false);
        pausePanel.setFocusCycleRoot(false);
        JDialog pauseWindow = new JDialog();
        var p = new JLabel("Game Paused");
        var resumeButton = new JButton("Resume");
        var saveButton = new JButton("Save & Exit");
        var exitUnsaved = new JButton("Exit without Saving");
        populatePopUp(pauseWindow, "Pause", 400, 200, false);
        p.setHorizontalAlignment(JLabel.CENTER);
        pauseWindow.add(p, BorderLayout.CENTER);
        buttons.add(resumeButton);
        buttons.add(saveButton);
        buttons.add(exitUnsaved);
        for (JButton button : buttons) {

            button.addActionListener((ActionEvent e) -> {
                if (button.getText().equals("Resume")) {
                    isPaused = false;
                    t1.start();
                    pauseWindow.dispose();
                    System.out.println("Game Resumed  " + isPaused);
                } else if (button.getText().equals("Save & Exit")) {
                    if (isRecording == true) {
                        save();
                        isPaused = false;
                        pauseWindow.dispose();
                        exit();
                    } else {
                        JOptionPane.showMessageDialog(this, "Game is not being recorded");
                        isPaused = false;
                        pauseWindow.dispose();
                    }
                } else if (button.getText().equals("Exit without Saving")) {
                    pauseWindow.dispose();
                    exit();
                }
            });
            pausePanel.add(button);
        }
        escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        pauseWindow.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escape, "ESCAPE");
        pauseWindow.getRootPane().getActionMap().put("ESCAPE", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isPaused = false;
                t1.start();
                pauseWindow.dispose();
            }
        });
        pauseWindow.add(pausePanel, BorderLayout.SOUTH);

    }
    public void rules() {
        JPanel rulesPanel = new JPanel();
        JDialog rulesWindow = new JDialog();
        var tArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(tArea);
        var okButton = new JButton("OK");
        populatePopUp(rulesWindow, "Rules", 300, 200, true);
        tArea.setEditable(false);
        tArea.setLineWrap(true);
        tArea.setWrapStyleWord(true);
        tArea.setText(
                "Rules of Chap's Challenge:\n\n1. Move Chap around the maze using the arrow keys.\n\n2. Collect all the keys to unlock the door.\n\n3. Collect all the gems to win the game.\n\n4. Avoid the ghosts and the fire.\n\n5. Press space to pause the game.\n\n6. Press escape to exit the game.\n\n7. Press ctrl + s to save the game.\n\n8. Press ctrl + x to exit the game.\n\n9. Press ctrl + h to view the rules.\n\n10. Press ctrl + r to resume a saved game -.\n\n11. Press ctrl + 1 to start a new game at level 1.\n\n12. Press ctrl + 2 to start a new game at level 2.");
        scroll.getVerticalScrollBar().setValue(0);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rulesWindow.add(scroll, BorderLayout.CENTER);
        okButton.addActionListener((ActionEvent e) -> {
            rulesWindow.dispose();
        });
        rulesWindow.add(rulesPanel, BorderLayout.SOUTH);
    }

    public void populatePopUp(JDialog window, String title, int width, int height, boolean resizable) {
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(resizable);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setTitle(title);
    }

    /**
     * 
     * @param kStroke    - KeyStroke to be added to the input map
     * @param action     - Action to be performed when the key is pressed
     * @param actionName - Name of the action
     * @param keyEvent   - KeyEvent to be added to the input map
     * @param inputEvent - InputEvent to be added to the input map
     * 
     *                   This method is used to add key bindings to the game.
     *                   It takes in a key stroke, an action, an action name, a key
     *                   event and an input event.
     *                   It then adds the key stroke to the input map and the action
     *                   to the action map.
     * 
     */

    public void populateShortCuts(KeyStroke kStroke, Action action, String actionName, int keyEvent, int inputEvent) {
        kStroke = KeyStroke.getKeyStroke(keyEvent, inputEvent);
        action = new AbstractAction(actionName) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (actionName.equals("Exit")) {
                    exit();
                } else if (actionName.equals("Save")) {
                    save();
                    isRecording = false;
                } else if (actionName.equals("Load")) {
                    load();
                } else if (actionName.equals("Load Level 1")) {
                    try {
                        loadLevel1();
                    } catch (JDOMException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (actionName.equals("Load Level 2")) {
                    loadLevel2();
                }
            }
        };
        getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(kStroke, actionName);
        getRootPane().getActionMap().put(actionName, action);
    }

    /**
     * 
     * @param item       - the item to be added to the inventory
     * @param title      - the title of the item
     * @param keyEvent   - the key event to be used for the shortcut
     * @param inputEvent - the input event to be used for the shortcut
     * 
     *                   This method populates the menu bar with the menu items and
     *                   shortcuts
     */
    public void populateMenuItems(JMenuItem item, String title, int keyEvent, int inputEvent) {
        item.setText(title);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (item.getText().equals("Exit")) {
                    exit();
                } else if (item.getText().equals("Save")) {
                    save();
                    isRecording = false;
                } else if (item.getText().equals("Rules")) {
                    rules();
                } else if (item.getText().equals("Load")) {
                    load();
                } else if (item.getText().equals("Load Level 1")) {
                    try {
                        loadLevel1();
                    } catch (JDOMException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } else if (item.getText().equals("Load Level 2")) {
                    loadLevel2();
                }
            }
        });
    }

    public void loadLv1Timer(){
    t1 = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lv1Time > 0) {
                lv1Time--;
                //format lv1Time to display as mm:ss
                String timeDString = String.format("%02d:%02d", lv1Time / 60, lv1Time % 60);
                System.out.print("\r " + timeDString);
            } else {
                t1.stop();
                JOptionPane.showMessageDialog(null, "Time's Up!");
                exit();
            }
        }
    });
    t1.start();
    }

    //method that restarts the timer
    public void restartTimer(){
        if(lvl == 1)t1.restart();
        else if(lvl == 2)t2.restart();
        else t1.restart(); t2.restart();
    }

}
