import java.awt.Color;

public class PoisonPlane extends Plane {
   public PoisonPlane(Vector2 position, double width, double height,
                                                 Color background) {
      super(position, width, height, background, Color.green);
   }

   public Effect getEffect(Player player) {
      return new PoisonEffect(player);
   }
}
