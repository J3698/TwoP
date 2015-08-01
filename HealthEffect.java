import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class HealthEffect extends Effect {
   private static Font effectFont = new Font("Sans", 0, 18);
   private Vector2 myPosition;
   private Vector2 myVelocity;

   public HealthEffect(Player player) {
      super(player, 100);
      player.applyHealing(1);
      myVelocity = new Vector2(0, -1);
      myPosition = player.getCenter().copy();
   }

   public void update() {
      myPosition.addVector(myVelocity);
      loseLife(5);
   }
   public void draw(Graphics pen) {
      pen.setColor(Color.white);
      pen.setFont(effectFont);
      int xPos = (int) myPosition.getX();
      int yPos = (int) myPosition.getY();
      pen.drawString("+1", xPos, yPos);
   }
}