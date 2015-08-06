package twop.handler;

import twop.item.*;
import twop.Player;
import twop.util.Vector2;

import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;

public class ItemHandler {
   private int myGameWidth;
   private int myGameHeight;
   private Random myRandom;
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
      for (Item item: myItems)
         item.draw(pen);
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (myRandom.nextDouble() < 0.001) {
         createItem();
      }

      Item item;
      for (int index = 0; index < myItems.size(); index++) {
         item = myItems.get(index);
         if (item.isDead())
            myItems.remove(item);
         else {
            item.update(firstPlayer, secondPlayer);
            item.getPosition().addY(1);
         }
      }
   }

   public void createItem() {
      myItems.add(new HealthPack(new Vector2(myRandom.nextInt(myGameWidth), -100)));
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