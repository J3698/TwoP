import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends Circle {
   private static double gravity = -0.5;
   private Controls myControls = new Controls(this);
   private double myVelocityX;
   private double myVelocityY;
   private double mySpeed = 3;
   private Ball myBall;
   private double myBallHeight = 130;
   private int myJumps = 2;
   private int maxJumps = 2;
   private int myJumpHeight = 10;
   int myGroundX;
   int myGroundY;
   int myCeilingX;
   int myCeilingY;

   public Player(double radius, double x, double y, int groundX,
                       int groundY, int ceilingX, int ceilingY) {
      super(radius, x, y);
      myGroundX = groundX;
      myGroundY =  groundY;
      myCeilingX = ceilingX;
      myCeilingY = ceilingY;
      setRandomColor();
      myVelocityY = 10;
   }

   public void update() {
      updateVelocity();
      updatePosition();
      keepInBounds();
      updateJumpAbility();


      if (myBall != null)
         myBall.update();
   }
   public void updateVelocity() {
      myVelocityY += gravity;
   }
   public void updatePosition() {
      setY(getY() - myVelocityY);
      setX(getX() + myVelocityX);
   }
   public void keepInBounds() {
      if (getY() + getRadius() > myGroundY) {
         myVelocityY = 0;
         setY(myGroundY - getRadius());
      }
      if (getY() - getRadius() < myCeilingY) {
         myVelocityY = 0;
         setY(myCeilingY + getRadius());
      }
      if (getX() + getRadius() > myCeilingX) {
         myVelocityX = 0;
         setX(myCeilingX - getRadius());
      }
      if (getX() - getRadius() < myGroundX) {
         myVelocityX = 0;
         setX(getRadius() + myGroundX);
      }
   }
   public void updateJumpAbility() {
      if (getY() + getRadius() == myGroundY)
         myJumps = 0;
   }
   public void drawSelfAndBall(Graphics pen) {
      draw(pen);
      if (myBall != null)
         myBall.draw(pen);
   }
   public void up() {
      if (myJumps < maxJumps) {
         myVelocityY += myJumpHeight;
         myJumps++;
      }
   }
   public void releaseUp() {
   }
   public void down() {
   }
   public void releaseDown() {
   }
   public void left() {
      myVelocityX -= mySpeed;
   }
   public void releaseLeft() {
   }
   public void right() {
      myVelocityX += mySpeed;
   }
   public void releaseRight() {
   }
   public void firstAction() {
      myBall = new Ball(this);
   }
   public void secondAction() {
   }
   public Controls getControls() {
      return myControls;
   }
   public void setRandomColor() {
      Random rand = new Random();
      int r = 120 + rand.nextInt(135);
      int g = 120 + rand.nextInt(135);
      int b = 120 + rand.nextInt(135);
      setColor(new Color(r, g, b));
   }
   public double getBallHeight() {
      return myBallHeight; 
   }
   public Ball getBall() {
      return myBall;
   }
}