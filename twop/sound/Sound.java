package twop.sound;

import java.io.*;
import javax.sound.sampled.*;

public class Sound {

   private File myFile;
   private AudioInputStream myAudioIn;
   private Clip myClip;
   private FloatControl myGainControl;
  
   public Sound(String fileName) {
      if (fileName.equalsIgnoreCase("crackling"))
         fileName = "twop\\sound\\crackling.wav";
      else if (fileName.equalsIgnoreCase("healthpackage"))
         fileName = "twop\\sound\\healthpackage.wav";
      else if (fileName.equalsIgnoreCase("keytyped"))
         fileName = "twop\\sound\\keytyped.wav";
      loadSound(fileName);
   }

   private void loadSound(String fileName) {
      try {
         myFile = new File(fileName);
         myAudioIn = AudioSystem.getAudioInputStream(myFile);
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