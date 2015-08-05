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
      pen.fillRect(150, 240, 50, 50);
      pen.fillRect(210, 240, 50, 50);
      pen.fillRect(90, 240, 50, 50);
      pen.fillRect(150, 180, 50, 50);
      pen.fillRect(30, 155, 50, 50);
      pen.fillRect(90, 155, 50, 50);
      pen.setColor(mySecondPlayer.getColor());
      pen.fillRect(150 + 320, 240, 50, 50);
      pen.fillRect(210 + 320, 240, 50, 50);
      pen.fillRect(90 + 320, 240, 50, 50);
      pen.fillRect(150 + 320, 180, 50, 50);
      pen.fillRect(30 + 320, 155, 50, 50);
      pen.fillRect(90 + 320, 155, 50, 50);
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