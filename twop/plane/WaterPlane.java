package twop.plane;

import twop.util.Vector2;
import twop.Player;
import twop.effect.WaterEffect;

import java.awt.Color;

public class WaterPlane extends Plane {
   public WaterPlane(Vector2 position, double width, double height,
                                        Color background, int spot) {
      super(position, width, height, background, Color.blue, spot);
   }

   public void onCollision(Player player) {
      if (!player.hasEffectKey("water"));
         player.addEffect("water", new WaterEffect(player));
   }
}
