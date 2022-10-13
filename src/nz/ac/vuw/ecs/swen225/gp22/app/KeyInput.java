package nz.ac.vuw.ecs.swen225.gp22.app;

import java.awt.event.KeyListener;

import javax.swing.Renderer;

import nz.ac.vuw.ecs.swen225.gp22.recorder.Recorder;

import java.awt.event.KeyEvent;

//this class is used to handle key inputs
public class KeyInput implements KeyListener{

public int up, down, left, right, pause, escape, replaying;

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
        }else if(guiPanel.gameState == guiPanel.gameOverState){
            keyGameOverState(keyCode);
        }else if (guiPanel.gameLevel == guiPanel.replay && guiPanel.gameState == guiPanel.pauseState){
            keyReplayState(keyCode);
            keyResumeRepState(keyCode);
        }
    }

    public void keyResumeState(int keyCode){
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("RESUMED");
            GUI.renderMazePanel.playMusic();
            guiPanel.gameState = guiPanel.playState;
            guiPanel.isPaused = false;
            guiPanel.timer.start();
            guiPanel.pauseButton.setText("Pause");
        }
    }
    public void keyStartState(int keyCode){
        if(keyCode == KeyEvent.VK_ENTER){
            System.out.println("ENTER GAME");
            guiPanel.gameState = guiPanel.playState;
            guiPanel.gameLevel = guiPanel.level1;
            guiPanel.setUpLevel();
            guiPanel.timer.start();
        }
    }
    public void keyGameOverState(int keyCode){
        if(keyCode == KeyEvent.VK_ENTER){
            System.out.println("RETRY Level");
            guiPanel.gameState = guiPanel.playState;
            if(guiPanel.gameLevel == guiPanel.level1) guiPanel.gameLevel = guiPanel.level1;
            else if(guiPanel.gameLevel == guiPanel.level2) guiPanel.gameLevel = guiPanel.level2;
            guiPanel.setUpLevel();
        }
        else if(keyCode == KeyEvent.VK_1){
            System.out.println("RETRY Level 1");
            guiPanel.gameState = guiPanel.playState;
            guiPanel.gameLevel = guiPanel.level1;
            guiPanel.setUpLevel();
        }
        else if(keyCode == KeyEvent.VK_2){
            System.out.println("RETRY Level 2");
            guiPanel.gameState = guiPanel.playState;
            guiPanel.gameLevel = guiPanel.level2;
            guiPanel.setUpLevel();
        }
        else if(keyCode == KeyEvent.VK_ESCAPE){
            System.out.println("EXIT GAME");
            System.exit(0);
        }
        else if(keyCode == KeyEvent.VK_M){
            System.out.println("RETURN TO MENU");
            guiPanel.gameState = guiPanel.menuState;
            guiPanel.setUpMenu();
        }
    }

    public void keyReplayState(int keyCode){
        if(keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_SPACE){
            System.out.println("REPLAYING");
            //setupReplay7
            // Recorder.runStepReplay(guiPanel);
            replaying = 1;
        }
    }
    public void keyResumeRepState(int keyCode){
        if(keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("Back to main menu from replaying");
            guiPanel.gameState = guiPanel.menuState;
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
            System.out.println("Paused");
            GUI.renderMazePanel.stopMusic();
            guiPanel.timer.stop();
            guiPanel.pauseButton.setText("Resume");

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
            replaying = 0;
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