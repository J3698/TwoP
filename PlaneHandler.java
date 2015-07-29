import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class PlaneHandler {
   private int myGameWidth;
   private int myGameHeight;
   private int myPlaneWidth = 50;
   private int myPlaneHeight = 20;
   private PlaneFactory myPlaneFactory;
   private ArrayList<Plane> myPlanes;
   private Random myRandom;

   public PlaneHandler(int gameWidth, int gameHeight) {
      myGameHeight = gameHeight;
      myGameWidth = gameWidth;
      myPlanes = new ArrayList<Plane>();
      myPlaneFactory = new PlaneFactory();
      myRandom = new Random();
   }

   public void createPlane(Player firstPlayer, Player secondPlayer) {
      int startX = myRandom.nextInt(myGameWidth - myPlaneWidth);
      Vector2 position = new Vector2(startX, myGameHeight - myPlaneHeight);
      if (startX < myGameWidth / 2 - myPlaneWidth) {
         myPlanes.add(myPlaneFactory.getPlane("fire", position, myPlaneWidth, myPlaneHeight, secondPlayer.getColor()));
      } else if (startX > myGameWidth / 2) {
         myPlanes.add(myPlaneFactory.getPlane("fire", position, myPlaneWidth, myPlaneHeight, firstPlayer.getColor()));
      } else {
//         myPlanes.add(new FirePlane(pos, myPlaneWidth, myPlaneHeight, firstPlayer.getColor()));
//         myPlanes.add(new FirePlane(pos, myPlaneWidth, myPlaneHeight, second.getColor()));
      }

   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (myRandom.nextInt(500) == 0)
         createPlane(firstPlayer, secondPlayer);

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

   private class PlaneFactory {
      public Plane getPlane(String planeType, Vector2 position, int width, int height, Color backGround) {
         if (planeType.equalsIgnoreCase("Fire"))
            return new FirePlane(position, width, height, backGround);
         else if (planeType.equalsIgnoreCase("Water"))
            return new WaterPlane(position, width, height, backGround);
         else if (planeType.equalsIgnoreCase("Health"))
            return new HealthPlane(position, width, height, backGround);
         else if (planeType.equalsIgnoreCase("Poison"))
            return new PoisonPlane(position, width, height, backGround);
         return new Plane(position, width, height, backGround, Color.gray);
      }
   }
}









//
