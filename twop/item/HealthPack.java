package twop.item;

import twop.Player;
import twop.Rect;
import twop.effect.HealthPackEffect;
import twop.util.Vector2;
import twop.sound.Sound;

import java.awt.Graphics;
import java.awt.Color;

public class HealthPack extends Rect implements Item {
   private static int availableID = 0;
   private static double myHealing = 65;
   private boolean myIsDead = false;


   public HealthPack(Vector2 position) {
      super(position, 30, 30);
      setColor(Color.red);
      setOutline(Color.pink);
   }

   public void update(Player firstPlayer, Player secondPlayer) {
      if (collidesCircle(firstPlayer)) {
         onCollision(firstPlayer);
      } else if (collidesCircle(secondPlayer)) {
         onCollision(secondPlayer);
      }
   }

   public void onCollision(Player player) {
      myIsDead = true;
      player.applyHealing(myHealing);
      Vector2 effectPosition = getPosition().copy();
      effectPosition.addVector(new Vector2(getWidth(), getHeight()));
      effectPosition.subtractVector(new Vector2(getWidth() / 2, getHeight() / 2));
      player.addEffect("" + availableID, new HealthPackEffect(player, effectPosition));
      new Sound("healthpackage").play();
      availableID++;
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