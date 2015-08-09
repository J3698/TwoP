package twop.effect;

import twop.Player;
import twop.particlesys.ParticleSystem;

import java.awt.Graphics;

public class PoisonEffect extends Effect {
   private static int maxIntensity = 5;
   private static double myDamage = 0.001;
   private ParticleSystem myVisualEffect;

   public PoisonEffect(Player player) {
      super(player, 1000);
      myVisualEffect = new ParticleSystem("poison", player.getCenter().copy());
   }

   public void update() {
      getPlayer().applyDamage(getIntensity() * myDamage);
      myVisualEffect.setSourcePosition(getPlayer().getCenter().copy());
      myVisualEffect.update();
      loseLife(1);
   }
   public void draw(Graphics pen) {
      myVisualEffect.draw(pen);
   }

   public void increaseIntensity() {
      if (getIntensity() < maxIntensity) {
         super.increaseIntensity();
         myVisualEffect.setEmissionRate(getIntensity());
         setLife(getLife() + 200);
      }
   }
}