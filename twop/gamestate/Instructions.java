package twop.gamestate;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import twop.GamePanel;
import twop.gui.InstructionsButton;
import twop.util.StringDraw;
import twop.util.Vector2;

public class Instructions extends GameState {
   private int myGameWidth;
   private int myGameHeight;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;
   private int myCurrentPage;

   public Instructions(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super(gamePanel, "instructions");
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myCurrentPage = 1;
      getGUIManager().addButton(new InstructionsButton(new MainMenuListener(), "MainMenu", new Vector2(20, 420), myGameWidth, myGameHeight));
      getGUIManager().addButton(new InstructionsButton(new BackListener(), "Back", new Vector2(250, 420), myGameWidth, myGameHeight));
      getGUIManager().addButton(new InstructionsButton(new NextListener(), "Next", new Vector2(480, 420), myGameWidth, myGameHeight));
   }

   @Override
   public void update() {}

   @Override
   public void draw(Graphics pen) {
      pen.setColor(bg);
      pen.fillRect(0, 0, myGameWidth, myGameHeight);

      switch (myCurrentPage){
      case 1: firstPage(pen);
      break;
      case 2: secondPage(pen);
      break;
      case 3: thirdPage(pen);
      break;
      }

      getGUIManager().draw(pen);
   }

   public void firstPage(Graphics pen) {
      pen.setColor(Color.white);
      StringDraw.drawStringCenter(pen, "Welcome to TwoP, the simple two player bash 'em up game!", myGameWidth / 2, myGameHeight / 2);
   }

   public void secondPage(Graphics pen) {
      pen.setColor(Color.white);
      StringDraw.drawStringCenter(pen, "Read the second page here!", myGameWidth / 2, myGameHeight / 2);
   }

   public void thirdPage(Graphics pen) {
      pen.setColor(Color.white);
      StringDraw.drawStringCenter(pen, "Press next to return to the main menu!", myGameWidth / 2, myGameHeight / 2);
   }

   public class BackListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         if (myCurrentPage == 1) {
            getGamePanel().setGameMode("mainmenu");
            getGUIManager().resetInputs();
            myCurrentPage = 1;
         } else
            myCurrentPage--;
      }
   }

   public class NextListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         if (myCurrentPage == 3) {
            getGamePanel().setGameMode("mainmenu");
            getGUIManager().resetInputs();
            myCurrentPage = 1;
         } else
            myCurrentPage++;
      }
   }

   public class MainMenuListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent event) {
         getGamePanel().setGameMode("mainmenu");
         getGUIManager().resetInputs();
         myCurrentPage = 1;
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