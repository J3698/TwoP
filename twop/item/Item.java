package twop.item;

import twop.Player;

import java.awt.Graphics;

public interface Item {
   void draw(Graphics pen);
   void update(Player firstPlayer, Player secondPlayer);
}