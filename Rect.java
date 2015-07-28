import java.awt.Graphics;
import java.awt.Color;

public class Rect {
   private Vector2 myPosition;
   private double myWidth;
   private double myHeight;
   private Color myColor = Color.white;
   private Color myOutline = Color.white;

   public Rect(Vector2 position, double width, double height) {
      myPosition = position;
      myWidth = width;
      myHeight = height;
   }

   public boolean collidesRect(Rect r) {
      boolean collides = true;
      if (myPosition.getX() > r.getPosition().getX() + r.getWidth()) {
         collides = false;
      }
      else if (myPosition.getX() + myWidth < r.getPosition().getX()) {
         collides = false;
      }
      else if (myPosition.getY() > r.getPosition().getY() + r.getWidth()) {
         collides = false;
      }
      else if (myPosition.getY() + myHeight < r.getPosition().getY()) {
         collides = false;
      }
      return collides;
   }
   public boolean collidesCircle(Circle c) {
      boolean collides = false;
      boolean aboveEdge = c.getCenter().getX() > myPosition.getX();
      boolean belowEdge = c.getCenter().getX() < myPosition.getX() + myWidth;
      boolean rightOfEdge = c.getCenter().getY() > myPosition.getY();
      boolean leftOfEdge = c.getCenter().getY() < myPosition.getY() + myHeight;
      if ((aboveEdge && belowEdge) || (rightOfEdge && leftOfEdge)) {
         if (myPosition.getX() > c.getCenter().getX() + c.getRadius()) {
         } else if (myPosition.getX() + myWidth < c.getCenter().getX() - c.getRadius()) {
         } else if (myPosition.getY() > c.getCenter().getY() + c.getRadius()) {
         } else if (myPosition.getY() + myHeight < c.getCenter().getY() - c.getRadius()) {
         } else {
            collides = true;
         }
      } else {
         Vector2 firstCorner = new Vector2(myPosition.getX(), myPosition.getY() + myHeight);
         Vector2 secondCorner = new Vector2(myPosition.getX() + myWidth, myPosition.getY());
         Vector2 thirdCorner = new Vector2(myPosition.getX(), myPosition.getY() + myHeight);
         Vector2 fourthCorner = new Vector2(myPosition.getX() + myWidth, myPosition.getY() + myHeight);
         Vector2[] corners = {firstCorner, secondCorner, thirdCorner, fourthCorner};
         for (Vector2 v: corners) {
            v.subtractVector(c.getCenter());
            if (v.getMagnitude() <= c.getRadius()) {
               collides = true;
               break;

            }
         }
      }
      return collides;
   }
   public void draw(Graphics pen) {
      pen.setColor(myColor);
      pen.fillRect((int) myPosition.getX(), (int)myPosition.getY(),
                                       (int)myWidth, (int)myHeight);
      pen.setColor(myOutline);
      pen.drawRect((int)myPosition.getX(), (int)myPosition.getY(),
                                      (int)myWidth, (int)myHeight);
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
   public void setAllColor(Color color) {
      setColor(color);
      setOutline(color);
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
