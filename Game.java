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
    * Create instances of the different
    * game modes.
    *
    */
   public void initGameModes() {
      myInstructions = new Instructions(myFirstPlayer, mySecondPlayer, myGameWidth, myGameHeight);
      myPlay = new Play(myFirstPlayer, mySecondPlayer, myGameWidth, myGameHeight);
      myPause = new Pause(myPlay, myGameWidth, myGameHeight);
      myGameOver = new GameOver(myPlay, myGameWidth, myGameHeight);
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
       myInstructions.checkKeyListenTrigger(myCurrentGameMode, event);
       myPlay.checkKeyListenTrigger(myCurrentGameMode, event);
       myPause.checkKeyListenTrigger(myCurrentGameMode, event);
       myGameOver.checkKeyListenTrigger(myCurrentGameMode, event);
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
}
