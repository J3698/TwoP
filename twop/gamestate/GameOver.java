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
   private Font playResumeFont;
   private int myVeilOpacity = 0;
   private int myStringOpacity = 0;
   private double myTextLocation = 175;
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
      playResumeFont = StringDraw.playResumeFont();
   }

   /**
    *
    *
    *
    */
   public void draw(Graphics pen) {
      if (myVeilOpacity != 255)
         myGame.getPlay().draw(pen);
      pen.setColor(new Color(255, 255, 255, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(new Color(0, 0, 0, myStringOpacity));
      pen.setFont(playResumeFont);
      StringDraw.drawStringCenter(pen, "Game Over", myGameWidth / 2, (int) myTextLocation);
   }

   /**
    *
    *
    *
    */
   public void update() {
      if (myVeilOpacity != 255)
         myGame.getPlay().update();
      if (myVeilOpacity <= 250)
         myVeilOpacity += 5;
      else if (myVeilOpacity <= 250 || myStringOpacity <= 253) {
         myStringOpacity += 2;
         if (myTextLocation >= 150)
            myTextLocation -= 0.5;
      }
   }

   public void mouseListen(MouseEvent event) {
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         if (myVeilOpacity < 255) {
            myFirstPlayer.getControls().keyDown(event);
            mySecondPlayer.getControls().keyDown(event);
         }
      }
      public void keyReleased(KeyEvent event) {
         if (myVeilOpacity < 255) {
            myFirstPlayer.getControls().keyUp(event);
            mySecondPlayer.getControls().keyUp(event);
         }
      }
   }

   private class MouseListener extends MouseAdapter {
	   
   }
}