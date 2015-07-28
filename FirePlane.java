import java.awt.Color;

public class FirePlane extends Plane {
   public FirePlane(Vector2 position, double width, double height,
                     Color background, int morphTime, int maturationTime) {
      super(position, width, height, background,
            Color.red, morphTime, maturationTime);
   }

}