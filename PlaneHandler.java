import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class PlaneHandler {
   private int myGameWidth;
   private int myGameHeight;
   private ArrayList<Plane> planes;
   private Random myRandom;

   public PlaneHandler(int gameWidth, int gameHeight) {
      myGameHeight = gameHeight;
      myGameWidth = gameWidth;
      planes = new ArrayList<Plane>();
      myRandom = new Random();
   }

   public void update() {
      if (myRandom.nextInt(1000) == 44) {
         planes.add(new FirePlane(new Vector2(myGameWidth / 2, myGameHeight - 10),
                                                   30, 10, Color.green, 200, 200));
         System.out.println(",,,,....");
      }
      for (Plane p: planes)
         p.update();
   }
   public void draw(Graphics pen) {
      for (Plane p: planes)
         p.draw(pen);
   }
}
