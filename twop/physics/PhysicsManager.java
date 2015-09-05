package twop.physics;

import java.util.ArrayList;

public class PhysicsManager {
   private ArrayList<PhysicsObject> myObjects;
   public PhysicsManager() {
      myObjects = new ArrayList<PhysicsObject>();
   }

   public void update() {
      for (PhysicsObject object : myObjects) {
         object.keepInBounds();
      }
   }
   public void resgister(PhysicsObject object) {
      myObjects.add(object);
   }
}
