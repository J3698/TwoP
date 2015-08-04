import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * Instructions game state for showing controls
 * to players.
 *
 */
public class Instructions implements GameState {
   private String myGameMode = "instructions";
   private Player myFirstPlayer;
   private Player mySecondPlayer;
   private int myGameWidth;
   private int myGameHeight;

   public Instructions(Player firstPlayer, Player secondPlayer, 
                                 int gameWidth, int gameHeight) {
      myFirstPlayer = firstPlayer;
      mySecondPlayer = secondPlayer;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
   }

   /**
    * Draw instructions screen if current gamemode
    * is instructions.
    *
    */
   public void checkDrawTrigger(String currentGameMode, Graphics pen) {
      if (myGameMode == currentGameMode)
         draw(pen);
   }

   /**
    * Draw the instructions screen.
    *
    */
   public void draw(Graphics pen) {
      pen.setColor(Color.white);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(myFirstPlayer.getColor());
      pen.fillRect(120, 210, 70, 70);
      pen.fillRect(40, 210, 70, 70);
      pen.fillRect(120, 130, 70, 70);
      pen.fillRect(200, 210, 70, 70);

      pen.setColor(mySecondPlayer.getColor());
      pen.fillRect(440, 210, 70, 70);
      pen.fillRect(360, 210, 70, 70);
      pen.fillRect(440, 130, 70, 70);
      pen.fillRect(520, 210, 70, 70);
   }

   /**
    * Update the instructions if current game state
    * is instructions.
    *
    */
   public void checkUpdateTrigger(String currentGameMode) {
      if (myGameMode == currentGameMode)
         update();
   }

   /**
    * Update the instructions screen
    *
    */
   public void update() {
   }

   /**
    * Listen for key events if current game state
    * is instructions.
    *
    */
   public void checkKeyListenTrigger(String currentGameMode, KeyEvent event) {
      if (myGameMode == currentGameMode)
         keyListen(event);
   }

   /**
    * Listen for instruction key events.
    *
    */
   public void keyListen(KeyEvent event) {
   }
}