package twop;

/**
 *TwoP.java
 * @version 1.2
 * @author Antioch John Sanders
 */

import javax.swing.JFrame;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;
   public static int drawWidth = 770;
   public static int drawHeight = 580;

   /* Here is the problem. When you set a width or height,
      Swing/awt doesn't actually listen to you. So you can
      make things resizable, but when you scale a mouse
      event, make sure to scale it with getWidth(), and
      getHeight(). You can't hard-code in the numbers.
      This would be easier if you eliminate Game, and have
      GamePanel cater directly to the needs of the game
      states.
   */
   public static void main(String[] args) {
      JFrame frame = new JFrame("TwoP");
      frame.setSize(770, 580);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel(gameWidth, gameHeight));
      frame.setVisible(true);
   }
}