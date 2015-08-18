package twop.gui;

import twop.util.Vector2;
import twop.util.StringDraw;

import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.Random;

public class MenuButton extends Button {
   public MenuButton(ActionListener listener, String text, Vector2 position, Vector2 size, int gameWidth, int gameHeight) {
      super(listener, text, Color.black, Color.black, StringDraw.menuFont(), position, size, gameWidth, gameHeight);
      Random r = new Random();
      setBodyColor(new Color(190 + r.nextInt(50), 190 + r.nextInt(50), 190 + r.nextInt(50)));
      setTextColor(new Color(100, 100, 100));
   }
}