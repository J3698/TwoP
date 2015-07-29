import java.awt.Color;

public class HealthPlane extends Plane {
   public HealthPlane(Vector2 position, double width, double height,
                                                 Color background) {
      super(position, width, height, background, Color.white);
   }

   public Effect getEffect(Player player) {
      return new HealthEffect(player);
   }
}
