import java.awt.Color;

public class Plane extends Rect {
   private ColorCross myColorCross;
   private int myMorphTime = 200;
   private int myMaturationTime = 200;
   private int myAge = 0;
   /**
    *
    *
    */
    public Plane(Vector2 position, double width, double height,
                                 Color background, Color color) {
      super(position, width, height);
      setColor(color);
      setOutline(color);
      myColorCross = new ColorCross(background, color, 1);
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (collidesCircle(firstPlayer)) {
      }
      if (collidesCircle(secondPlayer)) {
      }
      if (myAge <= myMorphTime) {
         setAllColor(myColorCross.getPercent((myAge + 0.0) / myMorphTime));
      }else if (myAge <= myMaturationTime + myMorphTime) {
      }else if (myAge <= myMaturationTime + 2 * myMorphTime) {
         setAllColor(myColorCross.getPercent((1 - (myAge - (myMaturationTime + myMorphTime)) / (double)myMorphTime)));
      }
      myAge++;
   }
   
   public void setMorphTime(int morphTime) {
      myMorphTime = morphTime;
   }
   public void setmyMaturationTime(int maturationTime) {
      myMaturationTime = maturationTime;
   }
   public Effect getEffect(Player player) {
      return new Effect(player);
   }
   public boolean isDead() {
      return (myAge > myMaturationTime + 2 * myMorphTime);
   }
}