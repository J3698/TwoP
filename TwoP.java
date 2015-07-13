import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;
   public static Player firstPlayer = new Player(60, 350, 20);
   public static Player secondPlayer = new Player(gameWidth - 60, 350, 20);
   public static double gravity = 0.5;
   public static String gameMode = "play";
   public static double pauseOpacity = 0;

   public static void main(String[] args) {
      JFrame frame = new JFrame("TwoP");
      frame.setSize(gameWidth, gameHeight);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel());
      frame.setVisible(true);
   }

   public static class GamePanel extends JPanel {
      BufferedImage myImage;
      Graphics myBuffer;
      Timer timer;
      Font bigA;

      public GamePanel() {
         setFocusable(true);
         firstPlayer.getControls().setUpKey(KeyEvent.VK_W);
         firstPlayer.getControls().setLeftKey(KeyEvent.VK_A);
         firstPlayer.getControls().setDownKey(KeyEvent.VK_S);
         firstPlayer.getControls().setRightKey(KeyEvent.VK_D);
         firstPlayer.getControls().setFirstActionKey(KeyEvent.VK_1);
         firstPlayer.getControls().setSecondActionKey(KeyEvent.VK_2);
         myImage = new BufferedImage(gameWidth, gameHeight,
                              BufferedImage.TYPE_INT_ARGB);
         myBuffer = myImage.getGraphics();
         bigA = new Font("Ariel", 1, 90);
         myBuffer.setFont(bigA);
         addKeyListener(new KeyListener());
         timer = new Timer(30, new UpdateListener());
         timer.start();
      }

      public void update() {
         if (gameMode == "play") {
            firstPlayer.update();
            secondPlayer.update();
         } else if (gameMode == "pause") {
            if (pauseOpacity < 100) {
               pauseOpacity += 20;
            }
         }
      }
      public void paintComponent(Graphics pen) {
         pen.clearRect(0, 0, getWidth(), getHeight());
         myBuffer.clearRect(0, 0, gameWidth, gameHeight);

         drawGame(myBuffer);
         if (gameMode == "play") {
            
            drawGame(myBuffer);
         }
         else if (gameMode == "pause") {
            drawGame(myBuffer);
            drawPaused(myBuffer);
         }
         pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
      }
      public void drawGame(Graphics pen) {
         drawBackground(myBuffer);
         drawPlayers(myBuffer);
         drawGUI(myBuffer);
      }
      public void drawPaused(Graphics pen) {
         myBuffer.setColor(new Color(0, 0, 0, (int) pauseOpacity));
         myBuffer.fillRect(0, 0, gameWidth, gameHeight);
      }
      
      public void drawBackground(Graphics pen) {
         pen.setColor(firstPlayer.getColor());
         pen.fillRect(0, 0, gameWidth / 2, gameHeight);
         pen.setColor(secondPlayer.getColor());
         pen.fillRect(gameWidth / 2, 0, gameWidth / 2, gameHeight);
         pen.setColor(new Color(0, 0, 0, 50));
         pen.drawString("P to Pause", 100, 250);
      }
      public void drawPlayers(Graphics pen) {
         firstPlayer.drawSelfAndBall(pen);
         secondPlayer.drawSelfAndBall(pen);
      }
      public void drawGUI(Graphics pen) {
         pen.setColor(new Color(0, 0, 0));
         pen.fillRect(gameWidth / 2 - 15, 5, 30, 40);
         pen.setColor(firstPlayer.getColor());
         pen.fillRect(gameWidth / 2 + 15, 10, 100, 30);
         pen.setColor(secondPlayer.getColor());
         pen.fillRect(gameWidth / 2 - 15 - 100, 10, 100, 30);
      }
      public class UpdateListener implements ActionListener {
         public void actionPerformed(ActionEvent event) {
            update();
            repaint();
         }
      }
      public class KeyListener extends KeyAdapter {
         public void keyPressed(KeyEvent event) {
            firstPlayer.getControls().keyDown(event);
            secondPlayer.getControls().keyDown(event);
            if (event.getKeyCode() == KeyEvent.VK_P)
               gameMode = "pause";
         }
         public void keyReleased(KeyEvent event) {
            firstPlayer.getControls().keyUp(event);
            secondPlayer.getControls().keyUp(event);
         }
      }
   }

   public static abstract class Circle {
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

   public static class Player extends Circle {
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

   public static class Ball extends Circle {
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


   public static class Controls {
      private Player myPlayer;
      private int myUpKey = KeyEvent.VK_UP;
      private int myDownKey = KeyEvent.VK_DOWN;
      private int myLeftKey = KeyEvent.VK_LEFT;
      private int myRightKey = KeyEvent.VK_RIGHT;
      private int myFirstActionKey = KeyEvent.VK_PERIOD;
      private int mySecondActionKey = KeyEvent.VK_SLASH;

      public Controls(Player player) {
         myPlayer = player;
      }

      public void keyDown(KeyEvent event) {
         if (event.getKeyCode() == myUpKey)
            myPlayer.up();
         else if (event.getKeyCode() == myDownKey)
            myPlayer.down();
         else if (event.getKeyCode() == myLeftKey)
            myPlayer.left();
         else if (event.getKeyCode() == myRightKey)
            myPlayer.right();
         else if (event.getKeyCode() == myFirstActionKey)
            myPlayer.firstAction();
         else if (event.getKeyCode() == mySecondActionKey)
            myPlayer.secondAction();
      }
      public void keyUp(KeyEvent event) {
         if (event.getKeyCode() == myUpKey)
            myPlayer.releaseUp();
         else if (event.getKeyCode() == myDownKey)
            myPlayer.releaseDown();
         else if (event.getKeyCode() == myLeftKey)
            myPlayer.releaseLeft();
         else if (event.getKeyCode() == myRightKey)
            myPlayer.releaseRight();
      }

      public void setUpKey(int upKey) {
         myUpKey = upKey;
      }
      public void setDownKey(int downKey) {
         myDownKey = downKey;
      }
      public void setLeftKey(int leftKey) {
         myLeftKey = leftKey;
      }
      public void setRightKey(int rightKey) {
         myRightKey = rightKey;
      }
      public void setFirstActionKey(int firstActionKey) {
         myFirstActionKey = firstActionKey;
      }
      public void setSecondActionKey(int secondActionKey) {
         mySecondActionKey = secondActionKey;
      }
      public int getUpKey() {
         return myUpKey;
      }
      public int getDownKey() {
         return myDownKey;
      }
      public int getLeftKey() {
         return myLeftKey;
      }
      public int getRightKey() {
         return myRightKey;
      }
      public int getFirstActionKey() {
         return myFirstActionKey;
      }
      public int getSecondActionKey() {
         return mySecondActionKey;
      }
   }
}