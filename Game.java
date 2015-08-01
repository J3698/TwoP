import java.awt.Graphics;
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


/**
 * Game class holds game modes and
 * contains methods for managing them.
 *
 */
public class Game {
   private String myCurrentGameMode;
   private int myGameWidth;
   private int myGameHeight;
   private Player myFirstPlayer;
   private Player mySecondPlayer;
   private BumperHandler myBumperHandler;
   private PlaneHandler myPlaneHandler;
   private Font playAndResumeFont;
   private Font versionFont;
   private Instructions myInstructions;
   private Play myPlay;
   private Pause myPause;
   private GameOver myGameOver;

   /**
    *
    *
    *
    */
   public Game(String currentGameMode, int gameWidth, int gameHeight) {
      myCurrentGameMode = currentGameMode;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myBumperHandler = new BumperHandler(myGameWidth, myGameHeight);
      myPlaneHandler = new PlaneHandler(myGameWidth, myGameHeight);
      initPlayers();
      initGameModes();
      setFonts();
   }

   /**
    * Create instances of the different
    * game modes.
    *
    */
   public void initGameModes() {
      myInstructions = new Instructions();
      myPlay = new Play();
      myPause = new Pause();
      myGameOver = new GameOver();
   }


   /**
    * Instantiate players and  take care of
    * some player related fields.
    *
    */
   public void initPlayers() {
      int radius = 20;
      Vector2 firstPos = new Vector2(60, 350);
      Vector2 secondPos = new Vector2(myGameWidth - 60, 350);
      myFirstPlayer = new Player(firstPos, radius, 0, myGameHeight, myGameWidth, 0);
      mySecondPlayer = new Player(secondPos, radius, 0, myGameHeight, myGameWidth, 0);
      mySecondPlayer.getControls().setSecondControls();
      mySecondPlayer.getGun().setSpinDirection(Gun.LEFT);
   }


   /**
    * Instantiate fonts to be used
    * in the game.
    *
    */
   public void setFonts() {
      playAndResumeFont = new Font("Ariel", Font.BOLD, 90);
      versionFont = new Font("Sans", 1, 14);
   }

   /**
    * Tell all game states to check if they
    * should update and act accordingly.
    *
    */
   public void update() {
      myInstructions.checkUpdateTrigger(myCurrentGameMode);
      myPlay.checkUpdateTrigger(myCurrentGameMode);
      myPause.checkUpdateTrigger(myCurrentGameMode);
      myGameOver.checkUpdateTrigger(myCurrentGameMode);
   }

   /**
    * Tell all game states to check if they
    * should draw and act accordingly.
    * Display version in set position.
    *
    */
   public void draw(Graphics pen) {
      myInstructions.checkDrawTrigger(myCurrentGameMode, pen);
      myPlay.checkDrawTrigger(myCurrentGameMode, pen);
      myPause.checkDrawTrigger(myCurrentGameMode, pen);
      myGameOver.checkDrawTrigger(myCurrentGameMode, pen);
      pen.setFont(versionFont);
      pen.setColor(new Color(123, 45, 249));
      pen.drawString("Pre-Alpha Build", 10, 60);
   }

   /**
    * Tell all game states to check if they should
    * check for key-down events and act accordingly.
    *
    */
   public void keyDownListen(KeyEvent event) {
       myInstructions.checkKeyListenTrigger(event);
       myPlay.checkKeyListenTrigger(event);
       myPause.checkKeyListenTrigger(event);
       myGameOver.checkKeyListenTrigger(event);
   }

   /**
    * Tell all game states to check if they should
    * check for key-up events and act accordingly.
    *
    */
   public void keyUpListen(KeyEvent event) {
       myFirstPlayer.getControls().keyUp(event);
       mySecondPlayer.getControls().keyUp(event);
   }

   /**
    * Interface that different game states should
    * implement.
    *
    */
   public interface GameState {
      void checkDrawTrigger(String myCurrentGameMode, Graphics pen);
      void draw(Graphics pen);
      void checkUpdateTrigger(String myCurrentGameMode);
      void update();
      void checkKeyListenTrigger(KeyEvent event);
      void keyListen(KeyEvent event);
   }

   /**
    * Instructions game state for showing controls
    * to players.
    *
    */
   public class Instructions implements GameState {
      private String myGameMode = "instructions";

      /**
       * Draw instructions screen if current gamemode
       * is instructions.
       *
       */
      public void checkDrawTrigger(String myCurrentGameMode, Graphics pen) {
         if (myGameMode == myCurrentGameMode)
            draw(pen);
      }

      /**
       * Draw the instructions screen.
       *
       */
      public void draw(Graphics pen) {
      }

      /**
       * Update the instructions if current game state
       * is instructions.
       *
       */
      public void checkUpdateTrigger(String myCurrentGameMode) {
         if (myGameMode == myCurrentGameMode)
            update();
      }

      /**
       * Update the instructions screen
       *
       */
      public void update() {
      }

