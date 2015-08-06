package twop.handler;

import twop.item.*;
import twop.Player;

import java.awt.Graphics;
import java.util.Random;
import

public class ItemHandler {
   private int myGameWidth;
   private int myGameHeight;
   private Random myRandom
   private ArrayList<Item> myItems;
   private ItemFactory myItemFactory;

   public ItemHandler(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myRandom = new Random();
      myItems = new ArrayList<Item>();
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