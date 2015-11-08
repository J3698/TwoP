package twop;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
   private static final String myVersion = Launcher.getCurrentVersion();

   private Camera myCamera;

   private ListenerManager myListenerManager;

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
      myListenerManager = new ListenerManager(this);
      myListenerManager.addListeners();
      setFocusable(true);
      initGameModes();
      timer = new Timer(20, new UpdateListener());
      timer.start();
   }

   public void initGameModes() {
      myMainMenu = new MainMenu(this, myGameWidth, myGameHeight);
      myGameStates.add(myMainMenu);
      myTypeSelector = new TypeSelector(this, myGameWidth, myGameHeight);
      myGameStates.add(myTypeSelector);
      myLevelSelector = new LevelSelector(this, myGameWidth, myGameHeight);
      myGameStates.add(myLevelSelector);
      myPlay = new Play(this, myGameWidth, myGameHeight);
      myGameStates.add(myPlay);
      myInstructions = new Instructions(this, myGameWidth, myGameHeight);
      myGameStates.add(myInstructions);
      myQuickInstructions = new QuickInstructions(this, myGameWidth, myGameHeight);
      myGameStates.add(myQuickInstructions);
      myPause = new Pause(this, myGameWidth, myGameHeight);
      myGameStates.add(myPause);
      myGameOver = new GameOver(this, myGameWidth, myGameHeight);
      myGameStates.add(myGameOver);
      myCredits = new Credits(this, myGameWidth, myGameHeight);
      myGameStates.add(myCredits);
      myOptions = new Options(this, myGameWidth, myGameHeight);
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
      pen.setColor(new Color(200, 255, 200, 150));
      pen.drawString("v" + myVersion, 5, 20);
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

   public int getGameWidth() { return myGameWidth; }
   public int getGameHeight() { return myGameHeight; }
   public void setGameWidth(int gameWidth) { myGameWidth = gameWidth; }
   public void setGameHeight(int gameHeight) { myGameHeight = gameHeight; }

   public void setGameMode(String gameMode) { myCurrentGameMode = gameMode; }
   public String getGameMode() { return myCurrentGameMode; }

   public Camera getCamera() { return myCamera; }

   public ArrayList<GameState> getGameStates() { return myGameStates; }

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
}