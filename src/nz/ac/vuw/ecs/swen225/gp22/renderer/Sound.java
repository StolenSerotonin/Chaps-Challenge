package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class is used to play sounds in the game.
 * 
 * @author livapurane
 *
 */
public class Sound{
    Clip clip;
    URL soundURL[] = new URL[30];

    /* Sound class Constructor
     * 
     */
    public Sound(){
        soundURL[0] = getClass().getResource("Sounds/Scruffy.wav");
        soundURL[1] = getClass().getResource("Sounds/Computerchip.wav");
        soundURL[2] = getClass().getResource("Sounds/Key.wav");
        soundURL[3] = getClass().getResource("Sounds/LevelWin.wav");
    }

    /* Set the sound to be played
     * 
     * @param soundNum - the number of the sound to be played
     */
    public void setFile(int i){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e){}
    }

    /* Start playing the sound
     * 
     */
    public void play(){
        clip.start();
    }

    /*Continue playing the sound
    *
     */
    public void loop(){
            clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    /* Stop playing the sound
     * 
     */
    public void stop(){
        clip.stop();
    }
}