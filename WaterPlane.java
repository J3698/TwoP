import java.awt.Color;

public class WaterPlane extends Plane {
   public WaterPlane(Vector2 position, double width, double height,
                                        Color background, int spot) {
      super(position, width, height, background, Color.blue, spot);
   }

   public Effect getEffect(Player player) {
      return new WaterEffect(player);
   }
}
