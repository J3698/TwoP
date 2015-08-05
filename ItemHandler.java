public class ItemHandler {
   private int myGameWidth;
   private int myGameHeight;
   private ItemFactory myItemFactory;

   public DropHandler(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myItemFactory = new ItemFactory();
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