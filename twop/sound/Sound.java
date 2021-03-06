package twop.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
   private static boolean myEnabled = true;

   private AudioInputStream myAudioIn;
   private Clip myClip;
   private FloatControl myGainControl;

   public Sound(String fileName, boolean autoClose) {
      if (! fileName.contains(".wav")) {
         fileName += ".wav";
      }

      loadSound(fileName);

      if (autoClose) {
         myClip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent myLineEvent) {
               if (myLineEvent.getType() == LineEvent.Type.STOP)
                  myClip.close();
            }
         });
      }
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
      if (myEnabled) {
         myClip.start();
      }
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

   public void close() {
      myClip.close();
   }

   public void closeIfFinished() {
      if (! isRunning()) {
         close();
      }
   }

   public static void enable() {
      myEnabled = true;
   }

   public static void disable() {
      myEnabled = false;
   }
}