package twop;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import javax.swing.Timer;

public class GamePanel extends JPanel {
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer timer;
   private Game myGame;
   private int myGameWidth;
   private int myGameHeight;

   /**
    *
    *
    *
    */
   public GamePanel(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      setFocusable(true);
      preparePanelImage();
      myGame = new Game("mainmenu", myGameWidth, myGameHeight);
      addThreadInputs();
   }

   /**
    *
    *
    *
    */
   public void preparePanelImage() {
      myImage = new BufferedImage(myGameWidth,myGameHeight,
                              BufferedImage.TYPE_INT_ARGB);
      myBuffer = myImage.getGraphics();
   }

   /**
    *
    *
    *
    */
   public void addThreadInputs() {
      addKeyListener(new KeyListener());
      addMouseListener(new MouseListener());
      timer = new Timer(20, new UpdateListener());
      timer.start();
   }

   /**
    *
    *
    *
    */
   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      myGame.draw(myBuffer);
      pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   /**
    *
    *
    *
    */
   public class UpdateListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         myGame.update();
         repaint();
      }
   }

   /**
    *
    *
    *
    */
   public class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         myGame.getKeyListener().keyPressed(event);
      }
      public void keyReleased(KeyEvent event) {
         myGame.getKeyListener().keyReleased(event);
      }
      public void keyTyped(KeyEvent event) {
    	  myGame.getKeyListener().keyTyped(event);
      }
   }

   public class MouseListener extends MouseAdapter {
      public void mouseClicked(MouseEvent event) {
    	  myGame.getMouseListener().mouseClicked(event);
      }
      public void mouseDragged(MouseEvent event) {
    	  myGame.getMouseListener().mouseDragged(event);
      }
      public void mouseEntered(MouseEvent event) {
    	  myGame.getMouseListener().mouseEntered(event);
      }
      public void mouseExited(MouseEvent event) {
    	  myGame.getMouseListener().mouseExited(event);
      }
      public void mouseMoved(MouseEvent event) {
    	  myGame.getMouseListener().mouseMoved(event);
      }
      public void mousePressed(MouseEvent event) {
    	  myGame.getMouseListener().mousePressed(event);
      }
      public void mouseReleased(MouseEvent event) {
    	  myGame.getMouseListener().mouseReleased(event);
      }
      public void mouseWheelMoved(MouseWheelEvent event) {
    	  myGame.getMouseListener().mouseWheelMoved(event);
      }
   }
}







