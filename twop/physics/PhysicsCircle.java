package twop.physics;

import java.awt.Rectangle;

import twop.Circle;
import twop.physics.Collider.CollideDirection;

public class PhysicsCircle implements PhysicsObject {
   private Circle myCircle;
   private Rectangle myBounds;
   private CircleCollider myCollider;
   private boolean myIsSolid = false;
   private boolean myIsAnimate = false;

   public PhysicsCircle(Circle circle) {
      myCircle = circle;
      myCollider = new CircleCollider(this);
   }

   @Override
   public void keepInBounds() {
      /*
      if (myCircle.getCenter().getY() + myCircle.getRadius() > myBounds.getY() + myBounds.getHeight()) {
         myVelocity.setY(0);
         getCenter().setY(myBounds.getY() + myBounds.getHeight() - getRadius());
      }
      if (getCenter().getY() - getRadius() < myBounds.getY()) {
         myVelocity.setY(0);
         getCenter().setY(myBounds.getY() + getRadius());
      }
      if (getCenter().getX() + getRadius() > myBounds.getX() + myBounds.getWidth()) {
         myVelocity.setX(0);
         mySpeed = 0;
         getCenter().setX(myBounds.getX() + myBounds.getWidth() - getRadius());
      }
      if (getCenter().getX() - getRadius() < myBounds.getX()) {
         myVelocity.setX(0);
         mySpeed = 0;
         getCenter().setX(getRadius() + myBounds.getX());
      }
       */
   }

   @Override
   public void boundRelativeTo(Collider collider, CollideDirection direction) {
      System.out.println("Unimplemented.");
      /*
      if (collider.getType() == CollideType.Rect) {
           RectCollider colliderRect = (RectCollider) collider;
      }
       */

   }

   @Override
   public Rectangle getBounds() {
      return myBounds;
   }

   @Override
   public void setBounds(Rectangle bounds) {
      myBounds = bounds;
   }

   @Override
   public int getWidth() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getHeight() {
      return 0;
   }

   @Override
   public boolean isSolid() {
      return myIsSolid;
   }

   @Override
   public boolean isAnimate() {
      return myIsAnimate;
   }

   @Override
   public void setAnimate(boolean isAnimate) {
      myIsAnimate = isAnimate;
   }

   @Override
   public void setSolid(boolean isSolid) {
      // TODO Auto-generated method stub

   }

   @Override
   public Collider getCollider() {
      return myCollider;
   }

   public Circle getCircle() {
      return myCircle;
   }
}