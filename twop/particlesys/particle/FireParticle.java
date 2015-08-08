package twop.particlesys.particle;

import twop.util.Vector2;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class FireParticle extends Particle {
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
      pen.fillOval(x0 - 3, y0 - 3, 6, 6);
   }
}