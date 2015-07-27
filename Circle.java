import java.awt.Color;
import java.awt.Graphics;

public class Circle {
   private Vector2 myCenter;
   private double myRadius;
   private Color myColor = Color.black;
   private Color myOutline = Color.black;

   /**
    * Constructor to set center and radius
    *
    * @param center Vector2 representing center of circle
    * @param radius double representing radius
    */
   public Circle(Vector2 center, double radius) {
      myCenter = center;
      myRadius = radius;
   }

   /**
    * Returns if two Circle instances intersect
    *
    * @oaram c other circle with which to test collision
    * @return boolean if the circles collide
    */
   public boolean collidesCircle(Circle c) {
      double aDiff = myCenter.getX() - c.getCenter().getX();
      double bDiff = myCenter.getY() - c.getCenter().getY();
      double aDiffSquared = Math.pow(aDiff, 2);
      double bDiffSquared = Math.pow(bDiff, 2);
      double distance = Math.pow(aDiffSquared + bDiffSquared, 0.5);
      return (myRadius + c.getRadius() >= distance);
   }

   /**
    * Returns if a Circle and Rect intersect
    *
    * @oaram r rectangle with which to test collision
    * @return boolean if the rectangle and circle collide
    */
   public boolean collidesRect(Rect r) {
      return false;
   }

   /**
    * Draw and outlines the circle
    *
    * @param pen the graphics object on which to draw the circle
    */
   public void draw(Graphics pen) {
      int centerX = (int) (myCenter.getX() - myRadius);
      int centerY = (int) (myCenter.getY() - myRadius);
      int diameter = (int) (myRadius * 2);
      pen.setColor(myColor);
      pen.fillOval(centerX, centerY,
                 diameter, diameter);
      pen.setColor(myOutline);
      pen.drawOval(centerX, centerY,
                 diameter, diameter);
   }

   /**
    * Returns the fill color
    *
    * @return Color fill color
    */
   public Color getColor() {
      return myColor;
   }

   /**
    * Sets the fill color
    *
    * @param color new fill color
    */
   public void setColor(Color color) {
      myColor = color;
   }

   /**
    * Returns the outline color
    *
    * @return Color
    */
   public Color getOutline() {
      return myOutline;
   }

   /**
    * Sets the outline color
    *
    * @param color new outline color
    */
   public void setOutline(Color outline) {
      myOutline = outline;
   }

   /**
    * Returns the radius
    *
    * @return double
    */
   public double getRadius() {
      return myRadius;
   }

   /**
    * Sets the radius of the circle
    *
    * @param radius
    */
   public void setRadius(double radius) {
      myRadius = radius;
   }

   /**
    * Returns the center of the circle
    *
    * @return Vector2
    */
   public Vector2 getCenter() {
      return myCenter;
   }

   /**
    * Sets the center of the circle
    *
    * @param center
    */
   public void setCenter(Vector2 center) {
      myCenter = center;
   }
}
