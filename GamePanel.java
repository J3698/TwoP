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
   Timer timer;
   Font playAndResumeFont;
   Player firstPlayer;
   Player secondPlayer;
   String gameMode = "play";
   int gameWidth;
   int gameHeight;
   int pauseOpacity = 0;

   public GamePanel(int _gameWidth, int _gameHeight) {
      gameWidth = _gameWidth;
      gameHeight = _gameHeight;
      setFocusable(true);
      myImage = new BufferedImage(gameWidth, gameHeight,
                           BufferedImage.TYPE_INT_ARGB);
      myBuffer = myImage.getGraphics();
      playAndResumeFont = new Font("Ariel", Font.BOLD, 90);
      addKeyListener(new KeyListener());
      timer = new Timer(30, new UpdateListener());
      timer.start();
   }

   public void update() {
      if (gameMode == "play") {
         firstPlayer.update();
         secondPlayer.update();
      } else if (gameMode == "pause") {
         if (pauseOpacity < 100)
            pauseOpacity += 20;
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