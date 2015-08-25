package twop.plane;

import twop.Player;
import twop.util.Vector2;

import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

public class PlaneHandler {
   private int myGameWidth;
   private int myGameHeight;
   private int myPlaneWidth = 40;
   private int myPlaneHeight = 7;
   private PlaneFactory myPlaneFactory;
   private Plane[] myAvailableSpots;
   private Random myRandom;

   public PlaneHandler(int gameWidth, int gameHeight) {
      myGameHeight = gameHeight;
      myGameWidth = gameWidth;
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
      if (spaces == 0)
         return;
      int mySpot = myRandom.nextInt(spaces) + 1;
      spaces = 0;
      for (int index = 0; index < myAvailableSpots.length; index++) {
         if (myAvailableSpots[index] == null)
            spaces++;
         if (spaces == mySpot) {
            finalIndex = index;
            break;
         }
      }
      int startX = finalIndex * (myGameWidth / myAvailableSpots.length) + (myGameWidth % myPlaneWidth) / 2;
      Vector2 position = new Vector2(startX, myGameHeight - myPlaneHeight);
      if (startX < myGameWidth / 2 - myPlaneWidth) {
         Plane newPlane = myPlaneFactory.getRandomPlane(position, myPlaneWidth, myPlaneHeight,
                                                          secondPlayer.getColor(), finalIndex);
         myAvailableSpots[finalIndex] = newPlane;
      } else {
         Plane newPlane = myPlaneFactory.getRandomPlane(position, myPlaneWidth, myPlaneHeight,
                                                           firstPlayer.getColor(), finalIndex);
         myAvailableSpots[finalIndex] = newPlane;
      }
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (myRandom.nextInt(1000) < 5)
         createPlane(firstPlayer, secondPlayer);

      Plane plane;
      for (int index = 0; index < myAvailableSpots.length; index++)  {
         plane = myAvailableSpots[index];
         if (plane != null) {
            if (plane.isDead())
               myAvailableSpots[index] = null;
            else
               plane.update(firstPlayer, secondPlayer);
         }
      }
   }

   public void reset() {
      for (int index = 0; index < myAvailableSpots.length; index++)
         myAvailableSpots[index] = null;
   }

   public void draw(Graphics pen) {
      for (Plane plane: myAvailableSpots) {
         if (plane != null)
            plane.draw(pen);
      }
   }

   private class PlaneFactory {
      private String[] myPlaneOptions = {"fire", "health", "poison"};

      public Plane getRandomPlane(Vector2 position, int width, int height, Color background, int spot) {
         String choice = myPlaneOptions[myRandom.nextInt(myPlaneOptions.length)];
         return getPlane(choice, position, width, height, background, spot);
      }
      public Plane getPlane(String planeType, Vector2 position, int width, int height, Color background, int spot) {
         if (planeType.equalsIgnoreCase("fire"))
            return new FirePlane(position, width, height, background, spot);
         else if (planeType.equalsIgnoreCase("water"))
            return new WaterPlane(position, width, height, background, spot);
         else if (planeType.equalsIgnoreCase("health"))
            return new HealthPlane(position, width, height, background, spot);
         else if (planeType.equalsIgnoreCase("poison"))
            return new PoisonPlane(position, width, height, background, spot);
         return new FirePlane(position, width, height, background, spot);
      }
   }
}