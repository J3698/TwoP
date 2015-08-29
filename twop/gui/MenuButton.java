package twop.gui;

import twop.util.Vector2;
import twop.util.StringDraw;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.util.Random;

public class MenuButton extends Button {
   public MenuButton(ActionListener listener, String text, Vector2 position, Vector2 size, int gameWidth, int gameHeight) {
      super(listener, text, Color.black, Color.black, StringDraw.menuFont(), position, size, gameWidth, gameHeight);
      Random r = new Random();
      int base = 100;
      int rand = 255 - base;
      setBodyColor(new Color(base + r.nextInt(rand), base + r.nextInt(rand), base + r.nextInt(rand)));
      setTextColor(new Color(100, 100, 100));
   }
}