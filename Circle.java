import java.awt.Color;
import java.awt.Graphics;

public class Circle {
   private double myX;
   private double myY;
   private double myRadius;
   private Color myColor = Color.black;
   private Color myOutline = Color.black;

   public Circle(double x, double y, double radius) {
      myX = x;
      myY = y;
      myRadius = radius;
   }

   public boolean isColliding(Circle c) {
      double aSquared = Math.pow(myX - c.getX(), 2);
      double bSquared = Math.pow(myY - c.getY(), 2);
      double distance = Math.pow(aSquared + bSquared, 0.5);
      return (myRadius + c.getRadius() >= distance);
   }
   public void draw(Graphics pen) {
      int centerX = (int) (myX - myRadius);
      int centerY = (int) (myY - myRadius);
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
   public double getX() {
      return myX;
   }
   public void setX(double x) {
      myX = x;
   }
   public double getY() {
      return myY;
   }
   public void setY(double y) {
      myY = y;
   }
}