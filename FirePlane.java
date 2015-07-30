import java.awt.Color;

public class FirePlane extends Plane {
   public FirePlane(Vector2 position, double width, double height,
                                       Color background, int spot) {
      super(position, width, height, background, Color.red, spot);
   }

   public Effect getEffect(Player player) {
      return new FireEffect(player);
   }
}
