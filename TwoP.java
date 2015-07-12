import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Random;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;
   public static Player firstPlayer = new Player(60, 350, 20);
   public static Player secondPlayer = new Player(gameWidth - 60, 350, 20);
   public static double gravity;

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
         firstPlayer.update();
         secondPlayer.update();
      }
      public void paintComponent(Graphics pen) {
         pen.clearRect(0, 0, getWidth(), getHeight());
         myBuffer.clearRect(0, 0, gameWidth, gameHeight);
         drawBackground(myBuffer);
         drawPlayers(myBuffer);
         drawGUI(myBuffer);
         pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
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
         firstPlayer.draw(pen);
         secondPlayer.draw(pen);
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
      public Circle(double x, double y, double radius) {
         myX = x;
         myY = y;
         myRadius = radius;
      }
      public boolean isColliding() { return false; }
      public double getRadius() { return myRadius; }
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
      private Color myColor;
      private double myBallHeight = 80;
      public Player(double x, double y, double radius) {
         super(x, y, radius);
         setRandomColor();
         myVelocityY = 10;
      }
      public void update() {
         myVelocityY -= .5;
         setY(getY() - myVelocityY);
         setX(getX() + myVelocityX);
         if (getY() + getRadius() > gameHeight) {
            myVelocityY = 0;
            setY(gameHeight - getRadius());
         }
      }
      public void draw(Graphics pen) {
         int centerX = (int) (getX() - getRadius());
         int centerY = (int) (getY() - getRadius());
         int diameter = (int) (getRadius() * 2);
         pen.setColor(myColor);
         pen.fillOval(centerX, centerY,
                    diameter, diameter);
         pen.setColor(Color.black);
         pen.drawOval(centerX, centerY,
                    diameter, diameter);
      }

      public void up() {
         myVelocityY += 10;
      }
      public void releaseUp() {
         
      }
      public void down() {
         
      }
      public void releaseDown() {
      
      }
      public void left() {
         myVelocityX -= 3;
      }
      public void releaseLeft() {
      
      }
      public void right() {
         myVelocityX += 3;
      }
      public void releaseRight() {
      
      }
      public void firstAction() {
      
      }
      public void secondAction() {
      
      }

      public Controls getControls() {
         return myControls;
      }
      public void setRandomColor() {
         Random rand = new Random();
         int r = 127 + rand.nextInt(128);
         int g = 127 + rand.nextInt(128);
         int b = 127 + rand.nextInt(128);
         myColor = new Color(r, g, b);
      }
      public Color getColor() {
         return myColor;
      }
      public double getBallHeight() {
         return myBallHeight; 
      }
   }

   public static class Ball extends Circle {
      public int LEFT = -1;
      public int RIGHT = -1;
      public Ball (Player p) {
         super(p.getX(), p.getBallHeight(), 40);
      }
      public void update() {}
      public void draw(Graphics pen) {}
      public void rotate(double degrees) {}
   }


   public static class Controls {
      private Player myPlayer;
      private int myUpKey = KeyEvent.VK_UP;
      private int myDownKey = KeyEvent.VK_DOWN;
      private int myLeftKey = KeyEvent.VK_LEFT;
      private int myRightKey = KeyEvent.VK_RIGHT;
      private int myFirstActionKey = KeyEvent.VK_COMMA;
      private int mySecondActionKey = KeyEvent.VK_PERIOD;
      private int myThirdActionKey = KeyEvent.VK_SLASH;

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