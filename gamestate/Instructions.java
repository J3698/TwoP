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
   private InstructRect[] myInstructRects = new InstructRect[12];

   public Instructions(Player firstPlayer, Player secondPlayer,
                                 int gameWidth, int gameHeight) {
      myFirstPlayer = firstPlayer;
      mySecondPlayer = secondPlayer;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;

      Color c = myFirstPlayer.getColor();
      myInstructRects[0] = new InstructRect(c, KeyEvent.VK_S, 150, 240, 50, 50);
      myInstructRects[1] = new InstructRect(c, KeyEvent.VK_D, 210, 240, 50, 50);
      myInstructRects[2] = new InstructRect(c, KeyEvent.VK_A, 90, 240, 50, 50);
      myInstructRects[3] = new InstructRect(c, KeyEvent.VK_W, 150, 180, 50, 50);
      myInstructRects[4] = new InstructRect(c, KeyEvent.VK_1, 30, 155, 50, 50);
      myInstructRects[5] = new InstructRect(c, KeyEvent.VK_2, 90, 155, 50, 50);

      c = mySecondPlayer.getColor();
      myInstructRects[6] = new InstructRect(c, KeyEvent.VK_DOWN, 150 + 320, 240, 50, 50);
      myInstructRects[7] = new InstructRect(c, KeyEvent.VK_RIGHT, 210 + 320, 240, 50, 50);
      myInstructRects[8] = new InstructRect(c, KeyEvent.VK_LEFT, 90 + 320, 240, 50, 50);
      myInstructRects[9] = new InstructRect(c, KeyEvent.VK_UP, 150 + 320, 180, 50, 50);
      myInstructRects[10] = new InstructRect(c, KeyEvent.VK_PERIOD, 30 + 320, 155, 50, 50);
      myInstructRects[11] = new InstructRect(c, KeyEvent.VK_SLASH, 90 + 320, 155, 50, 50);
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
      for (InstructRect rect: myInstructRects)
         rect.draw(pen);
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
      boolean readyToStart = true;
      for (InstructRect rect: myInstructRects) {
         if (rect.isActivated() == false)
            readyToStart = false;
      }
      if (readyToStart)
         System.out.println("GAME READY!!!");
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
      for (InstructRect rect: myInstructRects)
         rect.keyListen(event);
   }

   private class InstructRect {
      private Color myColor;
      private int myKeyCode;
      private int myPositionX;
      private int myPositionY;
      private int myWidth;
      private int myHeight;
      private boolean myIsActivated;
      private int myTick;
      private int myBlinkDelay;

      public InstructRect(Color color, int keyCode, int positionX,
                             int positionY, int width, int height) {
         myKeyCode = keyCode;
         myColor = color;
         myPositionX = positionX;
         myPositionY = positionY;
         myWidth = width;
         myHeight = height;
         myIsActivated = false;
         myTick = 0;
         myBlinkDelay = 60;
      }

      public void draw(Graphics pen) {
         if (myIsActivated) {
            pen.setColor(myColor);
            pen.fillRect(myPositionX, myPositionY, myWidth, myHeight);
         } else {
            if (myTick % myBlinkDelay >= myBlinkDelay / 2) {
               pen.setColor(myColor);
               pen.fillRect(myPositionX, myPositionY, myWidth, myHeight);
            }
         }
         myTick++;
      }
      public void keyListen(KeyEvent event) {
         if (event.getKeyCode() == myKeyCode) {
            myIsActivated = true;
         }
      }
      public boolean isActivated() {
         return myIsActivated;
      }
   }
}