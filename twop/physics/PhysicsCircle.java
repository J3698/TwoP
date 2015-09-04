package twop.physics;

import java.awt.Rectangle;

import twop.Circle;

public class PhysicsCircle implements PhysicsObject {
   private Circle myCircle;

   public PhysicsCircle(Circle circle) {
      myCircle = circle;
   }

   @Override
   public boolean collides(PhysicsCircle circle) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean collides(PhysicsRect rect) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void keepInBounds() {
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
   }

   @Override
   public Rectangle getBounds() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setBounds(Rectangle bounds) {
      // TODO Auto-generated method stub

   }

   @Override
   public int getWidth() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getHeight() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public boolean isSolid() {
      // TODO Auto-generated method stub
      return false;
   }

}