package twop.gui;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Random;

import twop.util.StringDraw;
import twop.util.Vector2;

public class MenuButton extends BasicButton {
   public MenuButton(ActionListener listener, String text, Vector2 position, Vector2 size, int gameWidth, int gameHeight) {
      super(listener, text, Color.black, Color.black, StringDraw.menuFont(), position, size);
      Random r = new Random();
      int base = 100;
      int rand = 255 - base;
      setBodyColor(new Color(base + r.nextInt(rand), base + r.nextInt(rand), base + r.nextInt(rand)));
      setTextColor(new Color(100, 100, 100));
   }
}