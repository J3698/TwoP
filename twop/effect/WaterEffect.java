package twop.effect;

import twop.Player;

import java.awt.Graphics;

public class WaterEffect extends Effect {
   //private WaterParticleSystem myWaterVisual

   public WaterEffect(Player player) {
      super(player, 300);
      //myWaterVisual = new ___;
   }

   public void update() {
      getPlayer().applyDamage(0.001);
      //myWaterVisual.update();
   }
   public void draw(Graphics pen) {
      //myWaterVisual.draw(pen);
   }
}