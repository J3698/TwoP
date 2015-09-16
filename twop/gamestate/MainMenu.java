package twop.gamestate;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import twop.GamePanel;
import twop.Launcher;
import twop.gui.MenuButton;
import twop.util.StringDraw;
import twop.util.Vector2;

public class MainMenu extends GameState {
   private int myGameWidth;
   private int myGameHeight;

   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   Launcher myLauncher;

   public MainMenu(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "mainmenu");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myLauncher = new Launcher(getGamePanel());
      getGUIManager().addButton(new MenuButton(new PlayListener(), "PLAY", new Vector2(200, 20), new Vector2(240, 100), myGameWidth, myGameHeight));
      getGUIManager().addButton(new MenuButton(new InstructionsListener(), "HOW-TO", new Vector2(200, 130), new Vector2(240, 100), myGameWidth, myGameHeight));
      getGUIManager().addButton(new MenuButton(new OptionsListener(), "OPTIONS", new Vector2(200, 240), new Vector2(240, 100), myGameWidth, myGameHeight));
      getGUIManager().addButton(new MenuButton(new CreditsListener(), "CREDITS", new Vector2(200, 350), new Vector2(240, 100), myGameWidth, myGameHeight));
      MenuButton updateButton = new MenuButton(new GameUpdateListener(), "UPDATE", new Vector2(0, 440), new Vector2(95, 40), myGameWidth, myGameHeight);
      updateButton.setFont(StringDraw.instructionsButtonsFont());
      getGUIManager().addButton(updateButton);
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(bg);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      getGUIManager().draw(pen);
   }

   @Override
   public void update() {
   }

   @Override
   public KeyAdapter getKeyListener() { return myKeyListener; }
   @Override
   public MouseAdapter getMouseListener() { return myMouseListener; }

   private class KeyListener extends KeyAdapter {
   }

   private class MouseListener extends MouseAdapter {
      @Override
      public void mousePressed(MouseEvent event) {
         getGUIManager().mousePressed(event);
      }
      @Override
      public void mouseMoved(MouseEvent event) {
         getGUIManager().mouseMoved(event);
      }
   }

   private class GameUpdateListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         myLauncher.checkAndInstallUpdates();
      }
   }

   private class PlayListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         getGamePanel().setGameMode("typeselector");
         getGUIManager().resetInputs();
      }
   }

   private class InstructionsListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         getGamePanel().setGameMode("instructions");
         getGUIManager().resetInputs();
      }
   }

   private class OptionsListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         getGamePanel().setGameMode("options");
         getGUIManager().resetInputs();
      }
   }

   private class CreditsListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         getGamePanel().setGameMode("credits");
         getGUIManager().resetInputs();
      }
   }
}



