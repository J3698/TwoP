package twop.gui;

import twop.util.Vector2;

import java.awt.event.ActionListener;
import java.awt.Graphics;

public abstract class Button {
   private ActionListener myListener;
   private int myGameWidth;
   private int myGameHeight;
   private Vector2 myPosition;
   private Vector2 mySize;

   public Button(ActionListener listener, Vector2 position, Vector2 size,
                                            int gameWidth, int gameHeight) {
      myListener = listener;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myPosition = position;
      mySize = size;
   }

   public abstract void draw(Graphics pen);

   public void doAction() {
      myListener.actionPerformed(null);
   }

   public boolean collidesPoint(int x, int y) {
      boolean inXBounds = (x > myPosition.getX() && x < myPosition.getX() + mySize.getX());
      boolean inYBounds = (y > myPosition.getY() && y < myPosition.getY() + mySize.getY());
      return (inXBounds && inYBounds);
   }

   public Vector2 getPosition() { return myPosition; }
   public Vector2 getSize() { return mySize; }
}