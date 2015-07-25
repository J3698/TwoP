import java.awt.Graphics;
import java.awt.Color;

public class Rect {
   private Vector2 myPosition;
   private double myWidth;
   private double myHeight;
   private Color myColor;
   private Color myOutline;

   public Rect(Vector2 position, int width, int height) {
      myPosition = position;
      myWidth = width;
      myHeight = height;
   }

   public boolean collidesRect(Rect r) {
      return true;
   }

   public boolean collidesCircle(Circle c) {
      boolean aboveEdge = c.getCenter().getX() > myPosition.getX()
      boolean belowEdge = c.getCenter().getX() < myPosition.getX() + myWidth;
      boolean rightOfEdge = c.getCenter().getY() > myPosition.getY()
      boolean leftOfEdge = c.getCenter().getY() < myPosition.getY() + myHeight;
      if ((aboveEdge && belowEdge) || (rightOfEdge && leftOfEdge)) {
         //Test collision as if circle were a rectangle
      } else {
         //Test if rect corners collide with circle
      }
   }
   public Color getColor() {
      return myColor;
   }
   public void setColor(Color color) {
      myColor = color;
   }
   public Color getOutline() {
      return myOutline;
   }
   public void setOutline(Color outline) {
      myOutline = outline;
   }
   public Vector2 getPosition() {
      return myPosition;
   }
   public void setPosition(Vector2 position) {
      myPosition = position;
   }
   public double getWidth() {
      return myWidth;
   }
   public void setWidth(double width) {
      myWidth = width;
   }
   public double getHeight() {
      return myHeight;
   }
   public void setHeight(double height) {
      myHeight = height;
   }
}