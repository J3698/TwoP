package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import twop.GamePanel;
import twop.Player;
import twop.effect.FireEffect;
import twop.gui.GUIManager;
import twop.util.Vector2;

public class LevelSelector extends GameState {
   private GamePanel myGamePanel;

   private int myGameWidth;
   private int myGameHeight;

   private KeyListener myKeyListener;
   private MouseListener myMouseListener;

   private Player myPlayer;

   public LevelSelector(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super("levelselector");
      myGamePanel = gamePanel;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myKeyListener = new KeyListener();
      myMouseListener = new MouseListener();
      myPlayer = new Player(new Vector2(100, 50), 19, 0, myGameHeight - 1, myGameWidth, 0);
      myPlayer.getControls().setSecondControls();
   }

   @Override
   public void draw(Graphics pen) {
      myPlayer.draw(pen);
   }

   @Override
   public void update() {
      myPlayer.update();

      //Center Camera
      Vector2 camPos1 = myPlayer.getCenter().copy();
      Vector2 camPos2 = myPlayer.getCenter().copy();
      Vector2 halfScreen = new Vector2(myGameWidth / 2, myGameHeight / 2);
      camPos1.subtractVector(halfScreen);
      camPos2.addVector(halfScreen);
      myGamePanel.getCamera().setPos1(camPos1);
      myGamePanel.getCamera().setPos2(camPos2);
   }


   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   @Override
   public KeyAdapter getKeyListener() {
      return myKeyListener;
   }

   @Override
   public MouseAdapter getMouseListener() {
      return myMouseListener;
   }

   private class KeyListener extends KeyAdapter {
      public void keyPressed(KeyEvent event) {
         myPlayer.getControls().keyDown(event);
      }
      public void keyReleased(KeyEvent event) {
         myPlayer.getControls().keyUp(event);
      }
   }

   private class MouseListener extends MouseAdapter {
   }
}
