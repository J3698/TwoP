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
   private double[] myXPoints = new double[4];
   private double[] myYPoints = new double[4];
   private ArrayList<Bullet> myBullets = new ArrayList<Bullet>();
   private boolean myIsShooting = false;

   public Gun (Player p) {
      myPlayer = p;
      initializePoints();
   }

   private void shoot() {
      double x = (myXPoints[0] + myXPoints[2]) / 2;
      double y = (myYPoints[0] + myYPoints[1]) / 2;
      double velocityX = (myXPoints[3] - myXPoints[0]) * mySpinDirection;
      double velocityY = (myYPoints[3] - myYPoints[0]) * mySpinDirection;
      myBullets.add(new Bullet(x, y, velocityX, velocityY));
   }

   public void update() {
      fixPosition();
      if (myIsSpinning)
         rotatePoints(mySpinDirection * mySpeed);
      for (Bullet b: myBullets)
         b.update();
      if (myIsShooting)
         shoot();
      garbageCollectBullets();
   }
   public void draw(Graphics pen) {
      int[] tempXPoints = new int[4];
      int[] tempYPoints = new int[4];
      for (int i = 0; i < 4; i++)
         tempXPoints[i] = (int)myXPoints[i];
      for (int i = 0; i < 4; i++)
         tempYPoints[i] = (int)myYPoints[i];
      pen.setColor(Color.black);
      pen.fillPolygon(tempXPoints, tempYPoints, 4);
      for (Bullet b: myBullets)
         b.draw(pen);
   }
   public void garbageCollectBullets() {
      ArrayList<Bullet> toTrash = new ArrayList<Bullet>();
      for (Bullet b: myBullets) {
         boolean outLeft = (myPlayer.getGroundX() > b.getX());
         boolean outBottom = (myPlayer.getGroundY() < b.getY());
         boolean outRight = (myPlayer.getCeilingX() < b.getX());
         boolean outTop = (myPlayer.getCeilingY() > b.getY());
         if (outLeft || outBottom || outRight || outTop)
            toTrash.add(b);
      }
      for (Bullet b: toTrash)
         myBullets.remove(b);
   }
   public void rotatePoints(double angle) {
      angle = Math.toRadians(angle);
      while(Math.abs(angle)>2*Math.PI){
         angle -= Math.abs(angle)/angle * 2*Math.PI;
      }
      double sin = Math.sin(angle);
      double cos = Math.cos(angle);
      for (int i = 0; i < 4; i++) {
         myXPoints[i] -= myPlayer.getX();
         myYPoints[i] -= myPlayer.getY();
         double tempX = myXPoints[i];
         myXPoints[i] = myXPoints[i] * cos - myYPoints[i] * sin;
         myYPoints[i] = myYPoints[i] * cos + tempX * sin;
         myXPoints[i] += myPlayer.getX();
         myYPoints[i] += myPlayer.getY();
      }
   }
   public void initializePoints() {
      myOldPlayerX = myPlayer.getX();
      myOldPlayerY = myPlayer.getY() - myPlayer.getRadius();
      myXPoints[0] = myPlayer.getX() - 6;
      myXPoints[1] = myXPoints[0];
      myXPoints[2] = myPlayer.getX() + 6;
      myXPoints[3] = myXPoints[2];
      myYPoints[0] = myPlayer.getY() - myPlayer.getRadius() - 6;
      myYPoints[1] = myPlayer.getY() - myPlayer.getRadius() - 12;
      myYPoints[2] = myYPoints[1];
      myYPoints[3] = myYPoints[0];
   }
   public void fixPosition() {
      double xDiff = myPlayer.getX() - myOldPlayerX;
      double yDiff = myPlayer.getY() - myPlayer.getRadius() - myOldPlayerY;
      for (int i = 0; i < 4; i++)
         myXPoints[i] += xDiff;
      for (int i = 0; i < 4; i++)
         myYPoints[i] += yDiff;
      myOldPlayerX = myPlayer.getX();
      myOldPlayerY = myPlayer.getY() - myPlayer.getRadius();
   }
   public ArrayList<Bullet> getBullets() {
      return myBullets;
   }
   public void setIsSpinning(boolean isSpinning) {
      myIsSpinning = isSpinning;
   }
   public void flipIsSpinning() {
      myIsSpinning = !myIsSpinning;
   }
   public void setSpinDirection(int spinDirection) {
      mySpinDirection = spinDirection;
   }
   public void flipSpinDirection() {
      mySpinDirection *= -1;
   }
   public void setShooting(boolean isShooting) {
      myIsShooting = isShooting;
   }

   public class Bullet extends Circle {
      private double myRadius = 3;
      private double myVelocityX;
      private double myVelocityY;

      public Bullet(double x, double y, double velocityX,
                                        double velocityY) {
         super(3, x, y);
         myVelocityX = velocityX;
         myVelocityY = velocityY;
      }
      
      public void update() {
         setX(getX() + myVelocityX);
         setY(getY() + myVelocityY);
      }
   }
}