public class FireBumper extends Bumper() {
   public FireBumper(Vector2 position, double width, double height,
                     Color background, int morphTime, int maturationTime) {
      super(position, width, height, background,
            Color.red, morphTime, maturationTime);
   }
}

public class FireEffect implements Mortal {  //How were interfaces sposed to be used??
   private Player myPlayer;
   //private FireParticleSystem myFireVisual    particle systemswould have
                                                //to havae angle limit param

   public FireEffect(Player player) {
      myPlayer = player;
      //myFireVisual
   }

   public void update() {
      //myFireVisual.draw();
   }
   public void draw(Graphics pen) {
      //myFireVisual.draw(pen);
   }
}
