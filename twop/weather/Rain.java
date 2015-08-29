package twop.weather;

import twop.GamePanel;
import twop.Player;
import twop.sound.Sound;
import twop.util.Vector2;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Rain extends Weather {
   private static int myDarkness = 100;
   private ArrayList<RainParticle> myRainParticles;
   private int myIntensity;
   private boolean myLightning;
   private int myLightningTick;

   public Rain(int life, GamePanel gamePanel) {
      super(life, gamePanel);
      myRainParticles = new ArrayList<RainParticle>();
      myIntensity = 3;
      myLightning = false;
      myLightningTick = 0;
   }

   public void update() {
      if (getTick() < 2 * myDarkness) {
      } else if (getTick() < getLife() - 2 * myDarkness) {
         int xPos;
         for (int i = 0; i < myIntensity; i++) {
            xPos = new Random().nextInt(getGamePanel().getGameWidth());
            myRainParticles.add(new RainParticle(new Vector2(xPos, 0)));
         }

         for (int i = myRainParticles.size() - 1; i >= 0; i--) {
            if (myRainParticles.get(i).isExpired()) {
               myRainParticles.remove(i);
            } else {
               myRainParticles.get(i).update();
            }
         }

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
            new Sound("thunder").play();
         }
         if (getTick() - myLightningTick == 20) {
            myLightning = false;
         }
      } else {}
      tick();
   }

   public void draw(Graphics pen) {
      if (getTick() < 2 * myDarkness) {
         pen.setColor(new Color(0, 0, 0, getTick() / 2));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
      } else if (getTick() < getLife() - 2 * myDarkness) {
         pen.setColor(new Color(0, 0, 0, myDarkness));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
         for (int i = 0; i < myRainParticles.size(); i++) {
            myRainParticles.get(i).draw(pen);
         }

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

   public class RainParticle {
      private Vector2 myPosition;
      private Vector2 myVelocity;

      public RainParticle(Vector2 position) {
         myPosition = position;
         myVelocity = new Vector2(0, 10);
      }

      public void update() {
         myPosition.addVector(myVelocity);
      }
      public void draw(Graphics pen) {
         int x = (int) myPosition.getX();
         int y = (int) myPosition.getY();
         for (int i = 0; i < 5; i++) {
            pen.setColor(new Color(0, 0, 255, 50 * (i + 1)));
            pen.drawLine(x, y + 2 * i, x, y + 2 * i + 2);
         }
      }
      public boolean isExpired() {
         if (myPosition.getY() > getGamePanel().getGameHeight()) {
            return true;
         }
         return false;
      }
   }
}