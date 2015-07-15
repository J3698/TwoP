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
   private double mySpeed = 10;
   private double[] myXPoints = new double[4];
   private double[] myYPoints = new double[4];
//   private ArrayList<Bullet> myBullets = new ArrayList<Bullet>();
//   private boolean myIsShooting = false;

   public Gun (Player p) {
      myPlayer = p;
      initializePoints();
   }

//   private void shoot() {}

   public void update() {
      fixPosition();
      if (myIsSpinning)
         rotatePoints(mySpinDirection * mySpeed);
//      for (Bullet b: myBullets)
//         b.update();
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
//      for (Bullet b: myBullets)
//         b.draw(pen);
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
/*   public void setShooting(boolean isShooting) {
      myIsShooting = isShooting;
   }


   public class Bullet {
      private double myX;
      private double myY;
      private double myRadius = 2;
      private double myVelocityX;
      private double myVelocityY;

      public Bullet(double x, double y, double velocityX,
                                        double velocityY) {
         myX = x;
         myY = y;
         myVelocityX = velocityX;
         myVelocityY = velocityY;
      }

      public void update() {
         myX += myVelocityX;
         myY += myVelocityY;
      }

      public void draw(Graphics pen) {
         int centerX = (int) (myX - myRadius);
         int centerY = (int) (myY - myRadius);
         int diameter = (int) (myRadius * 2);
         pen.setColor(Color.black);
         pen.fillOval(centerX, centerY,
                   diameter, diameter);
      }

      public double getX() {
         return myX;
      }
      public double getY() {
         return myY;
      }
      public double getRadius() {
         return myRadius;
      }
   }
*/
}