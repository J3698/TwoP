package twop.gamestate;

import twop.Game;
import twop.handler.*;
import twop.Player;
import twop.util.StringDraw;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * Play game state for playing the game.
 *
 */
public class Play implements GameState {
   private String myGameMode = "play";
   private Game myGame;
   private PlaneHandler myPlaneHandler;
   private BumperHandler myBumperHandler;
   private ItemHandler myItemHandler;
   private Player myFirstPlayer;
   private Player mySecondPlayer;
   private Font playResumeFont;
   private int myGameWidth;
   private int myGameHeight;
   private int myTextOpacity = 50;
   private String myBackgroundMessage = "P to Pause";

   public Play(Game game, Player firstPlayer, Player secondPlayer,
                                    int gameWidth, int gameHeight) {
      myGame = game;
      myFirstPlayer = firstPlayer;
      mySecondPlayer = secondPlayer;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myPlaneHandler = new PlaneHandler(myGameWidth, myGameHeight);
      myBumperHandler = new BumperHandler(myGameWidth, myGameHeight);
      myItemHandler = new ItemHandler(myGameWidth, myGameHeight);
      playResumeFont = StringDraw.playResumeFont();
   }

   public void draw(Graphics pen) {
      drawBackground(pen);
      myPlaneHandler.draw(pen);
      myBumperHandler.draw(pen);
      myItemHandler.draw(pen);
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
      myItemHandler.update(myFirstPlayer, mySecondPlayer);

      if (myFirstPlayer.getHealth() <= 0 || mySecondPlayer.getHealth() <= 0)
         myGame.setGameMode("gameOver");
   }

   /**
    * Listen for play key events if current game
    * state is play.
    *
    */
   public void checkKeyListenTrigger(String currentGameMode, KeyEvent event) {
      if (myGameMode == currentGameMode)
         keyListen(event);
   }

   /**
    * Listen for play key events
    *
    */
   public void keyListen(KeyEvent event) {
      myFirstPlayer.getControls().keyDown(event);
      mySecondPlayer.getControls().keyDown(event);
      if (event.getKeyCode() == KeyEvent.VK_P) {
         myBackgroundMessage = "R to Resume";
         myGame.setGameMode("pause");
         myGame.getPause().setIsPausing(true);
      }
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
      pen.setFont(playResumeFont);
      StringDraw.drawStringCenter(pen, myBackgroundMessage, myGameWidth / 2, myGameHeight / 2 - 30);
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
   public void checkDrawTrigger(String currentGameMode, Graphics pen) {
      if (myGameMode == currentGameMode)
         draw(pen);
   }

   /**
    *
    *
    *
    */
   public void checkUpdateTrigger(String currentGameMode) {
      if (myGameMode == currentGameMode)
         update();
   }

   public void setBackgroundMessage(String message) {
      myBackgroundMessage = message;
   }
}