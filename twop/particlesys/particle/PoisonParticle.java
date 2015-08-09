package twop.particlesys.particle;

import twop.util.Vector2;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class PoisonParticle extends Particle {
   private double myRadius = 10;

   public PoisonParticle(Vector2 position, Vector2 velocity) {
      super(position, velocity);
      setLife(150);
      Random r = new Random();
      getParticlePosition().addVector(new Vector2(r.nextInt(30) - 15, r.nextInt(30) - 15));
   }

   public void update() {
      myRadius += 0.1;
      loseLife(5);
   }

   public void draw(Graphics pen) {
      pen.setColor(new Color(0, 100, 0, getLife()));
      int r = (int) myRadius;
      int x0 = (int) (getParticlePosition().getX() - r);
      int y0 = (int) (getParticlePosition().getY() - r);
      pen.fillOval(x0, y0, 2 * r, 2 * r);
      pen.setColor(Color.green);
      pen.drawOval(x0, y0, 2 * r, 2 * r);
   }
}