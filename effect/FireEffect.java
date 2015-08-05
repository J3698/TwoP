package twop.effect;

import twop.ParticleSystem;
import twop.Player;

import java.awt.Graphics;

public class FireEffect extends Effect {
   private ParticleSystem myVisualEffect;
   private int myIntensity;

   public FireEffect(Player player) {
      super(player, 350);
      myVisualEffect = new ParticleSystem(getPlayer().getCenter(), "fire");
      myIntensity = 1;
   }

   public void update() {
      getPlayer().applyDamage(0.01);
      myVisualEffect.setSourcePosition(getPlayer().getCenter().copy());
      myVisualEffect.update();
      loseLife(1);
   }
   public void draw(Graphics pen) {
      myVisualEffect.draw(pen);
   }

   public void increaseIntensity() {
      myIntensity++;
   }
}