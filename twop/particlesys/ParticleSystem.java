package twop.particlesys;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import twop.particlesys.particle.FireParticle;
import twop.particlesys.particle.Particle;
import twop.particlesys.particle.PoisonParticle;
import twop.particlesys.particle.RainParticle;
import twop.util.Vector2;

public class ParticleSystem {
   private ArrayList<Particle> myParticles;

   private Vector2 mySourcePosition;
   private Vector2 myMaxSourceOffset;;
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
      myParticles = new ArrayList<Particle>();
      parseParticleType();
   }

   public void parseParticleType() {
      if (myParticleType.equals("fire")) {
         myMaxSourceOffset = new Vector2(30, 30);
         myInitialVelocity = new Vector2(0, -3);
         myLowerAngleLimit = -40;
         myUpperAngleLimit = 40;
      } else if (myParticleType.equals("poison")) {
         myMaxSourceOffset = new Vector2(30, 30);
      } else if (myParticleType.equals("rain")) {
         myMaxSourceOffset = new Vector2(640, 0);
         myInitialVelocity = new Vector2(0, 10);
         myLowerAngleLimit = -1;
         myUpperAngleLimit = 1;
      } else {
         System.out.println("Unknown Particle Type");
      }
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
      if (myMaxSourceOffset != null) {
         double xLowerLim = -(myMaxSourceOffset.getX() / 2);
         double yLowerLim = -(myMaxSourceOffset.getY() / 2);
         double xUpperLim = myMaxSourceOffset.getX() / 2;
         double yUpperLim = myMaxSourceOffset.getY() / 2;
         position.addVector(Vector2.randVector(xLowerLim, yLowerLim, xUpperLim, yUpperLim));
      }

      Vector2 velocity = myInitialVelocity.copy();

      int angle = myLowerAngleLimit + new Random().nextInt(myUpperAngleLimit - myLowerAngleLimit);
      velocity.rotateDegrees(angle);

      if (myParticleType.equalsIgnoreCase("poison")) {
         if (new Random().nextDouble() < 0.01)
            myParticles.add(new PoisonParticle(position, velocity));
      } else if (myParticleType.equalsIgnoreCase("fire")) {
         myParticles.add(new FireParticle(position, velocity));
      } else if (myParticleType.equalsIgnoreCase("rain")) {
         myParticles.add(new RainParticle(position, velocity));
      }
   }

   public Vector2 getSourcePosition() { return mySourcePosition; }
   public void setSourcePosition(Vector2 position) { mySourcePosition = position; }
   public void setSourceOffset(Vector2 offset) { myMaxSourceOffset = offset; }
   @SuppressWarnings("unused")
   private Vector2 getSourceOffset() { return myMaxSourceOffset; }
   public void setEmissionRate(int emissionRate) { myEmissionRate = emissionRate; }
   public void setEmissionAngles(int lowerLimit, int upperLimit) {
      myLowerAngleLimit = lowerLimit;
      myUpperAngleLimit = upperLimit;
   }
   public int[] getEmissionAngles() { return new int[]{myLowerAngleLimit, myUpperAngleLimit}; }
   public void setInitialVelocity(Vector2 velocity) { myInitialVelocity = velocity; }
   public Vector2 getInitialVelocity() { return myInitialVelocity; }
}