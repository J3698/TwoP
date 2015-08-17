package twop.gamestate;

import twop.Game;
import twop.util.StringDraw;

import java.util.Random;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Credits extends GameState {
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;
   private Color myColor;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Credits(Game game, int gameWidth, int gameHeight) {
      super("credits");
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      Random r = new Random();
      myColor = new Color(150 + r.nextInt(50), 150 + r.nextInt(50), 150 + r.nextInt(50));
   }

   public void draw(Graphics pen) {
      pen.setColor(new Color(100, 100, 100));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(myColor);
      int halfWidth = myGameWidth / 2;
      int quarterHeight = myGameHeight / 4;
      pen.setFont(new Font("Ariel", Font.BOLD, 25));
      StringDraw.drawStringCenter(pen, "Sound: www.freesfx.co.uk", halfWidth, quarterHeight);
      StringDraw.drawStringCenter(pen, "Support: All of TwoP's players!", halfWidth, 2 * quarterHeight);
      StringDraw.drawStringCenter(pen, "Other stuff: Antioch Sanders", halfWidth, 3 * quarterHeight);
   }

   public void update() {
   }

   public void mouseListen(MouseEvent event) {}
   public void keyListen(KeyEvent event) {}

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
	   
   }

   private class MouseListener extends MouseAdapter {
	   
   }
}




