package twop.physics;

import java.awt.Rectangle;

import twop.Rect;
import twop.physics.Collider.CollideDirection;

public class PhysicsRect implements PhysicsObject {
   private Rectangle myBounds;
   private Rect myRect;
   private boolean myIsAnimate = false;
   private boolean myIsSolid;
   private RectCollider myCollider;

   public PhysicsRect(Rect rect) {
      myRect = rect;
      myCollider = new RectCollider(myRect);
   }

   @Override
   public void keepInBounds() {
      //      System.out.println("Not implemented.");
   }

   @Override
   public Rectangle getBounds() {
      return myBounds;
   }

   @Override
   public void setBounds(Rectangle bounds) {
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
      myIsSolid = isSolid;
   }

   @Override
   public Collider getCollider() {
      return myCollider;
   }

   public Rect getRect() {
      return myRect;
   }

   @Override
   public int getWidth() {
      System.out.println("Not implemented.");
      return 0;
   }

   @Override
   public int getHeight() {
      System.out.println("Not implemented.");
      return 0;
   }

   @Override
   public void boundRelativeTo(Collider collider, CollideDirection direction) {
      System.out.println("Not implemented.");
   }
}
