 package nz.ac.vuw.ecs.swen225.gp22.renderer;

// import java.io.IOException;
// import java.net.URL;

// import javax.sound.sampled.AudioInputStream;
// import javax.sound.sampled.AudioSystem;
// import javax.sound.sampled.Clip;

// public class Sound {
//     Clip soundclip;
//     URL soundURL[] = new URL[10000]; 

//     public Sound(){
//         soundURL[0] = getClass().getResource("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sounds/Scruffy.wav");
//     }

//     public void setFile(int i){
//         try {
//             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[0]);
//             soundclip = AudioSystem.getClip();
//             soundclip.open(audioInputStream);
//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }

//     public void play(){
//         if(soundclip!=null){
//             soundclip.start();
//         } else{
//             System.out.println("null pussio");
//         }
//     }

//     public void loop(){
//         if(soundclip!=null){
//         soundclip.loop(Clip.LOOP_CONTINUOUSLY);
//         } else{
//             System.out.println("null pussio");
//         }
//     }

//     public void stop(){
//         soundclip.stop();
//     }

// }

import java.io.File; 
import java.io.IOException; 
import java.util.Scanner; 
  
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 
  
public class Sound { 
  
    // to store current position 
    Long currentFrame; 
    Clip soundClip; 
      
    // current status of clip 
    String status; 
    
    AudioInputStream audioInputStream; 
    AudioInputStream[] audio; 

    static String filePath = "src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sounds/Scruffy.wav"; 
  
    // constructor to initialize streams and clip 
    public Sound() 
        throws UnsupportedAudioFileException, 
        IOException, LineUnavailableException  
    { 
        // create AudioInputStream object 
        // audio[0]
        // audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
        // // audio[1] = AudioSystem.getAudioInputStream(new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sounds/ComputerChip.wav").getAbsoluteFile());
        // // audio[2] = AudioSystem.getAudioInputStream(new File("src/nz/ac/vuw/ecs/swen225/gp22/renderer/Sounds/Key.wav").getAbsoluteFile());  
          
        // // create clip reference 
        // soundClip = AudioSystem.getClip(); 
          
        // // open audioInputStream to the clip 
        // soundClip.open(audioInputStream);
        // soundClip.loop(Clip.LOOP_CONTINUOUSLY);
        audioInputStream =  
                AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
          
        // create clip reference 
        soundClip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        soundClip.open(audioInputStream);  
    } 

    // public void setFile(int i){
    //     try {
    //         soundClip.open(audio[i]);
    //     } catch (LineUnavailableException | IOException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }

    public void play(){
        soundClip.start();
    }

    public void loop(){
        soundClip.loop(Clip.LOOP_CONTINUOUSLY); 
    }

    public void stop(){
        soundClip.stop();
    }
}