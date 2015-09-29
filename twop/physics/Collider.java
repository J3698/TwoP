package twop.physics;

import twop.Circle;
import twop.Rect;

public interface Collider {
   public enum CollideType {
      Circle, Rect, Point
   }
   public enum CollideDirection {
      Top, Bottom,
      Left, Right,
      TopLeft, TopRight,
      BottomLeft, BottomRight,
      Center, None
   }

   CollideDirection collides(Collider c);

   Circle getCircle();
   Rect getRect();

   CollideType getType();
}