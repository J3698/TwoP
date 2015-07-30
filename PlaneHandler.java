import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class PlaneHandler {
   private int myGameWidth;
   private int myGameHeight;
   private int myPlaneWidth = 40;
   private int myPlaneHeight = 7;
   private PlaneFactory myPlaneFactory;
   private Plane[] myAvailableSpots;
   private ArrayList<Plane> myPlanes;
   private Random myRandom;

   public PlaneHandler(int gameWidth, int gameHeight) {
      myGameHeight = gameHeight;
      myGameWidth = gameWidth;
      myPlanes = new ArrayList<Plane>();
      myPlaneFactory = new PlaneFactory();
      myRandom = new Random();

      myAvailableSpots = new Plane[myGameWidth / myPlaneWidth];
      for (int index = 0; index < myAvailableSpots.length; index++)
         myAvailableSpots[index] = null;
   }

   public void createPlane(Player firstPlayer, Player secondPlayer) {
      int finalIndex = 0;
      int spaces = 0;
      for (int index = 0; index < myAvailableSpots.length; index++) {
         if (myAvailableSpots[index] == null)
            spaces++;
      }
      int mySpot = myRandom.nextInt(spaces);
      spaces = 0;
      for (int index = 0; index < myAvailableSpots.length; index++) {
         if (myAvailableSpots[index] == null)
            spaces++;
         if (spaces == mySpot)
            finalIndex = index;
      }

      System.out.println(finalIndex);
      int startX = finalIndex * (myGameWidth / myAvailableSpots.length) + myGameWidth % myPlaneWidth / 2;
      Vector2 position = new Vector2(startX, myGameHeight - myPlaneHeight);
      if (startX < myGameWidth / 2 - myPlaneWidth) {
         myPlanes.add(myPlaneFactory.getRandomPlane(position, myPlaneWidth, myPlaneHeight, secondPlayer.getColor()));
      } else if (startX > myGameWidth / 2) {
         myPlanes.add(myPlaneFactory.getRandomPlane(position, myPlaneWidth, myPlaneHeight, firstPlayer.getColor()));
      } else {
//         myPlanes.add(new FirePlane(pos, myPlaneWidth, myPlaneHeight, firstPlayer.getColor()));
//         myPlanes.add(new FirePlane(pos, myPlaneWidth, myPlaneHeight, second.getColor()));
      }

   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (myRandom.nextInt(3) == 0)
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
      private String[] myPlaneOptions = {"fire", "health"};

      public Plane getRandomPlane(Vector2 position, int width, int height, Color background) {
         String choice = myPlaneOptions[myRandom.nextInt(myPlaneOptions.length)];
         return getPlane(choice, position, width, height, background);
      }
      public Plane getPlane(String planeType, Vector2 position, int width, int height, Color background) {
         if (planeType.equalsIgnoreCase("fire"))
            return new FirePlane(position, width, height, background);
         else if (planeType.equalsIgnoreCase("water"))
            return new WaterPlane(position, width, height, background);
         else if (planeType.equalsIgnoreCase("health"))
            return new HealthPlane(position, width, height, background);
         else if (planeType.equalsIgnoreCase("poison"))
            return new PoisonPlane(position, width, height, background);
         return new FirePlane(position, width, height, background);
      }
   }
}