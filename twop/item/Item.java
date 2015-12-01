package twop.item;

import java.awt.Graphics;

import twop.Circle;
import twop.Player;
import twop.util.Vector2;

public interface Item {
   void draw(Graphics pen);
   void update(Player firstPlayer, Player secondPlayer);
   boolean collidesCircle(Circle circle);
   Vector2 getPosition();
   void setPosition(Vector2 position);
   boolean isDead();
}