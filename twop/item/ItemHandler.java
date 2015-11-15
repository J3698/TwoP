package twop.item;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import twop.Player;
import twop.item.container.FallingItem;
import twop.item.container.ItemContainer;
import twop.util.Vector2;

public class ItemHandler {
   private int myGameWidth;
   @SuppressWarnings("unused")
   private int myGameHeight;
   private Random myRandom;
   private ArrayList<ItemContainer> myItems;
   @SuppressWarnings("unused")
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
      @SuppressWarnings("unused")
      private String[] myDropOptions = {};

      /*
      public Item getItem() {
         return ;
      }
       */
   }
}