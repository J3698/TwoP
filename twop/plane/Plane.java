package twop.plane;

import twop.util.ColorCross;
import twop.util.Vector2;
import twop.Player;
import twop.Rect;

import java.awt.Color;

public abstract class Plane extends Rect {
   private ColorCross myColorCross;
   private int myMorphTime = 100;
   private int myMaturationTime = 100;
   private int myAge = 0;
   private int mySpot;
   /**
    *
    *
    */
    public Plane(Vector2 position, double width, double height,
                       Color background, Color color, int spot) {
      super(position, width, height);
      setColor(color);
      setOutline(color);
      mySpot = spot;
      myColorCross = new ColorCross(background, color, 1);
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (collidesCircle(firstPlayer))
         onCollision(firstPlayer);
      if (collidesCircle(secondPlayer))
         onCollision(secondPlayer);

      if (myAge <= myMorphTime)
         setAllColor(myColorCross.getPercent((myAge + 0.0) / myMorphTime));
      else if (myAge <= myMaturationTime + myMorphTime);
      else if (myAge <= myMaturationTime + 2 * myMorphTime)
         setAllColor(myColorCross.getPercent((1 - (myAge - (myMaturationTime + myMorphTime)) / (double)myMorphTime)));

      myAge++;
   }

   public void setMorphTime(int morphTime) {
      myMorphTime = morphTime;
   }
   public void setmyMaturationTime(int maturationTime) {
      myMaturationTime = maturationTime;
   }

   public abstract void onCollision(Player player);

   public boolean isDead() {
      return (myAge > myMaturationTime + 2 * myMorphTime);
   }

   public int getSpot() {
      return mySpot;
   }
   
   public void setSpot(int spot) {
      mySpot = spot;
   }
}