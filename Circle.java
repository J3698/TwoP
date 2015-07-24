import java.awt.Color;
import java.awt.Graphics;

public class Circle {
   private Vector2 myCenter;
   private double myRadius;
   private Color myColor = Color.black;
   private Color myOutline = Color.black;

   public Circle(Vector2 center, double radius) {
      myCenter = center;
      myRadius = radius;
   }

   public boolean collidesCircle(Circle c) {
      double aDiff = myCenter.getX() - c.getCenter().getX();
      double bDiff = myCenter.getY() - c.getCenter().getY();
      double aDiffSquared = Math.pow(aDiff, 2);
      double bDiffSquared = Math.pow(bDiff, 2);
      double distance = Math.pow(aDiffSquared + bDiffSquared, 0.5);
      return (myRadius + c.getRadius() >= distance);
   }
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
   public double getRadius() {
      return myRadius;
   }
   public void setRadius(double radius) {
      myRadius = radius;
   }
   public Vector2 getCenter() {
      return myCenter;
   }
   public void setCenter(Vector2 center) {
      myCenter = center;
   }
}