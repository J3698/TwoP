import javax.swing.JFrame;

public class TwoP {
   public static int gameWidth = 640;
   public static int gameHeight = 480;

   public static void main(String[] args) {
      JFrame frame = new JFrame("TwoP");
      frame.setSize(gameWidth, gameHeight);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new GamePanel(gameWidth, gameHeight));
      frame.setVisible(true);
   }
}