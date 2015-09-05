package twop.effect;

import java.awt.Graphics;

import twop.Player;
import twop.particlesys.ParticleSystem;
import twop.sound.Sound;

public class FireEffect extends Effect {
   private static int maxIntensity = 20;
   private static boolean disabled = false;
   private static double damageCurve =  1.3;
   private ParticleSystem myVisualEffect;
   private Sound mySound;

   public FireEffect(Player player) {
      super(player, 200);
      myVisualEffect = new ParticleSystem("fire", getPlayer().getCenter().copy());
      mySound = new Sound("crackling", true);
      if (! disabled)
         mySound.play();
   }

   @Override
   public void update() {
      getPlayer().applyDamage(0.01 * Math.pow(getIntensity(), damageCurve));
      myVisualEffect.setSourcePosition(getPlayer().getCenter().copy());
      myVisualEffect.update();
      loseLife(1);
   }
   @Override
   public void draw(Graphics pen) {
      myVisualEffect.draw(pen);
   }

   @Override
   public void increaseIntensity() {
      if (getIntensity() < maxIntensity) {
         super.increaseIntensity();
         myVisualEffect.setEmissionRate(getIntensity());
      }
   }
   @Override
   public void onDeath() {
      mySound.stop();
   }

   public static void disable() {
      disabled = true;
   }

   public static void enable() {
      disabled = false;
   }
}