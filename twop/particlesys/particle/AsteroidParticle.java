package twop.particlesys.particle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import twop.util.Vector2;

public class AsteroidParticle extends Particle {
   private static Vector2 gravity = new Vector2(0, -0.5);

   private static Color[] colorOptions = new Color[] {
         Color.BLACK,   Color.DARK_GRAY,  Color.ORANGE,
         Color.ORANGE,  Color.RED
   };

   private Color myColor;

   public AsteroidParticle(Vector2 position, Vector2 velocity) {
      super(position, velocity);
      int choice = new Random().nextInt(colorOptions.length);
      myColor = colorOptions[choice];
   }

   @Override
   public void update() {
      getPosition().addVector(getVelocity());
      getPosition().addVector(gravity);
      loseLife(5);
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(myColor);

      int x0 = (int) getPosition().getX() - 3;
      int y0 = (int) getPosition().getY() - 3;
      pen.drawOval(x0, y0, 6, 6);
   }

}
