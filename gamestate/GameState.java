import java.awt.Graphics;
import java.awt.event.KeyEvent;


/**
 * Interface that different game states should
 * implement.
 *
 */
public interface GameState {
   void checkDrawTrigger(String myCurrentGameMode, Graphics pen);
   void draw(Graphics pen);
   void checkUpdateTrigger(String myCurrentGameMode);
   void update();
   void checkKeyListenTrigger(String currentGameMode, KeyEvent event);
   void keyListen(KeyEvent event);
}