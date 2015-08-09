package twop.item;

import twop.Player;
import twop.Rect;
import twop.util.Vector2;
import twop.sound.Sound;

import java.awt.Graphics;
import java.awt.Color;

public class HealthPack extends Rect implements Item {
   private static double myHealing = 65;
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
         new Sound("healthpackage").play();
      }
      if (collidesCircle(secondPlayer)) {
         myIsDead = true;
         secondPlayer.applyHealing(myHealing);
         new Sound("healthpackage").play();
      }
   }

   public void draw(Graphics pen) {
      int x = (int) getPosition().getX();
      int y = (int) getPosition().getY();
      int w = (int) getWidth();
      int h = (int) getHeight();
      pen.setColor(getColor());
      pen.fillRect(x, y, w, h);
      pen.setColor(getOutline());
      pen.fillRect(x + 3 * w / 7, y, w / 7, h);
      pen.fillRect(x, y + 3 * h / 7, w, h / 7);
   }

   public boolean isDead() {
      return myIsDead;
   }
}