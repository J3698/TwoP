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
   GameState myPlay = new Play();
   GameState myPause = new Pause();
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
   public void update() {
      myPlay.checkUpdateTrigger(gameMode);
      myPause.checkUpdateTrigger(gameMode);
   }
   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      myPlay.checkDrawTrigger(gameMode, myBuffer);
      myPause.checkDrawTrigger(gameMode, myBuffer);
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
      void checkDrawTrigger(String gameMode, Graphics pen);
      void checkUpdateTrigger(String gameMode);
      void checkChangeModeTrigger(String gameMode);
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
      }
      public void keyListen(KeyEvent event) {
      }
      public void drawBackground(Graphics pen) {
         pen.setColor(firstPlayer.getColor());
         pen.fillRect(0, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(secondPlayer.getColor());
         pen.fillRect(myGameWidth / 2, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(new Color(0, 0, 0, myTextOpacity));
         pen.setFont(playAndResumeFont);
         pen.drawString("P to Pause", 100, 250);
         pen.drawString(""+firstPlayer.getHealth(), 100,100);
         pen.drawString(""+secondPlayer.getHealth(), 300, 300);
      }
      public void drawPlayers(Graphics pen) {
         firstPlayer.drawSelfAndGun(pen);
         secondPlayer.drawSelfAndGun(pen);
      }
      public void drawGUI(Graphics pen) {
         int firstLength = firstPlayer.getHealth();
         int firstStartPoint = myGameWidth / 2 - firstLength;
         int secondLength = secondPlayer.getHealth();
         Color firstColor = secondPlayer.getColor();
         Color secondColor = firstPlayer.getColor();
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
      public void setTextOpacity(int opacity) {
         myTextOpacity = opacity;
      }
      public int getTextOpacity() {
         return myTextOpacity;
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
         if (myTextOpacity < 200)
            myTextOpacity += 40;
//         myPlay.setTextOpacity(myPlay.getTextOpacity() / 2);
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
   
   public class GameOver implements GameState {
      private int myVeilOpacity = 0;
      private int myStringOpacity = 0;
      private String myGameMode = "gameover";
      public void draw(Graphics pen) {
         myPlay.draw(pen);
         pen.setColor(new Color(0, 0, 0, myVeilOpacity));
         pen.fillRect(0, 0, myGameWidth, myGameHeight);
         pen.setColor(new Color(255, 255, 255, myStringOpacity));
      }
      public void update() {
         myVeilOpacity++;
         myStringOpacity++;
      }
      public void keyListen(KeyEvent event) {
      
      }
      public void checkDrawTrigger(String gameMode, Graphics pen) {
      
      }
      public void checkUpdateTrigger(String gameMode) {
      
      }
      public void checkChangeModeTrigger(String gameMode) {
      
      }
   }
}