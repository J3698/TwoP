package twop.handler;

import twop.item.*;
import twop.Player;

import java.awt.Graphics;

public class ItemHandler {
   private int myGameWidth;
   private int myGameHeight;
   private ItemFactory myItemFactory;

   public ItemHandler(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myItemFactory = new ItemFactory();
   }

   public void draw(Graphics pen) {
   }
   public void update(Player firstPlayer, Player secondPlayer) {
   }

   //Implement different sorts of entrances, e.g. from explosion of
   //particle system, falling from the sky, etc.

   private class ItemFactory {
      private String[] myDropOptions = {};

/*
      public Item getItem() {
         return ;
      }
*/
   }
}