package nz.ac.vuw.ecs.swen225.gp22.app;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GUI extends JFrame{
    
    //menu Dock
    private JMenuBar menuBar;
    private JPanel startPanel;
    private JButton startButton;
    private JLabel WelcomeText;

    private KeyStroke exitWindow;
    private KeyStroke saveGame;

    private final int pauseGame = KeyEvent.VK_SPACE;

    private Action exitAction;
    private Action saveAction;

    private JMenuItem Exit;
    private JMenuItem Save;

    private ArrayList<JMenuItem> menuItems = new ArrayList<>();


    //run the GUI
    public GUI(String title, int width, int height, int delay){
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

        //start button has a listener so that when clicked, it will start the game
        startButton = new JButton("Start");
        // startButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         exit();
        //     }
        // });
        //add start panel to frame
        startPanel = new JPanel();
        startPanel.setLayout(new FlowLayout());
        startPanel.add(startButton);
        
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

        populateMenuItems(Exit, "Exit", KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        populateMenuItems(Save, "Save", KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
        
        menuItems.add(Exit);
        menuItems.add(Save);
        Game.add(Save);
        Game.add(Exit);
        
        add(WelcomeText, BorderLayout.CENTER);
        add(startPanel, BorderLayout.SOUTH);
        add(menuBar, BorderLayout.NORTH);
        //add a background colour different shade of green
        getContentPane().setBackground(new Color(0,110,51));
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        timer.start();   
        //shortcuts
        exitShortCut();  //ctrl + x to exit

    }
    //exit window
    public void exit(){
        System.exit(0);
    }

    //shortcut to exit the game
    public void exitShortCut(){
        exitWindow = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
        exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        };
        getRootPane().getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(exitWindow, "Exit");
        getRootPane().getActionMap().put("Exit", exitAction);
    }

    //populate Menu Items
    public void populateMenuItems(JMenuItem item, String title, int keyEvent, int inputEvent){
        item.setText(title);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuItems.forEach((menuItem) -> {
                    if(menuItem.getText().equals("Exit")){
                        exit();
                    }
                });
            }
        });
    }
}