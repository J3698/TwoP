import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;


public abstract class ParticleSystem {
   private ArrayList<Particle> myParticles;
   private Vector2 mySourcePosition;
   private String myParticleType;

   public ParticleSystem(Vector2 sourcePosition, String particleType) {
      mySourcePosition = sourcePosition;
      myParticles = new ArrayList<Particle>();
      myParticleType = particleType;
   }

   public void update(Graphics pen) {
      Particle temp;
      for (int i = 0; i < myParticles.size(); i++) {
         temp = myParticles.get(i);
         if(temp.isDead())
            myParticles.remove(temp);
         else
            myParticles.get(i).run(pen);
      }
      createParticles();
   }

   public Particle createParticles() { // .rotateDegrees(leastDegree + randint(-leastDegree+maxDegree));
      return new FireParticle(mySourcePosition);
   }
   public Vector2 getSourcePosition() {
      return mySourcePosition;
   }
   public void setSourcePosition(Vector2 sourcePosition) {
      mySourcePosition = sourcePosition;
   }


   public abstract class Particle {
      private Vector2 myPosition;
      private Vector2 myVelocity;
      private int myLife = 255;

      public Particle(Vector2 position, Vector2 velocity){
         myPosition = position;
         myVelocity = velocity;
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
      public Vector2 getPosition() { return myPosition; }
      public void setPosition(Vector2 position) {myPosition = position; }
      public Vector2 getVelocity() { return myVelocity; }
      public void setVelocity(Vector2 velocity) { myVelocity = velocity; }
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
         super(position, new Vector2(0, 0));
         setVelocity(new Vector2(3, 3));
      }
      public void update() {}
      public void draw(Graphics pen) {}
   }
}
