import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;
//   public static double gravity = 0.5;
//   public static String gameMode = "play";
//   public static double pauseOpacity = 0;
   
   public static void main(String[] args) {
      JFrame frame = new JFrame("TwoP");
      frame.setSize(gameWidth, gameHeight);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel(gameWidth, gameHeight));
      frame.setVisible(true);
   }
}