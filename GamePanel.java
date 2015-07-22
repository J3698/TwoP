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
import java.lang.Thread;

public class GamePanel extends JPanel {
   Instructions myInstructions = new Instructions();
   Play myPlay = new Play();
   Pause myPause = new Pause();
   GameOver myGameOver = new GameOver();
   BufferedImage myImage;
   Graphics myBuffer;
   int myGameWidth;
   int myGameHeight;
   Player firstPlayer;
   Player secondPlayer;
   String gameMode = "play";
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
      firstPlayer = new Player(new Vector2(60, 350), 20, 0,
                             myGameHeight, myGameWidth, 0);
      secondPlayer = new Player(new Vector2(myGameWidth - 60, 350)
                            , 20, 0, myGameHeight,myGameWidth, 0);
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
      myInstructions.checkUpdateTrigger(gameMode);
      myPlay.checkUpdateTrigger(gameMode);
      myPause.checkUpdateTrigger(gameMode);
      myGameOver.checkUpdateTrigger(gameMode);
   }
   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      myInstructions.checkDrawTrigger(gameMode, myBuffer);
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
         myInstructions.checkKeyListenTrigger(event);
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
   }

   public class Instructions implements GameState {
      private String myGameMode = "instructions";

      public void checkDrawTrigger(String gameMode, Graphics pen) {
         if (myGameMode == gameMode)
            draw(pen);
      }
      public void draw(Graphics pen) {
         pen.setColor(Color.white);
         pen.fillRect(0, 0, myGameWidth, myGameHeight);
         pen.setColor(Color.red);
         pen.drawString("w", 10, 120);
         pen.drawString("a", 20, 110);
         pen.drawString("s", 30, 100);
         pen.drawString("d", 40, 90);
         pen.drawString("up", 50, 80);
         pen.drawString("left", 60, 70);
         pen.drawString("down", 70, 60);
         pen.drawString("right", 80, 50);
         pen.drawString("1", 90, 40);
         pen.drawString("2", 100, 30);
         pen.drawString(".", 110, 20);
         pen.drawString("/", 120, 10);
      }
      public void checkUpdateTrigger(String gameMode) {
         if (myGameMode == gameMode)
            update();
      }
      public void update() {
      }
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == gameMode)
            keyListen(event);
      }
      public void keyListen(KeyEvent event) {
      }
   }

   public class Play implements GameState {
      private String myGameMode = "play";
      private int myTextOpacity = 50;
      public void draw(Graphics pen) {
         drawBackground(myBuffer);
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
      public void fadeOutTextOpacity() {
      }
      public void fadeInTextOpacity() {
      }
   }
   
   public class GameOver implements GameState {
      private int myVeilOpacity = 0;
      private int myStringOpacity = 0;
      private double myTextLocation = 175;
       private String myGameMode = "gameOver";
      public void draw(Graphics pen) {
         if (myVeilOpacity != 255)
            myPlay.draw(pen);
         pen.setColor(new Color(255, 255, 255, myVeilOpacity));
         pen.fillRect(0, 0, myGameWidth, myGameHeight);
         pen.setColor(new Color(0, 0, 0, myStringOpacity));
         pen.drawString("Game Over", 30, (int) myTextLocation);
      }
      public void update() {
         if (myVeilOpacity != 255)
            myPlay.update();
         if (myVeilOpacity <= 250)
            myVeilOpacity += 5;
         else if (myVeilOpacity <= 250 || myStringOpacity <= 254) {
            myStringOpacity += 1;
            if (myTextLocation >= 150)
               myTextLocation -= 0.5;
         }
      }
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == gameMode)
            keyListen(event);
      }
      public void keyListen(KeyEvent event) {
         if (myVeilOpacity != 255)
            myPlay.keyListen(event);
      }
      public void checkDrawTrigger(String gameMode, Graphics pen) {
         if (myGameMode == gameMode)
            draw(pen);
      }
      public void checkUpdateTrigger(String gameMode) {
         if (myGameMode == gameMode)
            update();
      }
   }
}