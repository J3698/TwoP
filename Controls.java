import java.awt.event.KeyEvent;

public class Controls {
   private Player myPlayer;
   private int myUpKey = KeyEvent.VK_W;
   private int myDownKey = KeyEvent.VK_S;
   private int myLeftKey = KeyEvent.VK_A;
   private int myRightKey = KeyEvent.VK_D;
   private int myFirstActionKey = KeyEvent.VK_1;
   private int mySecondActionKey = KeyEvent.VK_2;

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
      }

   public void setFirstControls() {
      setUpKey(KeyEvent.VK_W);
      setDownKey(KeyEvent.VK_S);
      setLeftKey(KeyEvent.VK_A);
      setRightKey(KeyEvent.VK_D);
      setFirstActionKey(KeyEvent.VK_1);
      setSecondActionKey(KeyEvent.VK_2);
   }

   public void setSecondControls() {
      setUpKey(KeyEvent.VK_UP);
      setDownKey(KeyEvent.VK_DOWN);
      setLeftKey(KeyEvent.VK_LEFT);
      setRightKey(KeyEvent.VK_RIGHT);
      setFirstActionKey(KeyEvent.VK_PERIOD);
      setSecondActionKey(KeyEvent.VK_SLASH);
   }

   public void setUpKey(int upKey) {
      myUpKey = upKey;
   }
   public void setDownKey(int downKey) {
      myDownKey = downKey;
   }
   public void setLeftKey(int leftKey) {
      myLeftKey = leftKey;
   }
   public void setRightKey(int rightKey) {
      myRightKey = rightKey;
   }
   public void setFirstActionKey(int firstActionKey) {
      myFirstActionKey = firstActionKey;
   }
   public void setSecondActionKey(int secondActionKey) {
      mySecondActionKey = secondActionKey;
   }
   public int getUpKey() {
      return myUpKey;
   }
   public int getDownKey() {
      return myDownKey;
   }
   public int getLeftKey() {
      return myLeftKey;
   }
   public int getRightKey() {
      return myRightKey;
   }
   public int getFirstActionKey() {
      return myFirstActionKey;
   }
   public int getSecondActionKey() {
      return mySecondActionKey;
   }
}