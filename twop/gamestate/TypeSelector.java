package twop.gamestate;

import twop.GamePanel;
import twop.gui.GUIManager;
import twop.gui.MenuButton;
import twop.util.Vector2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TypeSelector extends GameState {
   private GamePanel myGamePanel;

   private int myGameWidth;
   private int myGameHeight;

   private KeyListener myKeyListener;
   private MouseListener myMouseListener;

   private GUIManager myGUIManager;

   public TypeSelector(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super("typeselector");
      myGamePanel = gamePanel;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myKeyListener = new KeyListener();
      myMouseListener = new MouseListener();
      myGUIManager = new GUIManager(myGamePanel);
      myGUIManager.addButton(new MenuButton(new CampaignListener(), "Campaign", new Vector2(200, 100), new Vector2(240, 100), myGameWidth, myGameHeight));
      myGUIManager.addButton(new MenuButton(new TwoPlayerListener(), "Two Players", new Vector2(200, 210), new Vector2(240, 100), myGameWidth, myGameHeight));
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(new Color(100, 100, 100));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      myGUIManager.draw(pen);
      pen.setColor(Color.white);
      pen.setFont(new Font("sans", Font.ITALIC, 20));
      pen.drawString("Coming soon!", 440, 165);
   }

   @Override
   public void update() {
   }

   private class CampaignListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         myGamePanel.setGameMode("levelselector");
      }
   }
   public class TwoPlayerListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         myGamePanel.setGameMode("quickinstructions");
         myGUIManager.resetInputs();
      }
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

   @Override
   public KeyAdapter getKeyListener() {
      return myKeyListener;
   }

   @Override
   public MouseAdapter getMouseListener() {
      return myMouseListener;
   }
}
