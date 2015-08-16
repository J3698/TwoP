package twop.gamestate;

import twop.Game;
import twop.util.StringDraw;
import twop.gui.*;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class MainMenu implements GameState {
   private String myGameMode = "mainMenu";
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;
   private GUIManager myGUIManager;

   public MainMenu(Game game, int gameWidth, int gameHeight) {
      myGame = game;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myGUIManager = new GUIManager();
      myGUIManager.addButton(new MenuButton());
   }

   public void draw(Graphics pen) {
      pen.setColor(Color.white);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(Color.black);
      StringDraw.drawStringCenter(pen, "TwoP", myGameWidth / 2, 60);
      StringDraw.drawStringCenter(pen, "Play", myGameWidth / 2, 100);
      StringDraw.drawStringCenter(pen, "Instructions", myGameWidth / 2, 140);
      StringDraw.drawStringCenter(pen, "Credits", myGameWidth / 2, 180);
   }

   public void update() {
   }

   public void keyListen(KeyEvent event) {
   }

   public void checkUpdateTrigger(String currentGameMode) {
      if (myGameMode == currentGameMode)
         update();
   }
   public void checkDrawTrigger(String currentGameMode, Graphics pen) {
      if (myGameMode == currentGameMode)
         draw(pen);
   }
   public void checkKeyListenTrigger(String currentGameMode, KeyEvent event) {
      if (myGameMode == currentGameMode)
         keyListen(event);
   }
}