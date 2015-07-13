import java.awt.Color;

public class Ball extends Circle {
   public int LEFT = -1;
   public int RIGHT = 1;
   public Color myColor = Color.gray;
   public Color myOutline = Color.black;

   public Ball (Player p) {
      super(p.getX(), p.getY() - p.getBallHeight(), 15);
   }

   public void update() {
      rotate(5);
   }
   public void rotate(double angle) {
      angle = Math.toRadians(angle);
      while(Math.abs(angle)>2*Math.PI){
         angle -= Math.abs(angle)/angle * 2*Math.PI;
      }
      double tempX = getX();
      setX(getX() * Math.cos(angle) - getY() * Math.sin(angle));
      setY(getY() * Math.cos(angle) + tempX * Math.sin(angle));
   }
   public void setColor(Color color) {
      myColor = color;
   }
   public void setOutline(Color outline) {
      myOutline = outline;
   }
}