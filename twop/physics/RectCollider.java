package twop.physics;

import twop.Circle;
import twop.Rect;
import twop.util.Vector2;

public class RectCollider implements Collider {
   public Rect myRect;

   public RectCollider(Rect rect) {
      myRect = rect;
   }

   @Override
   public CollideDirection collides(Collider collider) {
      if (collider.getType() == CollideType.Circle) {
         return collidesCircle((CircleCollider) collider);
      }

      System.out.println("Collider Type Unrecognized.");
      return CollideDirection.None;
   }

   public CollideDirection collidesCircle(CircleCollider c) {
      CollideDirection collides = CollideDirection.None;

      boolean aboveEdge = c.getCircle().getCenter().getX() > myRect.getPosition().getX();
      boolean belowEdge = c.getCenter().getX() < myRect.getPosition().getX() + myRect.getWidth();
      boolean rightOfEdge = c.getCenter().getY() > myRect.getPosition().getY();
      boolean leftOfEdge = c.getCenter().getY() < myRect.getPosition().getY() + myRect.getHeight();

      if ((aboveEdge && belowEdge) || (rightOfEdge && leftOfEdge)) {
         if (myRect.getPosition().getX() < c.getCenter().getX() + c.getRadius()) {
            if (myRect.getPosition().getX() + myRect.getWidth() > c.getCenter().getX() - c.getRadius()) {
               if (myRect.getPosition().getY() < c.getCenter().getY() + c.getRadius()) {
                  if (myRect.getPosition().getY() + myRect.getHeight() > c.getCenter().getY() - c.getRadius()) {
                     collides = CollideDirection.Center;
                  }
               }
            }
         }
      } else {
         Vector2 firstCorner = new Vector2(myRect.getPosition().getX(), myRect.getPosition().getY());
         Vector2 secondCorner = new Vector2(myRect.getPosition().getX() + myRect.getWidth(), myRect.getPosition().getY());
         Vector2 thirdCorner = new Vector2(myRect.getPosition().getX(), myRect.getPosition().getY() + myRect.getHeight());
         Vector2 fourthCorner = new Vector2(myRect.getPosition().getX() + myRect.getWidth(), myRect.getPosition().getY() + myRect.getHeight());
         Vector2[] corners = {firstCorner, secondCorner, thirdCorner, fourthCorner};
         CollideDirection[] cornerCollideTypes = {CollideDirection.TopLeft, CollideDirection.BottomLeft,
               CollideDirection.TopRight, CollideDirection.BottomRight};
         Vector2 v;
         for (int index = 0; index < corners.length; index++) {
            v = corners[index];
            v.subtractVector(c.getCenter());
            if (v.getMagnitude() <= c.getRadius()) {
               collides = cornerCollideTypes[index];
               break;
            }
         }
      }

      return collides;
   }

   @Override
   public CollideType getType() {
      return CollideType.Rect;
   }

   @Override
   public Circle getCircle() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Rect getRect() {
      return myRect;
   }

}
