package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

//this class is used to handle key inputs
public class KeyInput implements KeyListener{

public int up, down, left, right, pause, escape;

GUI guiPanel;

public KeyInput(GUI guiPanel) {
    this.guiPanel = guiPanel;
}
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(guiPanel.gameState == guiPanel.menuState){
            keyStartState(keyCode);
        }else if(guiPanel.gameState == guiPanel.playState && guiPanel.isPaused == false){
            keyPlayState(keyCode);
        }else if(guiPanel.gameState == guiPanel.pauseState && guiPanel.isPaused ==true){
            keyResumeState(keyCode);
        }
    }

    public void keyResumeState(int keyCode){
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("RESUMED");
            guiPanel.gameState = guiPanel.playState;
            guiPanel.isPaused = false;
        }
    }
    public void keyStartState(int keyCode){
        if(keyCode == KeyEvent.VK_ENTER){
            System.out.println("ENTER GAME");
            guiPanel.gameState = guiPanel.playState;
            guiPanel.gameLevel = guiPanel.level1;
            guiPanel.setUpLevel();
        }
    }

    public void keyPlayState(int keyCode){
        if(keyCode == KeyEvent.VK_UP) {
            up = 1;
            // System.out.println("upPressed");
        }
        if(keyCode == KeyEvent.VK_DOWN) {
            //move down
            down = 1;
            // System.out.println("downPressed");

        }
        if(keyCode == KeyEvent.VK_LEFT) {
            //move left
            left = 1;
            // System.out.println("leftPressed");

        }
        if(keyCode == KeyEvent.VK_RIGHT) {
            //move right
            right = 1;
            //System.out.println("rightPressed");

        }
        if(keyCode == KeyEvent.VK_SPACE) {
            guiPanel.gameState = guiPanel.pauseState;
            guiPanel.isPaused = true;
            System.out.println("Paused1");

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_UP) {
            //move up
            up = 0;
        }
        if(key == KeyEvent.VK_DOWN) {
            //move down
            down = 0;
        }
        if(key == KeyEvent.VK_LEFT) {
            //move left
            left = 0;
        }
        if(key == KeyEvent.VK_RIGHT) {
            //move right
            right = 0;
        }
        if(key == KeyEvent.VK_SPACE) {
            //pause
            pause = 0;
        }
        if(key == KeyEvent.VK_ESCAPE) {
            //escape
            escape = 0;
        }
        
        
    } 

}