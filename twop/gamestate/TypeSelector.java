package twop.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import twop.GamePanel;
import twop.gui.MenuButton;
import twop.util.Vector2;

public class TypeSelector extends GameState {
   private int myGameWidth;
   private int myGameHeight;

   private KeyListener myKeyListener;
   private MouseListener myMouseListener;

   public TypeSelector(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "typeselector");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myKeyListener = new KeyListener();
      myMouseListener = new MouseListener();
      getGUIManager().addButton(new MenuButton(new CampaignListener(), "Campaign", new Vector2(200, 100), new Vector2(240, 100), myGameWidth, myGameHeight));
      getGUIManager().addButton(new MenuButton(new TwoPlayerListener(), "Two Players", new Vector2(200, 210), new Vector2(240, 100), myGameWidth, myGameHeight));
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(bg);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      getGUIManager().draw(pen);
      pen.setColor(Color.white);
      pen.setFont(new Font("sans", Font.ITALIC, 20));
      pen.drawString("Coming soon!", 440, 165);
   }

   @Override
   public void update() {
   }

   private class CampaignListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("levelselector");
         getGamePanel().getLevelSelector().init();
         getGUIManager().resetInputs();
      }
   }
   public class TwoPlayerListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("quickinstructions");
         getGUIManager().resetInputs();
      }
   }

   private class KeyListener extends KeyAdapter {
   }

   private class MouseListener extends MouseAdapter {
      @Override
      public void mousePressed(MouseEvent event) {
         getGUIManager().mousePressed(event);
      }
      @Override
      public void mouseMoved(MouseEvent event) {
         getGUIManager().mouseMoved(event);
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
