package twop.gamestate;

import twop.Game;
import twop.util.StringDraw;
import twop.util.Vector2;
import twop.Player;
import twop.gui.GUIManager;
import twop.gui.GameOverButton;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameOver extends GameState {
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;

   private Player myFirstPlayer;
   private Player mySecondPlayer;

   private GUIManager myGUIManager;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   private int myVeilOpacity = 0;


   public GameOver(Game game, Player firstPlayer, Player secondPlayer, int gameWidth, int gameHeight) {
      super("gameOver");
      myGame = game;
      myFirstPlayer = firstPlayer;
      mySecondPlayer = secondPlayer;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myGUIManager = new GUIManager();
      int halfWidth = myGameWidth / 2;
      myGUIManager.addButton(new GameOverButton(new MainMenuListener(), "MAIN MENU", new Vector2(halfWidth - 160, 200), myGameWidth, myGameHeight));
      myGUIManager.addButton(new GameOverButton(new RematchListener(), "REMATCH", new Vector2(halfWidth + 50, 200), myGameWidth, myGameHeight));
   }

   public void draw(Graphics pen) {
      myGame.getPlay().draw(pen);

      pen.setColor(new Color(0, 0, 0, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);

      pen.setFont(new Font("Sans", 0, 22));
      pen.setColor(myFirstPlayer.getColor());

      String firstWinState, secondWinState;
      if (myGame.getPlay().getWinner() == 1) {
    	  firstWinState = "WINS";
    	  secondWinState = "LOSES";
      } else {
    	  firstWinState = "LOSES";
    	  secondWinState = "WINS";
      }

      StringDraw.drawStringCenter(pen, "P L A Y E R  O N E  " + firstWinState , myGameWidth / 2 - 155, myGameHeight / 2 - 60);
      pen.setColor(mySecondPlayer.getColor());
      StringDraw.drawStringCenter(pen, "P L A Y E R  T W O  " + secondWinState, myGameWidth / 2 + 155, myGameHeight / 2 - 60);

      myGUIManager.draw(pen);
   }

   public void update() {
      myGame.getPlay().update();

      if (myVeilOpacity <= 150) {
         myVeilOpacity += 5;
      }
   }

   public class MainMenuListener implements ActionListener {
	   public void actionPerformed(ActionEvent event) {
//		   myGame.getPlay().reset();
		   myGame.setGameMode("mainmenu");
	   }
   }

   public class RematchListener implements ActionListener {
	   public void actionPerformed(ActionEvent event) {
//		   myGame.getPlay().reset();
		   myGame.setGameMode("play");
	   }
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         if (myVeilOpacity < 200) {
            myFirstPlayer.getControls().keyDown(event);
            mySecondPlayer.getControls().keyDown(event);
         }
      }
      public void keyReleased(KeyEvent event) {
         if (myVeilOpacity < 200) {
            myFirstPlayer.getControls().keyUp(event);
            mySecondPlayer.getControls().keyUp(event);
         }
      }
   }

   private class MouseListener extends MouseAdapter {
	   public void mousePressed(MouseEvent event) {
		   myGUIManager.mousePressed(event);
	   }
	   public void mouseMoved(MouseEvent event) {
		   myGUIManager.mouseMoved(event);
	   }
   }
}