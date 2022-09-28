package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
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


public class GUI extends JFrame{
    
    //menu Dock
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
    
    private JMenuItem Exit;
    private JMenuItem Save;
    private JMenuItem Rules;
    private JMenuItem Load;
    private JMenuItem lvl1;
    private JMenuItem lvl2;

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

    private int lvl;
    
    private ArrayList<JMenuItem> menuItems = new ArrayList<>();
    
    public GUI(String title, int width, int height, int level){
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        this.lvl = level;
        getContentPane().setBackground(new Color(0,110,51));
        setLevel();
        this.requestFocus();
    }
    public void setLevel(){
        if (lvl == 0){
            level0();
        }
        else{
            addComponents();
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == pauseKey){
                        pause();
                    }
                }
            });
        }
    }
    public void level0(){
        //add start button to a panel
        JPanel panel = new JPanel();
        //array of buttons
        JButton[] lvl0Buttons = {start, exit, load};
        panel.setLayout(new GridLayout(1,1));
        panel.add(start);
        panel.add(load);
        panel.add(exit);
        panel.setBackground(new Color(0,110,51));
        add(panel, BorderLayout.SOUTH);
        //add action listeners to buttons using lambda expressions
        for(JButton b : lvl0Buttons){
            b.addActionListener(e -> {
                if (e.getSource() == start){loadLevel1(); lvl = 1; setLevel();}
                else if (e.getSource() == exit){exit();}
                else if (e.getSource() == load){load();}});
        }
        populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(saveGame, saveAction, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadGame, loadAction, "Load", KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
    }

    public void loadLevel1(){
        GUI g1 = new GUI("Level 1", 800, 600, 1);
        g1.setVisible(true);
        lvl = 1;
        this.dispose();
    }
    public void loadLevel2(){
        GUI g2 = new GUI("Level 2", 800, 600, 2);
        g2.setVisible(true);
        lvl = 2;
        this.dispose();
    }












    public void addComponents(){
        addButtons();
        addMenu();
        populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(saveGame, saveAction, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadGame, loadAction, "Load", KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadL1, loadlevel1, "Load Level 1", KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(loadL2, loadlevel2, "Load Level 2", KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK);
    }
    public void addMenu(){
        menuBar = new JMenuBar();
        var Game = new JMenu("Game");
        var Options = new JMenu("Options");
        var Level = new JMenu("Level");
        var Help = new JMenu("Help");
        
        menuBar.add(Game);
        menuBar.add(Options);
        menuBar.add(Level);
        menuBar.add(Help);
        
        Exit = new JMenuItem();
        Save = new JMenuItem();
        Rules = new JMenuItem();
        Load = new JMenuItem();
        lvl1 = new JMenuItem();
        lvl2 = new JMenuItem();
        
        populateMenuItems(Save, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(Exit, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(Rules, "Rules", KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(Load, "Load", KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(lvl1, "Load Level 1", KeyEvent.VK_1, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(lvl2, "Load Level 2", KeyEvent.VK_2, InputEvent.CTRL_DOWN_MASK);
        
        menuItems.add(Save);
        menuItems.add(Exit);
        menuItems.add(Rules);
        menuItems.add(Load);
        
        Game.add(Save);
        Game.add(Exit);
        Options.add(Load);
        Help.add(Rules);
        Level.add(lvl1);
        Level.add(lvl2);
        add(menuBar, BorderLayout.NORTH); 
    }

    public void addButtons(){
        ArrayList<JButton>buttons = new ArrayList<>();
        var buttonPanel = new JPanel();
        buttonPanel.setFocusable(false);
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.setBackground(new Color(0,110,51));
        buttons.add(pause); buttons.add(load); buttons.add(save); buttons.add(exit);
        buttonPanel.add(pause); buttonPanel.add(load); buttonPanel.add(save); buttonPanel.add(exit);
        buttons.forEach(button -> button.addActionListener(e -> {
            if(button.getText().equals("Pause")){pause(); setFocusable(false);}
            else if(button.getText().equals("Load")){load(); setFocusable(false);}
            else if(button.getText().equals("Save")){save(); setFocusable(false);}
            else if(button.getText().equals("Exit")){exit(); setFocusable(false);}
            setFocusable(false);
        }));
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void startGame(){
        start.addActionListener(e -> loadLevel1());
        start.setVisible(false);
    }

    public void exit(){
        System.exit(0);
    }
    public void save(){
        JOptionPane.showMessageDialog(this, "Game Saved");
    }
    public File load(){
        //System.out.println("Game Loaded");
        fileChooser = new JFileChooser(".");
        int res = fileChooser.showOpenDialog(this);
        if(res == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }
    //pause game
    public void pause(){
        ArrayList<JButton> buttons = new ArrayList<>();
        JPanel pausePanel = new JPanel();
        JDialog pauseWindow = new JDialog();
        var p = new JLabel("Game Paused");
        var resumeButton = new JButton("Resume");
        var saveButton = new JButton("Save & Exit");
        populatePopUp(pauseWindow, "Pause", 300, 200, false);
        p.setHorizontalAlignment(JLabel.CENTER);
        pauseWindow.add(p, BorderLayout.CENTER);
        buttons.add(resumeButton);  
        buttons.add(saveButton);
        buttons.forEach((button) -> {
            button.addActionListener((ActionEvent e) -> {
                if(button.getText().equals("Resume")){pauseWindow.dispose(); System.out.println("Game Resumed");}
                else if(button.getText().equals("Save & Exit")){
                    save();
                    pauseWindow.dispose();
                    exit();
                }
            });
            pausePanel.add(button);
        });
        escape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        pauseWindow.getRootPane().registerKeyboardAction(e -> pauseWindow.dispose(), escape, JComponent.WHEN_IN_FOCUSED_WINDOW);
        pauseWindow.add(pausePanel, BorderLayout.SOUTH);
    }

    //print rules
    public void rules(){
        JPanel rulesPanel = new JPanel();
        JDialog rulesWindow = new JDialog();
        var tArea = new JTextArea();
        JScrollPane scroll = new JScrollPane(tArea);
        var okButton = new JButton("OK");
        populatePopUp(rulesWindow, "Rules", 300, 200, true);
        tArea.setEditable(false);
        tArea.setLineWrap(true);
        tArea.setWrapStyleWord(true);
        tArea.setText("Rules of Chap's Challenge:\n\n1. Move Chap around the maze using the arrow keys.\n\n2. Collect all the keys to unlock the door.\n\n3. Collect all the gems to win the game.\n\n4. Avoid the ghosts and the fire.\n\n5. Press space to pause the game.\n\n6. Press escape to exit the game.\n\n7. Press ctrl + s to save the game.\n\n8. Press ctrl + x to exit the game.\n\n9. Press ctrl + h to view the rules.\n\n10. Press ctrl + r to resume a saved game -.\n\n11. Press ctrl + 1 to start a new game at level 1.\n\n12. Press ctrl + 2 to start a new game at level 2.");
        scroll.getVerticalScrollBar().setValue(0);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        rulesWindow.add(scroll, BorderLayout.CENTER);
        okButton.addActionListener((ActionEvent e) -> {
            rulesWindow.dispose();
        });
        rulesWindow.add(rulesPanel, BorderLayout.SOUTH);
    }

    //make a method to populate the window with the menu items
    public void populatePopUp(JDialog window, String title, int width, int height, boolean resizable){
        window.setSize(width, height);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setResizable(resizable);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        window.setTitle(title);
    }

    public void populateShortCuts(KeyStroke kStroke, Action action, String actionName, int keyEvent, int inputEvent){
        kStroke = KeyStroke.getKeyStroke(keyEvent, inputEvent);
        action = new AbstractAction(actionName) {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionName.equals("Exit")){exit();}
                else if(actionName.equals("Save")){save();}
                else if(actionName.equals("Load")){load();}
                else if(actionName.equals("Load Level 1")){loadLevel1();}
                else if(actionName.equals("Load Level 2")){loadLevel2();}
            }
        };
        getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(kStroke, actionName);
        getRootPane().getActionMap().put(actionName, action);
    }
    
    //populate Menu Items
    public void populateMenuItems(JMenuItem item, String title, int keyEvent, int inputEvent){
        item.setText(title);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(item.getText().equals("Exit")){exit();}
                else if(item.getText().equals("Save")){save();}
                else if(item.getText().equals("Rules")){rules();}
                else if(item.getText().equals("Load")){load();}
                else if(item.getText().equals("Load Level 1")){loadLevel1();}
                else if(item.getText().equals("Load Level 2")){loadLevel2();}
            }
        });
    }
}