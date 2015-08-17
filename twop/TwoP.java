package twop;

/**
 *TwoP.java
 * @version 1.1
 * @author Antioch John Sanders
 */

import javax.swing.JFrame;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;

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