      /**
       * Listen for key events if current game state
       * is instructions.
       *
       */
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == myCurrentGameMode)
            keyListen(event);
      }

      /**
       * Listen for instruction key events.
       *
       */
      public void keyListen(KeyEvent event) {
      }
   }

   /**
    * Play game state for playing the game.
    *
    */
   public class Play implements GameState {
      private String myGameMode = "play";
      private int myTextOpacity = 50;

      public void draw(Graphics pen) {
         drawBackground(pen);
         myPlaneHandler.draw(pen);
         myFirstPlayer.drawSelfAndWeapon(pen);
         mySecondPlayer.drawSelfAndWeapon(pen);
         drawHealth(pen);
      }

      /**
       * Update the cuurently played game.
       *
       */
      public void update() {
         myFirstPlayer.update();
         myFirstPlayer.takeDamage(mySecondPlayer.getGun().getBullets());
         mySecondPlayer.update();
         mySecondPlayer.takeDamage(myFirstPlayer.getGun().getBullets());
         myPlaneHandler.update(myFirstPlayer, mySecondPlayer);
         myBumperHandler.update(myFirstPlayer, mySecondPlayer);
         if (myFirstPlayer.getHealth() <= 0 || mySecondPlayer.getHealth() <= 0)
            myCurrentGameMode = "gameOver";
      }

      /**
       * Listen for play key events if current game
       * state is play.
       *
       */
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == myCurrentGameMode)
            keyListen(event);
      }

      /**
       * Listen for play key events
       *
       */
      public void keyListen(KeyEvent event) {
         myFirstPlayer.getControls().keyDown(event);
         mySecondPlayer.getControls().keyDown(event);
         if (event.getKeyCode() == KeyEvent.VK_P)
            myCurrentGameMode = "pause";
      }

      /**
       * Draw the background for play.
       *
       */
      public void drawBackground(Graphics pen) {
         pen.setColor(mySecondPlayer.getColor());
         pen.fillRect(0, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(myFirstPlayer.getColor());
         pen.fillRect(myGameWidth / 2, 0, myGameWidth / 2, myGameHeight);
         pen.setColor(new Color(0, 0, 0, myTextOpacity));
         pen.setFont(playAndResumeFont);
         pen.drawString("P to Pause", 100, 250);
      }

      /**
       * Draw the healthbars of the players.
       *
       */
      public void drawHealth(Graphics pen) {
         int firstLength = (int) myFirstPlayer.getHealth();
         int firstStartPoint = myGameWidth / 2 - firstLength;
         int secondLength = (int) mySecondPlayer.getHealth();
         Color firstColor = myFirstPlayer.getColor();
         Color secondColor = mySecondPlayer.getColor();
         pen.setColor(firstColor);
         pen.fillRect(firstStartPoint, 10, firstLength, 30);
         pen.setColor(secondColor);
         pen.fillRect(myGameWidth / 2, 10, secondLength, 30);
      }

      /**
       * Draws the play screen if game state is play.
       *
       */
      public void checkDrawTrigger(String myCurrentGameMode, Graphics pen) {
         if (myGameMode == myCurrentGameMode)
            draw(pen);
      }

      /**
       *
       *
       *
       */
      public void checkUpdateTrigger(String myCurrentGameMode) {
         if (myGameMode == myCurrentGameMode)
            update();
      }

      /**
       *
       *
       *
       */
      public void fadeOutTextOpacity() {
      }

      /**
       *
       *
       *
       */
      public void fadeInTextOpacity() {
      }
   }

   /**
    *
    *
    *
    */
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

      /**
       *
       *
       *
       */
      public void update() {
         if (myVeilOpacity < 100)
            myVeilOpacity += 10;
         fadeInTextOpacity();
         myPlay.fadeOutTextOpacity();
      }

      /**
       *
       *
       *
       */
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == myCurrentGameMode)
            keyListen(event);
      }

      /**
       *
       *
       *
       */
      public void keyListen(KeyEvent event) {
         if (event.getKeyCode() == KeyEvent.VK_R)
            myCurrentGameMode = "play";
      }

      /**
       *
       *
       *
       */
      public void checkDrawTrigger(String myCurrentGameMode, Graphics pen) {
         if (myGameMode == myCurrentGameMode)
            draw(pen);
      }

      /**
       *
       *
       *
       */
      public void checkUpdateTrigger(String myCurrentGameMode) {
         if (myGameMode == myCurrentGameMode)
            update();
      }

      /**
       *
       *
       *
       */
      public void fadeOutTextOpacity() {
      }

      /**
       *
       *
       *
       */
      public void fadeInTextOpacity() {
      }
   }

   /**
    *
    *
    *
    */
   public class GameOver implements GameState {
      private int myVeilOpacity = 0;
      private int myStringOpacity = 0;
      private double myTextLocation = 175;
      private String myGameMode = "gameOver";

      /**
       *
       *
       *
       */
      public void draw(Graphics pen) {
         if (myVeilOpacity != 255)
            myPlay.draw(pen);
         pen.setColor(new Color(255, 255, 255, myVeilOpacity));
         pen.fillRect(0, 0, myGameWidth, myGameHeight);
         pen.setColor(new Color(0, 0, 0, myStringOpacity));
         pen.setFont(playAndResumeFont);
         pen.drawString("Game Over", 30, (int) myTextLocation);
      }

      /**
       *
       *
       *
       */
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

      /**
       *
       *
       *
       */
      public void checkKeyListenTrigger(KeyEvent event) {
         if (myGameMode == myCurrentGameMode)
            keyListen(event);
      }

      /**
       *
       *
       *
       */
      public void keyListen(KeyEvent event) {
         if (myVeilOpacity != 255)
            myPlay.keyListen(event);
      }

      /**
       *
       *
       *
       */
      public void checkDrawTrigger(String myCurrentGameMode, Graphics pen) {
         if (myGameMode == myCurrentGameMode)
            draw(pen);
      }

      /**
       *
       *
       *
       */
      public void checkUpdateTrigger(String myCurrentGameMode) {
         if (myGameMode == myCurrentGameMode)
            update();
      }
   }
}
