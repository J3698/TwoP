package twop.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import twop.GamePanel;
import twop.gui.InstructionsButton;
import twop.util.StringDraw;
import twop.util.Vector2;

public class Credits extends GameState {
   private int myGameWidth;
   private int myGameHeight;
   private Color myColor;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;

   public Credits(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "credits");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      Random r = new Random();
      myColor = new Color(150 + r.nextInt(50), 150 + r.nextInt(50), 150 + r.nextInt(50));
      getGUIManager().addButton(new InstructionsButton(new MainMenuListener(), "Main menu", new Vector2(20, 420), myGameWidth, myGameHeight));
   }

   @Override
   public void draw(Graphics pen) {
      pen.setColor(bg);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);
      pen.setColor(myColor);
      int halfWidth = myGameWidth / 2;
      int quarterHeight = myGameHeight / 4;
      pen.setFont(new Font("Ariel", Font.BOLD, 25));
      StringDraw.drawStringCenter(pen, "Sound: www.freesfx.co.uk / freesound.org", halfWidth, quarterHeight);
      StringDraw.drawStringCenter(pen, "Support: All of TwoP's players!", halfWidth, 2 * quarterHeight);
      StringDraw.drawStringCenter(pen, "Other stuff: Antioch Sanders", halfWidth, 3 * quarterHeight);
      getGUIManager().draw(pen);
   }

   @Override
   public void update() {
   }

   public class MainMenuListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("mainmenu");
         getGUIManager().resetInputs();
      }
   }

   @Override
   public KeyAdapter getKeyListener() { return myKeyListener; }
   @Override
   public MouseAdapter getMouseListener() { return myMouseListener; }

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
}




