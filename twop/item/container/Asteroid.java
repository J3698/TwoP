package twop.item.container;

import java.awt.Graphics;

import twop.Player;
import twop.item.Item;
import twop.particlesys.ParticleSystem;
import twop.util.Vector2;

public class Asteroid extends ItemContainer {
   private static Vector2 gravity = new Vector2(0, -0.1);

   private Vector2 myVelocity;
   private ParticleSystem myTail;

   public Asteroid(Item item, Vector2 velocity) {
      super(item);
      myVelocity = velocity;
      myTail = new ParticleSystem("asteroid", getPosition(),
            defaultTailVelocity(), 0, 0);
      myTail.setEmissionRate(10);
   }

   @Override
   public void update(Player firstPlayer, Player secondPlayer) {
      getItem().update(firstPlayer, secondPlayer);

      getPosition().addVector(myVelocity);
      myVelocity.subtractVector(gravity);

      double oppositeDir = myVelocity.asRadians() + Math.PI;
      myTail.setEmissionAngles(oppositeDir + Math.PI / 6, oppositeDir - Math.PI / 6);
      myTail.setSourcePosition(getPosition().copy());
      myTail.update();
   }
   @Override
   public void draw(Graphics pen) {
      myTail.draw(pen);
   }

   private static Vector2 defaultTailVelocity() {
      return new Vector2(10, 0);
   }
}