package twop.Physics;

import java.awt.Rectangle;

public interface Physics {

   public static enum CollisionType {
      CIRCLE, RECTANGLE, NONE
   }

   public Rectangle getBounds();
   public CollisionType getCollisionType();
   public boolean isSolid();
   public boolean isSolidFromBelow();
}
