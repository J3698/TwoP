import java.awt.Color;

public class WaterPlane extends Plane {
   public WaterPlane(Vector2 position, double width, double height,
                                                 Color background) {
      super(position, width, height, background, Color.blue);
   }

   public Effect getEffect(Player player) {
      return new WaterEffect(player);
   }
}
