package twop.gamestate;

import twop.GamePanel;
import twop.util.StringDraw;
import twop.util.Vector2;
import twop.gui.GUIManager;
import twop.gui.InstructionsButton;


import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Instructions extends GameState {
   private GamePanel myGamePanel;
   private int myGameWidth;
   private int myGameHeight;
   private GUIManager myGUIManager;
   private KeyAdapter myKeyListener;
   private MouseAdapter myMouseListener;
   private int myCurrentPage;

   public Instructions(GamePanel gamePanel, int gameWidth, int gameHeight) {
      super("instructions");
      myGamePanel = gamePanel;
      myGameWidth = gameWidth;
      myGameHeight = gameHeight;
      myGUIManager = new GUIManager(myGamePanel);
      myMouseListener = new MouseListener();
      myKeyListener = new KeyListener();
      myCurrentPage = 1;
      myGUIManager.addButton(new InstructionsButton(new MainMenuListener(), "MainMenu", new Vector2(20, 420), myGameWidth, myGameHeight));
      myGUIManager.addButton(new InstructionsButton(new BackListener(), "Back", new Vector2(250, 420), myGameWidth, myGameHeight));
      myGUIManager.addButton(new InstructionsButton(new NextListener(), "Next", new Vector2(480, 420), myGameWidth, myGameHeight));
   }

   public void update() {}

   public void draw(Graphics pen) {
      pen.setColor(new Color(100, 100, 100));
      pen.fillRect(0, 0, myGameWidth, myGameHeight);

      switch (myCurrentPage){
         case 1: firstPage(pen);
            break;
         case 2: secondPage(pen);
            break;
         case 3: thirdPage(pen);
            break;
      }

      myGUIManager.draw(pen);
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
      public void actionPerformed(ActionEvent event) {
         if (myCurrentPage == 1) {
            myGamePanel.setGameMode("mainmenu");
            myGUIManager.resetInputs();
            myCurrentPage = 1;
         } else
            myCurrentPage--;
      }
   }

   public class NextListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         if (myCurrentPage == 3) {
            myGamePanel.setGameMode("mainmenu");
            myGUIManager.resetInputs();
            myCurrentPage = 1;
         } else
            myCurrentPage++;
      }
   }

   public class MainMenuListener implements ActionListener {
      public void actionPerformed(ActionEvent event) {
         myGamePanel.setGameMode("mainmenu");
         myGUIManager.resetInputs();
         myCurrentPage = 1;
      }
   }

   public KeyAdapter getKeyListener() { return myKeyListener; }
   public MouseAdapter getMouseListener() { return myMouseListener; }

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