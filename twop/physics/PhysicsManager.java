package twop.physics;

import java.util.ArrayList;

public class PhysicsManager {
   private ArrayList<PhysicsObject> myObjects;
   public PhysicsManager() {
      myObjects = new ArrayList<PhysicsObject>();
   }

   public void update() {
      for (PhysicsObject object : myObjects) {
         //keep in bounds
         object.keepInBounds();
         //compare animate objects to solid objects
         if (object.isAnimate()) {
            //check solids against animate object
            for (PhysicsObject solid : myObjects) {
               if (solid.isSolid() && ! solid.isAnimate()) {
                  if (solid.collides(object)) {
                  }
               }
            }
         }
      }
   }
   public void add(PhysicsObject object) {
      myObjects.add(object);
   }
}
