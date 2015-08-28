package twop.sound;

import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

public class Sound {

   private AudioInputStream myAudioIn;
   private Clip myClip;
   private FloatControl myGainControl;
  
   public Sound(String fileName) {
      if (fileName.equalsIgnoreCase("crackling")) {
         fileName = "crackling.wav";
      } else if (fileName.equalsIgnoreCase("healthpackage")) {
         fileName = "healthpackage.wav";
      } else if (fileName.equalsIgnoreCase("keytyped")) {
         fileName = "keytyped.wav";
      } else if (fileName.equalsIgnoreCase("rain")) {
         fileName = "rain.wav";
      } else if (fileName.equalsIgnoreCase("thunder")) {
         fileName = "thunder.wav";
      }
      loadSound(fileName);
   }

   private void loadSound(String fileName) {
      try {
         URL url = this.getClass().getResource(fileName);
         myAudioIn = AudioSystem.getAudioInputStream(url);
         AudioFormat format = myAudioIn.getFormat();
         DataLine.Info info = new DataLine.Info(Clip.class, format);
         myClip = (Clip) AudioSystem.getLine(info);
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
      if (myClip.isRunning()) {
         myClip.stop();
         myClip.flush();
      }
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
}