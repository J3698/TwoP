package twop;

import twop.util.Controls;
import twop.util.Vector2;
import twop.effect.Effect;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 *
 *
 */
public class Player extends Circle {
   private static double gravity = -0.5;
   private Controls myControls = new Controls(this);
   private HashMap<String, Effect> myEffects = new HashMap<String, Effect>();
   private double myHealth = 300;
   private double myMaxHealth = myHealth;
   private Vector2 myVelocity;
   private double myAcceleration = 1;
   private double mySpeed = 0;
   private double myMaxSpeed = 6;
   private Gun myGun = new Gun(this);
   private double myBallHeight = 130;
   private boolean myIsJumpReleased = true;
   private boolean myIsFlipReleased = true;
   private boolean myIsLeftPressed = false;
   private boolean myIsRightPressed = false;
   private boolean myIsSpinToggleReleased = true;
   private int myJumps = 3;
   private int maxJumps = 3;
   private int myJumpHeight = 7;
   private double myInertia = 0.95;
   int myGroundX;
   int myGroundY;
   int myCeilingX;
   int myCeilingY;

   /**
    *
    *
    *
    */
   public Player(Vector2 center, double radius, int groundX,
                  int groundY, int ceilingX, int ceilingY) {
      super(center, radius);
      myGroundX = groundX;
      myGroundY =  groundY;
      myCeilingX = ceilingX;
      myCeilingY = ceilingY;
      setRandomColor();
      myVelocity = new Vector2(0, 10);
   }

   /**
    *
    *
    *
    */
   public void update() {
      updateVelocity();
      updatePosition();
      keepInBounds();
      updateJumpAbility();
      myGun.update();

      ArrayList<String> toDelete = new ArrayList<String>();
      Effect effect;
      for (String key: myEffects.keySet()) {
         effect = myEffects.get(key);
         effect.update();
         if (effect.isDead())
            toDelete.add(key);
      }
      for (String key: toDelete)
         myEffects.remove(key);
   }


   //Can't update speed in keylistener, only update whether
   //acceleration is occuring, otherwise there will be a
   //delay.
   //Note: May fix later, may have nice effect
   /**
    *
    *
    *
    */
   public void updateVelocity() {
      myVelocity.addX(mySpeed);
      myVelocity.multiplyX(myInertia);
      myVelocity.addY(gravity);
   }

   /**
    *
    *
    *
    */
   public void updatePosition() {
      getCenter().addX(myVelocity.getX());
      getCenter().subtractY(myVelocity.getY());
   }

   /**
    *
    *
    *
    */
   public void keepInBounds() {
      if (getCenter().getY() + getRadius() > myGroundY) {
         myVelocity.setY(0);
         getCenter().setY(myGroundY - getRadius());
      }
      if (getCenter().getY() - getRadius() < myCeilingY) {
         myVelocity.setY(0);
         getCenter().setY(myCeilingY + getRadius());
      }
      if (getCenter().getX() + getRadius() > myCeilingX) {
         myVelocity.setX(0);
         mySpeed = 0;
         getCenter().setX(myCeilingX - getRadius());
      }
      if (getCenter().getX() - getRadius() < myGroundX) {
         myVelocity.setX(0);
         mySpeed = 0;
         getCenter().setX(getRadius() + myGroundX);
      }
   }

   /**
    *
    *
    *
    */
   public void updateJumpAbility() {
      if (getCenter().getY() + getRadius() == myGroundY)
         myJumps = 0;
   }

   /**
    *
    *
    *
    */
   public void takeDamage(ArrayList<Gun.Bullet> a) {
      ArrayList<Gun.Bullet> toDelete = new ArrayList<Gun.Bullet>();
      for (Gun.Bullet b: a) {
         if (collidesCircle(b)) {
            myHealth -= 1;
            toDelete.add(b);
         }
      }
      for (Gun.Bullet b: toDelete)
         a.remove(b);
   }

