import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;

public class Player extends Circle {
   private static double gravity = -0.5;
   private Controls myControls = new Controls(this);
   private int myHealth = 200;
   private double myVelocityX;
   private double myAcceleration = 1;
   private double myVelocityY;
   private double mySpeed = 0;
   private double myMaxSpeed = 6;
   private Gun myGun = new Gun(this);
   private double myBallHeight = 130;
   private boolean myIsJumpReleased = true;
   private boolean myIsFlipReleased = true;
   private boolean myIsSpinToggleReleased = true;
   private int myJumps = 3;
   private int maxJumps = 3;
   private int myJumpHeight = 7;
   private double myInertia = 0.95;
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
      myGun.update();
   }
   //Can't update speed in keylistener, only update whether
   //acceleration is occuring, otherwise there will be a
   //delay.
   //Note: May fix later, may have nice effect
   public void updateVelocity() {
      myVelocityX += mySpeed;
      myVelocityX *= myInertia;
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
   public void takeDamage(ArrayList<Gun.Bullet> a) {
      ArrayList<Gun.Bullet> toDelete = new ArrayList<Gun.Bullet>();
      for (Gun.Bullet b: a) {
         if (isColliding(b)) {
            myHealth -= 3;
            toDelete.add(b);
         }
      }
      for (Gun.Bullet b: toDelete)
         a.remove(b);
   }
   public void drawSelfAndGun(Graphics pen) {
      draw(pen);
      myGun.draw(pen);
   }
   public void up() {
      if (myJumps < maxJumps && myIsJumpReleased) {
         myVelocityY += myJumpHeight;
         myJumps++;
         myIsJumpReleased = false;
      }
   }
   public void releaseUp() {
      myIsJumpReleased = true;
   }
   public void down() {
      if (myIsFlipReleased) {
         myGun.flipSpinDirection();
         myIsFlipReleased = false;
      }
   }
   public void releaseDown() {
      myIsFlipReleased = true;
   }
   public void left() {
      mySpeed -= myAcceleration;
      if (Math.abs(mySpeed) > myMaxSpeed) {
         mySpeed = -myMaxSpeed;
      }
   }
   public void releaseLeft() {
      if (mySpeed < 0)
         mySpeed = 0;
   }
   public void right() {
      mySpeed += myAcceleration;
      if (Math.abs(mySpeed) > myMaxSpeed) {
         mySpeed = myMaxSpeed;
      }
   }
   public void releaseRight() {
      if (mySpeed > 0)
         mySpeed = 0;
   }
   public void firstAction() {
      myGun.setShooting(true);
   }
   public void releaseFirstAction() {
      myGun.setShooting(false);
   }
   public void secondAction() {
      if (myIsSpinToggleReleased) {
         myGun.flipIsSpinning();
         myIsSpinToggleReleased = false;
      }
   }
   public void releaseSecondAction() {
      myIsSpinToggleReleased = true;
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
   public Gun getGun() {
      return myGun;
   }
   public int getHealth() {
      return myHealth;
   }
}