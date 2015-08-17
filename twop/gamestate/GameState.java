package twop.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Interface that different game states should
 * implement.
 *
 */
public abstract class GameState {
   public String myGameMode;

   public GameState(String gameMode) {
      myGameMode = gameMode;
   }

   public abstract void draw(Graphics pen);
   public abstract void update();
   public abstract void keyListen(KeyEvent event);
   public abstract void mouseListen(MouseEvent event);

   public void checkDrawTrigger(String currentGameMode, Graphics pen) {
      if (myGameMode == currentGameMode)
         draw(pen);
   }

   public void checkUpdateTrigger(String currentGameMode) {
      if (myGameMode == currentGameMode)
         update();
   }

   public void checkKeyListenTrigger(String currentGameMode, KeyEvent event) {
      if (myGameMode == currentGameMode)
         keyListen(event);
   }

   public void checkMouseListenTrigger(String currentGameMode, MouseEvent event) {
      if (myGameMode == currentGameMode)
         mouseListen(event);
   }
}