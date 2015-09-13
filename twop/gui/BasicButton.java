package twop.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import twop.util.StringDraw;
import twop.util.Vector2;

public abstract class BasicButton implements Button {
   private ActionListener myListener;
   private boolean myMouseHovering;
   private Vector2 myPosition;
   private Vector2 mySize;
   private String myText;
   private Color myBodyColor;
   private Color myTextColor;
   private Font myFont;

   public BasicButton(ActionListener listener, String text, Color bodyColor, Color textColor, Font font,
         Vector2 position, Vector2 size) {
      myListener = listener;
      myText = text;
      myFont = font;
      myBodyColor = bodyColor;
      myTextColor = textColor;
      myPosition = position;
      mySize = size;
      myMouseHovering = false;
   }

   @Override
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

   @Override
   public void drawHovered(Graphics pen) {
      //shrink
      myPosition.addVector(new Vector2(1, 1));
      mySize.subtractVector(new Vector2(2, 2));
      //draw normal
      draw(pen);
      //draw veil
      pen.setColor(new Color(0, 0, 0, 100));
      int x = (int) myPosition.getX();
      int y = (int) myPosition.getY();
      int width = (int) mySize.getX();
      int height = (int) mySize.getY();
      pen.fillRect(x, y, width, height);
      //undo shrink
      myPosition.subtractVector(new Vector2(1, 1));
      mySize.addVector(new Vector2(2, 2));
   }

   @Override
   public void doAction() {
      myListener.actionPerformed(null);
   }

   @Override
   public boolean collidesPoint(double x, double y) {
      boolean inXBounds = (x >= myPosition.getX() && x <= myPosition.getX() + mySize.getX());
      boolean inYBounds = (y >= myPosition.getY() && y <= myPosition.getY() + mySize.getY());
      return (inXBounds && inYBounds);
   }

   @Override
   public Vector2 getPosition() { return myPosition; }
   @Override
   public Vector2 getSize() { return mySize; }
   public String getText() { return myText; }
   public void setBodyColor(Color bodyColor) { myBodyColor = bodyColor; }
   public void setTextColor(Color textColor) { myTextColor = textColor; }
   public Color getTextColor() { return myTextColor; }
   @Override
   public boolean getMouseHovering() { return myMouseHovering; }
   @Override
   public void setMouseHovering(boolean isHovering) { myMouseHovering = isHovering; }
   public void setFont(Font font) { myFont = font; }
}