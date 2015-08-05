import java.awt.Graphics;

public class PoisonEffect extends Effect {
   private ParticleSystem myPoisonVisual;

   public PoisonEffect(Player player) {
      super(player, 300);
      myPoisonVisual = new ParticleSystem(player.getCenter(), "poison");
      myPoisonVisual.setEmissionSkip(5);
   }

   public void update() {
      getPlayer().applyDamage(0.001);
      myPoisonVisual.setSourcePosition(getPlayer().getCenter().copy());
      myPoisonVisual.update();
      loseLife(1);
   }
   public void draw(Graphics pen) {
      myPoisonVisual.draw(pen);
   }
}