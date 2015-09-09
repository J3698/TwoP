package twop.physics;

import java.awt.Rectangle;

public class PhysicsRect implements PhysicsObject {
   private Rectangle myBounds;
   private boolean myIsAnimate;
   private boolean myIsSolid;

   public PhysicsRect() {
   }

   public boolean collides(PhysicsCircle circle) {
      // TODO Auto-generated method stub
      return false;
   }

   public boolean collides(PhysicsRect rect) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void keepInBounds() {
      // TODO Auto-generated method stub

   }

   @Override
   public Rectangle getBounds() {
      return myBounds;
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

   @Override
   public boolean isAnimate() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void setAnimate(boolean isAnimate) {
      myIsAnimate = isAnimate;
   }

   @Override
   public void setSolid(boolean isSolid) {
      myIsSolid = isSolid;
   }

   @Override
   public boolean collides(PhysicsObject object) {
      // TODO Auto-generated method stub
      return false;
   }

}
