package twop.item.container;

import twop.item.Item;
import twop.Player;

import java.awt.Graphics;

public class FallingItem extends ItemContainer {
   private int mySpeed;

   public FallingItem(Item item, int speed) {
      super(item);
      mySpeed = speed;
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      getItem().update(firstPlayer, secondPlayer);
      getPosition().addY(mySpeed);
   }
   public void draw(Graphics pen) {
      getItem().draw(pen);
   }

   public int getSpeed() { return mySpeed; }
   public void setSpeed(int speed) { mySpeed = speed; }
}