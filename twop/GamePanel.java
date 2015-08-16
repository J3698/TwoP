package twop;

import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Color;

/**
*
*
*
*/
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
      myGame = new Game("mainMenu", myGameWidth, myGameHeight);
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

      /**
       *
       *
       *
       */
      public void keyPressed(KeyEvent event) {
         myGame.keyDownListen(event);
      }

      /**
       *
       *
       *
       */
      public void keyReleased(KeyEvent event) {
         myGame.keyUpListen(event);
      }
   }

   public class MouseListener extends MouseAdapter {
      public void mouseClicked(MouseEvent event) {
         if (event.getButton() == MouseEvent.BUTTON1) {
            System.out.println("(" + event.getX() + ", " + event.getY() + ")");
         }
      }
   }
}