package twop.util;

import twop.Player;

import java.awt.event.KeyEvent;

public class Controls {
   private Player myPlayer;
   private int myUpKey = KeyEvent.VK_E;
   private int myDownKey = KeyEvent.VK_D;
   private int myLeftKey = KeyEvent.VK_S;
   private int myRightKey = KeyEvent.VK_F;
   private int myFirstActionKey = KeyEvent.VK_1;
   private int mySecondActionKey = KeyEvent.VK_2;
   private int myThirdActionKey = KeyEvent.VK_3;

   public Controls(Player player) {
      myPlayer = player;
   }

   public void keyDown(KeyEvent event) {
      if (event.getKeyCode() == myUpKey)
         myPlayer.up();
      else if (event.getKeyCode() == myDownKey)
         myPlayer.down();
      else if (event.getKeyCode() == myLeftKey)
         myPlayer.left();
      else if (event.getKeyCode() == myRightKey)
         myPlayer.right();
      else if (event.getKeyCode() == myFirstActionKey)
         myPlayer.firstAction();
      else if (event.getKeyCode() == mySecondActionKey)
         myPlayer.secondAction();
      else if (event.getKeyCode() == myThirdActionKey)
         myPlayer.thirdAction();
   }

   public void keyUp(KeyEvent event) {
      if (event.getKeyCode() == myUpKey)
         myPlayer.releaseUp();
      else if (event.getKeyCode() == myDownKey)
         myPlayer.releaseDown();
      else if (event.getKeyCode() == myLeftKey)
         myPlayer.releaseLeft();
      else if (event.getKeyCode() == myRightKey)
         myPlayer.releaseRight();
      else if (event.getKeyCode() == myFirstActionKey)
         myPlayer.releaseFirstAction();
      else if (event.getKeyCode() == mySecondActionKey)
         myPlayer.releaseSecondAction();
      else if (event.getKeyCode() == myThirdActionKey)
         myPlayer.releaseThirdAction();
   }

   public void setFirstControls() {
      setUpKey(KeyEvent.VK_W);
      setDownKey(KeyEvent.VK_S);
      setLeftKey(KeyEvent.VK_A);
      setRightKey(KeyEvent.VK_D);
      setFirstActionKey(KeyEvent.VK_1);
      setSecondActionKey(KeyEvent.VK_2);
      setThirdActionKey(KeyEvent.VK_3);
   }

   public void setSecondControls() {
      setUpKey(KeyEvent.VK_UP);
      setDownKey(KeyEvent.VK_DOWN);
      setLeftKey(KeyEvent.VK_LEFT);
      setRightKey(KeyEvent.VK_RIGHT);
      setFirstActionKey(KeyEvent.VK_COMMA);
      setSecondActionKey(KeyEvent.VK_PERIOD);
      setThirdActionKey(KeyEvent.VK_M);
   }

   public void setUpKey(int upKey) { myUpKey = upKey; }
   public void setDownKey(int downKey) { myDownKey = downKey; }
   public void setLeftKey(int leftKey) { myLeftKey = leftKey; }
   public void setRightKey(int rightKey) { myRightKey = rightKey; }
   public void setFirstActionKey(int firstActionKey) { myFirstActionKey = firstActionKey; }
   public void setSecondActionKey(int secondActionKey) { mySecondActionKey = secondActionKey; }
   public void setThirdActionKey(int thirdActionKey) { myThirdActionKey = thirdActionKey; }

   public int getUpKey() { return myUpKey; }
   public int getDownKey() { return myDownKey; }
   public int getLeftKey() { return myLeftKey; }
   public int getRightKey() { return myRightKey; }
   public int getFirstActionKey() { return myFirstActionKey; }
   public int getSecondActionKey() { return mySecondActionKey; }
   public int getThirdActionKey() { return myThirdActionKey; }
}
