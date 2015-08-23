package twop.bumper;

import twop.Player;

import java.util.ArrayList;
import java.awt.Graphics;

public class BumperHandler {
   private int myGameWidth;
   private int myGameHeight;
   private ArrayList<Bumper> bumpers;

   public BumperHandler(int gameWidth, int gameHeight) {
      myGameHeight = gameHeight;
      myGameWidth = gameWidth;
      bumpers = new ArrayList<Bumper>();
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      for (Bumper b: bumpers)
         b.update(firstPlayer, secondPlayer);
   }

   public void reset() {
      bumpers = new ArrayList<Bumper>();
   }

   public void draw(Graphics pen) {
      for (Bumper b: bumpers)
         b.draw(pen);
   }
}