package twop.item;

import twop.item.container.*;
import twop.Player;
import twop.util.Vector2;

import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;

public class ItemHandler {
   private int myGameWidth;
   private int myGameHeight;
   private Random myRandom;
   private ArrayList<ItemContainer> myItems;
   private ItemFactory myItemFactory;

   public ItemHandler(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myRandom = new Random();
      myItems = new ArrayList<ItemContainer>();
      myItemFactory = new ItemFactory();
   }

   public void draw(Graphics pen) {
      for (ItemContainer item: myItems)
         item.draw(pen);
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (myRandom.nextDouble() < 0.001) {
         createItem();
      }

      ItemContainer item;
      for (int index = 0; index < myItems.size(); index++) {
         item = myItems.get(index);
         if (item.isDead())
            myItems.remove(item);
         else {
            item.update(firstPlayer, secondPlayer);
         }
      }
   }

   public void reset() {
      myItems = new ArrayList<ItemContainer>();
   }

   public void createItem() {
      myItems.add(new FallingItem(new HealthPack(new Vector2(myRandom.nextInt(myGameWidth), -100)), 2));
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