package twop.gamestate;

import twop.Game;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class Pause extends GameState {
   private Game myGame;
   private boolean myIsPausing = true;
   private int myGameWidth;
   private int myGameHeight;
   private int myVeilOpacity = 0;
   private int myTextOpacity = 0;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Pause(Game game, int gameWidth, int gameHeight) {
      super("pause");
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
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

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         if (event.getKeyCode() == KeyEvent.VK_R)
            myIsPausing = false;
      }
   }

   private class MouseListener extends MouseAdapter {
	   
   }
}