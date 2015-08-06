package twop.effect;

import twop.Player;
import twop.ParticleSystem;

import java.awt.Graphics;

public class PoisonEffect extends Effect {
   private ParticleSystem myPoisonVisual;

   public PoisonEffect(Player player) {
      super(player, 1000);
      myPoisonVisual = new ParticleSystem(player.getCenter(), "poison");
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