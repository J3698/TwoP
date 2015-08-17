package twop.gui;

import twop.util.Vector2;
import twop.util.StringDraw;

import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.util.Random;

public class MenuButton extends Button {
   private static Font menuFont = StringDraw.menuFont();
   private String myText;
   private Color myColor;
   public MenuButton(ActionListener listener, String text, Vector2 position, Vector2 size, int gameWidth, int gameHeight) {
      super(listener, position, size, gameWidth, gameHeight);
      myText = text;
      Random r = new Random();
      myColor = new Color(150 + r.nextInt(50), 150 + r.nextInt(50), 150 + r.nextInt(50));
   }

   public void draw(Graphics pen) {
      pen.setColor(myColor);
      int x = (int) getPosition().getX();
      int y = (int) getPosition().getY();
      int width = (int) getSize().getX();
      int height = (int) getSize().getY();
      pen.fillRect(x, y, width, height);
      pen.setColor(new Color(100, 100, 100, 100));
      pen.setFont(menuFont);
      StringDraw.drawStringCenter(pen, myText, x + width / 2, y + height / 2);
   }
}