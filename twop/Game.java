package twop;

import twop.gamestate.*;
import twop.util.StringDraw;
import twop.util.Vector2;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
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
   private Font playAndResumeFont;
   private Font versionFont;
   private MainMenu myMainMenu;
   private Instructions myInstructions;
   private Play myPlay;
   private Pause myPause;
   private GameOver myGameOver;
   private Credits myCredits;
   private ArrayList<GameState> myGameStates;

   /**
    *
    *
    *
    */
   public Game(String currentGameMode, int gameWidth, int gameHeight) {
      myCurrentGameMode = currentGameMode;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      versionFont = StringDraw.versionFont();
      myGameStates = new ArrayList<GameState>();
      initPlayers();
      initGameModes();
   }

   /**
    * Instantiate players and  take care of
    * some player related fields.
    *
    */
   public void initPlayers() {
      int radius = 19;
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
      myMainMenu = new MainMenu(this, myGameWidth, myGameHeight);
      myInstructions = new Instructions(this, myGameWidth, myGameHeight);
      myPlay = new Play(this, myFirstPlayer, mySecondPlayer, myGameWidth, myGameHeight);
      myPause = new Pause(this, myGameWidth, myGameHeight);
      myGameOver = new GameOver(this, myGameWidth, myGameHeight);
      myCredits = new Credits(this, myGameWidth, myGameHeight);
      myGameStates.add(myMainMenu);
      myGameStates.add(myInstructions);
      myGameStates.add(myPlay);
      myGameStates.add(myPause);
      myGameStates.add(myGameOver);
      myGameStates.add(myCredits);
   }

   /**
    * Tell all game states to check if they
    * should update and act accordingly.
    *
    */
   public void update() {
      for (GameState gameState : myGameStates)
         gameState.checkUpdateTrigger(myCurrentGameMode);
   }

   /**
    * Tell all game states to check if they
    * should draw and act accordingly.
    * Display version in set position.
    *
    */
   public void draw(Graphics pen) {
      for (GameState gameState : myGameStates)
         gameState.checkDrawTrigger(myCurrentGameMode, pen);
      pen.setFont(versionFont);
      pen.setColor(new Color(123, 45, 249));
      pen.drawString("V. 1.1", 5, 20);
   }

   /**
    * Tell all game states to check if they should
    * check for key-down events and act accordingly.
    *
    */
   public void keyDownListen(KeyEvent event) {
       for (GameState gameState : myGameStates)
         gameState.checkKeyListenTrigger(myCurrentGameMode, event);
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

   public void mouseListen(MouseEvent event) {
       for (GameState gameState : myGameStates)
          gameState.checkMouseListenTrigger(myCurrentGameMode, event);
   }

   public void setGameMode(String gameMode) { myCurrentGameMode = gameMode; }
   public MainMenu getMainMenu() { return myMainMenu; }
   public Play getPlay() { return myPlay; }
   public Pause getPause() { return myPause; }
   public Instructions getInstructions() { return myInstructions; }
   public GameOver getGameOver() { return myGameOver; }
   public Credits getCredits() { return myCredits; }
}
