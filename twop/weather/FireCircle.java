package twop.weather;

import twop.Circle;
import twop.Player;
import twop.effect.FireEffect;
import twop.item.Item;
import twop.util.Vector2;

public class FireCircle extends Circle implements Item {
   private Vector2 myPosition;
   private boolean myIsDead;

   public FireCircle(Vector2 position) {
      super(new Vector2(2, 2), 3);
      myPosition = position;
   }

   @Override
   public void update(Player firstPlayer, Player secondPlayer) {
      Player[] players = new Player[]{ firstPlayer, secondPlayer };

      for (Player player : players) {
         if (collidesCircle(player)) {
            if (player.hasEffectKey("fire")) {
               player.getEffects().get("fire").increaseIntensity();
            } else {
               player.addEffect("fire", new FireEffect(player));
            }
            myIsDead = true;
         }
      }
   }

   @Override
   public Vector2 getPosition() {
      return myPosition;
   }

   @Override
   public void setPosition(Vector2 position) {
      myPosition = position;
   }

   @Override
   public boolean isDead() {
      return myIsDead;
   }
}
