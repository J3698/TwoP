import java.awt.Color;

public class Plane extends Rect implements Observer {
   private int myMorphTime;
   private int myMaturationTime;
   private ColorCross myColorCross;
   private int myAge = 0;
   /**
    *
    *
    */
    public Plane(Vector2 position, double width, double height,
                   Color background, Color color, int morphTime,
                                             int maturationTime) {
      super(position, width, height);
      setColor(color);
      setOutline(color);
      myColorCross = new ColorCross(background, color, 1);
      myMorphTime = morphTime;
      myMaturationTime = maturationTime;
   }

   public void update() {
      if (myAge <= myMorphTime) {
         //Growth time
         setAllColor(myColorCross.getPercent((myAge + 0.0) / myMorphTime));
      }else if (myAge <= myMaturationTime + myMorphTime) {
         //Stable time
      }else if (myAge <= myMaturationTime + 2 * myMorphTime) {
         //Aging time
         setAllColor(myColorCross.getPercent((myAge + 0.0) /
                        (myMaturationTime + 2 * myMorphTime)));
      }else {
         //Die

      }
      myAge++;
   }
   
   public Effect getEffect(Player player) {
      return new Effect(player);
   }
}
