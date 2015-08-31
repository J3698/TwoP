package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.color.ColorSpace;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

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
      myGamePanel.getCamera().setImageSize(1000, 1000);
      //Not sure lines of code, do I have to change gameWidth/Height?
//      myGamePanel.setGameWidth(1000);
//      myGamePanel.setGameHeight(1000);
   }

   @Override
   public void draw(Graphics pen) {
      Vector2 center = myPlayer.getCenter().copy();
      Vector2 newPos1 = myPlayer.getCenter().copy();
      Vector2 newPos2 = myPlayer.getCenter().copy();
      Vector2 half = new Vector2(myGamePanel.getGameWidth() / 2, myGamePanel.getGameHeight() / 2);
      center.subtractVector(half);
      myGamePanel.getCamera().reset();
      //Can't assume pos1 and 2 are correct, we have to completely overwrite them, not subtract from them.
      myGamePanel.getCamera().setPos1(newPos1);
      myGamePanel.getCamera().getPos1().subtractVector(half);
      myGamePanel.getCamera().setPos2(newPos2);
      myGamePanel.getCamera().getPos2().addVector(half);

      pen.setColor(Color.red);
      pen.drawRect(0, 0, 100, 100);
      myPlayer.draw(pen);
   }

   @Override
   public void update() {
      myPlayer.update();
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
