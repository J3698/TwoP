package twop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import twop.effect.Effect;
import twop.physics.PhysicsPlayer;
import twop.util.Controls;
import twop.util.Vector2;

public class Player extends Circle {
   private static double gravity = -0.5;

   private Controls myControls = new Controls(this);
   private HashMap<String, Effect> myEffects = new HashMap<String, Effect>();
   private Gun myGun = new Gun(this);

   private double myHealth = 300;
   private double myMaxHealth = myHealth;

   private Vector2 myVelocity;
   private double myAcceleration = 0.2;
   private double mySpeed = 0;
   private double myMaxSpeed = 6;
   private double myInertia = 0.8;

   private double myBallHeight = 130;

   private boolean myIsJumpReleased = true;
   private boolean myIsFlipReleased = true;
   private boolean myIsGoingLeft = false;
   private boolean myIsGoingRight = false;
   private boolean myIsSpinToggleReleased = true;

   private int myJumps = 3;
   private int maxJumps = 3;
   private int myJumpHeight = 7;

   private PhysicsPlayer myPhysics;

   public Player(Vector2 center, double radius, Rectangle bounds) {
      super(center, radius);
      myPhysics = new PhysicsPlayer(this);
      myPhysics.setBounds(bounds);
      setRandomColor();
      myVelocity = new Vector2(0, 10);
   }

   public void update() {
      updateVelocity();
      updatePosition();
      updateJumpAbility();
      myGun.update();

      ArrayList<String> toDelete = new ArrayList<String>();
      Effect effect;
      for (String key: myEffects.keySet()) {
         effect = myEffects.get(key);
         effect.update();
         if (effect.isDead()) {
            toDelete.add(key);
            effect.onDeath();
         }
      }
      for (String key: toDelete)
         myEffects.remove(key);
   }

   public void updateVelocity() {

      if (myIsGoingLeft) {
         mySpeed -= myAcceleration;
         if (Math.abs(mySpeed) > myMaxSpeed) {
            mySpeed = -myMaxSpeed;
         }
      } else {
         if (mySpeed < 0) {
            mySpeed = 0;
         }
      }

      if (myIsGoingRight) {
         mySpeed += myAcceleration;
         if (Math.abs(mySpeed) > myMaxSpeed) {
            mySpeed = myMaxSpeed;
         }
      } else {
         if (mySpeed > 0) {
            mySpeed = 0;
         }
      }

      myVelocity.addX(mySpeed);
      myVelocity.multiplyX(myInertia);
      myVelocity.addY(gravity);
   }

   public void updatePosition() {
      getCenter().addX(myVelocity.getX());
      getCenter().subtractY(myVelocity.getY());
   }

   public void updateJumpAbility() {
      double tempFeet = getCenter().getY() + getRadius();
      double tempFloor = myPhysics.getBounds().getY() + myPhysics.getBounds().getHeight();
      //if feet touching the floor
      if (tempFeet - tempFloor >= 1) {
         myJumps = 0;
      }
   }

   public void takeDamage(ArrayList<Gun.Bullet> a) {
      ArrayList<Gun.Bullet> toDelete = new ArrayList<Gun.Bullet>();
      for (Gun.Bullet b : a) {
         if (collidesCircle(b)) {
            myHealth -= 1;
            toDelete.add(b);
         }
      }
      for (Gun.Bullet b : toDelete) {
         a.remove(b);
      }
   }

   @Override
   public void draw(Graphics pen) {
      myGun.draw(pen);
      super.draw(pen);
      for (Effect effect: myEffects.values()) {
         effect.draw(pen);
      }
   }

   public void up() {
      if (myJumps < maxJumps && myIsJumpReleased) {
         myVelocity.addY(myJumpHeight);
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
   public void releaseDown() { myIsFlipReleased = true; }
   public void left() {
      myIsGoingLeft = true;
      myIsGoingRight = false;
   }
   public void releaseLeft() { myIsGoingLeft = false; }
   public void right() {
      myIsGoingRight = true;
      myIsGoingLeft = false;
   }
   public void releaseRight() { myIsGoingRight = false; }
   public void firstAction() { myGun.setShooting(true); }
   public void releaseFirstAction() { myGun.setShooting(false); }
   public void secondAction() {
      if (myIsSpinToggleReleased) {
         myGun.flipIsSpinning();
         myIsSpinToggleReleased = false;
      }
   }
   public void releaseSecondAction() { myIsSpinToggleReleased = true; }
   public void thirdAction() {}
   public void releaseThirdAction() {}
   public Controls getControls() { return myControls; }

   public void setRandomColor() {
      Random rand = new Random();
      int r = rand.nextInt(100);
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

   public void applyForce(Vector2 force) {
      getCenter().addVector(force);
   }

   public void applyHealing(double healing) {
      if (myHealth + healing > myMaxHealth) {
         myHealth = myMaxHealth;
      }
      else {
         myHealth += healing;
      }
   }

   public void applyDamage(double damage) {
      myHealth -= damage;
   }

   public PhysicsPlayer getPhysics() { return myPhysics; }
   public Vector2 getVelocity() { return myVelocity; }
   public void setSpeed(double speed) { mySpeed = speed; }
   public double getBallHeight() { return myBallHeight; }
   public Gun getGun() { return myGun; }
   public double getHealth() { return myHealth; }
}
