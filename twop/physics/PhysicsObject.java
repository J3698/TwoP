package twop.physics;

import java.awt.Rectangle;

import twop.physics.Collider.CollideDirection;

public interface PhysicsObject {
   Collider getCollider();
   void keepInBounds();
   void boundRelativeTo(Collider collider, CollideDirection direction);
   Rectangle getBounds();
   void setBounds(Rectangle bounds);
   int getWidth();
   int getHeight();
   boolean isAnimate();
   void setAnimate(boolean isAnimate);
   boolean isSolid();
   void setSolid(boolean isSolid);
}