package nz.ac.vuw.ecs.swen225.gp22.app;

import javax.swing.SwingUtilities;

//run the GUI
public class Main{
    public static int width = 800;
    public static int height = 600;
    public static GUI gui;
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui = new GUI("Chap's Challenge", width, height, 0);
            }
        });
    }
}