package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.SwingUtilities;

//run the GUI
public class Main{
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI("Chap's Challenge", 800, 600, 0);
            }
        });
    }
}