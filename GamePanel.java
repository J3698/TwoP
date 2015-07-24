import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;

public class GamePanel extends JPanel {
   BufferedImage myImage;
   Graphics myBuffer;
   Timer timer;
   Game myGame;
   int myGameWidth;
   int myGameHeight;

   public GamePanel(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      setFocusable(true);
      preparePanelImage();
      myGame = new Game("play", myGameWidth, myGameHeight);
      addThreadInputs();
   }
   public void preparePanelImage() {
      myImage = new BufferedImage(myGameWidth,myGameHeight,
                              BufferedImage.TYPE_INT_ARGB);
      myBuffer = myImage.getGraphics();
   }
   public void addThreadInputs() {
      addKeyListener(new KeyListener());
      timer = new Timer(20, new UpdateListener());
      timer.start();
   }
   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      myGame.draw(myBuffer);
      pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }
   public class UpdateListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         myGame.update();
         repaint();
      }
   }
   public class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         myGame.keyDownListen(event);
      }
      public void keyReleased(KeyEvent event) {
         myGame.keyUpListen(event);
      }
   }
}