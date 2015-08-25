package twop.weather;

import twop.GamePanel;
import twop.Player;
import twop.util.Vector2;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import java.awt.Graphics;

public class Rain extends Weather {
   private ArrayList<RainParticle> myRainParticles;

   public Rain(int life, GamePanel gamePanel) {
      super(life, gamePanel);
      myRainParticles = new ArrayList<RainParticle>();
   }

   public void update() {
      if (getTick() < 2 * 150) {
      } else if (getTick() < getLife() - 2 * 150) {
         int xPos;
         for (int i = 0; i < 7; i++) {
            xPos = new Random().nextInt(getGamePanel().getGameWidth());
            myRainParticles.add(new RainParticle(new Vector2(xPos, 0)));
         }
         for (int i = 0; i < myRainParticles.size(); i++) {
            myRainParticles.get(i).update();
         }

         Player firstPlayer = getGamePanel().getPlay().getFirstPlayer();
         Player secondPlayer = getGamePanel().getPlay().getSecondPlayer();
         if (firstPlayer.hasEffectKey("fire")) {
            firstPlayer.getEffects().get("fire").setLife(0);
         }
         if (secondPlayer.hasEffectKey("fire")) {
            secondPlayer.getEffects().get("fire").setLife(0);
         }
      } else {}
      tick();
   }

   public void draw(Graphics pen) {
      if (getTick() < 2 * 150) {
         pen.setColor(new Color(0, 0, 0, getTick() / 2));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
      } else if (getTick() < getLife() - 2 * 150) {
         pen.setColor(new Color(0, 0, 0, 150));
         pen.fillRect(0, 0, getGamePanel().getGameWidth(), getGamePanel().getGameHeight());
         for (int i = 0; i < myRainParticles.size(); i++) {
            myRainParticles.get(i).draw(pen);
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
   }
}