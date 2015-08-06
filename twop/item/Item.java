package twop.item;

import twop.Player;
import twop.util.Vector2;
import twop.Circle;

import java.awt.Graphics;

public interface Item {
   void draw(Graphics pen);
   void update(Player firstPlayer, Player secondPlayer);
   boolean collidesCircle(Circle circle);
   Vector2 getPosition();
   void setPosition(Vector2 position);
   boolean isDead();
   double getWidth();
   double getHeight();
}