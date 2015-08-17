package twop.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public abstract class GameState {
   private String myGameMode;

   public GameState(String gameMode) {
      myGameMode = gameMode;
   }

   public abstract void draw(Graphics pen);
   public abstract void update();

   public void checkDrawTrigger(String currentGameMode, Graphics pen) {
      if (myGameMode == currentGameMode)
         draw(pen);
   }

   public void checkUpdateTrigger(String currentGameMode) {
      if (myGameMode == currentGameMode)
         update();
   }

   public String getGameMode() {
	   return myGameMode;
   }

   public abstract KeyAdapter getKeyListener();
   public abstract MouseAdapter getMouseListener();
}