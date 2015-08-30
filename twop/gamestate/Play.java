package twop.gamestate;

import twop.GamePanel;
import twop.Gun;
import twop.plane.PlaneHandler;
import twop.bumper.BumperHandler;
import twop.item.ItemHandler;
import twop.weather.WeatherHandler;
import twop.Player;
import twop.util.StringDraw;
import twop.util.Vector2;

import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

/**
 * Play game state for playing the game.
 *
 */
public class Play extends GameState {
   private GamePanel myGamePanel;
   private int myGameWidth;
   private int myGameHeight;

   private PlaneHandler myPlaneHandler;
   private BumperHandler myBumperHandler;
   private ItemHandler myItemHandler;
   private WeatherHandler myWeatherHandler;

   private Player myFirstPlayer;
   private Player mySecondPlayer;
   private int myWinner = 0;

   private Font playResumeFont;
   private String myBackgroundMessage = "P to Pause";
   private int myTextOpacity = 50;

   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Play(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super("play");
      myGamePanel = gamePanel;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      initPlayers();
      myPlaneHandler = new PlaneHandler(myGameWidth, myGameHeight);
      myBumperHandler = new BumperHandler(myGameWidth, myGameHeight);
      myItemHandler = new ItemHandler(myGameWidth, myGameHeight);
      myWeatherHandler = new WeatherHandler(myGamePanel);
      playResumeFont = StringDraw.playResumeFont();
   }

   public void initPlayers() {
      int radius = 19;
      Vector2 firstPos = new Vector2(60, 350);
      Vector2 secondPos = new Vector2(myGameWidth - 60, 350);
      myFirstPlayer = new Player(firstPos, radius, 0, myGameHeight - 1, myGameWidth, 0);
      mySecondPlayer = new Player(secondPos, radius, 0, myGameHeight - 1, myGameWidth, 0);
      mySecondPlayer.getControls().setSecondControls();
      mySecondPlayer.getGun().setSpinDirection(Gun.LEFT);
   }

   public void draw(Graphics pen) {
      drawBackground(pen);
      myBumperHandler.draw(pen);
      myItemHandler.draw(pen);
      myPlaneHandler.draw(pen);
      myFirstPlayer.draw(pen);
      mySecondPlayer.draw(pen);
      drawHealth(pen);
      myWeatherHandler.draw(pen);
   }

   public void update() {
      myFirstPlayer.update();
      myFirstPlayer.takeDamage(mySecondPlayer.getGun().getBullets());
      mySecondPlayer.update();
      mySecondPlayer.takeDamage(myFirstPlayer.getGun().getBullets());
      myPlaneHandler.update(myFirstPlayer, mySecondPlayer);
      myBumperHandler.update(myFirstPlayer, mySecondPlayer);
      myItemHandler.update(myFirstPlayer, mySecondPlayer);
      myWeatherHandler.update();

      if (myFirstPlayer.getHealth() <= 0 || mySecondPlayer.getHealth() <= 0) {
         myGamePanel.setGameMode("gameOver");
         myBackgroundMessage = "";
         if (myWinner == 0) {
            if (myFirstPlayer.getHealth() <= 0) {
               myWinner = 2;
            } else if (mySecondPlayer.getHealth() <= 0) {
               myWinner = 1;
            }
         }
      }
   }

   public void reset() {
      // stop sounds
      initPlayers();
      myBackgroundMessage = "P to Pause";
      myItemHandler.reset();
      myBumperHandler.reset();
      myPlaneHandler.reset();
      myWeatherHandler.reset();
      myGamePanel.getGameOver().reset();
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
    * Draw the health bars of the players.
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
	          myGamePanel.setGameMode("pause");
	          myGamePanel.getPause().setIsPausing(true);
 	       }
	   }
	   public void keyReleased(KeyEvent event) {
		   myFirstPlayer.getControls().keyUp(event);
		   mySecondPlayer.getControls().keyUp(event);
	   }
   }

   private class MouseListener extends MouseAdapter {
   }

   public Player getFirstPlayer() { return myFirstPlayer; }
   public Player getSecondPlayer() { return mySecondPlayer; }
   public int getWinner() { return myWinner; }
   public PlaneHandler getPlaneHandler() { return myPlaneHandler; }
}