package twop.particlesys.particle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import twop.util.Vector2;

public abstract class Particle {
   private Vector2 myParticlePosition;
   private Vector2 myParticleVelocity;
   private int myLife = 255;
   private Color myColor;

   public Particle(Vector2 position, Vector2 velocity) {
      myParticlePosition = position;
      myParticleVelocity = velocity;
   }

   public void run(Graphics pen) {
      update();
      draw(pen);
   }
   public abstract void update();
   public abstract void draw(Graphics pen);
   public boolean isDead() {
      if (myLife > 0) {
         return false;
      }
      return true;
   }

   public Color getRandomColor() {
      Random random = new Random();
      int red = random.nextInt(256);
      int green = random.nextInt(256);
      int blue = random.nextInt(256);
      return new Color(red, green, blue);
   }
   public Color getRandomColor(int minRed, int maxRed, int minGreen, int maxGreen,
         int minBlue, int maxBlue) {
      Random random = new Random();
      int red = minRed + random.nextInt(maxRed - minRed);
      int green = minGreen + random.nextInt(maxGreen - minGreen);
      int blue = minBlue + random.nextInt(maxBlue - minBlue);
      return new Color(red, green, blue);
   }

   public void loseLife(int damage) { myLife -= damage; }
   public int getLife(){ return myLife; }
   public void setLife(int life){ myLife = life; }
   public Vector2 getPosition() { return myParticlePosition; }
   public void setPosition(Vector2 position) {myParticlePosition = position; }
   public Vector2 getVelocity() { return myParticleVelocity; }
   public void setVelocity(Vector2 velocity) { myParticleVelocity = velocity; }
   public void setColor(Color color) { myColor = color; }
   public Color getColor() { return myColor; }
}