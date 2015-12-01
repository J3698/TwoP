package twop.weather;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import twop.GamePanel;
import twop.item.container.Asteroid;
import twop.item.container.ItemContainer;
import twop.util.Camera;
import twop.util.Vector2;

public class VolcanicEruption extends Weather {
   private Camera myCam;
   private ArrayList<ItemContainer> myDebris;

   public VolcanicEruption(int life, GamePanel gamePanel) {
      super(life, gamePanel);
      myCam = getGamePanel().getCamera();
      myDebris = new ArrayList<ItemContainer>();
   }

   @Override
   public void update() {
      manageDebris();
      screenShake();
      tick();
   }

   public void manageDebris() {
      Random r = new Random();

      if (r.nextDouble() < 0.05) {
         Vector2 randPos = new Vector2(r.nextInt(getGamePanel().getGameWidth()),
               r.nextInt(getGamePanel().getGameHeight()));
         Vector2 randVelocity = Vector2.randVector(0,  2 * Math.PI);
         randVelocity.multiply(4);

         myDebris.add(new Asteroid(new FireCircle(randPos), randVelocity));
      }

      for (int i = myDebris.size() - 1; i >= 0; i--) {
         myDebris.get(i).update(
               getGamePanel().getPlay().getFirstPlayer(),
               getGamePanel().getPlay().getSecondPlayer());
         if (myDebris.get(i).isDead()) {
            myDebris.remove(i);
         }
      }
   }

   public void screenShake() {
      myCam.reset();

      Vector2 pos1 = myCam.getPos1().copy();
      Vector2 offset1 = Vector2.randSquareVector(0, 0, 5, 5);
      pos1.addVector(offset1);
      pos1.addVector(new Vector2(5, 5));

      Vector2 pos2 = myCam.getPos2().copy();
      Vector2 offset2 = Vector2.randSquareVector(0, 0, -5, 0);
      pos2.addVector(offset2);
      pos2.addVector(new Vector2(-5, 0));

      myCam.setPerspective(pos1, pos2);
   }

   @Override
   public void draw(Graphics pen) {
      for (ItemContainer debris : myDebris) {
         debris.draw(pen);
      }
   }

   @Override
   public void close() {
      getGamePanel().getCamera().reset();
   }
}
