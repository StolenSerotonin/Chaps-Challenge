package nz.ac.vuw.ecs.swen225.gp22.app;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.*;

import org.jdom2.JDOMException;

public class Display{
    
    private ArrayList<JButton> buttons = new ArrayList<>();
    
    GUI guiPanel;
    Graphics2D gr2;
    

    
    private JMenuBar gameMenuBar;
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
    
    public Display(GUI guiPanel){
        this.guiPanel = guiPanel;
        this.gr2 = (Graphics2D) guiPanel.getGraphics();
    }
    
    public void addButtons(){}
    
    public void dispPause(){}
    
    public void dispTitle(){}
    
    public void dispGameOver(){}
    
    public void dispGame(){}
    
    public void addMenu() {
        if(gr2 != null){
        gr2.setColor(Color.RED);
        gr2.fillRect(0,0, 100,100);
        System.out.println("here");
        }
    }

    public void startRecording(){}
    public void stopRecording(){}
    
    public void populateMenuItems(JMenuItem item, String title, int keyEvent, int
    inputEvent) {
        item.setText(title);
        item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, inputEvent));
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent arg0) {
                if (item.getText().equals("Exit")) {
                    
                } else if (item.getText().equals("Save")) {
                    
                } else if (item.getText().equals("Rules")) {
                    
                } else if (item.getText().equals("Load")) {
                    
                } else if (item.getText().equals("Load Level 1")) {
                    
                } else if (item.getText().equals("Load Level 2")) {
                    
                }
                
            }
            
            
        });
    }
    
}