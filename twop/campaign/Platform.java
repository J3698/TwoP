package twop.campaign;

import twop.Rect;
import twop.physics.PhysicsObject;
import twop.physics.PhysicsRect;
import twop.util.Vector2;

public class Platform extends Rect {
   private PhysicsRect myPhysics;

   public Platform(Vector2 position, double width, double height) {
      super(position, width, height);
      myPhysics = new PhysicsRect(this);
      myPhysics.setAnimate(false);
      myPhysics.setSolid(true);
   }

   public PhysicsObject getPhysics() {
      return myPhysics;
   }
}