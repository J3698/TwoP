package twop;

import twop.util.Vector2;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class Gun {
   public static int RIGHT = 1;
   public static int LEFT = -1;
   private int mySpinDirection = RIGHT;
   private boolean myIsSpinning = true;
   private Player myPlayer;
   private double myOldPlayerX;
   private double myOldPlayerY;
   private double mySpeed = 7;
   private double myAngle = 0;
   private double[] myXPoints = new double[4];
   private double[] myYPoints = new double[4];
   private ArrayList<Bullet> myBullets = new ArrayList<Bullet>();
   private boolean myIsShooting = false;

   public Gun (Player p) {
      myPlayer = p;
      myOldPlayerX = myPlayer.getCenter().getX();
      myOldPlayerY = myPlayer.getCenter().getY() - myPlayer.getRadius();
      initializePoints();
   }

   private void shoot() {
      double x = (myXPoints[0] + myXPoints[2]) / 2;
      double y = (myYPoints[0] + myYPoints[1]) / 2;
      double velocityX = (myXPoints[3] - myXPoints[0]) * mySpinDirection;
      double velocityY = (myYPoints[3] - myYPoints[0]) * mySpinDirection;
      Vector2 center = new Vector2(x, y);
      Vector2 velocity = new Vector2(velocityX, velocityY);
      myBullets.add(new Bullet(center, velocity));
   }

   public void update() {
      fixPosition();
      if (myIsSpinning) {
         myAngle += mySpinDirection * mySpeed;
         rotatePoints(myAngle);
      }
      for (Bullet b: myBullets)
         b.update();
      if (myIsShooting)
         shoot();
      garbageCollectBullets();
   }

   public void draw(Graphics pen) {
      for (Bullet b: myBullets)
         b.draw(pen);
      initializePoints();
      rotatePoints(myAngle);
      int[] tempXPoints = new int[4];
      int[] tempYPoints = new int[4];
      for (int i = 0; i < 4; i++)
         tempXPoints[i] = (int)myXPoints[i];
      for (int i = 0; i < 4; i++)
         tempYPoints[i] = (int)myYPoints[i];
      pen.setColor(Color.black);
      pen.drawPolygon(tempXPoints, tempYPoints, 4);
      pen.setColor(myPlayer.getColor());
      pen.fillPolygon(tempXPoints, tempYPoints, 4);
   }

   public void garbageCollectBullets() {
      ArrayList<Bullet> toTrash = new ArrayList<Bullet>();
      for (Bullet b: myBullets) {
         boolean outLeft = (myPlayer.getBounds().getX() > b.getCenter().getX());
         boolean outBottom = (myPlayer.getBounds().getHeight() < b.getCenter().getY());
         boolean outRight = (myPlayer.getBounds().getWidth() < b.getCenter().getX());
         boolean outTop = (myPlayer.getBounds().getY() > b.getCenter().getY());
         if (outLeft || outBottom || outRight || outTop)
            toTrash.add(b);
      }
      for (Bullet b: toTrash)
         myBullets.remove(b);
   }

   public void rotatePoints(double angle) {
      angle = Math.toRadians(angle);
      while(Math.abs(angle) > 2 * Math.PI){
         angle -= Math.abs(angle) / angle * 2 * Math.PI;
      }
      double sin = Math.sin(angle);
      double cos = Math.cos(angle);
      for (int i = 0; i < 4; i++) {
         myXPoints[i] -= myPlayer.getCenter().getX();
         myYPoints[i] -= myPlayer.getCenter().getY();
         double tempX = myXPoints[i];
         myXPoints[i] = myXPoints[i] * cos - myYPoints[i] * sin;
         myYPoints[i] = myYPoints[i] * cos + tempX * sin;
         myXPoints[i] += myPlayer.getCenter().getX();
         myYPoints[i] += myPlayer.getCenter().getY();
      }
   }

   public void initializePoints() {
      myXPoints[0] = myPlayer.getCenter().getX() - 6;
      myXPoints[1] = myXPoints[0];
      myXPoints[2] = myPlayer.getCenter().getX() + 6;
      myXPoints[3] = myXPoints[2];
      myYPoints[0] = myPlayer.getCenter().getY() - myPlayer.getRadius() - 6;
      myYPoints[1] = myPlayer.getCenter().getY() - myPlayer.getRadius() - 12;
      myYPoints[2] = myYPoints[1];
      myYPoints[3] = myYPoints[0];
   }

   public void fixPosition() {
      double xDiff = myPlayer.getCenter().getX() - myOldPlayerX;
      double yDiff = myPlayer.getCenter().getY() - myPlayer.getRadius() - myOldPlayerY;
      for (int i = 0; i < 4; i++)
         myXPoints[i] += xDiff;
      for (int i = 0; i < 4; i++)
         myYPoints[i] += yDiff;
      myOldPlayerX = myPlayer.getCenter().getX();
      myOldPlayerY = myPlayer.getCenter().getY() - myPlayer.getRadius();
   }

   public ArrayList<Bullet> getBullets() { return myBullets; }
   public void setIsSpinning(boolean isSpinning) { myIsSpinning = isSpinning; }
   public void flipIsSpinning() { myIsSpinning = !myIsSpinning; }
   public void setSpinDirection(int spinDirection) { mySpinDirection = spinDirection; }
   public void flipSpinDirection() { mySpinDirection *= -1; }
   public void setShooting(boolean isShooting) { myIsShooting = isShooting; }

   public class Bullet extends Circle {
      private Vector2 myVelocity;

      public Bullet(Vector2 center, Vector2 velocity) {
         super(center, 3);
         myVelocity = velocity;
      }

      public void update() {
         getCenter().addX(myVelocity.getX());
         getCenter().addY(myVelocity.getY());
      }
   }
}
