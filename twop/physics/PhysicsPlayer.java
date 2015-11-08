package twop.physics;

import twop.Player;
import twop.physics.Collider.CollideDirection;
import twop.physics.Collider.CollideType;

public class PhysicsPlayer extends PhysicsCircle {
   private Player myPlayer;

   public PhysicsPlayer(Player player) {
      super(player);
      myPlayer = player;
      setAnimate(true);
   }

   @Override
   public void keepInBounds() {
      if (myPlayer.getCenter().getY() + myPlayer.getRadius() > getBounds().getY() + getBounds().getHeight()) {
         myPlayer.getVelocity().setY(0);
         myPlayer.getCenter().setY(getBounds().getY() + getBounds().getHeight() - myPlayer.getRadius());
      }
      if (myPlayer.getCenter().getY() - myPlayer.getRadius() < getBounds().getY()) {
         myPlayer.getVelocity().setY(0);
         myPlayer.getCenter().setY(getBounds().getY() + myPlayer.getRadius());
      }
      if (myPlayer.getCenter().getX() + myPlayer.getRadius() > getBounds().getX() + getBounds().getWidth()) {
         myPlayer.getVelocity().setX(0);
         myPlayer.setSpeed(0);
         myPlayer.getCenter().setX(getBounds().getX() + getBounds().getWidth() - myPlayer.getRadius());
      }
      if (myPlayer.getCenter().getX() - myPlayer.getRadius() < getBounds().getX()) {
         myPlayer.getVelocity().setX(0);
         myPlayer.setSpeed(0);
         myPlayer.getCenter().setX(myPlayer.getRadius() + getBounds().getX());
      }
   }

   @Override
   public void boundRelativeTo(Collider collider, CollideDirection direction) {
      if (collider.getType() == CollideType.Rect) {
         RectCollider colliderRect = (RectCollider) collider;
         // code to change, only handles center collision, do away with directions altogether?
         if (direction.equals(CollideDirection.Center)) {
            myPlayer.resetJumps();
            myPlayer.getCenter().setY(collider.getRect().getPosition().getY() - myPlayer.getRadius());
            if (myPlayer.getVelocity().getY() < 0) {
               myPlayer.getVelocity().setY(0);
            }
         }
      }
   }
}



