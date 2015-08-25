package twop.plane;

import twop.util.Vector2;
import twop.Player;
import twop.effect.HealthEffect;

import java.awt.Color;

public class HealthPlane extends Plane {
   public int myTick = 0;

   public HealthPlane(Vector2 position, double width, double height,
                                         Color background, int spot) {
      super(position, width, height, background, Color.white, spot);
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      super.update(firstPlayer, secondPlayer);
      myTick++;
   }
   public void onCollision(Player player) {
      int availableID = 0;
      if (myTick % 10 == 0) {
         while (player.hasEffectKey("health" + availableID))
            availableID++;
         player.addEffect("health" + availableID, new HealthEffect(player));

      }
   }
}
