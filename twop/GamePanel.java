package twop;

import twop.gamestate.*;
import twop.util.Camera;
import twop.util.StringDraw;
import twop.util.Vector2;

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

   private static final float version = 1.3f;

   private Camera myCamera;

   private Timer timer;

   private int myGameWidth;
   private int myGameHeight;

   private String myCurrentGameMode;
   private ArrayList<GameState> myGameStates;
   private MainMenu myMainMenu;
   private TypeSelector myTypeSelector;
   private LevelSelector myLevelSelector;
   private Options myOptions;
   private Credits myCredits;
   private Instructions myInstructions;
   private QuickInstructions myQuickInstructions;
   private Play myPlay;
   private Pause myPause;
   private GameOver myGameOver;

   public GamePanel(int gameWidth, int gameHeight) {
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myCurrentGameMode = "levelselector";
      myGameStates = new ArrayList<GameState>();
      myCamera = new Camera(this);
      setFocusable(true);
      addThreadInputs();
      initGameModes();
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
      myTypeSelector = new TypeSelector(this, myGameWidth, myGameHeight);
      myLevelSelector = new LevelSelector(this, myGameWidth, myGameHeight);
      myPlay = new Play(this, myGameWidth, myGameHeight);
      myInstructions = new Instructions(this, myGameWidth, myGameHeight);
      myQuickInstructions = new QuickInstructions(this, myGameWidth, myGameHeight);
      myPause = new Pause(this, myGameWidth, myGameHeight);
      myGameOver = new GameOver(this, myGameWidth, myGameHeight);
      myCredits = new Credits(this, myGameWidth, myGameHeight);
      myOptions = new Options(this, myGameWidth, myGameHeight);

      myGameStates.add(myMainMenu);
      myGameStates.add(myTypeSelector);
      myGameStates.add(myLevelSelector);
      myGameStates.add(myInstructions);
      myGameStates.add(myQuickInstructions);
      myGameStates.add(myPlay);
      myGameStates.add(myPause);
      myGameStates.add(myGameOver);
      myGameStates.add(myCredits);
      myGameStates.add(myOptions);
   }

   public void paintComponent(Graphics pen) {
      //Clear painting spaces
      pen.clearRect(0, 0, getWidth(), getHeight());
      myCamera.clearImage();
      //Draw painting spaces
      draw(myCamera.getPen());
      //Draw camera view to screen
      int x0 = (int) myCamera.getScaledPos1().getX();
      int y0 = (int) myCamera.getScaledPos1().getY();
      int x1 = (int) myCamera.getScaledPos2().getX();
      int y1 = (int) myCamera.getScaledPos2().getY();
      pen.drawImage(myCamera.getImage(), x0, y0, x1, y1, null);
      //Draw version information
      pen.setFont(StringDraw.versionFont());
      pen.setColor(new Color(250, 40, 200, 150));
      pen.drawString("V. " + version, 5, 20);
   }

   public void draw(Graphics pen) {
      Graphics2D pen2D = (Graphics2D) pen;
      pen2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      pen2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      pen2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
      pen2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

      for (GameState gameState : myGameStates) {
         if (gameState.getGameMode().equals(myCurrentGameMode)) {
            gameState.draw(pen);
            break;
         }
      }
   }

   public void update() {
      myCamera.update();

      for (GameState gameState : myGameStates) {
         if (gameState.getGameMode().equals(myCurrentGameMode)) {
            gameState.update();
            break;
         }
      }
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
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getKeyListener().keyPressed(event);
               break;
            }
         }
      }
      public void keyReleased(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getKeyListener().keyReleased(event);
               break;
            }
         }
      }
      public void keyTyped(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getKeyListener().keyTyped(event);
               break;
            }
         }
      }
   }

   public class MouseListener extends MouseAdapter {
      public void mouseClicked(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseClicked(event);
               break;
            }
         }
      }
      public void mouseDragged(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseDragged(event);
               break;
            }
         }
      }
      public void mouseEntered(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseEntered(event);
               break;
            }
         }
      }
      public void mouseExited(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseExited(event);
               break;
            }
         }
      }
      public void mouseMoved(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseMoved(event);
               break;
            }
         }
      }
      public void mousePressed(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mousePressed(event);
               break;
            }
         }
      }
      public void mouseReleased(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseReleased(event);
               break;
            }
         }
      }
      public void mouseWheelMoved(MouseWheelEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseWheelMoved(event);
               break;
            }
         }
      }
   }

   public int getGameWidth() { return myGameWidth; }
   public int getGameHeight() { return myGameHeight; }

   public void setGameMode(String gameMode) { myCurrentGameMode = gameMode; }

   public MainMenu getMainMenu() { return myMainMenu; }
   public TypeSelector getTypeSelector() { return myTypeSelector; }
   public LevelSelector getLevelSelector() { return myLevelSelector; }
   public Play getPlay() { return myPlay; }
   public Pause getPause() { return myPause; }
   public Instructions getInstructions() { return myInstructions; }
   public QuickInstructions getQuickInstructions() { return myQuickInstructions; }
   public GameOver getGameOver() { return myGameOver; }
   public Credits getCredits() { return myCredits; }
   public Options getOptions() { return myOptions; }

   public Camera getCamera() { return myCamera; }

   public void setGameWidth(int gameWidth) { myGameWidth = gameWidth; }
   public void setGameHeight(int gameHeight) { myGameHeight = gameHeight; }
}