import java.awt.Graphics;

public class PoisonEffect extends Effect {
   //private PoisonParticleSystem myPoisonVisual

   public PoisonEffect(Player player) {
      super(player, 300);
      //myPoisonVisual = new ___;
   }

   public void update() {
      getPlayer().applyDamage(0.001);
      //myPoisonVisual.update();
   }
   public void draw(Graphics pen) {
      //myPoisonVisual.draw(pen);
   }
}