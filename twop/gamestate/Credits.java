package twop.gamestate;

import twop.GamePanel;
import twop.gui.InstructionsButton;
import twop.util.StringDraw;
import twop.util.Vector2;

import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;

public class Credits extends GameState {
   private int myGameWidth;
   private int myGameHeight;
   private Color myColor;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Credits(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "credits");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      Random r = new Random();
      myColor = new Color(150 + r.nextInt(50), 150 + r.nextInt(50), 150 + r.nextInt(50));
      getGUIManager().addButton(new InstructionsButton(new MainMenuListener(), "Main menu", new Vector2(20, 420), myGameWidth, myGameHeight));
   }

   public void draw(Graphics pen) {
      pen.setColor(new Color(100, 100, 100));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(myColor);
      int halfWidth = myGameWidth / 2;
      int quarterHeight = myGameHeight / 4;
      pen.setFont(new Font("Ariel", Font.BOLD, 25));
      StringDraw.drawStringCenter(pen, "Sound: www.freesfx.co.uk / freesound.org", halfWidth, quarterHeight);
      StringDraw.drawStringCenter(pen, "Support: All of TwoP's players!", halfWidth, 2 * quarterHeight);
      StringDraw.drawStringCenter(pen, "Other stuff: Antioch Sanders", halfWidth, 3 * quarterHeight);
      getGUIManager().draw(pen);
   }

   public void update() {
   }

   public class MainMenuListener implements ActionListener {
	   public void actionPerformed(ActionEvent event) {
		   getGamePanel().setGameMode("mainmenu");
		   getGUIManager().resetInputs();
	   }
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
   }

   private class MouseListener extends MouseAdapter {
	   public void mousePressed(MouseEvent event) {
	      getGUIManager().mousePressed(event);
	   }
	   public void mouseMoved(MouseEvent event) {
	      getGUIManager().mouseMoved(event);
	   }
   }
}




