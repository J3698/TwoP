package twop.physics;

import java.awt.Rectangle;

public interface PhysicsObject {
   boolean collides(PhysicsObject object);
   void keepInBounds();
   Rectangle getBounds();
   void setBounds(Rectangle bounds);
   int getWidth();
   int getHeight();
   boolean isAnimate();
   void setAnimate(boolean isAnimate);
   boolean isSolid();
   void setSolid(boolean isSolid);
}