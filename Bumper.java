public class Bumper extends Circle implements Observer {
   private int myMorphTime;
   private int myMatureLifetime;
   private ColorCross myColorCross;
   private int myAge = 0;
   /**
    *
    *
    */
    public Bumper(Vector2 position, double width, double height,
                   Color background, Color color, int morphTime,
                                                 maturationTime) {
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
         setAllColor(myColorCross.getPercent((myAge + 0.0) / morphTime));
      }else if (myAge <= myMaturationTime + myMorphTime) {
         //Stable time
      }else if (myAge <= myMaturationTime + 2 * myMorphTime) {
         //Aging time
         setAllColor(myColorCross.getPercent((myAge + 0.0) /
                        (myMaturationTime + 2 * morphTime)));
      }else {
         //Die

      }
      myAge++;
   }
}
