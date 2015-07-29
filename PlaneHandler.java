import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class PlaneHandler {
   private int myGameWidth;
   private int myGameHeight;
   private boolean triggered = false;
   private ArrayList<Plane> myPlanes;
   private Random myRandom;

   public PlaneHandler(int gameWidth, int gameHeight) {
      myGameHeight = gameHeight;
      myGameWidth = gameWidth;
      myPlanes = new ArrayList<Plane>();
      myRandom = new Random();
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (myRandom.nextInt(500) == 44 && triggered == false) {
         Vector2 pos = new Vector2(myGameWidth / 2, myGameHeight - 50);
         myPlanes.add(new FirePlane(pos, 50, 20, firstPlayer.getColor(), 200, 2000000));
         triggered = true;
      }
      Plane p;
      for (int index = 0; index < myPlanes.size(); index++) {
         p = myPlanes.get(index);
         if (p.isDead())
            myPlanes.remove(p);
         else
            p.update(firstPlayer, secondPlayer);
      }
   }

   public void draw(Graphics pen) {
      for (Plane p: myPlanes)
         p.draw(pen);
   }
}
