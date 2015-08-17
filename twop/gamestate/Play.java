package twop.gamestate;

import twop.Game;
import twop.handler.*;
import twop.Player;
import twop.util.StringDraw;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Play game state for playing the game.
 *
 */
public class Play extends GameState {
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
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Play(Game game, Player firstPlayer, Player secondPlayer,
                                    int gameWidth, int gameHeight) {
      super("play");
      myGame = game;
      myFirstPlayer = firstPlayer;
      mySecondPlayer = secondPlayer;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
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

   public void setBackgroundMessage(String message) {
      myBackgroundMessage = message;
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
	   public void keyPressed(KeyEvent event) {
		   myFirstPlayer.getControls().keyDown(event);
		   mySecondPlayer.getControls().keyDown(event);

		   if (event.getKeyCode() == KeyEvent.VK_P) {
	          myBackgroundMessage = "R to Resume";
	          myGame.setGameMode("pause");
	          myGame.getPause().setIsPausing(true);
 	       }
	   }
	   public void keyReleased(KeyEvent event) {
		   myFirstPlayer.getControls().keyUp(event);
		   mySecondPlayer.getControls().keyUp(event);
	   }
   }

   private class MouseListener extends MouseAdapter {
   }
}