import java.awt.Graphics;

public class HealthEffect extends Effect {

   public HealthEffect(Player player) {
      super(player, 10);
   }

   public void update() {
      getPlayer().applyHealing(0.01);
      setLife(getLife() - 1);
   }
   public void draw(Graphics pen) {
   }
}