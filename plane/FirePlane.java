package twop.plane;

import twop.util.Vector2;
import twop.Player;
import twop.effect.FireEffect;

import java.awt.Color;

public class FirePlane extends Plane {
   public FirePlane(Vector2 position, double width, double height,
                                       Color background, int spot) {
      super(position, width, height, background, Color.red, spot);
   }

   public void onCollision(Player player) {
      if (player.hasEffectKey("fire"))
         player.getEffects().get("fire").increaseIntensity();
      else
         player.addEffect("fire", new FireEffect(player));
   }
}