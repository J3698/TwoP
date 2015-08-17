package twop.gamestate;

import twop.Game;
import twop.util.StringDraw;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
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

   public GameOver(Game game, int gameWidth, int gameHeight) {
      super("gameOver");
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
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

   /**
    *
    *
    *
    */
   public void keyListen(KeyEvent event) {
      if (myVeilOpacity != 255)
         myGame.getPlay().keyListen(event);
   }

   public void mouseListen(MouseEvent event) {
   }
}