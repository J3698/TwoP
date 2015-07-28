import java.util.ArrayList;
import java.awt.Graphics;

public class BumperHandler {
   private ArrayList<Bumper> bumpers;

   public BumperHandler() {
      bumpers = new ArrayList<Bumper>();
   }

   public void updateBumpers(Player firstPlayer, Player secondPlayer) {
      for (Bumper b: bumpers)
         b.update(firstPlayer, secondPlayer);
   }
   public void drawPlanes(Graphics pen) {
      for (Bumper b: bumpers)
         b.draw(pen);
   }
}