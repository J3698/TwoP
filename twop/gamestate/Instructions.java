package twop.gamestate;

import twop.Game;
import twop.util.StringDraw;
import twop.gui.GUIManager;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Instructions extends GameState {
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;
   private GUIManager myGUIManager;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Instructions(Game game, int gameWidth, int gameHeight) {
      super("instructions");
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myGUIManager = new GUIManager();
   }

   public void mouseListen(MouseEvent event) {}
   public void keyListen(KeyEvent event) {}
   public void update() {}
   public void draw(Graphics pen) {
      pen.setColor(new Color(100, 100, 100));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      firstPage(pen);
   }

   public void firstPage(Graphics pen) {
      pen.setColor(new Color(0, 30, 100));
      StringDraw.drawStringCenter(pen, "Welcome to TwoP, the simple two player bash 'em up game!", myGameWidth / 2, myGameHeight / 2);
   }

   public void secondPage(Graphics pen) {}

   public void thirdPage(Graphics pen) {}

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
	   
   }

   private class MouseListener extends MouseAdapter {
	   
   }
}