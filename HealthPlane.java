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
      if (myTick % 5 == 0)
         player.addEffect(new HealthEffect(player));
   }
}
