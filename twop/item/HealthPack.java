package twop.item;

import twop.Player;
import twop.util.Vector2;
import twop.Rect;

import java.awt.Graphics;
import java.awt.Color;

public class HealthPack extends Rect implements Item {
   private static double myHealing = 50;
   private boolean myIsDead = false;

   public HealthPack(Vector2 position) {
      super(position, 30, 30);
      setColor(Color.red);
      setOutline(Color.pink);   
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (collidesCircle(firstPlayer)) {
         myIsDead = true;
         firstPlayer.applyHealing(myHealing);
      }
      if (collidesCircle(secondPlayer)) {
         myIsDead = true;
      }
   }

   public void draw(Graphics pen) {
      int x = (int) getPosition().getX();
      int y = (int) getPosition().getY();
      int w = (int) getWidth();
      int h = (int) getHeight();
      pen.setColor(getColor());
      pen.fillRoundRect(x, y, w, h, 3, 3);
      pen.setColor(getOutline());
      pen.fillRect(x + 3 * w / 7, y, w / 7, h);
      pen.fillRect(x, y + 3 * h / 7, w, h / 7);
   }

   public boolean isDead() {
      return myIsDead;
   }
}