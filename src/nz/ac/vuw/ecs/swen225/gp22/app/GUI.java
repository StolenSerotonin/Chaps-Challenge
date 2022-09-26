package nz.ac.vuw.ecs.swen225.gp22.app;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


public class GUI extends JFrame{
    
    //menu Dock
    private JMenuBar menuBar;
    
    private JLabel WelcomeText;
    
    private KeyStroke exitWindow;
    private KeyStroke saveGame;
    private KeyStroke escape;
    
    private int pause = KeyEvent.VK_SPACE;
    
    private Action exitAction;
    private Action saveAction;
    
    private JMenuItem Exit;
    private JMenuItem Save;
    private JMenuItem Rules;
    
    
    private ArrayList<JMenuItem> menuItems = new ArrayList<>();
    
    //run the GUI
    public GUI(String title, int width, int height, int delay){
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
        
        WelcomeText = new JLabel("Welcome to Chap's Challenge!");
        WelcomeText.setFont(new Font("Monospaced", Font.BOLD, 40));
        WelcomeText.setForeground(Color.WHITE);
        WelcomeText.setHorizontalAlignment(JLabel.CENTER);
        
        //add menu bar to frame
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
        
        populateMenuItems(Save, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(Exit, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(Rules, "Rules", KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK);
        
        menuItems.add(Save);
        menuItems.add(Exit);
        menuItems.add(Rules);
        
        Game.add(Save);
        Game.add(Exit);
        Help.add(Rules);
        
        add(WelcomeText, BorderLayout.CENTER);
        add(menuBar, BorderLayout.NORTH);
        //add a background colour different shade of green
        getContentPane().setBackground(new Color(0,110,51));
        
        //add listen for when space is pressed
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == pause){
                    pause();
                }
            }
        });
        
        populateShortCuts(exitWindow, exitAction, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateShortCuts(saveGame, saveAction, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
    }
    //exit window
    public void exit(){
        System.exit(0);
    }
    public void save(){
        System.out.println("Game Saved");
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
                if(actionName.equals("Exit")){
                    exit();
                }
                else if(actionName.equals("Save")){
                    save();
                }
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
                if(item.getText().equals("Exit")){
                    exit();
                }
                else if(item.getText().equals("Save")){
                    save();
                }
                else if(item.getText().equals("Rules")){
                    rules();
                }
            }
        });
    }
}