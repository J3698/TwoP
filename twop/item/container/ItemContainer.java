package twop.item.container;

import twop.item.*;
import twop.util.Vector2;

import java.awt.Graphics;

public class ItemContainer {
   private Item myItem;
   private Vector2 myPosition;

   public ItemContainer(Item item, Vector2 position) {
      myItem = item;
      myPosition = position;
   }

   public void update() {
      
   }

   public void draw() {
      // myItem.draw(), this block
      // may have condititionals
      // to draw a different effect,
      // e.g. if a meteor shower is
      // occuring, or something else
   }
}