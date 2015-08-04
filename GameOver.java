import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

/**
 *
 *
 *
 */
public class GameOver implements GameState {
   private Play myPlay;
   private int myGameWidth;
   private int myGameHeight;
   private Font playResumeFont;
   private int myVeilOpacity = 0;
   private int myStringOpacity = 0;
   private double myTextLocation = 175;
   private String myGameMode = "gameOver";

   public GameOver(Play play, int gameWidth, int gameHeight) {
      myPlay = play;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      playResumeFont = StringDraw.playResumeFont();
   }

   /**
    *
    *
    *
    */
   public void draw(Graphics pen) {
      if (myVeilOpacity != 255)
         myPlay.draw(pen);
      pen.setColor(new Color(255, 255, 255, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(new Color(0, 0, 0, myStringOpacity));
      pen.setFont(playResumeFont);
      pen.drawString("Game Over", 30, (int) myTextLocation);
   }

   /**
    *
    *
    *
    */
   public void update() {
      if (myVeilOpacity != 255)
         myPlay.update();
      if (myVeilOpacity <= 250)
         myVeilOpacity += 5;
      else if (myVeilOpacity <= 250 || myStringOpacity <= 254) {
         myStringOpacity += 1;
         if (myTextLocation >= 150)
            myTextLocation -= 0.5;
      }
   }

   /**
    *
    *
    *
    */
   public void checkKeyListenTrigger(String currentGameMode, KeyEvent event) {
      if (myGameMode == currentGameMode)
         keyListen(event);
   }

   /**
    *
    *
    *
    */
   public void keyListen(KeyEvent event) {
      if (myVeilOpacity != 255)
         myPlay.keyListen(event);
   }

   /**
    *
    *
    *
    */
   public void checkDrawTrigger(String currentGameMode, Graphics pen) {
      if (myGameMode == currentGameMode)
         draw(pen);
   }

   /**
    *
    *
    *
    */
   public void checkUpdateTrigger(String currentGameMode) {
      if (myGameMode == currentGameMode)
         update();
   }
}