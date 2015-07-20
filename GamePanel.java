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
   Play myPlay = new Play();
   Pause myPause = new Pause();
   GameOver myGameOver = new GameOver();
   BufferedImage myImage;
   Graphics myBuffer;
   int myGameWidth;
   int myGameHeight;
   Player firstPlayer;
   Player secondPlayer;
   String gameMode = "play"; //Why doesn't this work if its "gameover"?
   Font playAndResumeFont;
   Font scoreFont;
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
      scoreFont = new Font("Ariel", 0, 14);
   }
   public void addThreadInputs() {
      addKeyListener(new KeyListener());
      timer = new Timer(20, new UpdateListener());
      timer.start();
   }
   public void update() {
      myPlay.checkUpdateTrigger(gameMode);
      myPause.checkUpdateTrigger(gameMode);
      myGameOver.checkUpdateTrigger(gameMode);
   }
   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      myPlay.checkDrawTrigger(gameMode, myBuffer);
      myPause.checkDrawTrigger(gameMode, myBuffer);
      myGameOver.checkDrawTrigger(gameMode, myBuffer);
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
         myPlay.checkKeyListenTrigger(event);
         myPause.checkKeyListenTrigger(event);
         myGameOver.checkKeyListenTrigger(event);
      }
      public void keyReleased(KeyEvent event) {
         firstPlayer.getControls().keyUp(event);
         secondPlayer.getControls().keyUp(event);
      }
   }

   public interface GameState {
      void checkDrawTrigger(String gameMode, Graphics pen);
      void draw(Graphics pen);
      void checkUpdateTrigger(String gameMode);
      void update();
      void checkKeyListenTrigger(KeyEvent event);
      void keyListen(KeyEvent event);
      void checkChangeModeTrigger(String gameMode);  // needed??
   }

   public class Play implements GameState {
      private String myGameMode = "play";
      private int myTextOpacity = 50;
      public void draw(Graphics pen) {
         drawBackground(myBuffer);  //drawGame();
         drawPlayers(myBuffer);
         drawGUI(myBuffer);
      }
      public void update() {
         firstPlayer.update();
         firstPlayer.takeDamage(secondPlayer.getGun().getBullets());
         secondPlayer.update();
         secondPlayer.takeDamage(firstPlayer.getGun().getBullets());
         if (firstPlayer.getHealth() <= 0 || secondPlayer.getHealth() <= 0)
            gameMode = "gameOver";
      }
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == gameMode)
            keyListen(event);
      }
      public void keyListen(KeyEvent event) {
         firstPlayer.getControls().keyDown(event);
         secondPlayer.getControls().keyDown(event);
         if (event.getKeyCode() == KeyEvent.VK_P)
            gameMode = "pause";
      }
      public void drawBackground(Graphics pen) {
         pen.setColor(secondPlayer.getColor());
         pen.fillRect(0, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(firstPlayer.getColor());
         pen.fillRect(myGameWidth / 2, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(new Color(0, 0, 0, myTextOpacity));
         pen.setFont(playAndResumeFont);
         pen.drawString("P to Pause", 100, 250);
      }
      public void drawPlayers(Graphics pen) {
         firstPlayer.drawSelfAndWeapon(pen);
         secondPlayer.drawSelfAndWeapon(pen);
      }
      public void drawGUI(Graphics pen) {
         int firstLength = firstPlayer.getHealth();
         int firstStartPoint = myGameWidth / 2 - firstLength;
         int secondLength = secondPlayer.getHealth();
         Color firstColor = firstPlayer.getColor();
         Color secondColor = secondPlayer.getColor();
         pen.setColor(firstColor);
         pen.fillRect(firstStartPoint, 10, firstLength, 30);
         pen.setColor(secondColor);
         pen.fillRect(myGameWidth / 2, 10, secondLength, 30);
      }
      public void checkDrawTrigger(String gameMode, Graphics pen) {
         if (myGameMode == gameMode)
            draw(pen);
      }
      public void checkUpdateTrigger(String gameMode) {
         if (myGameMode == gameMode)
            update();
      }
      public void checkChangeModeTrigger(String gameMode) {
      }
      public void fadeOutTextOpacity() {
      }
      public void fadeInTextOpacity() {
      }
   }

   public class Pause implements GameState {
      private String myGameMode = "pause";
      private int myVeilOpacity = 0;
      private int myTextOpacity = 0;
      public void draw(Graphics pen) {
         myPlay.draw(pen);
         pen.setColor(new Color(0, 0, 0, myVeilOpacity));
         pen.fillRect(0, 0, myGameWidth, myGameHeight);
         pen.setColor(new Color(255, 255, 255, myTextOpacity));
         pen.drawString("R to Resume", 50, 250);
      }
      public void update() {
         if (myVeilOpacity < 100)
            myVeilOpacity += 20;
         fadeInTextOpacity();
         myPlay.fadeOutTextOpacity();
      }
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == gameMode)
            keyListen(event);
      }
      public void keyListen(KeyEvent event) {
         if (event.getKeyCode() == KeyEvent.VK_R)
            gameMode = "play";
      }
      public void checkDrawTrigger(String gameMode, Graphics pen) {
         if (myGameMode == gameMode)
            draw(pen);
      }
      public void checkUpdateTrigger(String gameMode) {
         if (myGameMode == gameMode)
            update();
      }
      public void checkChangeModeTrigger(String gameMode) {
      }
      public void fadeOutTextOpacity() {
      }
      public void fadeInTextOpacity() {
      }
   }
   
   public class GameOver implements GameState {
      private int myVeilOpacity = 0;
      private int myStringOpacity = 0;
      private int myTextLocation = 300;
       private String myGameMode = "gameOver";
      public void draw(Graphics pen) {
         myPlay.draw(pen);
         pen.setColor(new Color(255, 255, 255, myVeilOpacity));
         pen.fillRect(0, 0, myGameWidth, myGameHeight);
         pen.setColor(new Color(0, 0, 0, myStringOpacity));
         pen.drawString("Game Over", 100, myTextLocation);
      }
      public void update() {
         if (myVeilOpacity <= 252)
            myVeilOpacity += 3;
         else if (myVeilOpacity <= 250 || myStringOpacity <= 252) {
            myStringOpacity += 3;
            if (myTextLocation >= 250)
               myTextLocation -= 1;
         }
      }
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == gameMode)
            keyListen(event);
      }
      public void keyListen(KeyEvent event) {
      }
      public void checkDrawTrigger(String gameMode, Graphics pen) {
         if (myGameMode == gameMode)
            draw(pen);
      }
      public void checkUpdateTrigger(String gameMode) {
         if (myGameMode == gameMode)
            update();
      }
      public void checkChangeModeTrigger(String gameMode) {
      
      }
   }
}