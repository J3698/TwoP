import java.awt.Graphics;

public class FireEffect extends Effect {
   private ParticleSystem myVisualEffect;

   public FireEffect(Player player) {
      super(player, 300);
      myVisualEffect = new ParticleSystem(getPlayer().getCenter(), "fire");
   }

   public void update() {
      getPlayer().applyDamage(0.001);
      myVisualEffect.setSourcePosition(getPlayer().getCenter().copy());
      myVisualEffect.update();
   }
   public void draw(Graphics pen) {
      myVisualEffect.draw(pen);
   }
}