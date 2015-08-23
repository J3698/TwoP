package twop.util;

import twop.GamePanel;

import java.util.Random;
import java.awt.Graphics;

public class Camera {
   private GamePanel myGamePanel;

   private Vector2 myDefaultPos1;
   private Vector2 myDefaultPos2;
   private Vector2 myPos1;
   private Vector2 myPos2;

   public Camera(GamePanel gamePanel) {
      myGamePanel = gamePanel;
      myDefaultPos1 = new Vector2(0, 0);
      myDefaultPos2 = new Vector2(myGamePanel.getGameWidth(),
                                  myGamePanel.getGameHeight());
      myPos1 = myDefaultPos1.copy();
      myPos2 = myDefaultPos2.copy();
   }

   public void update() {
      reset();
//      myPos1.addVector(Vector2.randVector(-3, -3, 0, 0));
      myPos2.addVector(Vector2.randVector(0, 0, 30, 30));
   }

   public void reset() {
      myPos1 = myDefaultPos1.copy();
      myPos2 = myDefaultPos2.copy();
   }

   public Vector2 getPos1() {
      Vector2 pos1 = myPos1.copy();
      pos1.divideX(myGamePanel.getGameWidth() / (double) myGamePanel.getWidth());
      pos1.divideY(myGamePanel.getGameHeight() / (double) myGamePanel.getHeight());
      return pos1;
   }
   public Vector2 getPos2() {
      Vector2 pos2 = myPos2.copy();
      pos2.divideX(myGamePanel.getGameWidth() / (double) myGamePanel.getWidth());
      pos2.divideY(myGamePanel.getGameHeight() / (double) myGamePanel.getHeight());
      return pos2;
   }
}