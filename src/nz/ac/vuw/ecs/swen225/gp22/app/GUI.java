package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class GUI extends JFrame{
    
    //menu Dock
    private JMenuBar menuBar;
    private JPanel startPanel;
    private JButton startButton;
    private JPanel gamePanel;
    private JLabel WelcomeText;
    private JLabel timeLabel;
    private JLabel levelLabel;
    private JLabel ChipsLabel;


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
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // startButton.setVisible(false);
                // startPanel.setVisible(false);
                // gamePanel.setVisible(true);
                // WelcomeText.setVisible(false);
                // timeLabel.setVisible(true);
                // levelLabel.setVisible(true);
                // ChipsLabel.setVisible(true);
                exit();
            }
        });
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
        
        add(WelcomeText, BorderLayout.CENTER);
        add(startPanel, BorderLayout.SOUTH);
        add(menuBar, BorderLayout.NORTH);
        //add a background colour different shade of green
        getContentPane().setBackground(new Color(0,110,51));
        
        Timer timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //update the game
                exit();
            }
        });
        timer.start();       
    }
    //update the game by exiting the game
    public void exit(){
        System.exit(0);
    }
    
}