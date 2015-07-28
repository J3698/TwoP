import java.awt.Graphics;
import java.util.ArrayList;

public class PlaneHandler {
   private ArrayList<Plane> planes;

   public PlaneHandler() {
      planes = new ArrayList<Plane>();
   }

   public void update() {
      for (Plane p: planes)
         p.update();
   }
   public void drawPlanes(Graphics pen) {
      for (Plane p: planes)
         p.draw(pen);
   }
}
