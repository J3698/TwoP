package twop.gamestate;

import twop.Game;
import twop.util.StringDraw;
import twop.Player;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 *
 *
 */
public class GameOver extends GameState {
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;
   private int myVeilOpacity = 0;
   private int myStringOpacity = 0;
   private Player myFirstPlayer;
   private Player mySecondPlayer;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public GameOver(Game game, Player firstPlayer, Player secondPlayer, int gameWidth, int gameHeight) {
      super("gameOver");
      myGame = game;
      myFirstPlayer = firstPlayer;
      mySecondPlayer = secondPlayer;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myGame.getPlay().setBackgroundMessage("");
   }

   /**
    *
    *
    *
    */
   public void draw(Graphics pen) {
      myGame.getPlay().draw(pen);

      pen.setColor(new Color(0, 0, 0, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);

      pen.setFont(new Font("Sans", 0, 25));
      pen.setColor(myFirstPlayer.getColor());

      StringDraw.drawStringCenter(pen, "P L A Y E R ONE etc..." , myGameWidth / 2 - 130, myGameHeight / 2 - 10);
      pen.setColor(mySecondPlayer.getColor());
      StringDraw.drawStringCenter(pen, "P L A Y E R TWO etc...", myGameWidth / 2 + 130, myGameHeight / 2 - 10);
   }

   /**
    *
    *
    *
    */
   public void update() {
      if (myVeilOpacity != 200)
         myGame.getPlay().update();
      if (myVeilOpacity <= 200)
         myVeilOpacity += 5;
      else if (myVeilOpacity <= 250 || myStringOpacity <= 253) {
         myStringOpacity += 2;
      }
   }

   public void mouseListen(MouseEvent event) {
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
	   
   }
}