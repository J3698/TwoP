import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Color;
import java.awt.Polygon;

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

   public void createParticles() { // .rotateDegrees(leastDegree + randint(-leastDegree+maxDegree));
      myParticles.add(new FireParticle(mySourcePosition));
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
      public int getLife(){ return myLife; }
      public void setLife(int life){ myLife = life; }
      public Vector2 getParticlePosition() { return myParticlePosition; }
      public void setParticlePosition(Vector2 position) {myParticlePosition = position; }
      public Vector2 getParticleVelocity() { return myParticleVelocity; }
      public void setParticleVelocity(Vector2 velocity) { myParticleVelocity = velocity; }
   }

   public interface ColoredParticle {
      default public Color randomColor() {
         Random random = new Random();
         int red = random.nextInt(256);
         int green = random.nextInt(256);
         int blue = random.nextInt(256);
         return new Color(red, green, blue);
      }
      default public Color randomColor(int minRed, int maxRed, int minGreen, int maxGreen,
                                                                 int minBlue, int maxBlue) {
         Random random = new Random();
         int red = minRed + random.nextInt(maxRed - minRed);
         int green = minGreen + random.nextInt(maxGreen - minGreen);
         int blue = minBlue + random.nextInt(maxBlue - minBlue);
         return new Color(red, green, blue);
      }
   }

   private class FireParticle extends Particle implements ColoredParticle {

      public FireParticle(Vector2 position) {
         super(position);
         setParticleVelocity(new Vector2(3, 3));
      }
      public void update() {
         System.out.println("...");
         getParticlePosition().addVector(getParticleVelocity());
      }
      public void draw(Graphics pen) {
         pen.setColor(Color.red);
         int x0 = (int) getParticlePosition().getX();
         int y0 = (int) getParticlePosition().getY();
         pen.fillRect(x0, y0, 10, 10);
      }
   }
}
