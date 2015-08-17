package twop.particlesys;

import twop.util.Vector2;
import twop.particlesys.particle.*;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;

public class ParticleSystem {
   private ArrayList<Particle> myParticles;
   private Vector2 mySourcePosition;
   private Vector2 myInitialVelocity = new Vector2(0, 0);
   private String myParticleType;
   private int myEmissionRate = 1;
   private int myLowerAngleLimit = -180;
   private int myUpperAngleLimit = 180;

   public ParticleSystem(String particleType, Vector2 position, Vector2 velocity,
                                        int lowerAngleLimit, int upperAngleLimit) {
      myParticleType = particleType;
      mySourcePosition = position;
      myInitialVelocity = velocity;
      myLowerAngleLimit = lowerAngleLimit;
      myUpperAngleLimit = upperAngleLimit;
      myParticles = new ArrayList<Particle>();
   }

   public ParticleSystem(String particleType, Vector2 position) {
      myParticleType = particleType;
      mySourcePosition = position;
      if (myParticleType.equals("fire")) {
         myInitialVelocity = new Vector2(0, -3);
         myLowerAngleLimit = -40;
         myUpperAngleLimit = 40;
      } else if (myParticleType.equals("poison")) {
      } else {
         System.out.println("Unknown Particle Type");
      }
      myParticles = new ArrayList<Particle>();
   }

   public void update() {
      Particle temp;
      for (int i = 0; i < myParticles.size(); i++) {
         temp = myParticles.get(i);
         if(temp.isDead())
            myParticles.remove(temp);
         else
            temp.update();
      }
      for (int i = 0; i < myEmissionRate; i++) {
         createParticles();
      }
   }

   public void draw(Graphics pen) {
      for (int i = 0; i < myParticles.size(); i++)
         myParticles.get(i).draw(pen);
   }

   public void createParticles() {
      Vector2 position = mySourcePosition.copy();
      Vector2 velocity = myInitialVelocity.copy();
      int angle = myLowerAngleLimit + new Random().nextInt(myUpperAngleLimit - myLowerAngleLimit);
      velocity.rotateDegrees(angle);
      if (myParticleType.equalsIgnoreCase("poison")) {
         if (new Random().nextDouble() < 0.01)
            myParticles.add(new PoisonParticle(position, velocity));
      }
      else if (myParticleType.equalsIgnoreCase("fire")) {
         myParticles.add(new FireParticle(position, velocity));
      }
   }

   public Vector2 getSourcePosition() { return mySourcePosition; }
   public void setSourcePosition(Vector2 position) { mySourcePosition = position; }
   public void setEmissionRate(int emissionRate) { myEmissionRate = emissionRate; }
   public void setEmissionAngles(int lowerLimit, int upperLimit) {
      myLowerAngleLimit = lowerLimit;
      myUpperAngleLimit = upperLimit;
   }
   public int[] getEmissionAngles() { return new int[]{myLowerAngleLimit, myUpperAngleLimit}; }
   public void setInitialVelocity(Vector2 velocity) { myInitialVelocity = velocity; }
   public Vector2 getInitialVelocity() { return myInitialVelocity; }
}