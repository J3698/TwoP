import java.awt.Graphics;

public class FireEffect extends Effect {
   //private FireParticleSystem myFireVisual

   public FireEffect(Player player) {
      super(player, 300);
      //myFireVisual = new ___;
   }

   public void update() {
      getPlayer().applyDamage(0.001);
      //myFireVisual.update();
   }
   public void draw(Graphics pen) {
      //myFireVisual.draw(pen);
   }
}