package twop.particlesys.particle;

import java.awt.Color;
import java.awt.Graphics;

import twop.util.Vector2;

public class RainParticle extends Particle {
   private static int floor = 480;

   public RainParticle(Vector2 position, Vector2 velocity) {
      super(position, velocity);
   }

   @Override
   public void update() {
      getParticlePosition().addVector(getParticleVelocity());
   }

   @Override
   public void draw(Graphics pen) {
      int x = (int) getParticlePosition().getX();
      int y = (int) getParticlePosition().getY();
      for (int i = 0; i < 5; i++) {
         pen.setColor(new Color(0, 0, 255, 50 * (i + 1)));
         pen.drawLine(x, y + 2 * i, x, y + 2 * i + 2);
      }
   }

   @Override
   public boolean isDead() {
      if (getParticlePosition().getY() > floor) {
         return true;
      }

      return false;
   }
}
