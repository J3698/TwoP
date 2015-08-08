package twop.effect;

import twop.util.Vector2;
import twop.util.StringDraw;
import twop.Player;

import java.awt.Graphics;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

public class HealthEffect extends Effect {
   private static Font healthPlaneFont = StringDraw.healthPlaneFont();
   private Vector2 myPosition;
   private Vector2 myVelocity;

   public HealthEffect(Player player) {
      super(player, 100);
      player.applyHealing(3);
      myVelocity = new Vector2(0, -4);
      myPosition = player.getCenter().copy();
   }

   public void update() {
      myPosition.addVector(myVelocity);
      loseLife(5);
   }
   public void draw(Graphics pen) {
      pen.setFont(healthPlaneFont);
      int xPos = (int) myPosition.getX();
      int yPos = (int) myPosition.getY();
      pen.setColor(new Color(255, 255, 255, 2 * getLife()));
      pen.drawString("+5", xPos, yPos);
   }
}