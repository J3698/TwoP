package twop.gamestate;

import twop.Game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Pause implements GameState {
   private String myGameMode = "pause";
   private Game myGame;
   private boolean myIsPausing = true;
   private int myGameWidth;
   private int myGameHeight;
   private Font playResumeFont;
   private int myVeilOpacity = 0;
   private int myTextOpacity = 0;

   public Pause(Game game, int gameWidth, int gameHeight) {
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
   }

   public void draw(Graphics pen) {
      myGame.getPlay().draw(pen);
      pen.setColor(new Color(0, 0, 0, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(new Color(255, 255, 255, myTextOpacity));
   }

   /**
    *
    *
    *
    */
   public void update() {
      if (myIsPausing)
         fadeInVeil();
      else {
         fadeOutVeil();
         if (myVeilOpacity == 0) {
            myGame.setGameMode("play");
            myGame.getPlay().setBackgroundMessage("P to Play");
         }
      }
   }

   /**
    *
    *
    *
    */
   public void checkKeyListenTrigger(String currentGameMode, KeyEvent event) {
      if (myGameMode == currentGameMode)
         keyListen(event);
   }

   /**
    *
    *
    *
    */
   public void keyListen(KeyEvent event) {
      if (event.getKeyCode() == KeyEvent.VK_R)
         myIsPausing = false;
   }

   /**
    *
    *
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

   public void fadeOutVeil() {
      if (myVeilOpacity > 0)
         myVeilOpacity -= 10;
   }

   public void fadeInVeil() {
      if (myVeilOpacity < 100)
         myVeilOpacity += 10;
   }

   public void setIsPausing(boolean isPausing) {
      myIsPausing = isPausing;
   }
}