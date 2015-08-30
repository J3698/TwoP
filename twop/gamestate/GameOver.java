package twop.gamestate;

import twop.GamePanel;
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
   private GamePanel myGamePanel;
   private int myGameWidth;
   private int myGameHeight;

   private GUIManager myGUIManager;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   private int myVeilOpacity = 0;


   public GameOver(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super("gameOver");
      myGamePanel = gamePanel;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myGUIManager = new GUIManager(myGamePanel);
      int halfWidth = myGameWidth / 2;
      myGUIManager.addButton(new GameOverButton(new MainMenuListener(), "MAIN MENU", new Vector2(halfWidth - 160, 200), myGameWidth, myGameHeight));
      myGUIManager.addButton(new GameOverButton(new RematchListener(), "REMATCH", new Vector2(halfWidth + 50, 200), myGameWidth, myGameHeight));
   }

   public void draw(Graphics pen) {
      myGamePanel.getPlay().draw(pen);

      pen.setColor(new Color(0, 0, 0, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);

      pen.setFont(new Font("Sans", 0, 22));
      pen.setColor(myGamePanel.getPlay().getFirstPlayer().getColor());

      String firstWinState, secondWinState;
      if (myGamePanel.getPlay().getWinner() == 1) {
    	  firstWinState = "WINS";
    	  secondWinState = "LOSES";
      } else {
    	  firstWinState = "LOSES";
    	  secondWinState = "WINS";
      }

      StringDraw.drawStringCenter(pen, "P L A Y E R  O N E  " + firstWinState , myGameWidth / 2 - 155, myGameHeight / 2 - 60);
      pen.setColor(myGamePanel.getPlay().getSecondPlayer().getColor());
      StringDraw.drawStringCenter(pen, "P L A Y E R  T W O  " + secondWinState, myGameWidth / 2 + 155, myGameHeight / 2 - 60);

      myGUIManager.draw(pen);
   }

   public void update() {
      myGamePanel.getPlay().update();

      if (myVeilOpacity <= 150) {
         myVeilOpacity += 5;
      }
   }

   public void reset() {
      myVeilOpacity = 0;
   }

   public class MainMenuListener implements ActionListener {
	   public void actionPerformed(ActionEvent event) {
		   myGamePanel.getPlay().reset();
		   myGamePanel.setGameMode("mainmenu");
         myGUIManager.resetInputs();
	   }
   }

   public class RematchListener implements ActionListener {
	   public void actionPerformed(ActionEvent event) {
		   myGamePanel.getPlay().reset();
		   myGamePanel.setGameMode("play");
         myGUIManager.resetInputs();
	   }
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         if (myVeilOpacity < 200) {
            myGamePanel.getPlay().getFirstPlayer().getControls().keyDown(event);
            myGamePanel.getPlay().getSecondPlayer().getControls().keyDown(event);
         }
      }
      public void keyReleased(KeyEvent event) {
         if (myVeilOpacity < 200) {
            myGamePanel.getPlay().getFirstPlayer().getControls().keyUp(event);
            myGamePanel.getPlay().getSecondPlayer().getControls().keyUp(event);
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