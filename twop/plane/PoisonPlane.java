package twop.plane;

import twop.util.Vector2;
import twop.Player;
import twop.effect.PoisonEffect;

import java.awt.Color;

public class PoisonPlane extends Plane {
   public PoisonPlane(Vector2 position, double width, double height,
                                         Color background, int spot) {
      super(position, width, height, background, Color.green, spot);
   }

   public void onCollision(Player player) {
      if (!player.hasEffectKey("poison"))
         player.addEffect("poison", new PoisonEffect(player));
      else {
      
      }
   }
}
