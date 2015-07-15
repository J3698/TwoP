import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;

public class GamePanel extends JPanel {
   BufferedImage myImage;
   Graphics myBuffer;
   int myGameWidth;
   int myGameHeight;
   Player firstPlayer;
   Player secondPlayer;
   String gameMode = "play";
   Font playAndResumeFont;
   Timer timer;

   public GamePanel(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      setFocusable(true);
      preparePanelImage();
      startGameValues();
      addThreadInputs();
   }
   public void preparePanelImage() {
      myImage = new BufferedImage(myGameWidth,myGameHeight,
                              BufferedImage.TYPE_INT_ARGB);
      myBuffer = myImage.getGraphics();
   }
   public void startGameValues() {
      firstPlayer = new Player(20, 60, 350, 0,
                    myGameHeight, myGameWidth, 0);
      secondPlayer = new Player(20, myGameWidth - 60,
                     350, 0, myGameHeight,myGameWidth, 0);
      secondPlayer.getControls().setSecondControls();
      secondPlayer.getGun().setSpinDirection(Gun.LEFT);
      playAndResumeFont = new Font("Ariel", Font.BOLD, 90);
   }
   public void addThreadInputs() {
      addKeyListener(new KeyListener());
      timer = new Timer(20, new UpdateListener());
      timer.start();      
   }
   public void update() {    //Make mode classes that hold game, paused, etc?
      if (gameMode == "play") {
         firstPlayer.update();
         firstPlayer.takeDamage(secondPlayer.getGun().getBullets());
         secondPlayer.update();
         secondPlayer.takeDamage(firstPlayer.getGun().getBullets());
      } else if (gameMode == "pause") {
         if (pauseOpacity < 100)
            pauseOpacity += 20;
      }
   }
   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      if (gameMode == "play") {
         drawGame(myBuffer);
      }
      else if (gameMode == "pause") {
         drawGame(myBuffer);
         drawPaused(myBuffer);
      }
      pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
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

   public interface GameState {
      void draw(Graphics pen);
      void update();
      void keyListen(KeyEvent event);
   }

   public class Play implements GameState {
      public void draw(Graphics pen) {
         drawBackground(myBuffer);  //drawGame();
         drawPlayers(myBuffer);
         drawGUI(myBuffer);
      }
      public void update() {
         
      }
      public void keyListen(KeyEvent event) {
      }
      public void drawBackground(Graphics pen) {
         pen.setColor(firstPlayer.getColor());
         pen.fillRect(0, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(secondPlayer.getColor());
         pen.fillRect(myGameWidth / 2, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(new Color(0, 0, 0, 50));
         pen.setFont(playAndResumeFont);
         pen.drawString("P to Pause", 100, 250);
         pen.drawString(""+firstPlayer.getHealth(), 100,100);
         pen.drawString(""+secondPlayer.getHealth(), 300, 300);
      }
      public void drawGUI(Graphics pen) {
         pen.setColor(new Color(0, 0, 0));
         pen.fillRect(myGameWidth / 2 - 15, 5, 30, 40);
         pen.setColor(firstPlayer.getColor());
         pen.fillRect(myGameWidth / 2 + 15, 10, 100, 30);
         pen.setColor(secondPlayer.getColor());
         pen.fillRect(myGameWidth / 2 - 15 - 100, 10, 100, 30);
      }
   }

   public class Pause implements GameState {
      private int pauseOpacity = 0;
      public void draw(Graphics pen) {
         myBuffer.setColor(new Color(0, 0, 0, (int) pauseOpacity));  //drawPaused();
         myBuffer.fillRect(0, 0, myGameWidth, myGameHeight);
      }
      public void update() {
      }
      public void keyListen(KeyEvent event) {
      }
   }

   public void drawPlayers(Graphics pen) {
      firstPlayer.drawSelfAndGun(pen);
      secondPlayer.drawSelfAndGun(pen);
   }
}