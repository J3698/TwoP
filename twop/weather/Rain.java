package twop.weather;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import twop.GamePanel;
import twop.Player;
import twop.particlesys.ParticleSystem;
import twop.sound.Sound;
import twop.util.Vector2;

public class Rain extends Weather {
   private static int myDarkness = 100;
   private int myIntensity;

   private boolean myLightning;
   private int myLightningTick;

   private Sound mySound;
   private boolean soundStarted;

   private ParticleSystem myRain;

   public Rain(int life, GamePanel gamePanel) {
      super(life, gamePanel);
      myIntensity = 7;
      myLightning = false;
      myLightningTick = 0;
      mySound = new Sound("rain", false);
      soundStarted = false;
      Vector2 rainPos = new Vector2(getGamePanel().getGameWidth() / 2, 0);
      myRain = new ParticleSystem("rain", rainPos);
      myRain.setEmissionRate(myIntensity);
   }

   @Override
   public void update() {
      if (getTick() < 2 * myDarkness) {
      } else if (getTick() < getLife() - 2 * myDarkness) {
         myRain.update();

         Player firstPlayer = getGamePanel().getPlay().getFirstPlayer();
         Player secondPlayer = getGamePanel().getPlay().getSecondPlayer();
         if (firstPlayer.hasEffectKey("fire")) {
            firstPlayer.getEffects().get("fire").setLife(0);
         }
         if (secondPlayer.hasEffectKey("fire")) {
            secondPlayer.getEffects().get("fire").setLife(0);
         }

         if (new Random().nextInt(300) == 0 && myLightning == false) {
            myLightning = true;
            myLightningTick = getTick();
            new Sound("thunder", true).play();
         }
         if (getTick() - myLightningTick == 20) {
            myLightning = false;
         }

         if (! soundStarted) {
            mySound.loop();
            soundStarted = true;
         }
      } else {
         if (soundStarted) {
            mySound.close();
            soundStarted = false;
         }
      }
      tick();
   }

   @Override
   public void draw(Graphics pen) {
      if (getTick() < 2 * myDarkness) {
         pen.setColor(new Color(0, 0, 0, getTick() / 2));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
      } else if (getTick() < getLife() - 2 * myDarkness) {
         pen.setColor(new Color(0, 0, 0, myDarkness));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
         //         for (int i = 0; i < myRainParticles.size(); i++) {
         //            myRainParticles.get(i).draw(pen);
         myRain.draw(pen);
         //      }

         if (getTick() - myLightningTick < 10) {
            pen.setColor(Color.white);
            pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
         } else if (getTick() - myLightningTick < 20) {
            pen.setColor(new Color(255, 255, 255, 150));
            pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
         }

      } else {
         pen.setColor(new Color(0, 0, 0, (getLife() - getTick()) / 2));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
      }
   }

   @Override
   public void close(){
      mySound.close();
   }
}