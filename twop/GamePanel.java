package twop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import twop.gamestate.Credits;
import twop.gamestate.GameOver;
import twop.gamestate.GameState;
import twop.gamestate.Instructions;
import twop.gamestate.LevelSelector;
import twop.gamestate.MainMenu;
import twop.gamestate.Options;
import twop.gamestate.Pause;
import twop.gamestate.Play;
import twop.gamestate.QuickInstructions;
import twop.gamestate.TypeSelector;
import twop.util.Camera;
import twop.util.StringDraw;

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
      myCurrentGameMode = "mainmenu";
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
      addFocusListener(new FocusListener() {
         @Override
         public void focusLost(FocusEvent e) {
            myCredits.getGUIManager().resetInputs();
            myGameOver.getGUIManager().resetInputs();
            myInstructions.getGUIManager().resetInputs();
            myLevelSelector.getGUIManager().resetInputs();
            myMainMenu.getGUIManager().resetInputs();
            myOptions.getGUIManager().resetInputs();
            myQuickInstructions.getGUIManager().resetInputs();
            myTypeSelector.getGUIManager().resetInputs();
         }
         @Override
         public void focusGained(FocusEvent e) {}
      });
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

   @Override
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
      @Override
      public void actionPerformed(ActionEvent event) {
         update();
         repaint();
      }
   }

   public class FocusListener extends FocusAdapter {
      public void focusLost() {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
            }
         }
      }
      public void focusGained() {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
            }
         }
      }
   }

   public class KeyListener extends KeyAdapter {
      @Override
      public void keyPressed(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getKeyListener().keyPressed(event);
               break;
            }
         }
      }
      @Override
      public void keyReleased(KeyEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getKeyListener().keyReleased(event);
               break;
            }
         }
      }
      @Override
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
      @Override
      public void mouseClicked(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseClicked(event);
               break;
            }
         }
      }
      @Override
      public void mouseDragged(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseDragged(event);
               break;
            }
         }
      }
      @Override
      public void mouseEntered(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseEntered(event);
               break;
            }
         }
      }
      @Override
      public void mouseExited(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseExited(event);
               break;
            }
         }
      }
      @Override
      public void mouseMoved(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseMoved(event);
               break;
            }
         }
      }
      @Override
      public void mousePressed(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mousePressed(event);
               break;
            }
         }
      }
      @Override
      public void mouseReleased(MouseEvent event) {
         for (GameState gameState : myGameStates) {
            if (gameState.getGameMode().equals(myCurrentGameMode)) {
               gameState.getMouseListener().mouseReleased(event);
               break;
            }
         }
      }
      @Override
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