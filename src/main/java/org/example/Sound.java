package org.example;
import java.io.File;
import  java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(){
        try {
            soundURL[0] = new File("src/main/resources/sound/Music.wav").toURI().toURL();
            soundURL[1] = new File("src/main/resources/sound/Open.wav").toURI().toURL();
            soundURL[2] = new File("src/main/resources/sound/Pickup.wav").toURI().toURL();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception ex){
            ex.printStackTrace();
        }
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
