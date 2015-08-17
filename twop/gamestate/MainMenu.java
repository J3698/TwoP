package twop.gamestate;

import twop.Game;
import twop.util.Vector2;
import twop.gui.MenuButton;
import twop.gui.GUIManager;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends GameState {
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;
   private GUIManager myGUIManager;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public MainMenu(Game game, int gameWidth, int gameHeight) {
      super("mainmenu");
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myGUIManager = new GUIManager();
      myGUIManager.addButton(new MenuButton(new PlayListener(), "PLAY", new Vector2(200, 20), new Vector2(240, 100), myGameWidth, myGameHeight));
      myGUIManager.addButton(new MenuButton(new InstructionsListener(), "HOW-TO", new Vector2(200, 130), new Vector2(240, 100), myGameWidth, myGameHeight));
      myGUIManager.addButton(new MenuButton(new OptionsListener(), "OPTIONS", new Vector2(200, 240), new Vector2(240, 100), myGameWidth, myGameHeight));
      myGUIManager.addButton(new MenuButton(new CreditsListener(), "CREDITS", new Vector2(200, 350), new Vector2(240, 100), myGameWidth, myGameHeight));
   }

   public void draw(Graphics pen) {
	  pen.setColor(new Color(100, 100, 100));
	  pen.fillRect(0, 0, myGameWidth, myGameHeight);
      myGUIManager.draw(pen);
   }

   public void update() {
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
   }

   private class MouseListener extends MouseAdapter {
	  public void mousePressed(MouseEvent event) {
         myGUIManager.mousePressed(event);
	  }
   }
   
   private class PlayListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         myGame.setGameMode("quickinstructions");
      }
   }

   private class InstructionsListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         myGame.setGameMode("instructions");
      }
   }

   private class OptionsListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         myGame.setGameMode("options");
      }
   }

   private class CreditsListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         myGame.setGameMode("credits");
      }
   }
}



