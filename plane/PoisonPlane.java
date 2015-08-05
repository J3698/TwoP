import java.awt.Color;

public class PoisonPlane extends Plane {
   public PoisonPlane(Vector2 position, double width, double height,
                                         Color background, int spot) {
      super(position, width, height, background, Color.green, spot);
   }

   public void onCollision(Player player) {
      player.addEffect(new PoisonEffect(player));
   }
}