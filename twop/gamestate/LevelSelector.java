package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

import twop.GamePanel;
import twop.Player;
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
      myPlayer = new Player(new Vector2(400, 400), 19, new Rectangle(0, 0, 1000, 1000));
   }

   public void init() {
      myPlayer.getControls().setSecondControls();
      myGamePanel.getCamera().setImageSize(1000, 1000);
   }

   @Override
   public void draw(Graphics pen) {
      fixCamera();
      pen.setColor(Color.white);
      pen.fillRect(0, 0, 1000, 1000);

      pen.setColor(Color.CYAN);
      for (int x = 0; x < 10; x++) {
         pen.fillRect(x * 100, 0, 50, 1000);
      }
      pen.setColor(Color.BLUE);
      for (int y = 0; y < 100; y++) {
         pen.fillRect(0, y * 100, 1000, 50);
      }

      myPlayer.draw(pen);
   }

   @Override
   public void update() {
      myPlayer.update();
   }

   public void fixCamera() {
      Vector2 newPos1 = myPlayer.getCenter().copy();
      Vector2 newPos2 = myPlayer.getCenter().copy();
      Vector2 half = new Vector2(myGamePanel.getGameWidth() / 2, myGamePanel.getGameHeight() / 2);
      newPos1.subtractVector(half);
      newPos2.addVector(half);
      myGamePanel.getCamera().setPerspective(newPos1, newPos2);
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
