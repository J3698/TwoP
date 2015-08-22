package twop;

import twop.gamestate.*;
import twop.util.StringDraw;
import twop.util.Vector2;
import twop.Player;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.Timer;

public class GamePanel extends JPanel {
   private static final long serialVersionUID = 1069592807236812370L;
   private BufferedImage myImage;
   private Graphics myBuffer;
   private Timer timer;
   private MainMenu myMainMenu;
   private Options myOptions;
   private Credits myCredits;
   private Instructions myInstructions;
   private QuickInstructions myQuickInstructions;
   private Play myPlay;
   private Pause myPause;
   private GameOver myGameOver;
   private String myCurrentGameMode;
   private ArrayList<GameState> myGameStates;

   private JFrame myFrame;
   private int myGameWidth;
   private int myGameHeight;

   public GamePanel(JFrame frame, int gameWidth, int gameHeight) {
      myFrame = frame;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myCurrentGameMode = "mainmenu";
      myGameStates = new ArrayList<GameState>();
      setFocusable(true);
      preparePanelImage();
      addThreadInputs();
      initGameModes();
   }

   public void preparePanelImage() {
      myImage = new BufferedImage(myGameWidth, myGameHeight, BufferedImage.TYPE_INT_ARGB);
      myBuffer = myImage.getGraphics();
   }

   public void addThreadInputs() {
      addKeyListener(new KeyListener());
      addMouseListener(new MouseListener());
      addMouseMotionListener(new MouseListener());
      timer = new Timer(20, new UpdateListener());
      timer.start();
   }

   public void initGameModes() {
      myMainMenu = new MainMenu(this, myGameWidth, myGameHeight);
      myPlay = new Play(this, myGameWidth, myGameHeight);
      myInstructions = new Instructions(this, myGameWidth, myGameHeight);
      myQuickInstructions = new QuickInstructions(this, myGameWidth, myGameHeight);
      myPause = new Pause(this, myGameWidth, myGameHeight);
      myGameOver = new GameOver(this, myGameWidth, myGameHeight);
      myCredits = new Credits(this, myGameWidth, myGameHeight);
      myOptions = new Options(this, myGameWidth, myGameHeight);

      myGameStates.add(myMainMenu);
      myGameStates.add(myInstructions);
      myGameStates.add(myQuickInstructions);
      myGameStates.add(myPlay);
      myGameStates.add(myPause);
      myGameStates.add(myGameOver);
      myGameStates.add(myCredits);
      myGameStates.add(myOptions);
   }

   public void paintComponent(Graphics pen) {
      pen.clearRect(0, 0, getWidth(), getHeight());
      myBuffer.clearRect(0, 0, myGameWidth, myGameHeight);
      draw(myBuffer);
      pen.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
   }

   public void draw(Graphics pen) {
      Graphics2D pen2D = (Graphics2D) pen;
      pen2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      pen2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      pen2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      pen2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

      for (GameState gameState : myGameStates) {
         gameState.checkDrawTrigger(myCurrentGameMode, pen);
      }

      pen.setFont(StringDraw.versionFont());
      pen.setColor(new Color(123, 45, 249));
      pen.drawString("V. 1.2", 5, 20);
   }

   public void update() {
      for (GameState gameState : myGameStates)
         gameState.checkUpdateTrigger(myCurrentGameMode);
   }

   public class UpdateListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         update();
         repaint();
      }
   }

   public class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getKeyListener().keyPressed(event);
         }
      }
      public void keyReleased(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getKeyListener().keyReleased(event);
         }
      }
      public void keyTyped(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getKeyListener().keyTyped(event);
         }
      }
   }

   public class MouseListener extends MouseAdapter {
      public void mouseClicked(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseClicked(event);
         }
      }
      public void mouseDragged(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseDragged(event);
         }
      }
      public void mouseEntered(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseEntered(event);
         }
      }
      public void mouseExited(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseExited(event);
         }
      }
      public void mouseMoved(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseMoved(event);
         }
      }
      public void mousePressed(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mousePressed(event);
         }
      }
      public void mouseReleased(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseReleased(event);
         }
      }
      public void mouseWheelMoved(MouseWheelEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode))
               gameState.getMouseListener().mouseWheelMoved(event);
         }
      }
   }

   public int getGameWidth() { return myGameWidth; }
   public int getGameHeight() { return myGameHeight; }

   public void setGameMode(String gameMode) { myCurrentGameMode = gameMode; }

   public MainMenu getMainMenu() { return myMainMenu; }
   public Play getPlay() { return myPlay; }
   public Pause getPause() { return myPause; }
   public Instructions getInstructions() { return myInstructions; }
   public QuickInstructions getQuickInstructions() { return myQuickInstructions; }
   public GameOver getGameOver() { return myGameOver; }
   public Credits getCredits() { return myCredits; }
   public Options getOptions() { return myOptions; }
}