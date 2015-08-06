package twop.particlesys;

import twop.util.Vector2;
import twop.particlesys.particle.*;

import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;

public class ParticleSystem {
   private ArrayList<Particle> myParticles;
   private Vector2 mySourcePosition;
   private String myParticleType;
   private int myTick = 0;

   public ParticleSystem(Vector2 sourcePosition, String particleType) {
      mySourcePosition = sourcePosition;
      myParticles = new ArrayList<Particle>();
      myParticleType = particleType;
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
      createParticles();
      myTick++;
   }

   public void draw(Graphics pen) {
      for (int i = 0; i < myParticles.size(); i++)
         myParticles.get(i).draw(pen);
   }

   public void createParticles() {
      if (myParticleType.equalsIgnoreCase("poison")) {
         if (new Random().nextDouble() < 0.01)
            myParticles.add(new PoisonParticle(mySourcePosition.copy()));
      }
      else if (myParticleType.equalsIgnoreCase("fire")) {
         myParticles.add(new FireParticle(mySourcePosition.copy()));
      }
   }
   public Vector2 getSourcePosition() {
      return mySourcePosition;
   }
   public void setSourcePosition(Vector2 sourcePosition) {
      mySourcePosition = sourcePosition;
   }
}