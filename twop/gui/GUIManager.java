package twop.gui;

import twop.GamePanel;

import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.Graphics;

public class GUIManager {
   private GamePanel myGamePanel;
   private ArrayList<Button> myButtons;
   private double scaleFactorX;
   private double scaleFactorY;

   public GUIManager(GamePanel gamePanel) {
      myGamePanel = gamePanel;
      myButtons = new ArrayList<Button>();
   }

   public void draw(Graphics pen) {
      for (Button button: myButtons) {
         if (button.getMouseHovering()) {
    		   button.drawHovered(pen);
         } else {
    	      button.draw(pen);
         }
      }
   }

   public void mousePressed(MouseEvent event) {
      if (event.getButton() == MouseEvent.BUTTON1) {
         double scaleFactorX = myGamePanel.getGameWidth() / (double) myGamePanel.getWidth();
         double scaleFactorY = myGamePanel.getGameHeight() / (double) myGamePanel.getHeight();
         for (Button button: myButtons) {
            if (button.collidesPoint(event.getX() * scaleFactorX, event.getY() * scaleFactorY)) {
               button.doAction();
            }
         }
      }
   }

   public void mouseMoved(MouseEvent event) {
      double scaleFactorX = myGamePanel.getGameWidth() / (double) myGamePanel.getWidth();
      double scaleFactorY = myGamePanel.getGameHeight() / (double) myGamePanel.getHeight();
      for (Button button: myButtons) {
         if (button.collidesPoint(event.getX() * scaleFactorX, event.getY() * scaleFactorY)) {
        	   button.setMouseHovering(true);
         } else {
        	   button.setMouseHovering(false);
         }
      }
   }

   public void addButton(Button button) {
      myButtons.add(button);
   }
}
