package twop.particlesys.particle;

import java.awt.Color;
import java.awt.Graphics;

import twop.util.Vector2;

public class FireParticle extends Particle {
   private int myDegreeOffset;

   public FireParticle(Vector2 position, Vector2 velocity) {
      super(position, velocity);
      setColor(getRandomColor(100, 256, 0, 10, 0, 10));
   }

   @Override
   public void update() {
      getParticlePosition().addVector(getParticleVelocity());
      loseLife(15);
   }
   @Override
   public void draw(Graphics pen) {
      int red = getColor().getRed();
      int green = getColor().getGreen();
      int blue = getColor().getBlue();
      Color color = new Color(red, green, blue, getLife());
      pen.setColor(color);
      int x0 = (int) getParticlePosition().getX();
      int y0 = (int) getParticlePosition().getY();
      pen.fillOval(x0 - 3, y0 - 3, 6, 6);
   }
}