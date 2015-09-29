package twop.physics;

import twop.Circle;
import twop.Rect;
import twop.util.Vector2;

public class CircleCollider implements Collider {
   private PhysicsCircle myCircle;

   public CircleCollider(PhysicsCircle circle) {
      myCircle = circle;
   }

   @Override
   public CollideDirection collides(Collider collider) {
      if (collider.getType() == CollideType.Rect) {
         return collider.collides(this);
      }

      System.out.println("Collider Type Unrecognized.");
      return CollideDirection.None;
   }

   public Vector2 getCenter() {
      return myCircle.getCircle().getCenter();
   }

   public double getRadius() {
      return myCircle.getCircle().getRadius();
   }

   @Override
   public CollideType getType() {
      return CollideType.Circle;
   }

   @Override
   public Circle getCircle() {
      return myCircle.getCircle();
   }

   @Override
   public Rect getRect() {
      // TODO Auto-generated method stub
      return null;
   }
}
