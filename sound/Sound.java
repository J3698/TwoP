package VectorGame;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

public class Sound {

   private URL myFile;
   private AudioInputStream myAudioIn;
   private Clip myClip;
   private FloatControl myGainControl;
  
   public Sound(String file) {
      try {
         myFile = this.getClass().getClassLoader().getResource(file);
         myAudioIn = AudioSystem.getAudioInputStream(myFile);
         myClip = AudioSystem.getClip();
         myClip.open(myAudioIn);
         myGainControl = (FloatControl) myClip.getControl(FloatControl.Type.MASTER_GAIN);
      } catch (UnsupportedAudioFileException exception) {
         exception.printStackTrace();
      } catch (IOException exception) {
         exception.printStackTrace();
      } catch (LineUnavailableException exception) {
         exception.printStackTrace();
      }
   }

   public void play() {
      myClip.start();
   }

   public void loop(int repetitions) {
      myClip.loop(repetitions - 1);
   }

   public void loop() {
      myClip.loop(Clip.LOOP_CONTINUOUSLY);
   }

   public void stop() {
      if(myClip.isRunning())
         myClip.stop();
   }

   public boolean isRunning() {
      return myClip.isRunning();
   }

   public void reset() {
      myClip.setFramePosition(0);
   }

   public void changeVolume(float decibels) {
      myGainControl.setValue(decibels);
   }

   public static void main(String[] args) {
   }
}