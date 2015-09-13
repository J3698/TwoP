package twop.particlesys.particle;

import java.awt.Color;
import java.awt.Graphics;

import twop.util.Vector2;

public class PoisonParticle extends Particle {
   private double myRadius = 10;

   public PoisonParticle(Vector2 position, Vector2 velocity) {
      super(position, velocity);
      setLife(150);
   }

   @Override
   public void update() {
      myRadius += 0.1;
      loseLife(5);
   }

   @Override
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