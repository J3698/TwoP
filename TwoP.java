/**
 *TwoP.java
 * @version 1.0.15
 * @author Antioch John Sanders
 */

import javax.swing.JFrame;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;

   /**
    * Main method to display and start game
    *
    * @param args not used
    */
   public static void main(String[] args) {
      JFrame frame = new JFrame("TwoP");
      frame.setSize((int)(1.2 * gameWidth), (int)(1.2 * gameHeight));
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel(gameWidth, gameHeight));
      frame.setVisible(true);
   }
}