import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

public class HealthEffect extends Effect {
   private static Font effectFont = new Font("Sans", 0, 18);
   private Vector2 myPosition;
   private Vector2 myVelocity;

   public HealthEffect(Player player) {
      super(player, 30 + new Random().nextInt(70));
      player.applyHealing(3);
      myVelocity = new Vector2(0, -4);
      myPosition = player.getCenter().copy();
   }

   public void update() {
      myPosition.addVector(myVelocity);
      loseLife(5);
   }
   public void draw(Graphics pen) {
      pen.setFont(effectFont);
      int xPos = (int) myPosition.getX();
      int yPos = (int) myPosition.getY();
      System.out.println(2 * getLife());
      pen.setColor(new Color(255, 255, 255, 2 * getLife()));
      pen.drawString("+3", xPos, yPos);
   }
}