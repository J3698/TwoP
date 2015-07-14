import java.awt.Color;

public class Gun {
   public static int CLOCKWISE = 1;
   public static int COUNTERCLOCKWISE = -1;
   private int mySpinDirection = CLOCKWISE;
   
   public Gun (Player p) {
      myX = x;
      myY = y;
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
   public void setSpinDirection(int spinDirection) {
      mySpinDirection = spinDirection;
   }
}