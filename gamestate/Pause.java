import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class Pause implements GameState {
   private String myGameMode = "pause";
   private Play myPlay;
   private int myGameWidth;
   private int myGameHeight;
   private Font playResumeFont;
   private int myVeilOpacity = 0;
   private int myTextOpacity = 0;

   public Pause(Play play, int gameWidth, int gameHeight) {
      myPlay = play;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
   }

   public void draw(Graphics pen) {
      myPlay.draw(pen);
      pen.setColor(new Color(0, 0, 0, myVeilOpacity));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(new Color(255, 255, 255, myTextOpacity));
      pen.drawString("R to Resume", 50, 250);
   }

   /**
    *
    *
    *
    */
   public void update() {
      if (myVeilOpacity < 100)
         myVeilOpacity += 10;
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
      if (event.getKeyCode() == KeyEvent.VK_R)
         System.out.println("GAME IS PLAYED!!!");
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