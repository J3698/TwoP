package twop.physics;

import twop.Player;

public class PhysicsPlayer extends PhysicsCircle {
   private Player myPlayer;

   public PhysicsPlayer(Player player) {
      super(player);
      myPlayer = player;
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
}