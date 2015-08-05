import java.awt.Color;

public class FirePlane extends Plane {
   public FirePlane(Vector2 position, double width, double height,
                                       Color background, int spot) {
      super(position, width, height, background, Color.red, spot);
   }

   public void onCollision(Player player) {
      player.addEffect(new FireEffect(player));
   }
}
