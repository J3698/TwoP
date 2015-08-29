package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import twop.GamePanel;
import twop.gui.GUIManager;

public class LevelSelector extends GameState {
   private GamePanel myGamePanel;

   private int myGameWidth;
   private int myGameHeight;

   private KeyListener myKeyListener;
   private MouseListener myMouseListener;

   private GUIManager myGUIManager;

   public LevelSelector(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super("levelselector");
      myGamePanel = gamePanel;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myKeyListener = new KeyListener();
      myMouseListener = new MouseListener();
      myGUIManager = new GUIManager(myGamePanel);
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(Color.red);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
   }

   @Override
   public void update() {
      // TODO Auto-generated method stub

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
   }

   private class MouseListener extends MouseAdapter {
      public void mousePressed(MouseEvent event) {
         myGUIManager.mousePressed(event);
      }
      public void mouseMoved(MouseEvent event) {
         myGUIManager.mouseMoved(event);
      }
   }
}
