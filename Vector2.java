public class Vector2{                        //Two dimensional vector
   private double x;                         //CVector elements
   private double y;

   public Vector2(double _x, double _y){     //Constructor
      x = _x;
      y = _y;
   }
   public void setX(double _x){              //Setters and getters
      x = _x;
   }
   public double getX(){
      return x;
   }
   public void setY(double _y){
      y = _y;
   }
   public double getY(){
      return y;
   }
   public void addX(double scalar){
      x += scalar;
   }
   public void addY(double scalar){
      y += scalar;
   }
   public void subtractX(double scalar){
      x -= scalar;
   }
   public void subtractY(double scalar){
      y -= scalar;
   }
   public void multiplyX(double scalar){
      x *= scalar;
   }
   public void multiplyY(double scalar){
      y *= scalar;      
   }
   public void divideX(double scalar){
      x /= scalar;      
   }
   public void divideY(double scalar){
      y /= scalar;
   }
   public void addVector(Vector2 v){         //Basic vector arithmetic
      x += v.getX();
      y += v.getY();
   }
   public void subtractVector(Vector2 v){
      x += v.getX();
      y += v.getY();
   }
   public void multiply(double scalar){
      x = x * scalar;
      y = y * scalar;
   }
   public void divide(double scalar){
      if (scalar == 0){
         System.out.println("Vector divided by zero");
         return;
      }
      x = x / scalar;
      y = y / scalar;
   }
   public double getMagnitude(){                //Return the magnitude
      return Math.pow(x*x + y*y, 0.5);
   }
   public void normalizeMagnitude(){         //Manipulate the magnitude
      double mag = getMagnitude();
      if (mag != 0){
         x = x / mag;
         y = y / mag;
      }
   }
   public void setMagnitude(double scalar){
      normalizeMagnitude();
      multiply(scalar);
   }
   public void limitMagnitude(double scalar){
      if(getMagnitude() > scalar)
         setMagnitude(scalar);
   }
   public double asRadians(){                //Return vector heading
      if(x == 0)
         return Math.PI / 2;
      return Math.atan2(y, x);
   }
   public double asDegrees(){
      return Math.toDegrees(asRadians());
   }
   public void rotateRadians(double angle){  //Rotate the vector
      while(Math.abs(angle)>2*Math.PI){
         angle -= Math.abs(angle)/angle * 2*Math.PI;
      }
      double tempX = x;
      x = x * Math.cos(angle) - y * Math.sin(angle);
      y = y * Math.cos(angle) + tempX * Math.sin(angle);
   }
   public void rotateDegrees(double angle){
      rotateRadians(Math.toRadians(angle % 360));
   }

   public String toString(){
      return "("+x+", "+y+")";
   }
}
