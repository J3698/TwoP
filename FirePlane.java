import java.awt.Color;

public class FirePlane extends Plane {
   public FirePlane(Vector2 position, double width, double height,
                                                 Color background) {
      super(position, width, height, background, Color.red);
   }

   public Effect getEffect(Player player) {
      return new FireEffect(player);
   }
}
