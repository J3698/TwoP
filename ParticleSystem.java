import java.util.ArrayList;
import java.util.Random;

import java.awt.Graphics;
import java.awt.Color;

public class ParticleSystem {
   private ArrayList<Particle> myParticles;
   private Vector2 mySourcePosition;
   private String myParticleType;

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
   }

   public void draw(Graphics pen) {
      for (int i = 0; i < myParticles.size(); i++)
         myParticles.get(i).draw(pen);
   }

   public void createParticles() {
      myParticles.add(new FireParticle(mySourcePosition.copy()));
   }
   public Vector2 getSourcePosition() {
      return mySourcePosition;
   }
   public void setSourcePosition(Vector2 sourcePosition) {
      mySourcePosition = sourcePosition;
   }


   public abstract class Particle {
      private Vector2 myParticlePosition;
      private Vector2 myParticleVelocity;
      private int myLife = 255;
      private Color myColor;

      public Particle(Vector2 particlePosition){
         myParticlePosition = particlePosition;
         myParticleVelocity = new Vector2(0, 0);
      }

      public void run(Graphics pen){
         update();
         draw(pen);
      }
      public abstract void update();
      public abstract void draw(Graphics pen);
      public boolean isDead(){
         if (myLife > 0)
            return false;
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
      public Vector2 getParticlePosition() { return myParticlePosition; }
      public void setParticlePosition(Vector2 position) {myParticlePosition = position; }
      public Vector2 getParticleVelocity() { return myParticleVelocity; }
      public void setParticleVelocity(Vector2 velocity) { myParticleVelocity = velocity; }
      public void setColor(Color color) { myColor = color; }
      public Color getColor() { return myColor; }
   }

   private class FireParticle extends Particle {
      private int myDegreeOffset;

      public FireParticle(Vector2 position) {
         super(position);
         Random r = new Random();
         getParticlePosition().addVector(new Vector2(r.nextInt(30) - 15, r.nextInt(30) - 15));
         setParticleVelocity(new Vector2(0, -3));
         int myDegreeOffset = new Random().nextInt(80) - 40;
         getParticleVelocity().rotateDegrees(myDegreeOffset);
         setColor(getRandomColor(100, 256, 0, 10, 0, 10));
      }

      public void update() {
         getParticlePosition().addVector(getParticleVelocity());
         loseLife(15);
      }
      public void draw(Graphics pen) {
         int red = getColor().getRed();
         int green = getColor().getGreen();
         int blue = getColor().getBlue();
         Color color = new Color(red, green, blue, getLife());
         pen.setColor(color);
         int x0 = (int) getParticlePosition().getX();
         int y0 = (int) getParticlePosition().getY();
         pen.fillRect(x0 - 3, y0 - 3, 6, 6);
      }
   }
}
