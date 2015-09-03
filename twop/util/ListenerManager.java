package twop.util;

import twop.GamePanel;

public class ListenerManager {
   private GamePanel myGamePanel;

   public ListenerManager(GamePanel gamePanel) {
      myGamePanel = gamePanel;
   }

   public GamePanel getGamePanel() {
      return myGamePanel;
   }
}
