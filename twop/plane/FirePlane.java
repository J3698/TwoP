package twop.plane;

import java.awt.Color;

import twop.Player;
import twop.effect.FireEffect;
import twop.util.Vector2;

public class FirePlane extends Plane {
   public FirePlane(Vector2 position, double width, double height,
         Color background, int spot) {
      super(position, width, height, background, Color.red, spot);
   }

   @Override
   public void onCollision(Player player) {
      if (player.hasEffectKey("fire")) {
         player.getEffects().get("fire").increaseIntensity();
      } else {
         player.addEffect("fire", new FireEffect(player));
      }
   }
}