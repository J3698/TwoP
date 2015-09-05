package twop.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import twop.util.StringDraw;
import twop.util.Vector2;

public abstract class Button {
   private ActionListener myListener;
   private boolean myMouseHovering;
   private int myGameWidth;
   private int myGameHeight;
   private Vector2 myPosition;
   private Vector2 mySize;
   private String myText;
   private Color myBodyColor;
   private Color myTextColor;
   private Font myFont;

   public Button(ActionListener listener, String text, Color bodyColor, Color textColor, Font font,
         Vector2 position, Vector2 size, int gameWidth, int gameHeight) {
      myListener = listener;
      myText = text;
      myFont = font;
      myBodyColor = bodyColor;
      myTextColor = textColor;
      myPosition = position;
      mySize = size;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseHovering = false;
   }

   public void draw(Graphics pen) {
      pen.setColor(myBodyColor);
      int x = (int) myPosition.getX();
      int y = (int) myPosition.getY();
      int width = (int) mySize.getX();
      int height = (int) mySize.getY();
      pen.fillRect(x, y, width, height);
      pen.setColor(myTextColor);
      pen.setFont(myFont);
      StringDraw.drawStringCenter(pen, myText, x + width / 2, y + height / 2);
   }

   public void drawHovered(Graphics pen) {
      draw(pen);
      pen.setColor(new Color(0, 0, 0, 100));
      int x = (int) myPosition.getX();
      int y = (int) myPosition.getY();
      int width = (int) mySize.getX();
      int height = (int) mySize.getY();
      pen.fillRect(x, y, width, height);
   }

   public void doAction() {
      myListener.actionPerformed(null);
   }

   public boolean collidesPoint(double x, double y) {
      boolean inXBounds = (x >= myPosition.getX() && x <= myPosition.getX() + mySize.getX());
      boolean inYBounds = (y >= myPosition.getY() && y <= myPosition.getY() + mySize.getY());
      return (inXBounds && inYBounds);
   }

   public Vector2 getPosition() { return myPosition; }
   public Vector2 getSize() { return mySize; }
   public String getText() { return myText; }
   public void setBodyColor(Color bodyColor) { myBodyColor = bodyColor; }
   public void setTextColor(Color textColor) { myTextColor = textColor; }
   public Color getTextColor() { return myTextColor; }
   public boolean getMouseHovering() { return myMouseHovering; }
   public void setMouseHovering(boolean isHovering) { myMouseHovering = isHovering; }
}