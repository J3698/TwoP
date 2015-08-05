package twop.item;

import java.awt.Graphics;

public interface Item {
   void draw(Graphics pen);
   void update(Player firstPlayer, Player secondPlayer);
}