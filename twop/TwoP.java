package twop;

import java.awt.Dimension;

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

   public static void main(String[] args) {
      JFrame frame = new JFrame("TwoP");
      frame.setSize(drawWidth, drawHeight);
      frame.setLocationRelativeTo(null);
      frame.setResizable(true);
      frame.setMinimumSize(new Dimension(gameWidth, gameHeight));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel(frame, gameWidth, gameHeight));
      frame.setVisible(true);
   }
}