package twop.gui;

import java.awt.Graphics;

import twop.util.Vector2;

public interface Button {
   void draw(Graphics pen);
   void drawHovered(Graphics pen);

   void doAction();
   boolean collidesPoint(double x, double y);

   Vector2 getPosition();
   Vector2 getSize();

   boolean getMouseHovering();
   void setMouseHovering(boolean isHovering);
}