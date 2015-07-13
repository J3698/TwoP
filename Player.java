import java.awt.Graphics;

public class Player extends Circle {
   private Controls myControls = new Controls(this);
   private double myVelocityX;
   private double myVelocityY;
   private double mySpeed = 3;
   private Ball myBall;
   private double myBallHeight = 130;
   private int myJumps = 2;
   private int maxJumps = 2;
   private int myJumpHeight = 10;

   public Player(double x, double y, double radius) {
      super(x, y, radius);
      setRandomColor();
      myVelocityY = 10;
   }

   public void update() {
      updateVelocity();
      updatePosition();
      keepInBounds();
      updateJumpAbility();
      if (myBall != null)
         myBall.update();
   }
   public void updateVelocity() {
      myVelocityY -= gravity;
   }
   public void updatePosition() {
      setY(getY() - myVelocityY);
      setX(getX() + myVelocityX);
   }
   public void keepInBounds() {
      if (getY() + getRadius() > gameHeight) {
         myVelocityY = 0;
         setY(gameHeight - getRadius());
      }
      if (getY() - getRadius() < 0) {
         myVelocityY = 0;
         setY(getRadius());
      }
      if (getX() + getRadius() > gameWidth) {
         myVelocityX = 0;
         setX(gameWidth - getRadius());
      }
      if (getX() - getRadius() < 0) {
         myVelocityX = 0;
         setX(getRadius());
      }
   }
   public void updateJumpAbility() {
      if (getY() + getRadius() == gameHeight)
         myJumps = 0;
   }
   public void drawSelfAndBall(Graphics pen) {
      draw(pen);
      if (myBall != null)
         myBall.draw(pen);
   }
   public void up() {
      if (myJumps < maxJumps) {
         myVelocityY += myJumpHeight;
         myJumps++;
      }
   }
   public void releaseUp() {
   }
   public void down() {
   }
   public void releaseDown() {
   }
   public void left() {
      myVelocityX -= mySpeed;
   }
   public void releaseLeft() {
   }
   public void right() {
      myVelocityX += mySpeed;
   }
   public void releaseRight() {
   }
   public void firstAction() {
      myBall = new Ball(this);
   }
   public void secondAction() {
   }
   public Controls getControls() {
      return myControls;
   }
   public void setRandomColor() {
      Random rand = new Random();
      int r = 75 + rand.nextInt(180);
      int g = 75 + rand.nextInt(180);
      int b = 75 + rand.nextInt(180);
      setColor(new Color(r, g, b));
   }
   public double getBallHeight() {
      return myBallHeight; 
   }
   public Ball getBall() {
      return myBall;
   }
}