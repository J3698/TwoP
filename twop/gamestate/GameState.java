package twop.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

import twop.GamePanel;
import twop.gui.GUIManager;

public abstract class GameState {
   private GamePanel myGamePanel;
   private String myGameMode;
   private GUIManager myGUIManager;

   public GameState(GamePanel gamePanel, String gameMode) {
      myGamePanel = gamePanel;
      myGameMode = gameMode;
      myGUIManager = new GUIManager(gamePanel);
   }

   public abstract void draw(Graphics pen);
   public abstract void update();
   public abstract KeyAdapter getKeyListener();
   public abstract MouseAdapter getMouseListener();

   public String getGameMode() { return myGameMode; }
   public GamePanel getGamePanel() { return myGamePanel; }
   public GUIManager getGUIManager() { return myGUIManager; }
}