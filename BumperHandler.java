import java.awt.Graphics;
import java.util.ArrayList;

public class BumperHandler implements Subject {
   private ArrayList<Bumper> bumpers;

   public Bumper() {
      bumpers = new ArrayList<Observer>();
   }

   public void register(Obser newObserver) {
      bumpers.add(newObserver);
   }
   public void unregister(Observer deleteObserver) {
      bumpers.remove(o);
   }
   public void updateBumpers() {
      for (Bumper b: bumpers)
         b.update();
   }
   public void drawBumpers(Graphics pen) {
      for (Bumper b: bumpers)
         b.draw(pen);
   }
}
