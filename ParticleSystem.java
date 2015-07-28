import java.util.ArrayList;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Color;


public abstract class ParticleSystem {
   private ArrayList<Particle> myParticles;
   private Vector2 myPosition;
   private String myParticleType;

   public ParticleSystem(Vector2 position, particleType) {
      myPosition = position;
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
      createParticles();   // To turn degree into vector, need "degree to vector" or somtin
   }

   public void createParticle() {}
   public Particle getParticle() {
      if (myParticleType.equalsIgnoreCas("FIRE"))

   }
   public Vector2 getPosition() {
      return myPosition;
   }
   public void setPosition(Vector2 position) {
      myPosition = position;
   }

   private abstract class Particle {
      private Vector2 myPosition;
      private int myLife = 255;

      public Particle(double x, double y){
         myPosition = new Vector2(x, y);
      }

      public void run(Graphics pen){
         update();
         draw(pen);
      }

      public abstract void update();

      public void draw(Graphics pen);

      public boolean isDead(){
         if (myLife > 0)
            return false;
         return true;
      }

      public void setLife(int life){ myLife = life; }
      public int getLife(){ return myLife; }
      public Vector2 getPosition() { return myPosition; }
   }
}
