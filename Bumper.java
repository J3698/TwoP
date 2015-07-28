import java.awt.Color;

public class Bumper extends Circle {
   private double myStrength;
   private int myMorphTime;
   private int myMaturationTime;
   private ColorCross myColorCross;
   private int myAge = 0;
   /**
    *
    *
    */
    public Bumper(Vector2 position, double radius, Color background,
                     Color color, int morphTime, int maturationTime) {
      super(position, radius);
      setColor(color);
      setOutline(color);
      myColorCross = new ColorCross(background, color, 1);
      myMorphTime = morphTime;
      myMaturationTime = maturationTime;
      myStrength = 8;
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      Vector2 bumpForce;
      if (collidesCircle(firstPlayer)) {
         bumpForce = firstPlayer.getCenter().copy();
         bumpForce.subtractVector(getCenter());
         bumpForce.setMagnitude(myStrength);
         firstPlayer.applyForce(bumpForce);
      }
      if (collidesCircle(secondPlayer)) {
         bumpForce = secondPlayer.getCenter().copy();
         bumpForce.subtractVector(getCenter());
         bumpForce.setMagnitude(myStrength);
         secondPlayer.applyForce(bumpForce);
      }
 
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
}
