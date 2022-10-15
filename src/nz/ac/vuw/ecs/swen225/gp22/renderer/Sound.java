package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound{
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("Sounds/Scruffy.wav");
        soundURL[1] = getClass().getResource("Sounds/Computerchip.wav");
        soundURL[2] = getClass().getResource("Sounds/Key.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e){}
    }

    public void play(){
        clip.start();
    }

    public void loop(){
            clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop(){
        clip.stop();
    }
}