   /**
    *
    *
    *
    */
   public void drawSelfAndWeapon(Graphics pen) {
      myGun.draw(pen);
      draw(pen);
      for (Effect effect: myEffects.values())
         effect.draw(pen);
   }

   /**
    *
    *
    *
    */
   public void up() {
      if (myJumps < maxJumps && myIsJumpReleased) {
         myVelocity.addY(myJumpHeight);
         myJumps++;
         myIsJumpReleased = false;
      }
   }

   /**
    *
    *
    *
    */
   public void releaseUp() {
      myIsJumpReleased = true;
   }

   /**
    *
    *
    *
    */
   public void down() {
      if (myIsFlipReleased) {
         myGun.flipSpinDirection();
         myIsFlipReleased = false;
      }
   }

   /**
    *
    *
    *
    */
   public void releaseDown() {
      myIsFlipReleased = true;
   }

   /**
    *
    *
    *
    */
   public void left() {
      myIsLeftPressed = true;
      mySpeed -= myAcceleration;
      if (Math.abs(mySpeed) > myMaxSpeed) {
         mySpeed = -myMaxSpeed;
      }
   }

   /**
    *
    *
    *
    */
   public void releaseLeft() {
      myIsLeftPressed = false;
      if (mySpeed < 0)
         mySpeed = 0;
   }

   /**
    *
    *
    *
    */
   public void right() {
      myIsRightPressed = true;
      mySpeed += myAcceleration;
      if (Math.abs(mySpeed) > myMaxSpeed) {
         mySpeed = myMaxSpeed;
      }
   }

   /**
    *
    *
    *
    */
   public void releaseRight() {
      myIsRightPressed = false;
      if (mySpeed > 0)
         mySpeed = 0;
   }

   /**
    *
    *
    *
    */
   public void firstAction() {
      myGun.setShooting(true);
   }

   /**
    *
    *
    *
    */
   public void releaseFirstAction() {
      myGun.setShooting(false);
   }

   /**
    *
    *
    *
    */
   public void secondAction() {
      if (myIsSpinToggleReleased) {
         myGun.flipIsSpinning();
         myIsSpinToggleReleased = false;
      }
   }

   /**
    *
    *
    *
    */
   public void releaseSecondAction() {
      myIsSpinToggleReleased = true;
   }


   /**
    *
    *
    *
    */
   public Controls getControls() {
      return myControls;
   }

   /**
    *
    *
    *
    */
   public void setRandomColor() {
      Random rand = new Random();
      int r = rand.nextInt(255);
      int g = rand.nextInt(255);
      int b = rand.nextInt(255);
      setColor(new Color(r, g, b));
   }

   public HashMap<String, Effect> getEffects() {
      return myEffects;
   }

   public void addEffect(String key, Effect effect) {
      myEffects.put(key, effect);
   }

   public boolean hasEffectKey(String key) {
      return myEffects.containsKey(key);
   }

   /**
    *
    *
    *
    */
   public void applyForce(Vector2 force) {
      getCenter().addVector(force);
   }

   public void applyHealing(double healing) {
      if (myHealth + healing > myMaxHealth)
         myHealth = myMaxHealth;
      else
         myHealth += healing;
   }

   public void applyDamage(double damage) {
      myHealth -= damage;
   }

   /**
    *
    *
    *
    */
   public double getBallHeight() {
      return myBallHeight;
   }

   /**
    *
    *
    *
    */
   public Gun getGun() {
      return myGun;
   }

   /**
    *
    *
    *
    */
   public int getGroundX() {
      return myGroundX;
   }

   /**
    *
    *
    *
    */
   public int getGroundY() {
      return myGroundY;
   }

   /**
    *
    *
    *
    */
   public int getCeilingX() {
      return myCeilingX;
   }

   /**
    *
    *
    *
    */
   public int getCeilingY() {
      return myCeilingY;
   }

   /**
    *
    *
    *
    */
   public double getHealth() {
      return myHealth;
   }
}
