package twop.item.container;

import twop.item.*;
import twop.Player;
import twop.util.Vector2;

import java.awt.Graphics;

public abstract class ItemContainer {
   private Item myItem;
   private Vector2 myPosition;

   public ItemContainer(Item item) {
      myItem = item;
      myPosition = item.getPosition();
   }

   public abstract void update(Player firstPlayer, Player secondPlayer);
   public abstract void draw(Graphics pen);

   public Item getItem() { return myItem; }
   public boolean isDead() { return myItem.isDead(); }
   public Vector2 getPosition() { return myPosition; }
   public void setPosition(Vector2 position) { myPosition = position; }
}