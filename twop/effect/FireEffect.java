package twop.effect;

import twop.particlesys.ParticleSystem;
import twop.Player;
import twop.sound.Sound;

import java.awt.Graphics;

public class FireEffect extends Effect {
   private static int maxIntensity = 20;
   private static double damageCurve =  1.3;
   private ParticleSystem myVisualEffect;
   private Sound mySound;

   public FireEffect(Player player) {
      super(player, 350);
      myVisualEffect = new ParticleSystem(getPlayer().getCenter(), "fire");
      mySound = new Sound("crackling");
      mySound.play();
   }

   public void update() {
      getPlayer().applyDamage(0.01 * Math.pow(getIntensity(), damageCurve));
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
      }
   }
   public void onDeath() {
      mySound.stop();
   }
}