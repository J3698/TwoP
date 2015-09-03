package twop.util;

import twop.GamePanel;

import java.util.Random;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Camera {
   private GamePanel myGamePanel;

   private BufferedImage myImage; 
   private Graphics myPen;

   private Vector2 myDefaultPos1;
   private Vector2 myDefaultPos2;
   private Vector2 myPos1;
   private Vector2 myPos2;

   public Camera(GamePanel gamePanel) {
      myGamePanel = gamePanel;
      myImage = new BufferedImage(myGamePanel.getGameWidth(), myGamePanel.getGameHeight(),
                                                              BufferedImage.TYPE_INT_ARGB);
      myPen = myImage.getGraphics();
      myDefaultPos1 = new Vector2(0, 0);
      myDefaultPos2 = new Vector2(myGamePanel.getGameWidth(),
                                  myGamePanel.getGameHeight());
      myPos1 = myDefaultPos1.copy();
      myPos2 = myDefaultPos2.copy();
   }

   public void update() {
   }

   public void reset() {
      myPos1 = myDefaultPos1.copy();
      myPos2 = myDefaultPos2.copy();
   }

   public void clearImage() {
      myPen.clearRect(0, 0, myImage.getWidth(), myImage.getHeight());
   }

   public Vector2 getScaledPos1() {
      Vector2 pos1 = myPos1.copy();
      pos1.divideX(myGamePanel.getGameWidth() / (double) myGamePanel.getWidth());
      pos1.divideY(myGamePanel.getGameHeight() / (double) myGamePanel.getHeight());
      return pos1;
   }
   public Vector2 getScaledPos2() {
      Vector2 pos2 = myPos2.copy();
      pos2.subtractVector(myPos1);
      pos2.divideX(myGamePanel.getGameWidth() / (double) myGamePanel.getWidth());
      pos2.divideY(myGamePanel.getGameHeight() / (double) myGamePanel.getHeight());
      return pos2;
   }

   public Vector2 getPos1() { return myPos1; }
   public Vector2 getPos2() { return myPos2; }

   public void setPos1(Vector2 pos1) { myPos1 = pos1; }
   public void setPos2(Vector2 pos2) { myPos2 = pos2; }

   public Graphics getPen() { return myPen; }
   public void setPen(Graphics pen) { myPen = pen; }

   public BufferedImage getImage() { return myImage; }

   public void setImageSize(int x, int y) {
      myImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
      myPen = myImage.getGraphics();
   }
   public void setPerspective(Vector2 startPos, Vector2 endPos) {
      Vector2 diff = endPos.copy();
      diff.subtractVector(startPos);
      double xRatio = 1 / (diff.getX() / myGamePanel.getGameWidth());
      double yRatio = 1 / (diff.getY() / myGamePanel.getGameHeight());

      myPos1 = startPos.copy();
      myPos1.multiply(-1);
      myPos1.multiplyX(xRatio);
      myPos1.multiplyY(yRatio);
      myPos2 = new Vector2(myImage.getWidth(), myImage.getHeight());
      myPos2.multiplyX(xRatio);
      myPos2.multiplyY(yRatio);
      myPos2.addVector(myPos1);
   }
}